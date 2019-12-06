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
		}//end of try
		/*catch(����� ���� ����)
		{
			������ ���� ��Ŷ�� ���� ->finally���� Ŭ���̾�Ʈ�� ����
			protocol.setBody(Exception ��ü);
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
	public void Login(Protocol protocol) throws Exception //maintype 1, �α���
	{	
		//���� �α��� ������ db�� �����ؼ� �ش� ������ �´��� �˻�
			/*
			if(�α��� ������ �´� ���)
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
		
			else	//�α��� ������ ���ų� Ʋ�� ���
			{
				protocol = new Protocol(protocol.makePacket(1,2,1,null));
				throw Exception;
			}	
			 */
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication(Protocol protocol) throws Exception	//maintype 11, �Ի��û 
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
			throws Exception;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryRoom(Protocol protocol) throws Exception	//maintype 12, ȣ����ȸ
	{
		/*
		db���� ȣ�������� �˻��غ���
		if(������ �ִ�)
		{
			//�ش� ������ ȣ������ ��ü�� ��´�
			protocol = new Protocol(makePacket(12,2,1,ȣ��������ü));	//code�� �ȽἭ 0���� �ʱ�ȭ�ߴ�
		}
		else 	//����������
		{
			protocol = new Protocol(makePacket(12,2,2,null));
			throws Exception;
		}
		*/	
	}
	//--------------------------------------------------------------------------------------------------
	public void inquireDormitoryApplication(Protocol protocol) throws Exception	//maintype 13, �Ի��û���� ��ȸ
	{
		/*
		db���� �л��� �й����� �Ի��û������ ��ȸ�غ���
		if(������ �ִ�)
		{
			//�ش� ������ �Ի��û���� ��ü�� ��´�
			 protocol = new Protocol(makePacket(13,2,1,�Ի��û������ü));
		}
		else	//������ ����
		{
			protocol = new Protocol(makePacket(13,2,2,null))
			throws Exception;
		}
		
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void printDetailedStatement_Bill(Protocol protocol) throws Exception	//maintype 14, ������ ���
	{
		/*
		db�� �л��� �й����� �Ի缱���� ���̺��� ������ �ִ��� �˻��غ���
		if(������ �ִ�)
		{
			//�л��� ������� �Ļ�� ���ļ� ������ ������ Ŭ���̾�Ʈ�� ����
			protocol = new Protocol(makePacket(14,2,1,������������ü));
		}
		else if(���� �Ի缱���ڸ� ���� ���� ���)
		{
			//�� ��쿡�� �Ի缱���ڸ� ���� �ʾҴٴ� ���� �޼����� �ѷ���� �Ұ� ����
		}
		else	//�Ի缱���ڸ� �̾Ҵµ� �ش� �л��� �Ի缱���ڰ� �ƴ� ���
		{
			protocol = new Protocol(makePacket(14,2,2,null));
			throws Exception;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void submissionTuberculosisDiagnosis(Protocol protocol) throws Exception	//maintype 15, �������ܼ� ����
	{
		/*
		Ŭ���̾�Ʈ�κ��� ���� �������ܼ� ����=>protocol.getBody()�� db�� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(makePacket(15,2,1,null));
		}
		else	//���� ����
		{
			protocol = new Protocol(makePacket(15,2,2,null));
			throws Exceptioin;
		}
		*/
	}
	//--------------------------------------------------------------------------------------------------
	public void enrollSelectionSchedule(Protocol protocol) throws Exception	//maintype 21, �������� ���
	{
		/*
		Ŭ���̾�Ʈ�� ���� �������� ����=>protocol.getBody()�� ��� �����Ѵ�
		if(���� ����)
		{
			protocol = new Protocol(makePacket(21,2,1,null));
		}
		else	//���� ����
		{
			protocol = new Procotol(makePacket(21,2,2,null));
			throws Exception;
		}
		*/
	}
	//������� ���� �ؾߵ�
	/*
	public void enrollDormitoryCost() throws Exception	//maintype 22, ��Ȱ�� ���� �� �޽ĺ� ���
	{
		switch(protocol.getSubType())
		{
		case 1:		//��Ȧ�� ���� �� �޽ĺ� ����
			//Ŭ���̾�Ʈ�� �������� ��Ȧ�� ���� �� �޽ĺ� ������ ������
			break;
		case 2:		//��Ȱ�� ���� �� �޽ĺ� ���� ��� ���
			//������ ���� ������ db�� �����Ѵ�, ���� ����� Ŭ���̾�Ʈ�� ����
			break;
		}
	}
	public void enrollFinalSelectedStudent() throws Exception	//maintype 23, �Ի��� ���(����)
	{
		switch(protocol.getSubType())
		{
		case 1:		//�Ի��� ��� ��û
			// Ŭ���̾�Ʈ�� ������ �Ի��� ��� ��û �޼����� ����
			break;
		case 2:		//�Ի��� ��� ó�� ���
			//������ �Ի� ������ ���̺��� ��ȸ�� ���ΰ� �Ϸ�� �л����� �Ի��ڷ� �����(��� ���� ������Ʈ)
			break;
		}
	}
	public void inquireFinalSelectedStudent() throws Exception	//maintype 24, �Ի��� ��ȸ
	{
		switch(protocol.getSubType())
		{
		case 1:		// �Ի��� ��ȸ ��û
			//Ŭ���̾�Ʈ�� �������� �Ի��� ��ȸ�� ��û��
			break;
		case 2:		//�Ի��� ��� ����
			//������ �Ի缱������ ��Ͽ��ΰ� �����հ��� �л����� ��ȸ�ؼ� Ŭ���̾�Ʈ�� ����
			break;
		}
	}
	*/
	
}