package Network;

import java.io.Serializable;

public class Protocol <T> implements Serializable
{
	private static final long serialVersionUID = 1L;	//역직렬화시 필요한 내용
	
	private int mainType;
	private int subType;
	private int code;
	T body;	//테이블을 class화 한 것을 body 내용으로 씀

	public Protocol(int mainType, int subType)
	{
		this.mainType = mainType;
		this.subType = subType;
	}
	
	public Protocol(int mainType, int subType, int code)
	{
		this.mainType = mainType;
		this.subType = subType;
		this.code = code;
	}
	
	public Protocol(int mainType, int subType, T some)
	{
		this.mainType = mainType;
		this.subType = subType;
		//body = new T(some); 
	}
	
	public Protocol(Protocol some)
	{
		this.mainType = some.getMainType();
		this.subType = some.getSubType();
		this.code = some.getCode();
	//	this.body = new T();
	}
//generic 이렇게 쓰는거 아닌가 ㅡㅡ 왜자꾸 오류나
	public Protocol makePacket(int mainType, int subType, int code, T some)
	{
		this.mainType = mainType; this.subType = subType;
		this.code = code; this.body =new some);
		
		return this;
	}
	
	public int getMainType() {return mainType;}
	public int getSubType() {return subType;}
	public int getCode() {return code;}
	//@SuppressWarnings("unchecked")
	public <T>T getBody() {return (T) body;}
	
	public void setMainType(int mainType) {this.mainType = mainType;}
	public void setSubType(int subType) {this.subType = subType;}
	//public void setBody(T some) {this.body = new <T> (some);}		//generic 생성자 알아볼것
	public void setCode(int code) {this.code = code;}
}
