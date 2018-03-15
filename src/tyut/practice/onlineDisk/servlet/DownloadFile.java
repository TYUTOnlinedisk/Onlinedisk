package tyut.practice.onlineDisk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tyut.practice.onlineDisk.model.Doc;
import tyut.practice.onlineDisk.service.DocService;

/**
 * Servlet implementation class DownloadFile
 */
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocService service = new DocService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String dir = request.getParameter("dir");
		String owner = (String)request.getSession().getAttribute("user");
		
		Doc doc = service.getDocById(Integer.parseInt(id), owner);
		
		File file = new File(doc.getRealdirectory());
		if(!file.exists()){
			response.sendRedirect("main.jsp?dir=" + dir);
			return;
		}
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(doc.getName(), "UTF-8"));
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		byte buffer[] = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))>0){
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
		
		response.sendRedirect("main.jsp?dir=" + dir);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
