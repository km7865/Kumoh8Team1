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
				case 1:		//�α���
					Login(protocol);
					break;
				case 2:      //�α��� �� �л��޴����� ���� ��ȸ ��û
		            inquireSchedule(protocol);
		            break;
				case 11:	//�Ի��û
					dormitoryApplication(protocol);
					break;
				case 12:		//ȣ����ȸ
					inquireDormitoryRoom(protocol);
					break;
				case 13:		//�Ի��û���� ��ȸ
					inquireDormitoryApplication(protocol);
					break;
				case 14:		//������ ���
					printDetailedStatement_Bill(protocol);
					break;
				case 15:		//�������ܼ� ����
					submissionTuberculosisDiagnosis(protocol);
					break;
				case 21:		//�������� ���
					enrollSelectionSchedule(protocol);
					break;
				case 22:		//��Ȱ�� ���� �� �޽ĺ� ���
					enrollDormitoryCost(protocol);
					break;
				case 23:		//�Ի��� ���
					enrollFinalSelectedStudent(protocol);
					break;
				case 24:		//�Ի��� ��ȸ
					inquireFinalSelectedStudent(protocol);
					break;
				case 25:		//�Ի缱���� ������
					enrollSelectedStudentResult(protocol);
					break;
				case 26:		//�������ܼ� ����Ȯ��
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
			protocol.setBody("������ �߻��߽��ϴ�");
		}
		catch(ClassNotFoundException c)
		{
			c.getStackTrace();
			protocol.setBody("������ �߻��߽��ϴ�");
		}
		catch(IOException e)
		{
			e.getStackTrace();
			protocol.setBody("������ �߻��߽��ϴ�");
		}
		finally
		{
			try
			{
				writer.writeObject(protocol);	//ó�� ����� Ŭ���̾�Ʈ���� ����
				writer.flush();
				writer.reset();
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
		}
}	
	
	//exception ����� ���� ���� ��������, ���⿡ ���� exception�� ���� Ŭ������ ���� �ȸ��� ����а���
	public void Login(Protocol protocol) throws SQLException //maintype 1, �α���
	{	
		dbManager.loginCheck(protocol, (User)protocol.getBody());
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireSchedule(Protocol protocol) 
	   {
	      dbManager.inquireSchedule(protocol);
	   }
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol)	//maintype 11, �Ի��û 
	{	if(protocol.getSubType()==1)
			dbManager.checkDormitoryApplication(protocol);
		else if(protocol.getSubType()==3)
			dbManager.insertDormitoryApplication(protocol, (dormitoryApplication)protocol.getBody());
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol)	//maintype 12, ȣ����ȸ
	{
		dbManager.roomCheck(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol)	//maintype 13, �Ի��û���� ��ȸ
	{
		dbManager.inquireDormitoryApplication(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol)	//maintype 14, ������ ���
	{
		dbManager.printBill(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol)	//maintype 15, �������ܼ� ����
	{
		String enrollNumber = (String)protocol.getBody();
		String filename = "C:\\Users\\82109\\source\\" + enrollNumber + ".jpg";              //String filename = "test.mp4"; //������ ���� �̸�
        
        try {
            ServerSocket server_t = new ServerSocket(5001);
            System.out.println("This server is listening... (Port: " + 5001  + ")");
            Socket socket_t = server_t.accept();  //���ο� ���� ���� ���� �� accept���
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
	public void enrollSelectionSchedule(Protocol protocol)//maintype 21, �������� ���
	{
		program_code_number += 1;
		SelectionSchedule schedule = (SelectionSchedule)protocol.getBody();
		schedule.setYear(year);
		schedule.setSemester(semester);
		dbManager.insertSchedule(protocol, schedule);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollDormitoryCost(Protocol protocol)	//maintype 22, ��Ȱ�� ���� �� �޽ĺ� ���
	{
		dbManager.insertDormitoryCost(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollFinalSelectedStudent(Protocol protocol)	//maintype 23, �Ի��� ���(����)
	{
		dbManager.enrollJoiner(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireFinalSelectedStudent(Protocol protocol)	//maintype 24, �Ի��� ��ȸ
	{
		dbManager.joinerCheck(protocol);
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectedStudentResult(Protocol protocol)	//maintype 25, �Ի缱���� ������
	{
		dbManager.enroll_test(protocol);
	}
	
	public void TuberculosisDiagnosisSubmitter(Protocol protocol)	//maintype 26, �������ܼ� ����Ȯ��
	{
		dbManager.checkTuberculosisDiagnosis(protocol);
	}
	
	public void uploadTuberculosisDiagnosis(Protocol protocol)		//maintype 27, �������ܼ� ���ε�
	{
		dbManager.dicisionTuberculosisDiagnosisSubmit(protocol);
	}
	
	public void uploadCost(Protocol protocol)
	{
		dbManager.dicisionCostUploadSubmit(protocol);
	}
	
}
