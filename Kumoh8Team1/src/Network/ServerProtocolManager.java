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
				dormitoryApplication(protocol);
				break;
			case 12:		//호실조회
				inquireDormitoryRoom(protocol);
				break;
			case 13:		//입사신청내역 조회
				inquireDormitoryApplication(protocol);
				break;
			case 14:		//고지서 출력
				printDetailedStatement_Bill(protocol);
				break;
			case 15:		//결핵진단서 제출
				submissionTuberculosisDiagnosis(protocol);
				break;
			case 21:		//선발일정 등록
				enrollSelectionSchedule(protocol);
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
		/*catch(사용자 정의 오류)
		{
			오류를 담은 패킷을 만듬 ->finally에서 클라이언트로 전송
			protocol.setBody(Exception 객체);
		}
		*/
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
		finally
		{
			try
			{
				writer.writeObject(protocol);	//처리 결과를 클라이언트에게 보냄
				writer.flush();
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
			
		}
	}
	
	//현재 구조에서 동작 함수에서 프로토콜의 값을 바꾸는데, call by reference로 작동할지 헷갈린다! ->확인해볼것
	
	//exception 사용자 정의 예외 만들어야함, 여기에 적은 exception은 예외 클래스를 아직 안만들어서 적어둔거임
	public void Login(Protocol protocol) throws Exception //maintype 1, 로그인
	{	
		//받은 로그인 정보로 db에 접속해서 해당 정보가 맞는지 검색
			/*
			if(로그인 정보가 맞는 경우)
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
		
			else	//로그인 정보가 없거나 틀린 경우
			{
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
				throw Exception;
			}	
			 */
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol) throws Exception	//maintype 11, 입사신청 
	{
		//클라이언트가 서버에게 입사신청정보를 보낸걸 받았다, 이 함수 밖에서
		//디비에 저장한다
		//처리 결과를 클라이언트에게 보낸다.
		/*
		if(처리에 성공한 경우)
			protocol = new Protocol(protocol.makePacket(11,2,1,null));
		else 	//처리에 실패한 경우
		{
			protocol = new Protocol(makePacket(11,2,2,null));
			throws Exception;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol) throws Exception	//maintype 12, 호실조회
	{
		/*
		db에서 호실정보를 검색해본다
		if(정보가 있다)
		{
			//해당 정보를 호실정보 객체에 담는다
			protocol = new Protocol(makePacket(12,2,1,호실정보객체));	//code는 안써서 0으로 초기화했다
		}
		else 	//정보가없다
		{
			protocol = new Protocol(makePacket(12,2,2,null));
			throws Exception;
		}
		*/	
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol) throws Exception	//maintype 13, 입사신청내역 조회
	{
		/*
		db에서 학생의 학번으로 입사신청내역을 조회해본다
		if(정보가 있다)
		{
			//해당 정보를 입사신청내역 객체에 담는다
			 protocol = new Protocol(makePacket(13,2,1,입사신청내역객체));
		}
		else	//정보가 없다
		{
			protocol = new Protocol(makePacket(13,2,2,null))
			throws Exception;
		}
		
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol) throws Exception	//maintype 14, 고지서 출력
	{
		/*
		db에 학생의 학번으로 입사선발자 테이블에 정보가 있는지 검색해본다
		if(정보가 있다)
		{
			//학생의 기숙사비와 식사비를 합쳐서 고지서 내역을 클라이언트로 전송
			protocol = new Protocol(makePacket(14,2,1,고지서내역객체));
		}
		else if(아직 입사선발자를 뽑지 않은 경우)
		{
			//이 경우에는 입사선발자를 뽑지 않았다는 오류 메세지를 뿌려줘야 할거 같음
		}
		else	//입사선발자를 뽑았는데 해당 학생이 입사선발자가 아닌 경우
		{
			protocol = new Protocol(makePacket(14,2,2,null));
			throws Exception;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol) throws Exception	//maintype 15, 결핵진단서 제출
	{
		/*
		클라이언트로부터 받은 결핵진단서 파일=>protocol.getBody()을 db에 저장한다
		if(저장 성공)
		{
			protocol = new Protocol(makePacket(15,2,1,null));
		}
		else	//저장 실패
		{
			protocol = new Protocol(makePacket(15,2,2,null));
			throws Exceptioin;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol) throws Exception	//maintype 21, 선발일정 등록
	{
		/*
		클라이언트가 보낸 선발일정 정보=>protocol.getBody()를 디비에 저장한다
		if(저장 성공)
		{
			protocol = new Protocol(makePacket(21,2,1,null));
		}
		else	//저장 실패
		{
			protocol = new Procotol(makePacket(21,2,2,null));
			throws Exception;
		}
		*/
	}
	//여기부터 수정 해야됨
	/*
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
	*/
	
}
