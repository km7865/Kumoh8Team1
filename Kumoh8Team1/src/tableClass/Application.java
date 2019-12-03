package tableClass;
//신청 테이블
public class Application
{
	private String applicatonNumber;	//신청번호, 2019010000, not null
	private String studentId;			//학번, not null
	private String year;				//년도, not null
	private String semester;			//학기, not null
	private String dormitoryCode;		//생활관 코드
	private String mealDivision;		//식비구분, 5일식, 7일식, not null
	private double grade;				//학점, not null
	private double distancePoint;		//거리가산점, not null
	private String dormitoryWish;		//생활관 지망, not null
	private String applicationDay;		//신청일, not null
	private String applicationState;	//신청상태, 학겹, 예비, 불합격, not null
	private String standbyNumber;		//대기번호, 총접을 기준으로 한 순위
	private String oneYearWhether;		//1년여부, not null
	private String acceptanceOfAgreement;	//입사서약동의여부, O,X, not null

	public Application()
	{
		applicatonNumber = null; studentId = null;
		year = null; semester = null; dormitoryCode = null; mealDivision = null;
		grade = 0; distancePoint = 0; dormitoryWish= null;
		applicationDay = null; applicationState = null; standbyNumber = null;
		oneYearWhether = null; acceptanceOfAgreement = null; 
	}
	public Application(String applicationNumber, String studentId, String dormitoryCode)
	{
		this.applicatonNumber = applicationNumber;
		this.studentId = studentId; this.dormitoryCode = dormitoryCode;
		year = null; semester = null; mealDivision = null;
		grade = 0; distancePoint = 0; dormitoryWish= null;
		applicationDay = null; applicationState = null; standbyNumber = null;
		oneYearWhether = null; acceptanceOfAgreement = null;
	}
	public Application(Application some)
	{
		applicatonNumber = some.getApplicatonNumber(); studentId = some.getStudentId();
		year = some.getYear(); semester = some.getSemester(); dormitoryCode = some.getDormitoryCode();
		grade = some.getGrade(); distancePoint = some.getDistancePoint();
		dormitoryWish = some.getdormitoryWish(); applicationDay = some.getApplicationDay();
		applicationState = some.getApplicationState(); standbyNumber = some.getStandbyNumber();
		oneYearWhether = some.getOneYearWhether(); acceptanceOfAgreement = some.getAcceptanceOfAgreement();	
	}

	public String getApplicatonNumber() {return applicatonNumber;}
	public String getStudentId() {return studentId;}
	public String getYear() {return year;}
	public String getSemester() {return semester;}
	public String getDormitoryCode() {return dormitoryCode;}
	public String getMealDivision() {return mealDivision;}
	public double getGrade() {return grade;}
	public double getDistancePoint() {return distancePoint;}
	public String getdormitoryWish() {return dormitoryWish;}
	public String getApplicationDay() {return applicationDay;}
	public String getApplicationState() {return applicationState;}
	public String getStandbyNumber() {return standbyNumber;}
	public String getOneYearWhether() {return oneYearWhether;}
	public String getAcceptanceOfAgreement() {return acceptanceOfAgreement;}

	public void setApplicationNumber(String applicationNumber) {this.applicatonNumber = applicationNumber;}
	public void setStudentId(String studentId) {this.studentId = studentId;}
	public void setYear(String year) {this.year = year;}
	public void setSemester(String semester) {this.semester = semester;}	
	public void setDormitoryCode(String dormitoryCode) {this.dormitoryCode = dormitoryCode;}
	public void setMealDivision(String mealDivision) {this.mealDivision = mealDivision;}
	public void setGrade(double grade) {this.grade = grade;}
	public void setDistancePoint(double distancePoint) {this.distancePoint = distancePoint;}
	public void setdormitoryWish(String dormitoryWish) {this.dormitoryWish = dormitoryWish;}
	public void setApplicationDay(String applicationDay) {this.applicationDay = applicationDay;}
	public void setApplicationState(String applicationState) {this.applicationState = applicationState;}
	public void setStandbyNumber(String standbyNumber) {this.standbyNumber = standbyNumber;}
	public void setOneYearWhether(String oneYearWhether) {this.oneYearWhether = oneYearWhether;}
	public void setAcceptanceOfAgreement(String acceptanceOfAgreement) {this.acceptanceOfAgreement = acceptanceOfAgreement;}
}
