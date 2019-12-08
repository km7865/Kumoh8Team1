package Network;

import java.io.Serializable;

public class Protocol <T> implements Serializable
{
	private static final long serialVersionUID = 1L;	//역직렬화시 필요한 내용
	
	private int mainType;
	private int subType;
	private int code;
	private T body;	//테이블을 class화 한 것을 body 내용으로 씀


	public Protocol()
	{
		mainType = 0; subType = 0;
		code = 0; body = null;
	}
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
		body = some; 
	}
	
	public Protocol(Protocol some)
	{
		this.mainType = some.getMainType();
		this.subType = some.getSubType();
		this.code = some.getCode();
		this.body = (T) some.getBody();
	}
	
	public Protocol(int mainType, int subType, int code, T some)
	{
		this.mainType = mainType; this.subType = subType;
		this.code = code; this.body =some;
	}
	
	public void makePacket(int mainType, int subType, int code, T some)
	{
		this.mainType = mainType; this.subType = subType;
		this.code = code; this.body =some;
	}
	
	public int getMainType() {return mainType;}
	public int getSubType() {return subType;}
	public int getCode() {return code;}
	public T getBody() {return body;}
	
	public void setMainType(int mainType) {this.mainType = mainType;}
	public void setSubType(int subType) {this.subType = subType;}
	public void setBody(T some) {this.body = some;}		//generic 생성자 알아볼것
	public void setCode(int code) {this.code = code;}
}
