package tableClass;

public class Dormitory
{
	private String dormitoryCode;		//�з��ڵ�, not null
	private String genderLimit;			//��������, M, W, not null
	private String dormitoryName;		//��Ȱ����, Ǫ��1��, Ǫ�� 2��, ����1��..., not null
	private String mealTime;			//�Ļ�ð�, ��ħ,����,���� ���, not null
	private int capacityNumber;			//�����ο�, not null
	private String mealDuty;			//�Ļ��ǹ�, O or X, not null

	public Dormitory()
	{
		dormitoryCode = null; genderLimit = null; dormitoryName = null;
		mealTime = null; capacityNumber = 0; mealDuty = null;
	}
	public Dormitory(String dormitoryCode, String genderLimit)
	{
		this.dormitoryCode = dormitoryCode; this.genderLimit = genderLimit;
		dormitoryName = null; mealTime = null; capacityNumber = 0;
		mealDuty = null;
	}
	
	public String getDormitoryCode() {return dormitoryCode;}
	public String getGenderLimit() {return genderLimit;}
	public String getDormitoryName() {return dormitoryName;}
	public String getMealTime() {return mealTime;}
	public int getCapacityNumber() {return capacityNumber;}
	public String getMealDuty() {return mealDuty;}

	public void setDormitoryCode(String dormitoryCode) {this.dormitoryCode = dormitoryCode;}
	public void setGenderLimit(String genderLimit) {this.genderLimit = genderLimit;}
	public void setDormitoryName(String dormitoryName) {this.dormitoryName = dormitoryName;}
	public void setMealTime(String mealTime) {this.mealTime = mealTime;}
	public void setCapacityNumber(int capacityNumber) {this.capacityNumber = capacityNumber;}
	public void setMealDuty(String mealDuty) {this.mealDuty = mealDuty;}
}
