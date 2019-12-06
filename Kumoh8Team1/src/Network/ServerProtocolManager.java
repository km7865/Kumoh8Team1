package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import tableClass.*;
import UserDefinedException.*;

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
					//do ��Ȱ�� ���� �� �޽ĺ� ���
					break;
				case 23:		//�Ի��� ���
					//do �Ի��� ���
					break;
				case 24:		//�Ի��� ��ȸ
					//do �Ի��� ��ȸ
					break;
				case 25:		//�Ի缱���� ������
					//do �Ի缱���� ������
					break;
				case 26:		//�������ܼ� ����Ȯ��
					//do �������ܼ� ����Ȯ��
					break;
			
			}//end of switch
			}
		}//end of try
		catch(ServerException a)
		{
			protocol.setBody(a);
			//������ ���� ��Ŷ�� ���� ->finally���� Ŭ���̾�Ʈ�� ����
			//protocol.setBody(Exception ��ü);
		}
		catch(ClassNotFoundException b)
		{
			b.getStackTrace();
		}
		catch(IOException c)
		{
			c.getStackTrace();
		}
		catch(Exception d)
		{
			d.getStackTrace();
		}
		finally
		{
			try
			{
				writer.writeObject(protocol);	//ó�� ����� Ŭ���̾�Ʈ���� ����
				writer.flush();
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
			
		}
	}
	
	//���� �������� ���� �Լ����� ���������� ���� �ٲٴµ�, call by reference�� �۵����� �򰥸���! ->Ȯ���غ���
	
	//exception ����� ���� ���� ��������, ���⿡ ���� exception�� ���� Ŭ������ ���� �ȸ��� ����а���
	public void Login(Protocol protocol) throws ServerException //maintype 1, �α���
	{	
		//���� �α��� ������ db�� �����ؼ� �ش� ������ �´��� �˻�
			/*
			if(�α��� ������ �´� ���)
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
		
			else	//�α��� ������ ���ų� Ʋ�� ���
			{
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
				throw new ServerException("�α��ο� ���� �߽��ϴ�.");
			}	
			 */
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol) throws ServerException	//maintype 11, �Ի��û 
	{
		//Ŭ���̾�Ʈ�� �������� �Ի��û������ ������ �޾Ҵ�, �� �Լ� �ۿ���
		//��� �����Ѵ�
		//ó�� ����� Ŭ���̾�Ʈ���� ������.
		/*
		if(ó���� ������ ���)
			protocol = new Protocol(protocol.makePacket(11,2,1,null));
		else 	//ó���� ������ ���
		{
			protocol = new Protocol(makePacket(11,2,2,null));
			throws new ServerException("���忡 ���� �߽��ϴ�.");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol) throws ServerException	//maintype 12, ȣ����ȸ
	{
		/*
		db���� ȣ�������� �˻��غ���
		if(������ �ִ�)
		{
			//�ش� ������ ȣ������ ��ü�� ��´�
			protocol = new Protocol(protocol.makePacket(12,2,1,ȣ��������ü));	//code�� �ȽἭ 0���� �ʱ�ȭ�ߴ�
		}
		else 	//����������
		{
			protocol = new Protocol(makePacket(12,2,2,null));
			throws new ServerException("�ش� ���� �����ϴ�");
		}
		*/	
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol) throws ServerException	//maintype 13, �Ի��û���� ��ȸ
	{
		/*
		db���� �л��� �й����� �Ի��û������ ��ȸ�غ���
		if(������ �ִ�)
		{
			//�ش� ������ �Ի��û���� ��ü�� ��´�
			 protocol = new Protocol(protocol.makePacket(13,2,1,�Ի��û������ü));
		}
		else	//������ ����
		{
			protocol = new Protocol(protocol.makePacket(13,2,2,null))
			throws new ServerException("�ش� ������ �����ϴ�")
		}
		
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol) throws ServerException	//maintype 14, ������ ���
	{
		/*
		db�� �л��� �й����� �Ի缱���� ���̺� ������ �ִ��� �˻��غ���
		if(������ �ִ�)
		{
			//�л��� ������� �Ļ�� ���ļ� ������ ������ Ŭ���̾�Ʈ�� ����
			protocol = new Protocol(protocol.makePacket(14,2,1,������������ü));
		}
		else if(���� �Ի缱���ڸ� ���� ���� ���)
		{
			//�� ��쿡�� �Ի缱���ڸ� ���� �ʾҴٴ� ���� �޼����� �ѷ���� �Ұ� ����
			 throws new ServerException("�Ի缱���� ���� ���� �����Դϴ�");
		}
		else if(�Ի缱���ڰ� ���� && Ŭ���̾�Ʈ�� �Ի� �����ڰ� �ƴ�)
		{
			protocol = new Protocol(protocol.makePacket(14,2,2,null));
			throws new ServerException("�ش� ������ �����ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol) throws ServerException	//maintype 15, �������ܼ� ����
	{
		/*
		Ŭ���̾�Ʈ�κ��� ���� �������ܼ� ����=>protocol.getBody()�� db�� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(protocol.makePacket(15,2,1,null));
		}
		else	//���� ����
		{
			protocol = new Protocol(protocol.makePacket(15,2,2,null));
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol) throws ServerException	//maintype 21, �������� ���
	{
		/*
		Ŭ���̾�Ʈ�� ���� �������� ����=>protocol.getBody()�� ��� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(protocol.makePacket(21,2,1,null));
		}
		else	//���� ����
		{
			protocol = new Procotol(protocol.makePacket(21,2,2,null));
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollDormitoryCost(Protocol protocol) throws ServerException	//maintype 22, ��Ȱ�� ���� �� �޽ĺ� ���
	{
		//Ŭ���̾�Ʈ�� ���� ������ db�� �����Ѵ�
		/*
		if(���� ����)
		{
			protocol = new Protocol(protocol.makePacket(22,2,1,null));
		}
		else	//���� ����
		{
			protocol = new Protocol(protocol.makePacket(22,2,2,null));
			throws new ServerException("���忡 ���� �߽��ϴ�");
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public Protocol[] enrollFinalSelectedStudent(Protocol protocol) throws ServerException	//maintype 23, �Ի��� ���(����)
	{
		//db�� ������ �Ի缱�������̺� �޾ƿ� ��Ȱ���� �����ߴ��� �������ܼ��� �����ߴ��� �˻��ϰ� �˻��� �л��鿡 ���ؼ� ��� ���¸� ������Ʈ ������
		//-> �Ի��� ����� list�� ������� db ���� �Լ����� ���Ͻ��Ѽ� if������ ���
		Protocol[] ptList = new Protocol[50];	//������ �迭 ũ��� list�� ũ����
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
			protocol = new Protocol(protocol.makePacket(23,2, null));
			throws new ServerException("ó�� ���� �߽��ϴ�);
		}
		 */
		return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public Protocol[] inquireFinalSelectedStudent(Protocol protocol) throws ServerException	//maintype 24, �Ի��� ��ȸ
	{
		//db�� ������ �Ի缱�������̺� ��� ���ΰ� o�� �л����� ����� �̾ƿͼ� list�� �����Ų��
		Protocol[] ptList = new Protocol[50];	//������ �迭 ũ��� list�� ũ����
		/*
		for(int i,list.next() !=true, i++)
		{
			ptList[i] = new Protocol(24, 2, list[i])	//��������θ� �̷��� ������
		}
		
		*/
		return ptList;
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectedStudentResult(Protocol protocol) throws ServerException	//maintype 25, �Ի缱���� ������
	{
		//�Ի缱���� ���� �˰��� �ʿ�!
		//���� �Լ��� ���� �Ի��û�� ����� ���ܿͼ� �ű⼭ list�� �����ؼ� �˰����� ©�� �Ի缱���� ���̺� ���
		/*
		if(������ ����)
		{
			protocol = new Protocol(protocol.makePacket(25, 2,1,null));
		}
		else	//������ ����
		{
			protocol = new Protocol(protocol.makePakcet(25,2,2,null));
			throws 
		}
		
		 */
	}
	
}
