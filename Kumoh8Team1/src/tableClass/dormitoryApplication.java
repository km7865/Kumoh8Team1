package tableClass;

import java.io.Serializable;

//��û ���̺�
public class dormitoryApplication implements Serializable
{
	private static final long serialVersionUID = 7L;
	private String applicatonNumber;	//��û��ȣ, 2019010000, not null
	private String studentId;			//�й�, not null
	private String year;				//�⵵, not null
	private String semester;			//�б�, not null

	private String mealDivision1;		//1�����ĺ񱸺�, 5�Ͻ�, 7�Ͻ�, not null
	private String mealDivision2;		//2�����ĺ񱸺�
	private String mealDivision3;		//3���� �ĺ񱸺�
	private String mealDivisionYear;	//1������ �ĺ񱸺�
	private double grade;				//����, not null
	private double distancePoint;		//�Ÿ�������, not null
	private double finallyValue;		//������ (���� + �Ÿ�������)
	
	private String dormitoryWish1;		//��Ȱ�� ����1, not null
	private String dormitoryWish2;		//��Ȱ�� ����2	
	private String dormitoryWish3;		//��Ȱ�� ����3
	private String dormitoryWishYear;	//��Ȱ�� ���� -1�� 
	private String applicationDay;		//��û��, not null
	private String applicationState;	//��û����, �а�, ����, ���հ�, not null
	private String standbyNumber;		//����ȣ, ������ �������� �� ����
	private String acceptanceOfAgreement;	//�Ի缭�ൿ�ǿ���, O,X, not null

	public dormitoryApplication()
	{
		applicatonNumber = null; studentId = null;
		year = null; semester = null; 
		mealDivision1 = null;mealDivision2 = null;mealDivision3 = null;
		grade = 0; distancePoint = 0; finallyValue = 0;dormitoryWish1= null;dormitoryWish2= null;dormitoryWish3= null;
		applicationDay = null; applicationState = null; standbyNumber = null;
		acceptanceOfAgreement = null; dormitoryWishYear = null; mealDivisionYear = null;
	}
	//������ �μ��� setter �ҷ��� �ϱ�
	public dormitoryApplication(String applicationNumber, String studentId)
	{
		this.applicatonNumber = applicationNumber;
		this.studentId = studentId;
	}
	
	public dormitoryApplication(dormitoryApplication some)
	{
		applicatonNumber = some.getApplicatonNumber(); studentId = some.getStudentId();
		year = some.getYear(); semester = some.getSemester();
		mealDivision1 = some.getMealDivision1(); mealDivision2 = some.getMealDivision2(); mealDivision3 = some.getMealDivision3();
		mealDivisionYear = some.getMealDivisionYear();
		grade = some.getGrade(); distancePoint = some.getDistancePoint();
		dormitoryWish1 = some.getDormitoryWish1(); dormitoryWish2 = some.getDormitoryWish2();dormitoryWish3 = some.getDormitoryWish3(); 
		applicationDay = some.getApplicationDay(); applicationState = some.getApplicationState(); standbyNumber = some.getStandbyNumber();
		acceptanceOfAgreement = some.getAcceptanceOfAgreement();	
	}

	public String getApplicatonNumber() {return applicatonNumber;}
	public String getStudentId() {return studentId;}
	public String getYear() {return year;}
	public String getSemester() {return semester;}
	public String getMealDivision1() {return mealDivision1;}
	public String getMealDivision2() {return mealDivision2;}
	public String getMealDivision3() {return mealDivision3;}
	public String getMealDivisionYear() {return mealDivisionYear;}
	public double getGrade() {return grade;}
	public double getDistancePoint() {return distancePoint;}
	public double getFinallyValue() {return finallyValue;}
	public String getDormitoryWish1() {return dormitoryWish1;}
	public String getDormitoryWish2() {return dormitoryWish2;}
	public String getDormitoryWish3() {return dormitoryWish3;}
	public String getDormitoryWishYear() {return dormitoryWishYear;}
	
	public String getApplicationDay() {return applicationDay;}
	public String getApplicationState() {return applicationState;}
	public String getStandbyNumber() {return standbyNumber;}
	public String getAcceptanceOfAgreement() {return acceptanceOfAgreement;}

	public void setApplicationNumber(String applicationNumber) {this.applicatonNumber = applicationNumber;}
	public void setStudentId(String studentId) {this.studentId = studentId;}
	public void setYear(String year) {this.year = year;}
	public void setSemester(String semester) {this.semester = semester;}	
	public void setMealDivision1(String mealDivision1) {this.mealDivision1 = mealDivision1;}
	public void setMealDivision2(String mealDivision2) {this.mealDivision2 = mealDivision2;}
	public void setMealDivision3(String mealDivision3) {this.mealDivision3 = mealDivision3;}
	public void setMealDivisionYear(String mealDivisionYear) {this.mealDivisionYear = mealDivisionYear;}
	
	public void setGrade(double grade) {this.grade = grade;}
	public void setDistancePoint(double distancePoint) {this.distancePoint = distancePoint;}
	public void setFinallyValue(double finallyValue) {this.finallyValue = finallyValue;}
	public void setFinallyValue() {finallyValue = grade + distancePoint;}
	public void setDormitoryWish1(String dormitoryWish1) {this.dormitoryWish1 = dormitoryWish1;}
	public void setDormitoryWish2(String dormitoryWish2) {this.dormitoryWish2 = dormitoryWish2;}
	public void setDormitoryWish3(String dormitoryWish3) {this.dormitoryWish1 = dormitoryWish3;}
	public void setDormitoryWishYear(String dormitoryWishYear) {this.dormitoryWishYear = dormitoryWishYear;}
	
	public void setApplicationDay(String applicationDay) {this.applicationDay = applicationDay;}
	public void setApplicationState(String applicationState) {this.applicationState = applicationState;}
	public void setStandbyNumber(String standbyNumber) {this.standbyNumber = standbyNumber;}
	public void setAcceptanceOfAgreement(String acceptanceOfAgreement) {this.acceptanceOfAgreement = acceptanceOfAgreement;}
}
