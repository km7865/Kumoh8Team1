package homeWork;

import java.io.*;

public class Protocol {
	//프로토콜 타입에 관한 변수
	public static final int PT_UNDEFINED = -1;	// 프로토콜이 지정되어 있지 않은 경우
	public static final int PT_EXIT = 0;		// 프로그램 종료
	
	public static final int PT_REQ_LOGIN = 1;// 로그인 요청 			CODE사용 (id : 0x01, pwd : 0x02)
	public static final int PT_RES_LOGIN = 2;	// 로그인 응답 		CODE사용 (id : 0x01, pwd : 0x02)
	public static final int PT_LOGIN_RESULT_ID = 3;	// 인증 결과	CODE사용 (성공 : 0x01, 실패  : 0x02)
	public static final int PT_LOGIN_RESULT_PWD = 4;	// 인증 결과	CODE사용 (성공 : 0x01, 실패  : 0x02)
	
	public static final int LEN_LOGIN = 40;	// 로그인 정보 길이
	public static final int LEN_PROTOCOL_TYPE = 1;	// 프로토콜 타입 길이
	public static final int LEN_MAX = 100;		//최대 데이터 길이
	public static final int LEN_CODE = 1;
	public static final int LEN_BODYLENGTH = 2;
	
	public static final int CODE_ID = 1; 		// ID 코드타입
	public static final int CODE_PASSWORD = 2; 	// PW 코드타입
	public static final int CODE_SUCCESS = 1; 	// 성공 코드타입
	public static final int CODE_FAILURE = 2;	// 실패 코드타입
	public static final int CODE_DEFAULT = 0; 	// 기본 코드타입
	
	protected int protocolType;
	protected int protocolCode;
	//바이너리 기반 패킷
	// 프로토콜과 데이터의 저장공간이 되는 바이트 배열
	private byte[] packet;

	public Protocol() {					// 생성자
		this(PT_UNDEFINED, 0);
	}

	public Protocol(int protocolType, int protocolCode) {	// 생성자
		this.protocolType = protocolType;
		this.protocolCode = protocolCode;
		getPacket(protocolType, protocolCode);
	}

	// 프로토콜 타입에 따라 바이트 배열 packet의 length가 다름
	public byte[] getPacket(int protocolType, int protocolCode) {
	    if(packet==null){
			switch(protocolType) {
				case PT_REQ_LOGIN : // 로그인 정보 요청 (ID코드 : 0x01, PW코드 : 0x02)
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_CODE];
					break;
				case PT_RES_LOGIN : // 로그인 요청에 대한 응답 (ID코드 : 0x01, PW코드 : 0x02) 
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_CODE+LEN_LOGIN];
					break;
				case PT_LOGIN_RESULT_ID : // ID 응답에 대한 결과 (성공코드 : 0x01, 실패코드 : 0x02)
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_CODE];
					break;
				case PT_LOGIN_RESULT_PWD : // PASSWORD 응답에 대한 결과 (성공코드 : 0x01, 실패코드 : 0x02)
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_CODE];
					break;
				case PT_EXIT : // 종료
					packet = new byte[LEN_PROTOCOL_TYPE+LEN_CODE];
					break;
				case PT_UNDEFINED : // 기본
					packet = new byte[LEN_MAX];
					break;
			}
	    } // end if
	    packet[0] = (byte)protocolType;	// 바이트 배열 packet 의 첫 번째 바이트에 프로토콜 타입 설정
	    packet[1] = (byte)protocolCode; // 바이트 배열 packet 의 두 번째 바이트에 프로토콜 코드 타입 설정
	    return packet;
	}
	
	// Default 생성자로 생성한 후 Protocol 클래스의 packet 데이터를 바꾸기 위한 메서드
	public void setPacket(int pt, int code, byte[] buf) {
		packet = null;
		packet = getPacket(pt, code);
		protocolType = pt;
		System.arraycopy(buf, 0, packet, 0, packet.length);
	}
	
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}

	public int getProtocolType() {
		return protocolType;
	}
	
	public void setProtocolCode(int protocolCode) {
		this.protocolCode = protocolCode;
	}

	public int getProtocolCode() {
		return protocolCode;
	}

	public byte[] getPacket() {
		return packet;
	}

	// 바이트배열인 packet에 아이디의 길이정보(byte단위)와 아이디 정보 삽입
	public void setId(String id) {
		byte[] length = intToByteArray(id.trim().getBytes().length);
		packet[2] = length[2];
		packet[3] = length[3];
		System.arraycopy(id.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_CODE + LEN_BODYLENGTH, id.trim().getBytes().length);
	}

	// 바이트배열인 packet에 패스워드의 길이정보(byte단위)와 패스워드 정보 삽입
	public void setPassword(String password) {
		byte[] length = intToByteArray(password.trim().getBytes().length);
		packet[2] = length[2];
		packet[3] = length[3];
		System.arraycopy(password.trim().getBytes(), 0, packet, LEN_PROTOCOL_TYPE + LEN_CODE + LEN_BODYLENGTH, password.trim().getBytes().length);
	}
	
	// 아이디 길이 정보를 byte단위로 변환하기 위한 메소드
	private static byte[] intToByteArray(int value) {
		byte[] byteArr = new byte[4];
		for (int i = 0; i < 4; i++)
			byteArr[i] = (byte)(value >> 8 * (3 - i) & 0xff);
		
		return byteArr;
	}
}
