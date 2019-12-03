package GUI;

import java.io.Serializable;

public class Protocol_test implements Serializable{
	private int mainType;
	private int subType;
	private String msg1;
	private String msg2;
	
	public Protocol_test() {}
	public Protocol_test(int m, int s, String m1, String m2)
	{
		mainType = m;
		subType = s;
		msg1 = m1;
		msg2 = m2;
	}
	
	public int getMainType() {return mainType;}
	public int getSubType() {return subType;}
	public String getMsg1() {return msg1;}
	public String getMsg2() {return msg2;}
	
	public void setMsg1(String m1) {msg1 = m1;}
	public void setMsg2(String m2) {msg2 = m2;}
}
