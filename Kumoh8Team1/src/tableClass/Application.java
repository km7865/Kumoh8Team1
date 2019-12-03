package tableClass;
//��û ���̺�
public class Application
{
	private String applicatonNumber;	//��û��ȣ, 2019010000, not null
	private String studentId;			//�й�, not null
	private String year;				//�⵵, not null
	private String semester;			//�б�, not null
	private String dormitoryCode;		//��Ȱ�� �ڵ�
	private String mealDivision;		//�ĺ񱸺�, 5�Ͻ�, 7�Ͻ�, not null
	private double grade;				//����, not null
	private double distancePoint;		//�Ÿ�������, not null
	private String dormitoryWish;		//��Ȱ�� ����, not null
	private String applicationDay;		//��û��, not null
	private String applicationState;	//��û����, �а�, ����, ���հ�, not null
	private String standbyNumber;		//����ȣ, ������ �������� �� ����
	private String oneYearWhether;		//1�⿩��, not null
	private String acceptanceOfAgreement;	//�Ի缭�ൿ�ǿ���, O,X, not null

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
