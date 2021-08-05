package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userID = request.getParameter("userId");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		} catch (ParseException e) {}
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		boolean isPresent = false;
		String attendanceStatus = request.getParameter("attendance");
		if (attendanceStatus.equalsIgnoreCase("present"))
			isPresent = true;

		AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
		attendanceDatabase.updateAttendance(localDate, userID, isPresent);
		
		out.println("<script type=\"text/javascript\">");
		out.println("alert('updated successfully');");
		out.println("location='admin.html';");
		out.println("</script>");	


	}
}
