package Network;

import java.io.Serializable;

public class Protocol <T> implements Serializable
{
	private static final long serialVersionUID = 1L;	//역직렬화시 필요한 내용
	
	private int mainType;
	private int subType;

	T body;	//테이블을 class화 한 것을 body 내용으로 씀
}
