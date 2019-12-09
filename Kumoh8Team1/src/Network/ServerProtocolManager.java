package Network;

import tableClass.*;
import connectionDB.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class ServerProtocolManager 
{
	private Socket socket= null;
	private InputStream is;
	private ObjectInputStream reader;
	private OutputStream os;
	private ObjectOutputStream writer;
	private DBManager dbManager=null;
	private int program_code_number = 0;
	private int year = 2019;
	private int semester = 2;
	
	public ServerProtocolManager(Socket socket) 
	{
		try
		{
			this.socket = socket;
			is = socket.getInputStream();
			reader = new ObjectInputStream(is);
			os = socket.getOutputStream();
			writer = new ObjectOutputStream(os);
			dbManager = new DBManager("root", "3306");
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
	}
	
	public void finalize() throws IOException, ClassNotFoundException, InterruptedException
	{
		is.close();
		os.close();
		socket.close();
	}
	public void workProtocol()
	{
		Protocol protocol = new Protocol();
		try
		{
			protocol = (Protocol)reader.readObject();
			if(protocol.getSubType()==1)
			{	
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
					enrollDormitoryCost(protocol);
					break;
				case 23:		//입사자 등록
					enrollFinalSelectedStudent(protocol);
					break;
				case 24:		//입사자 조회
					inquireFinalSelectedStudent(protocol);
					break;
				case 25:		//입사선발자 결과등록
					//do 입사선발자 결과등록
					break;
				case 26:		//결핵진단서 제출확인
					TuberculosisDiagnosisSubmitter(protocol);
					break;
				case 27:
					uploadTuberculosisDiagnosis(protocol);
					break;
			
			}//end of switch
			}
		}//end of try
		catch(SQLException d)
		{
			d.getStackTrace();
			protocol.setBody("오류가 발생했습니다");
		}
		catch(ClassNotFoundException c)
		{
			c.getStackTrace();
			protocol.setBody("오류가 발생했습니다");
		}
		catch(IOException e)
		{
			e.getStackTrace();
			protocol.setBody("오류가 발생했습니다");
		}
		finally
		{
			try
			{
				writer.writeObject(protocol);	//처리 결과를 클라이언트에게 보냄
				writer.flush();
				writer.reset();
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
		}
}	//asdf
	
	
	//현재 구조에서 동작 함수에서 프로토콜의 값을 바꾸는데, call by reference로 작동할지 헷갈린다! ->확인해볼것
	
	//exception 사용자 정의 예외 만들어야함, 여기에 적은 exception은 예외 클래스를 아직 안만들어서 적어둔거임
	public void Login(Protocol protocol) throws SQLException //maintype 1, 로그인
	{	
		dbManager.loginCheck(protocol, (User)protocol.getBody());
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol)	//maintype 11, 입사신청 
	{	if(protocol.getSubType()==1)
			dbManager.checkDormitoryApplication(protocol);
		else if(protocol.getSubType()==3)
			dbManager.insertDormitoryApplication(protocol, (dormitoryApplication)protocol.getBody());
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol)	//maintype 12, 호실조회
	{
		/*
		db에서 호실정보를 검색해본다
		if(정보가 있다)
		{
			//해당 정보를 호실정보 객체에 담는다
			protocol = new Protocol(12,2,1,호실정보객체);	//code는 안써서 0으로 초기화했다
		}
		else 	//정보가없다
		{
			protocol = new Protocol((12,2,2,null);
			throws new ServerException("해당 정보 없습니다");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol)	//maintype 13, 입사신청내역 조회
	{
		/*
		db에서 학생의 학번으로 입사신청내역을 조회해본다
		if(정보가 있다)
		{
			//해당 정보를 입사신청내역 객체에 담는다
			 protocol = new Protocol(13,2,1,입사신청내역객체);
		}
		else	//정보가 없다
		{
			protocol = new Protocol(13,2,2,null)
			throws new ServerException("해당 정보가 없습니다")
		}
		
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol)	//maintype 14, 고지서 출력
	{
		/*
		db에 학생의 학번으로 입사선발자 테이블에 정보가 있는지 검색해본다
		if(정보가 있다)
		{
			//학생의 기숙사비와 식사비를 합쳐서 고지서 내역을 클라이언트로 전송
			protocol = new Protocol(14,2,1,고지서내역객체);
		}
		else if(아직 입사선발자를 뽑지 않은 경우)
		{
			//이 경우에는 입사선발자를 뽑지 않았다는 오류 메세지를 뿌려줘야 할거 같음
			 throws new ServerException("입사선발이 되지 않은 상태입니다");
		}
		else if(입사선발자가 뽑힘 && 클라이언트가 입사 선발자가 아님)
		{
			protocol = new Protocol(14,2,2,null);
			throws new ServerException("해당 정보가 없습니다");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol)	//maintype 15, 결핵진단서 제출
	{
		/*
		클라이언트로부터 받은 결핵진단서 파일=>protocol.getBody()을 db에 저장한다
		if(저장 성공)
		{
			protocol = new Protocol(15,2,1,null);
		}
		else	//저장 실패
		{
			protocol = new Protocol(15,2,2,null);
			throws new ServerException("저장에 실패 했습니다");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol)//maintype 21, 선발일정 등록
	{
		program_code_number += 1;
		SelectionSchedule schedule = (SelectionSchedule)protocol.getBody();
		schedule.setYear(year);
		schedule.setSemester(semester);
		dbManager.insertSchedule(protocol, schedule);
		
		/*
		클라이언트가 보낸 선발일정 정보=>protocol.getBody()를 디비에 저장한다
		if(저장 성공)
		{
			protocol = new Protocol(21,2,1,null);
		}
		else	//저장 실패
		{
			protocol = new Procotol(21,2,2,null);
			throws new ServerException("저장에 실패 했습니다");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollDormitoryCost(Protocol protocol)	//maintype 22, 생활관 사용료 및 급식비 등록
	{
		dbManager.insertDormitoryCost(protocol);
		//클라이언트가 보낸 정보를 db에 저장한다
		/*
		if(저장 성공)
		{
			protocol = new Protocol(22,2,1,null);
		}
		else	//저장 실패
		{
			protocol = new Protocol(22,2,2,null);
			throws new ServerException("저장에 실패 했습니다");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollFinalSelectedStudent(Protocol protocol)	//maintype 23, 입사자 등록(최종)
	{
		dbManager.enrollJoiner(protocol);
		
		//db에 접속해 입사선발자테이블에 받아와 생활관비를 납부했는지 결핵진단서를 제출했는지 검사하고 검사한 학생들에 대해서 등록 상태를 업데이트 시켜줌
		//-> 입사자 목록을 list로 연결시켜 db 접속 함수에서 리턴시켜서 if문에서 사용
		//Protocol[] ptList = new Protocol[50];	//실제로 배열 크기는 list의 크기임
		/*
		if(처리 성공)
		{
			for(int i,list.next() !=true, i++)
			{
				ptList[i] = new Protocol(23, 2, list[i])	//개념상으로만 이렇게 적은것
			}
		}
		else	//처리 실패
		{
			protocol = new Protocol(23,2, null);
			throws new ServerException("처리 실패 했습니다);
		}
		 */
		//return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireFinalSelectedStudent(Protocol protocol)	//maintype 24, 입사자 조회
	{
		dbManager.joinerCheck(protocol);
		//db에 접속해 입사선발자테이블에 등록 여부가 o인 학생들의 목록을 뽑아와서 list에 연결시킨다
		//Protocol[] ptList = new Protocol[50];	//실제로 배열 크기는 list의 크기임
		/*
		for(int i,list.next() !=true, i++)
		{
			ptList[i] = new Protocol(24, 2, list[i])	//개념상으로만 이렇게 적은것
		}
		
		*/
		//return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectedStudentResult(Protocol protocol)	//maintype 25, 입사선발자 결과등록
	{
		//입사선발자 선발 알고리즘 필요!
		//따로 함수를 만들어서 입사신청한 목록을 땡겨와서 거기서 list에 저장해서 알고리즘대로 짤라서 입사선발자 테이블에 등록
		/*
		if(결과등록 성공)
		{
			protocol = new Protocol(25, 2,1,null);
		}
		else	//결과등록 실패
		{
			protocol = new Protocol(25,2,2,null);
			throws 
		}
		
		 */
	}
	
	public void TuberculosisDiagnosisSubmitter(Protocol protocol)	//maintype 26, 결핵진단서 제출확인
	{
		dbManager.checkTuberculosisDiagnosis(protocol);
	}
	
	public void uploadTuberculosisDiagnosis(Protocol protocol)		//maintype 27, 결핵진단서 업로드
	{
		if(protocol.getSubType() == 1)
			dbManager.dicisionTuberculosisDiagnosisSubmit(protocol);
		else if(protocol.getSubType() == 3)
			;
	}
	
}
