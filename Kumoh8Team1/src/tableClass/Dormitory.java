package tableClass;

import java.io.Serializable;

//생활관 테이블
public class Dormitory implements Serializable
{
	private static final long serialVersionUID = 6L;
	private String dormitoryCode;		//생활관 코드, not null
	private String genderLimit;			//성별제한, M, W, not null
	private String dormitoryName;		//생활관명, 푸름1관, 푸름2관, 오름1관..., not null
	private String mealTime;			//식사시간, 아침, 점심,저녁, not null
	private int capacityNumber;			//수용인원, not null
	private String mealDuty;			//식사의무, O or X, not null

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
