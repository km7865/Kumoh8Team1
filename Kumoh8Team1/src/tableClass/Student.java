package tableClass;

import java.io.Serializable;
//학생 테이블
public class Student implements Serializable
{
	private static final long serialVersionUID = 11L;
	private String university;			//대학 구분, not null
	private String studentId;			//학번, not null
	private String name;				//성명, not null
	private String registrationNumber;	//주민등록번호, not null
	private String gender;				//성별
	private String departmentCode;		//학과코드
	private String departmentName;		//학과명
	private int grade;					//학년
	private String major;				//전공구분
	private String studentAddress;		//학생주소
	private String studentPhoneNumber;	//학생전화번호

	public Student(String university, String studentId, String name, String registrationNumber)
	{
		this.university = university;
		this.studentId = studentId;
		this.name = name;
		this.registrationNumber = registrationNumber;
		gender = null; departmentCode = null; departmentName = null;
		grade = 0; major = null; studentAddress = null; studentPhoneNumber = null;
	}

	public Student (Student some)
	{
		university = some.getUniversity();	
		studentId = some.getStudentId();	
		name = some.getName();
		registrationNumber = some.getRegistrationNumber();	
		gender = some.getGender(); departmentCode = some.getDepartmentCode(); 
		departmentName = some.getDepartmentName(); grade = some.getGrade(); major = some.getMajor();
		studentAddress = some.getStudentAddress(); studentPhoneNumber = some.getStudentPhoneNumber();		
	}
	
	public String getUniversity() {return university;}
	public String getStudentId() {return studentId;}
	public String getName() {return name;}
	public String getRegistrationNumber() {return registrationNumber;}
	public String getGender() {return gender;}
	public String getDepartmentCode() {return departmentCode;}
	public String getDepartmentName() {return departmentName;}
	public int getGrade() {return grade;}
	public String getMajor() {return major;}
	public String getStudentAddress() {return studentAddress;}
	public String getStudentPhoneNumber() {return studentPhoneNumber;}

	public void setUniversity(String university) {this.university = university;}
	public void setStudentId(String studentId) {this.studentId = studentId;}
	public void setName(String name) {this.name = name;}
	public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}
	public void setGender(String gender) {this.gender = gender;}
	public void setDepartmenCode(String departmentCode) {this.departmentCode = departmentCode;}
	public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
	public void setGrade(int grade) {this.grade = grade;}
	public void setStudentAddress(String studentAddress) {this.studentAddress = studentAddress;}
	public void setStudentPhoneNumber(String studentPhoneNumber) {this.studentPhoneNumber = studentPhoneNumber;}
}
