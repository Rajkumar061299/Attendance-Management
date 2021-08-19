package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacultyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		List<Attendance> singleDayAttendance = Arrays.asList(new Attendance(userID, isPresent));
		AttendanceDatabase attendanceDatabase = AttendanceDatabase.getInstance();
		attendanceDatabase.createAttendance(localDate, singleDayAttendance);
		response.sendRedirect("faculty.html");
	}

}
