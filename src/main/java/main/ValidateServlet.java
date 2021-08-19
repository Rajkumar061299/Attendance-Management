package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		request.getSession().setAttribute("userId" , userId);
		Credentials credentials = Credentials.getInstance();
		User user = null;
		try {
			user = credentials.validate(userId, password);

			switch (user.getRole()) {
			case Admin:
				response.sendRedirect("admin.html");
				break;
			case Faculty:
				response.sendRedirect("faculty.html");
				break;
			case Student:
				response.sendRedirect("StudentServlet");
				break;
			}

		} catch (NullPointerException e) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User or password incorrect');");
			out.println("location='login.html';");
			out.println("</script>");
		}

	}

}
