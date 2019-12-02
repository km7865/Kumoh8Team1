public class Result implements serializable
{
	private String studentId;		 //학번, not null
	private String establishmentYear; 	 //개설년도
	private String establishmentSemester; 	 //개설학기
	private String courseName;		 //교과목명
	private int grade;			 //학점 
	private String gradeRank;		//성적등급, A+, A...
	
	public Result()
	{
		studentId = null; establishmenYear = null; establishmentSemester = null;
		courseName = null; courseName = null; grade = null; gradeRank = null;
	}

	public Result(String studentId, String establishmentYear, String establishmentSemester, String courseName)
	{
		this.studentId = studentID;
		this.establishmentYear = establishmentYear;
		this.establishementSemester = establishmentSemester;
		this.courseName = courseName;
		grade = null; gradeRank = null;
	}
	public Result(Result some)
	{
		this.studentId = some.getStudentId();
		this.establishmentYear = some.getStudentId();
		this.establishmentSemester = some.getEstablishmentSemester();
		this.courseName = some.getCourseName();
		this.grade = some.getGrade();
		this.gradeRank = some.getGradeRank();
	}
	
	String getStudentId() {return studentId;}
	String getEstablishmentYear() {return establishmentYear;}
	String getEstablishmentSemester() {return establishmentYear;}
	String getCourseName() {return courseName;}
	int getGrade() {return grade;}
	String getGradeRank() {return gradeRank;}

	void setStudentId(String studentId) {this.studentId = studentId;}
	void setEstablishementYear(String establishmentYear) {this.establishmentYear = establishmentYear;}
	void setEstablishmentSemester(String establishmentSemester) {this.establishmentSemester = establishmentSemester;}
	void setGrade(int grade) {this.grade = grade;}
	void setGradeRank(String gradeRank) {this.gradeRank = gradeRank;}
}
