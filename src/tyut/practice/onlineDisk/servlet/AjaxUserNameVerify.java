package tyut.practice.onlineDisk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tyut.practice.onlineDisk.dao.UserDao;

/**
 * Servlet implementation class AjaxUserNameVerify
 */
@WebServlet("/AjaxUserNameVerify")
public class AjaxUserNameVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUserNameVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		UserDao dao = new UserDao();
		String username = request.getParameter("username").toString();
		if(dao.getPassWordByName(username)!=null){
			PrintWriter writer= response.getWriter();
            writer.print("用户名已存在");
            writer.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubdoGet
		doGet(request,response);
	}

}
