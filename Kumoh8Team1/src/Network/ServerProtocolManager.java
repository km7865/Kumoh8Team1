package Network;

import tableClass.*;
import connectionDB.*;
import connectionDB.DBManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
		
		
				switch(protocol.getMainType())
				{
				case 1:		//로그인
					Login(protocol);
					break;
				case 2:      //로그인 후 학생메뉴에서 일정 조회 요청
		            inquireSchedule(protocol);
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
					enrollSelectedStudentResult(protocol);
					break;
				case 26:		//결핵진단서 제출확인
					TuberculosisDiagnosisSubmitter(protocol);
					break;
				case 27:
					uploadTuberculosisDiagnosis(protocol);
					break;
				case 28:
					uploadCost(protocol);
					break;
			
			}//end of switch

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
}	
	
	//exception 사용자 정의 예외 만들어야함, 여기에 적은 exception은 예외 클래스를 아직 안만들어서 적어둔거임
	public void Login(Protocol protocol) throws SQLException //maintype 1, 로그인
	{	
		dbManager.loginCheck(protocol, (User)protocol.getBody());
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireSchedule(Protocol protocol) 
	   {
	      dbManager.inquireSchedule(protocol);
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
		dbManager.roomCheck(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol)	//maintype 13, 입사신청내역 조회
	{
		dbManager.inquireDormitoryApplication(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol)	//maintype 14, 고지서 출력
	{
		dbManager.printBill(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol)	//maintype 15, 결핵진단서 제출
	{
		String enrollNumber = (String)protocol.getBody();
		String filename = "C:\\Users\\82109\\source\\" + enrollNumber + ".jpg";              //String filename = "test.mp4"; //저장할 파일 이름
        
        try {
            ServerSocket server_t = new ServerSocket(5001);
            System.out.println("This server is listening... (Port: " + 5001  + ")");
            Socket socket_t = server_t.accept();  //새로운 연결 소켓 생성 및 accept대기
            InetSocketAddress isaClient = (InetSocketAddress) socket_t.getRemoteSocketAddress();
             
            System.out.println("A client("+isaClient.getAddress().getHostAddress()+
                    " is connected. (Port: " +isaClient.getPort() + ")");
             
            FileOutputStream fos = new FileOutputStream(filename);
            InputStream is = socket_t.getInputStream();
             
            double startTime = System.currentTimeMillis(); 
            byte[] buffer = new byte[10000];
            int readBytes;
            while ((readBytes = is.read(buffer)) != -1) {
                fos.write(buffer, 0, readBytes);
            }      
            
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;
 
            System.out.println("time: " + diffTime+ " second(s)");
             
            is.close();
            fos.close();
            socket_t.close();
            server_t.close();          
            dbManager.updateState_TuberculosisDiagnosis(protocol);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol)//maintype 21, 선발일정 등록
	{
		program_code_number += 1;
		SelectionSchedule schedule = (SelectionSchedule)protocol.getBody();
		schedule.setYear(year);
		schedule.setSemester(semester);
		dbManager.insertSchedule(protocol, schedule);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollDormitoryCost(Protocol protocol)	//maintype 22, 생활관 사용료 및 급식비 등록
	{
		dbManager.insertDormitoryCost(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollFinalSelectedStudent(Protocol protocol)	//maintype 23, 입사자 등록(최종)
	{
		dbManager.enrollJoiner(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireFinalSelectedStudent(Protocol protocol)	//maintype 24, 입사자 조회
	{
		dbManager.joinerCheck(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectedStudentResult(Protocol protocol)	//maintype 25, 입사선발자 결과등록
	{
		dbManager.enroll_test(protocol);
	}
	
	public void TuberculosisDiagnosisSubmitter(Protocol protocol)	//maintype 26, 결핵진단서 제출확인
	{
		dbManager.checkTuberculosisDiagnosis(protocol);
	}
	
	public void uploadTuberculosisDiagnosis(Protocol protocol)		//maintype 27, 결핵진단서 업로드
	{
		dbManager.dicisionTuberculosisDiagnosisSubmit(protocol);
	}
	
	public void uploadCost(Protocol protocol)
	{
		dbManager.dicisionCostUploadSubmit(protocol);
	}
	
}
