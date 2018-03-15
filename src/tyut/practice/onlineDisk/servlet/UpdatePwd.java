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
 * Servlet implementation class UpdatePwd
 */
@WebServlet("/updatePwd")
public class UpdatePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user=(String) request.getSession().getAttribute("user");
		String pwdB = request.getParameter("passwordB");
		String pwd = request.getParameter("password");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String flag=service.UpdatePsd(user, pwdB, pwd);
		if(flag.equals("success")){
			out.flush();
			out.println("<script>");
			out.println("alert('修改成功！');");
			out.println("window.location.href='main.jsp'");
			out.println("</script>");
		} else if(flag.equals("fail")){
			out.flush();
			out.println("<script>");
			out.println("alert('修改失败！');");
			out.println("window.location.href='updatePwd.jsp'");
			out.println("</script>");
			//response.sendRedirect("updatePwd.jsp");
		}else {
			out.flush();
			out.println("<script>");
			out.println("alert('原密码错误！');");
			out.println("window.location.href='updatePwd.jsp'");
			out.println("</script>");
		}
	}

}
