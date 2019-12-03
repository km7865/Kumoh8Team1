package Network;

import java.io.Serializable;

public class Protocol <T> implements Serializable
{
	private static final long serialVersionUID = 1L;	//역직렬화시 필요한 내용
	
	private int mainType;
	private int subType;

	T body;	//테이블을 class화 한 것을 body 내용으로 씀

	public Protocol(int mainType, int subType, T some)
	{
		this.mainType = mainType;
		this.subType = subType;
		//body = new T(some); 
	}

	public int getMainType() {return mainType;}
	public int getSubType() {return subType;}
	public T getBody() {return body;}
	
	public void setMainType(int mainType) {this.mainType = mainType;}
	public void setSubType(int subType) {this.subType = subType;}
	//public void setBody(T some) {this.body = new <T> (some);}		//generic 생성자 알아볼것
}
