package tyut.practice.onlineDisk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tyut.practice.onlineDisk.service.DocService;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocService service = new DocService();  
	private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	private String root = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String savePath = this.getClass().getClassLoader().getResource("").getPath() + "db.properties";
        InputStream in = null;
		try {
			in = new FileInputStream(new File(savePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		Properties prop = new Properties();
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.root = prop.getProperty("root");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String dir = request.getParameter("dir");
		String owner = (String)request.getSession().getAttribute("user");
		String name = null;
		String savePath = root + "/" + owner;
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if(!ServletFileUpload.isMultipartContent(request)){
			return;
		}
		File f = null;
		
		String fn = null;
		String fp = null;
		try {
			FileItem fi = upload.parseRequest(request).get(0);
			if(!fi.isFormField()){
				InputStream in = fi.getInputStream();
				name  = fi.getName().substring(fi.getName().lastIndexOf("\\")+1);
				fn = owner + df.format(new Date()) + name.substring(name.lastIndexOf('.'));
				fp = savePath + "/" + fn;
				f = new File(fp);
				if(f.exists()){
					return;
				}
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(fp);
				byte buffer[] = new byte[1024];
				int len = 0;
				while((len=in.read(buffer))>0){
					out.write(buffer, 0, len);
				}
				in.close();
				out.close();
				fi.delete();
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		String s = "1";
		if(!service.createDoc(owner, dir, name, fp, new File(fp).length())){
			s = "0";
		}
		PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
