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
				case 1:		//�α���
					Login(protocol);
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
					//do �Ի缱���� ������
					break;
				case 26:		//�������ܼ� ����Ȯ��
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
}	//asdf
	
	
	//���� �������� ���� �Լ����� ���������� ���� �ٲٴµ�, call by reference�� �۵����� �򰥸���! ->Ȯ���غ���
	
	//exception ����� ���� ���� ��������, ���⿡ ���� exception�� ���� Ŭ������ ���� �ȸ��� ����а���
	public void Login(Protocol protocol) throws SQLException //maintype 1, �α���
	{	
		dbManager.loginCheck(protocol, (User)protocol.getBody());
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
		/*
		db���� ȣ�������� �˻��غ���
		if(������ �ִ�)
		{
			//�ش� ������ ȣ������ ��ü�� ��´�
			protocol = new Protocol(12,2,1,ȣ��������ü);	//code�� �ȽἭ 0���� �ʱ�ȭ�ߴ�
		}
		else 	//����������
		{
			protocol = new Protocol((12,2,2,null);
			throws new ServerException("�ش� ���� �����ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol)	//maintype 13, �Ի��û���� ��ȸ
	{
		/*
		db���� �л��� �й����� �Ի��û������ ��ȸ�غ���
		if(������ �ִ�)
		{
			//�ش� ������ �Ի��û���� ��ü�� ��´�
			 protocol = new Protocol(13,2,1,�Ի��û������ü);
		}
		else	//������ ����
		{
			protocol = new Protocol(13,2,2,null)
			throws new ServerException("�ش� ������ �����ϴ�")
		}
		
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol)	//maintype 14, ������ ���
	{
		/*
		db�� �л��� �й����� �Ի缱���� ���̺� ������ �ִ��� �˻��غ���
		if(������ �ִ�)
		{
			//�л��� ������� �Ļ�� ���ļ� ������ ������ Ŭ���̾�Ʈ�� ����
			protocol = new Protocol(14,2,1,������������ü);
		}
		else if(���� �Ի缱���ڸ� ���� ���� ���)
		{
			//�� ��쿡�� �Ի缱���ڸ� ���� �ʾҴٴ� ���� �޼����� �ѷ���� �Ұ� ����
			 throws new ServerException("�Ի缱���� ���� ���� �����Դϴ�");
		}
		else if(�Ի缱���ڰ� ���� && Ŭ���̾�Ʈ�� �Ի� �����ڰ� �ƴ�)
		{
			protocol = new Protocol(14,2,2,null);
			throws new ServerException("�ش� ������ �����ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol)	//maintype 15, �������ܼ� ����
	{
		/*
		Ŭ���̾�Ʈ�κ��� ���� �������ܼ� ����=>protocol.getBody()�� db�� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(15,2,1,null);
		}
		else	//���� ����
		{
			protocol = new Protocol(15,2,2,null);
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol)//maintype 21, �������� ���
	{
		program_code_number += 1;
		SelectionSchedule schedule = (SelectionSchedule)protocol.getBody();
		schedule.setYear(year);
		schedule.setSemester(semester);
		dbManager.insertSchedule(protocol, schedule);
		
		/*
		Ŭ���̾�Ʈ�� ���� �������� ����=>protocol.getBody()�� ��� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(21,2,1,null);
		}
		else	//���� ����
		{
			protocol = new Procotol(21,2,2,null);
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollDormitoryCost(Protocol protocol)	//maintype 22, ��Ȱ�� ���� �� �޽ĺ� ���
	{
		dbManager.insertDormitoryCost(protocol);
		//Ŭ���̾�Ʈ�� ���� ������ db�� �����Ѵ�
		/*
		if(���� ����)
		{
			protocol = new Protocol(22,2,1,null);
		}
		else	//���� ����
		{
			protocol = new Protocol(22,2,2,null);
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollFinalSelectedStudent(Protocol protocol)	//maintype 23, �Ի��� ���(����)
	{
		dbManager.enrollJoiner(protocol);
		
		//db�� ������ �Ի缱�������̺� �޾ƿ� ��Ȱ���� �����ߴ��� �������ܼ��� �����ߴ��� �˻��ϰ� �˻��� �л��鿡 ���ؼ� ��� ���¸� ������Ʈ ������
		//-> �Ի��� ����� list�� ������� db ���� �Լ����� ���Ͻ��Ѽ� if������ ���
		//Protocol[] ptList = new Protocol[50];	//������ �迭 ũ��� list�� ũ����
		/*
		if(ó�� ����)
		{
			for(int i,list.next() !=true, i++)
			{
				ptList[i] = new Protocol(23, 2, list[i])	//��������θ� �̷��� ������
			}
		}
		else	//ó�� ����
		{
			protocol = new Protocol(23,2, null);
			throws new ServerException("ó�� ���� �߽��ϴ�);
		}
		 */
		//return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireFinalSelectedStudent(Protocol protocol)	//maintype 24, �Ի��� ��ȸ
	{
		dbManager.joinerCheck(protocol);
		//db�� ������ �Ի缱�������̺� ��� ���ΰ� o�� �л����� ����� �̾ƿͼ� list�� �����Ų��
		//Protocol[] ptList = new Protocol[50];	//������ �迭 ũ��� list�� ũ����
		/*
		for(int i,list.next() !=true, i++)
		{
			ptList[i] = new Protocol(24, 2, list[i])	//��������θ� �̷��� ������
		}
		
		*/
		//return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectedStudentResult(Protocol protocol)	//maintype 25, �Ի缱���� ������
	{
		//�Ի缱���� ���� �˰��� �ʿ�!
		//���� �Լ��� ���� �Ի��û�� ����� ���ܿͼ� �ű⼭ list�� �����ؼ� �˰����� ©�� �Ի缱���� ���̺� ���
		/*
		if(������ ����)
		{
			protocol = new Protocol(25, 2,1,null);
		}
		else	//������ ����
		{
			protocol = new Protocol(25,2,2,null);
			throws 
		}
		
		 */
	}
	
	public void TuberculosisDiagnosisSubmitter(Protocol protocol)	//maintype 26, �������ܼ� ����Ȯ��
	{
		dbManager.checkTuberculosisDiagnosis(protocol);
	}
	
	public void uploadTuberculosisDiagnosis(Protocol protocol)		//maintype 27, �������ܼ� ���ε�
	{
		if(protocol.getSubType() == 1)
			dbManager.dicisionTuberculosisDiagnosisSubmit(protocol);
		else if(protocol.getSubType() == 3)
			;
	}
	
}
