package tableClass;

import java.io.Serializable;
//�л����̺�
public class Student implements Serializable
{
	private static final long serialVersionUID = 11L;
	private String studentId;			//�й�
	private String name;				//�̸�, not null
	private String registrationNumber;	//��û��ȣ
	private String gender;				//����
	private String departmentName;		//�а���
	private int grade;					//�г�
	private String studentAddress;		//�л��ּ�
	private String studentPhoneNumber;	//�л���ȭ��ȣ

	public Student(String studentId, String name, String registrationNumber)
	{
		this.studentId = studentId;
		this.name = name;
		this.registrationNumber = registrationNumber;
		gender = null; departmentName = null;
		grade = 0; studentAddress = null; studentPhoneNumber = null;
	}

	public Student (Student some)
	{
		studentId = some.getStudentId();	
		name = some.getName();
		registrationNumber = some.getRegistrationNumber();	
		gender = some.getGender(); departmentName = some.getDepartmentName(); grade = some.getGrade();
		studentAddress = some.getStudentAddress(); studentPhoneNumber = some.getStudentPhoneNumber();		
	}
	
	public String getStudentId() {return studentId;}
	public String getName() {return name;}
	public String getRegistrationNumber() {return registrationNumber;}
	public String getGender() {return gender;}
	public String getDepartmentName() {return departmentName;}
	public int getGrade() {return grade;}
	public String getStudentAddress() {return studentAddress;}
	public String getStudentPhoneNumber() {return studentPhoneNumber;}

	public void setStudentId(String studentId) {this.studentId = studentId;}
	public void setName(String name) {this.name = name;}
	public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}
	public void setGender(String gender) {this.gender = gender;}
	public void serDepartmentName(String departmentName) {this.departmentName = departmentName;}
	public void setDepartmentName(String departmentName) {this.departmentName = departmentName;}
	public void setGrade(int grade) {this.grade = grade;}
	public void setStudentAddress(String studentAddress) {this.studentAddress = studentAddress;}
	public void setStudentPhoneNumber(String studentPhoneNumber) {this.studentPhoneNumber = studentPhoneNumber;}
}