package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import tableClass.*;

public class ServerProtocolManager 
{
	Socket socket= null;
	public InputStream is;
	public ObjectInputStream reader;
	public OutputStream os;
	public ObjectOutputStream writer;
	
	public ServerProtocolManager(Socket socket) 
	{
		try
		{
			this.socket = socket;
			is = socket.getInputStream();
			reader = new ObjectInputStream(is);
			os = socket.getOutputStream();
			writer = new ObjectOutputStream(os);
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
	public <T>void workProtocol()
	{
		Protocol protocol = new Protocol();
			
		try
		{
			protocol = (Protocol)reader.readObject();
			
			switch(protocol.getMainType())
			{
			case 1:		//로그인
				Login(protocol);
				break;
			case 11:	//입사신청
				//do 입사신청
				break;
			case 12:		//호실조회
				//do 호실조회
				break;
			case 13:		//입사신청내역 조회
				//do 입사신청내역 조회
				break;
			case 14:		//고지서 출력
				//do 고지서 출력
				break;
			case 15:		//결핵진단서 제출
				//do 결핵진단서 제출
				break;
			case 21:		//선발일정 등록
				//do 선발일정 들록
				break;
			case 22:		//생활관 사용료 및 급식비 등록
				//do 생활관 사용료 및 급식비 등록
				break;
			case 23:		//입사자 등록
				//do 입삭자 등록
				break;
			case 24:		//입사자 조회
				//do 입사자 조회
				break;
			case 25:		//입사선발자 결과등록
				//do 입사선발자 결과등록
				break;
			case 26:		//결핵진단서 제출확인
				//do 결핵진단서 제출확인
				break;
			
			}//end of switch
		}//end of try
		//catch(사용자 정의 오류)
		//{
		//	오류를 담은 패킷을 만들어서 클라이언트에게 전송
		//}
		catch(ClassNotFoundException a)
		{
			a.getStackTrace();
		}
		catch(IOException b)
		{
			b.getStackTrace();
		}
		catch(Exception c)
		{
			c.getStackTrace();
		}
	}
	
	
	//exception 사용자 정의 예외 만들어야함, 여기에 적은 exception은 예외 클래스를 아직 안만들어서 적어둔거임
	public void Login(Protocol protocol) throws Exception //maintype 1, 로그인
	{	
		//받은 로그인 정보로 db에 접속해서 해당 정보가 맞는지 검색
			/*
			if(로그인 정보가 맞는 경우)
				protocol = new Protocol(makeLoginPacket(1));
		
			else	//로그인 정보가 없거나 틀린 경우
			{
				protocol = new Protocol(makeLoginPacket(2));
				throw Exception;
			}
			writer.writeObject(protocol);	//처리 결과를 클라이언트에게 보냄
			writer.flush()
			 */
	}

	public Protocol makeLoginPacket(int code)
	{
		Protocol protocol=new Protocol(1,2,code);	
		return protocol;
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol) throws Exception	//maintype 11, 입사신청 
	{
		//클라이언트가 서버에게 입사신청정보를 보낸걸 받았다, 이 함수 밖에서
		//디비에 저장한다
		//처리 결과를 클라이언트에게 보낸다.
		/*
		if(처리에 성공한 경우)
			protocol = new Protocol(makeDormitoryApplicationResultPacket(1));
		else	//처리에 실패한 경우
		{
			protocol = new Protocol(makeDormitoryApplicationResultPacket(1));
			throws Exception;
		}
		writer.writeObject(protocol);
		writer.flush();
		*/
	}
	
	public Protocol makeDormitoryApplicationResultPacket(int code)
	{
		Protocol protocol=new Protocol(2,2,code);
		return protocol;
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol) throws Exception	//maintype 12, 호실조회
	{
		//db에서 호실정보를 검색해본다
		//if(정보가 있다)
			
		
	}
	public void inquireDormitoryApplication() throws Exception	//maintype 13, 입사신청내역 조회
	{
		switch(protocol.getSubType())
		{
		case 1:		//입사신청내역 조회 요청
			//클라이언트가 서버에게 입사신청내역 조회를 요청함
			break;
		case 2:		//입사신청내역 정보
			//db에서  입사신청내역을 검색하고 클라이언트에게 전송, 없을 경우 예외코드 내보냄
			break;
		}
	}
	public void printDetailedStatement_Bill() throws Exception	//maintype 14, 고지서 출력
	{
		switch(protocol.getSubType())
		{
		case 1:		//고지서 내역 요청	
			//클라이언트가 서버에게 고지서 내역 요청
			break;
		case 2:		//고지서 내역
			//클라이언트읭 학번으로 디비 해서 관리비랑 식비 더해서 클라이언트에게 전송
			break;
		}
	}
	public void submissionTuberculosisDiagnosis() throws Exception	//maintype 15, 결핵진단서 제출
	{
		switch(protocol.getSubType())
		{
		case 1:		//결핵진단서 제출 요청
			//클라이언트가 서버에게 결핵진단서 제출 요청 메세지를 보냄
			break;
		case 2:		//결핵진단서 파일
			//클라이언트가 서버에게 결핵진단서 파일을 보내고 서버는 db에 저장한다
			break;
		}
	}
	
	public void enrollSelectionSchedule() throws Exception	//maintype 21, 선발일정 등록
	{
		switch(protocol.getSubType())
		{
		case 1:		//선발일정
			//클라이언트가 서버에게 선발일정 등록 내용을 보낸다
			break;
		case 2:		//선알빌정 등록 처리 결과
			//서버가 받은 내용을 db에 저장한다, 저장 결과를 클라이언트로 보냄
			break;
		}
	}
	
	public void enrollDormitoryCost() throws Exception	//maintype 22, 생활관 사용료 및 급식비 등록
	{
		switch(protocol.getSubType())
		{
		case 1:		//생홀관 사용료 및 급식비 정보
			//클라이언트가 서버에게 생홀관 사용료 및 급식비 정보를 보낸다
			break;
		case 2:		//생활관 사용료 및 급식비 정보 등록 결과
			//서버가 받은 정보를 db에 저장한다, 저장 결과를 클라이언트로 보냄
			break;
		}
	}
	public void enrollFinalSelectedStudent() throws Exception	//maintype 23, 입사자 등록(최종)
	{
		switch(protocol.getSubType())
		{
		case 1:		//입사자 등록 요청
			// 클라이언트가 서버에 입사자 등록 요청 메세지를 전송
			break;
		case 2:		//입사자 등록 처리 결과
			//서버는 입사 선발자 테이블을 조회해 납부가 완료된 학생들을 입사자로 등록함(등록 여부 업데이트)
			break;
		}
	}
	public void inquireFinalSelectedStudent() throws Exception	//maintype 24, 입사자 조회
	{
		switch(protocol.getSubType())
		{
		case 1:		// 입사자 조회 요청
			//클라이언트가 서버에게 입사자 조회를 요청함
			break;
		case 2:		//입사자 목록 정보
			//서버가 입사선발자의 등록여부가 최종합격인 학생들을 조회해서 클라이언트로 전송
			break;
		}
	}
	
	
}
