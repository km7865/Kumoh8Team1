package tableClass;

import java.io.Serializable;

//입사선발자 테이블
public class SelectedStudent  implements Serializable{
	private static final long serialVersionUID = 9L;
	private String sign_up_number;
	private String room_code;
	private String bed_code;
	private int mng_cost;
	private int food_cost;
	private int total_cost;
	private String pay_status;
	private String register_status;
	private String submissionTuberculosis;		//결핵진단서 제출여부
	private String dormitoryCode;	// 생활관분류코드
	
	public SelectedStudent() {}	

	public SelectedStudent(String s, String r, String b, int m, int f, int t, String p, String rs, String tuber, String dormitoryCode)
	{
		sign_up_number = s;
		room_code = r;
		bed_code = b;
		mng_cost = m;
		food_cost = f;
		total_cost = t;
		pay_status = p;
		register_status = rs;
		submissionTuberculosis = tuber;
		this.dormitoryCode = dormitoryCode;
	}
	
	public String getSign_up_number() {return sign_up_number;}
	public String getRoom_code() {return room_code;}
	public String getBed_code() {return bed_code;}
	public int getMng_cost() {return mng_cost;}
	public int getFood_cost() {return food_cost;}
	public int getTotal_cost() {return total_cost;}
	public String getPay_status() {return pay_status;}
	public String getRegister_status() {return register_status;}
	public String getSubmissionTuberculosis() {return submissionTuberculosis;}
	public String getDormitoryCode() {return dormitoryCode;}
	
	public void setSign_up_number(String s) {sign_up_number = s;}
	public void setRoom_code(String r) {room_code = r;}
	public void setBed_code(String b) {bed_code = b;}
	public void setMng_cost(int m) {mng_cost = m;}
	public void setFood_cost(int f) {food_cost = f;}
	public void setTotal_cost(int t) {total_cost = t;}
	public void setPay_status(String p) {pay_status = p;}
	public void setRegister_status(String rs) {register_status = rs;}
	public void setSubmissionTuberculosis(String tuber) {submissionTuberculosis = tuber;}
	public void setDormitoryCode(String dormitoryCode) {this.dormitoryCode = dormitoryCode;} 
}
