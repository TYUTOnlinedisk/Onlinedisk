package tyut.practice.onlineDisk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tyut.practice.onlineDisk.model.Doc;
import tyut.practice.onlineDisk.service.DocService;

/**
 * Servlet implementation class GetFilesInDir
 */
public class GetFilesInDir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocService service = new DocService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFilesInDir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = (String)request.getSession().getAttribute("user");
		String dir = request.getParameter("dir");
		
		List<Doc> list = service.getDocs(dir, user);
		String json = JSONArray.fromObject(list).toString();  
		response.setContentType("text/json;charset=UTF-8");  
		try {  
            response.getWriter().write(json);  
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
