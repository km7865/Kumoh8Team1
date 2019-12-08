package tableClass;

import java.io.Serializable;
//�л� ���̺�
public class Student implements Serializable
{
	private static final long serialVersionUID = 11L;
	private String university;			//���� ����, not null
	private String studentId;			//�й�, not null
	private String name;				//����, not null
	private String registrationNumber;	//�ֹε�Ϲ�ȣ, not null
	private String gender;				//����
	private String departmentCode;		//�а��ڵ�
	private String departmentName;		//�а���
	private int grade;					//�г�
	private String major;				//��������
	private String studentAddress;		//�л��ּ�
	private String studentPhoneNumber;	//�л���ȭ��ȣ

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
