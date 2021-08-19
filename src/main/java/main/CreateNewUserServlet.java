package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String action = request.getParameter("submit");

		if (action.equalsIgnoreCase("Add student")) {
			String name = request.getParameter("sdtname");
			String id = request.getParameter("sdtid");
			String password = request.getParameter("sdtpass");
			String email = request.getParameter("sdtemail");

			addStudent(name, id, password, email, request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Student added Sucessfully');");
			out.println("location='admin.html';");
			out.println("</script>");
		}

		if (action.equalsIgnoreCase("Add Faculty"))

		{
			String name = request.getParameter("facname");
			String id = request.getParameter("facid");
			String password = request.getParameter("facpass");
			String email = request.getParameter("facemail");

			addFaculty(name, id, password, email, request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Faculty added Sucessfully');");
			out.println("location='admin.html';");
			out.println("</script>");
		}

	}

	public void addStudent(String name, String id, String password, String email, HttpServletRequest request,
			HttpServletResponse response) {
		Credentials credentials = Credentials.getInstance();
		UserAuth userAuth = new UserAuth(id, password);
		Student student;
		if (email != null)
			student = new Student(name, email);
		student = new Student(name);
		credentials.addUser(userAuth, student);

	}

	public void addFaculty(String name, String id, String password, String email, HttpServletRequest request,
			HttpServletResponse response) {
		Credentials credentials = Credentials.getInstance();
		UserAuth userAuth = new UserAuth(id, password);
		Faculty faculty;
		if (email != null) {
			faculty = new Faculty(name, email);
		} else {
			faculty = new Faculty(name);
		}
		credentials.addUser(userAuth, faculty);

	}

}
