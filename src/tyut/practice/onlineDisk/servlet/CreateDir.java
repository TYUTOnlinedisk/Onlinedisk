package tyut.practice.onlineDisk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tyut.practice.onlineDisk.service.DocService;

/**
 * Servlet implementation class CreateDir
 */
public class CreateDir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocService service = new DocService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDir() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dir = request.getParameter("dir");
		String owner = (String)request.getSession().getAttribute("user");
		String name = request.getParameter("name");
		
		String s = "1";
		if(!service.createDir(dir, owner, name)){
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
