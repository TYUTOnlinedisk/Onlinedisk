package tyut.practice.onlineDisk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tyut.practice.onlineDisk.service.UserService;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if(service.verify(name, pwd)){
			if(name.indexOf("@")==-1){
				request.getSession().setAttribute("user", name);
				response.sendRedirect("main.jsp");
			}
			else{
				String realname=service.getNameByEmail(name);
				request.getSession().setAttribute("user", realname);
				response.sendRedirect("main.jsp");
			}
		} else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('用户名或密码错误');");
			out.println("window.location.href='login.html'");
			//out.println("history.back();");
			out.println("</script>");
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
