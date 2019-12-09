package Network;

import java.io.Serializable;

public class Protocol implements Serializable
{
	private static final long serialVersionUID = 1L;	//역직렬화시 필요한 내용
	
	private int mainType;
	private int subType;
	private int code;

	public Protocol()
	{
		mainType = 0; subType = 0; code = 0;
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
	
	public Protocol(Protocol some)
	{
		this.mainType = some.getMainType();
		this.subType = some.getSubType();
		this.code = some.getCode();
	}
	
	public void makePacket(int mainType, int subType, int code)
	{
		this.mainType = mainType; this.subType = subType; this.code = code;
	}
	
	public int getMainType() {return mainType;}
	public int getSubType() {return subType;}
	public int getCode() {return code;}
	
	public void setMainType(int mainType) {this.mainType = mainType;}
	public void setSubType(int subType) {this.subType = subType;}
	public void setCode(int code) {this.code = code;}
}
