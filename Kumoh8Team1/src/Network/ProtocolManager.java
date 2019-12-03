package Network;

public class ProtocolManager 
{
	private Protocol protocol;

	public void workProtocol()
	{
		try
		{
			switch(protocol.getMainType())
			{
			case 1:		//로그인
				//do 로그인
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
			
		}
		}
		catch(Exception e)
		{
			
		}
	}
	
	//exception 사용자 정의 예외 만들어야함, 여기에 적은 exception은 예외 클래스를 아직 안만들어서 적어둔거임
	public void Login()	throws Exception //maintype 1, 로그인
	{	
		switch(protocol.getSubType())
		{
		case 1:		//로그인 정보 요청
			//서버가 client에게 로그인 정보 요청하는 packet을 보냄
			break;
		case 2:
			//클라이언트가 서버에게 로그인 정보 데이터를 보냄
			break;
		case 3:
			//로그인 정보가 맞는지 검사
			break;
		}
	}
	
	public void dormitoryApplication() throws Exception	//maintype 11, 입사신청 
	{
		switch(protocol.getSubType())
		{
		case 1:		//입사신청
			//클라이언트가 서버에게 입사신청 정보를 보낸다
			break;
		case 2:
			//클라이언트가 서버에게 정보 처리에 대한 결과를 보냄
			break;
		}
	}
	
	public void inquireDormitoryRoom() throws Exception	//maintype 12, 호실조회
	{
		switch(protocol.getSubType())
		{
		case 1:		//호실정보 요청
			//클라이언트가 서버에게 호실정보를 요청함
			break;
		case 2:		//호실정보
			//db에서 호실정보를 검색해보고 클라이언트에게 전송
			break;
		}
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
	
	
}
