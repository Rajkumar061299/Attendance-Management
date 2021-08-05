package main;

public class Attendance {

	private String studentId;
	private boolean isPresent;

	public Attendance(String studentId, boolean isPresent) {
		this.studentId = studentId;
		this.isPresent = isPresent;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String convertBooleanToString(){
		if(isPresent) 
			return "present"; 
		return "absent";
	}
	@Override 
	public String toString() {
		return studentId + "=" + convertBooleanToString();
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean present) {
		isPresent = present;
	}


}
