package main;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceDatabase{

	private static AttendanceDatabase attendanceDatabaseInstance;

	private final Map<LocalDate, List<Attendance>> attendanceDB;

	public static AttendanceDatabase getInstance() {
		if (attendanceDatabaseInstance == null)
			attendanceDatabaseInstance = new AttendanceDatabase();
		return attendanceDatabaseInstance;
	}

	private AttendanceDatabase() {
		attendanceDB = new HashMap<>();
		attendanceDB.put(LocalDate.now().minusDays(3),
				Arrays.asList(new Attendance("s1", true), new Attendance("s2", false), new Attendance("s3", true)));
		attendanceDB.put(LocalDate.now().minusDays(2),
				Arrays.asList(new Attendance("s1", false), new Attendance("s2", true), new Attendance("s3", true)));
		attendanceDB.put(LocalDate.now().minusDays(1),
				Arrays.asList(new Attendance("s1", true), new Attendance("s2", true), new Attendance("s3", false)));
	}

	// Getter Method
	public Map<LocalDate, List<Attendance>> getAttendanceDB() {
		return attendanceDB;
	}

	// faculty can entry the attendance for particular student 
	public void createAttendance(LocalDate date, List<Attendance> singleDayAttendance) {
		attendanceDB.put(date, singleDayAttendance);
	}
	
	public List<Attendance> getAttendance(LocalDate date) {
		return attendanceDB.get(date);
	}
	// particular date student was present or not
	public boolean wasStudentPresentOn(LocalDate date, String userId) {

		return attendanceDB.get(date).stream().filter(attendance -> attendance.getStudentId().equals(userId))
				.findFirst().orElse(new Attendance(userId, false)).isPresent();
	}

	// get particular student date
	public Map<LocalDate, Boolean> getParticularStudentAttendance(String userId) {

		return attendanceDB.entrySet().stream().collect(Collectors.toMap(entrySet -> entrySet.getKey(),
				entrySet -> wasStudentPresentOn(entrySet.getKey(), userId)));
	}

	// update from admin -> attendance student by userid -> present/absent set
	public void updateAttendance(LocalDate date, String userId, boolean isPresent) {

		attendanceDB.get(date).stream().filter(attendance -> attendance.getStudentId().equals(userId)).findFirst()
				.orElse(new Attendance(userId, false)).setPresent(isPresent);
	}
	
	// find average of particular student attendance
	public double findAverageStudentAttendance(String userId) {
		
		List<Boolean> attendanceStatus = getParticularStudentAttendance(userId).values().stream().collect(Collectors.toList());
		return attendanceStatus.stream(). 
				mapToInt(status -> status ? 1 : 0).average().orElse(0);
		
	}

	public List<LocalDate> getSortedDates(){
		List<String> dateStringList = attendanceDB.keySet().stream().map(date -> date.toString()).
				collect(Collectors.toList());

		Collections.sort(dateStringList, Comparator.naturalOrder());

		return dateStringList.stream().
				map(dateString -> LocalDate.parse(dateString)).collect(Collectors.toList());
	}

}
