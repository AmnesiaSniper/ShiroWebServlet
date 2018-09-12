package shiro.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

@WebServlet(name ="Servlet" , urlPatterns = "/login")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//全局对象通过安全管理者生成Subject对象
		Subject subject = SecurityUtils.getSubject();
		//封装用户的数据
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			//将用户的数据token 最终传递到Realm中进行对比
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);

			response.sendRedirect("");
		} catch (AuthenticationException e) {
			request.setAttribute("error", "验证失败");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
