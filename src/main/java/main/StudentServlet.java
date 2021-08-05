package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String userId = (String) request.getSession().getAttribute("userId");

		AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();

		List<LocalDate> dates = attendanceDatabase.getSortedDates();

		for (LocalDate date : dates) {

			String value = String.valueOf(attendanceDatabase.wasStudentPresentOn(date, userId));
			if (value.equals("true"))
				response.getWriter().println(date + " --  present");
			else {
				response.getWriter().println(date + " --  absent");
			}

		}

		double average = attendanceDatabase.findAverageStudentAttendance(userId);
		out.println("Average " + average);
		if (average <= 0.5) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('your attendance percentage is below 50 %');");
			out.println("location='/LogoutServlet';");
			out.println("</script>");

		}
	//	out.print("<a href = \"LogoutServlet\">Logout</a>");

	}

}
