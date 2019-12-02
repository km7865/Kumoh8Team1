public class Student implements serializable
{
	private String university;	//대학 구분, not null
	private String studentId;	//학번, not null
	private String name;		//성명, not null
	private String registrationNumber;	//주민등록번호, not null
	private String gender;		//성별
	private String departmentCode;	//학과코드
	private String departmentName;	//학과명
	private String grade;		//학년
	private String major;		//전공구분
	private String DayOrNight;	//주야구분
	private String studentPostcode;	//학생우편번호
	private String studentAddress;	//학생주소
	private String studentPhoneNumber;	//학생전화번호
	private String parentsName;	//보호자이름
	private String parentsPostcode;	//보호자우편번호	
	private String parentsAddress;	//보호자주소
	private String parentsPhoneNumber;	//보호자전화번호
	private String parentsRelation;	//보호자와의 관계

	public Student(String university, String studentId, String name, String registration)
	{
		this.university = universith;
		this.studentId = studentId;
		this.name = name;
		this.registration = registration;
		gender = null; departmentCode = null; departmentName = null;
		grade = null; major = null; DayOrNight = null;
		studentPostcode = null; studentAddress = null; studentPhoneNumber = null;
		parentsname = null; parentsAddress = null; parentsPhoneNumber = null;
		parentsRelation = null;
	}

	public Student (Student some)
	{
		unibersity = some.getUniversity();	
		studendId = some.getStudentId();	
		name = some.getName();
		registration = some.getRegistration;	
		gender = null; departmentCode = null; departmentName = null;
		grade = null; major = null; DayOrNight = null;
		studentPostcode = null; studentAddress = null; studentPhoneNumber = null;
		parentsname = null; parentsAddress = null; parentsPhoneNumber = null;
		parentsRelation = null;
		
	}
	
	public String getUniversity() {return university;}
	public String getStudentId() {return studentId;}
	public String getName() {return name;}
	public String getRegistration() {return Registration;}
	public String getGender() {return gender;}
	public String getDepartmentCode() {return departmentCode;}
	public Stirng getDepartmentName() {return getDepartmentName;}
	public int getGrade() {return grade;}
	public String getMajor() {return major;}
	public String getDay_or_Night() {return DayOrNight;}
	public String getStudentPostCode() {return studentPostcode;}
	public String getStudentAddress() {return studentAddress;}
	public String getStudentPhoneNumber() {return studentPhoneNumber;}
	public String getParentRelation() {return parentRelation;}

	public void setUniversity(String university) {this.university = university;}
	public void setStudentId(String studentId) {this.studentId = studentId;}
	public void setName(String name) {this.name = name;}
	public void setRegistration(String registration) {this.registration = registration;}
	public void setGender(String gender) {this.gender = gender;}
	public void setDepartmenCode(String departmentCode) {this.departmentCode = departmentCode;}
	public void serDepartmentName(String departmentName) {this.departmentName = departmentName;}
	public void setGrade(int grade) {this.grade = grade;}
	public void setDayOrNight(String DayOrNight) {this.DayOrNight = DayOrNight;}
	public void setStudentPostcode(String postCode) {this.postCode = postCode;}
	public void setStudentAddress(String studentAddress) {this.studentAddress = studentAddress;}
	public void setStudentPhoneNumber(String studentPhoneNumber) {this.studentPhoneNumber = studentPhoneNumber;}
	public void setParentsName(String parentsName) {this.parentsName = parentsName;}
	public void setParentsAddress(String parentsAddress) {this.parentsAddress = parentsAddress;}
	public void setParentsPhoneNumber(String parenstPhoneNumber) {this.parentsPhoneNubmer = parentsPhoneNumber;}
	public void setParentsRelation(String parentsRelation) {this.parentsRelation = parentsRelation;}
}
