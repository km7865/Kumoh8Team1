package tableClass;

import java.io.Serializable;

//신청 테이블
public class dormitoryApplication implements Serializable
{
	private static final long serialVersionUID = 7L;
	private String applicatonNumber;	//신청번호, 2019010000, not null
	private String studentId;			//학번, not null
	private String year;				//년도, not null
	private String semester;			//학기, not null

	private String mealDivision1;		//1지망식비구분, 5일식, 7일식, not null
	private String mealDivision2;		//2지망식비구분
	private String mealDivision3;		//3지망 식비구분
	private String mealDivisionYear;	//1년지망 식비구분
	private double grade;				//학점, not null
	private double distancePoint;		//거리가산점, not null
	private double finallyValue;		//최종값 (학점 + 거리가산점)
	
	private String dormitoryWish1;		//생활관 지망1, not null
	private String dormitoryWish2;		//생활관 지망2	
	private String dormitoryWish3;		//생활관 지망3
	private String dormitoryWishYear;	//생활관 지망 -1년 
	private String applicationDay;		//신청일, not null
	private String applicationState;	//신청상태, 학겹, 예비, 불합격, not null
	private String standbyNumber;		//대기번호, 총접을 기준으로 한 순위
	private String acceptanceOfAgreement;	//입사서약동의여부, O,X, not null

	public dormitoryApplication()
	{
		applicatonNumber = null; studentId = null;
		year = null; semester = null; 
		mealDivision1 = null;mealDivision2 = null;mealDivision3 = null;
		grade = 0; distancePoint = 0; finallyValue = 0;dormitoryWish1= null;dormitoryWish2= null;dormitoryWish3= null;
		applicationDay = null; applicationState = null; standbyNumber = null;
		acceptanceOfAgreement = null; dormitoryWishYear = null; mealDivisionYear = null;
	}
	//나머지 인수는 setter 불러서 하기
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
