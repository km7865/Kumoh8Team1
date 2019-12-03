package Network;

public class ProtocolManager 
{
	private Protocol protocol;

	public void workProtocol()
	{
		switch(protocol.getMainType())
		{
		case 11:		//로그인
			//do 로그인;
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
	
	public void Login()
	{
		switch(protocol.getSubType())
		{
			case 1:		//로그인 정보 요청
			
		}
	}
}
