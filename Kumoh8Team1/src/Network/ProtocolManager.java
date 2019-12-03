package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import tableClass.*;

public class ProtocolManager 
{
	private Protocol protocol;
	
	Socket socket= null;
	InputStream is;
	ObjectInputStream reader;
	OutputStream os;
	ObjectOutputStream writer;
	
	public ProtocolManager(Socket socket) 
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
	
	public void workProtocol(Socket theSocket)
	{
		try
		{
			switch(protocol.getMainType())
			{
			case 1:		//�α���
				Login();
				break;
			case 11:	//�Ի��û
				//do �Ի��û
				break;
			case 12:		//ȣ����ȸ
				//do ȣ����ȸ
				break;
			case 13:		//�Ի��û���� ��ȸ
				//do �Ի��û���� ��ȸ
				break;
			case 14:		//������ ���
				//do ������ ���
				break;
			case 15:		//�������ܼ� ����
				//do �������ܼ� ����
				break;
			case 21:		//�������� ���
				//do �������� ���
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
		catch(Exception e)
		{
			//�� ��ɿ��� ������ �����ڵ� ó��
		}
	}
	
	
	//exception ����� ���� ���� ��������, ���⿡ ���� exception�� ���� Ŭ������ ���� �ȸ��� ����а���
	public void Login()	throws Exception //maintype 1, �α���
	{	
		int subType = protocol.getSubType();
		
		if(subType==1)		//�α��� ���� ��û, ������ ����
		{
			protocol = new Protocol(makeLoginPacket(1,0,null));
			writer.writeObject(protocol);	//�α��� ������ ��û�ϴ� ��Ŷ�� ������.
			writer.flush();
		}
		else if(subType==2)	//�α��� ���� ����, Ŭ���̾�Ʈ�� ����
		{
			reader.readObject();	//�������׼� ������ �о�´�
			if(protocol.getMainType()==1 && subType==1)
			{
				//Ŭ���̾�Ʈ�� �α��� ������ �Է���
				//protocol = new Protocol(makeLoginPacket(2,0, User data))
				writer.writeObject(protocol);
				writer.flush();	//������ ����
			}
		}
		else if(subType==3)	//�α��� ó���� ���� ���
		{
			reader.readObject();
			
			if(protocol.getMainType()==1 && subType==2)
			{
				//���� �α��� ������ db�� �����ؼ� �ش� ������ �´��� �˻�
				/*
				if(�α��� ������ �´� ���)
					protocol = new Protocol(makeLoginPacket(subType, 1));
		
				else	//�α��� ������ ���ų� Ʋ�� ���
					throw Exception;
				
				writer.writeObject(protocol);
				writer.flush()
				*/
			}
		}
	}

	public Protocol makeLoginPacket(int subType,int code, User user)
	{
		if(subType==1)
			protocol = new Protocol(1,subType);
		else if(subType==2)
			protocol = new Protocol(1,subType,user);
		else if(subType==3)
			protocol = new Protocol(1,subType,code);
		return protocol;
	}
	//--------------------------------------------------------------------------------------------------
	public void dormitoryApplication() throws Exception	//maintype 11, �Ի��û 
	{
		switch(protocol.getSubType())
		{
		case 1:		//�Ի��û
			//Ŭ���̾�Ʈ�� �������� �Ի��û ������ ������
			break;
		case 2:
			//Ŭ���̾�Ʈ�� �������� ���� ó���� ���� ����� ����
			break;
		}
	}
	public Protocol makeDormitoryApplicationPakcet(int subType, int code, dormitoryApplication application)
	{
		if(sybType==1)
			protocol = new Protocol()
	}
	public void inquireDormitoryRoom() throws Exception	//maintype 12, ȣ����ȸ
	{
		switch(protocol.getSubType())
		{
		case 1:		//ȣ������ ��û
			//Ŭ���̾�Ʈ�� �������� ȣ�������� ��û��
			break;
		case 2:		//ȣ������
			//db���� ȣ�������� �˻��غ��� Ŭ���̾�Ʈ���� ����
			break;
		}
	}
	public void inquireDormitoryApplication() throws Exception	//maintype 13, �Ի��û���� ��ȸ
	{
		switch(protocol.getSubType())
		{
		case 1:		//�Ի��û���� ��ȸ ��û
			//Ŭ���̾�Ʈ�� �������� �Ի��û���� ��ȸ�� ��û��
			break;
		case 2:		//�Ի��û���� ����
			//db����  �Ի��û������ �˻��ϰ� Ŭ���̾�Ʈ���� ����, ���� ��� �����ڵ� ������
			break;
		}
	}
	public void printDetailedStatement_Bill() throws Exception	//maintype 14, ������ ���
	{
		switch(protocol.getSubType())
		{
		case 1:		//������ ���� ��û	
			//Ŭ���̾�Ʈ�� �������� ������ ���� ��û
			break;
		case 2:		//������ ����
			//Ŭ���̾�Ʈ�� �й����� ��� �ؼ� ������� �ĺ� ���ؼ� Ŭ���̾�Ʈ���� ����
			break;
		}
	}
	public void submissionTuberculosisDiagnosis() throws Exception	//maintype 15, �������ܼ� ����
	{
		switch(protocol.getSubType())
		{
		case 1:		//�������ܼ� ���� ��û
			//Ŭ���̾�Ʈ�� �������� �������ܼ� ���� ��û �޼����� ����
			break;
		case 2:		//�������ܼ� ����
			//Ŭ���̾�Ʈ�� �������� �������ܼ� ������ ������ ������ db�� �����Ѵ�
			break;
		}
	}
	
	public void enrollSelectionSchedule() throws Exception	//maintype 21, �������� ���
	{
		switch(protocol.getSubType())
		{
		case 1:		//��������
			//Ŭ���̾�Ʈ�� �������� �������� ��� ������ ������
			break;
		case 2:		//���˺��� ��� ó�� ���
			//������ ���� ������ db�� �����Ѵ�, ���� ����� Ŭ���̾�Ʈ�� ����
			break;
		}
	}
	
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
	
	
}
