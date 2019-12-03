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
			case 1:		//�α���
				//do �α���
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
			
		}
		}
		catch(Exception e)
		{
			
		}
	}
	
	//exception ����� ���� ���� ��������, ���⿡ ���� exception�� ���� Ŭ������ ���� �ȸ��� ����а���
	public void Login()	throws Exception //maintype 1, �α���
	{	
		switch(protocol.getSubType())
		{
		case 1:		//�α��� ���� ��û
			//������ client���� �α��� ���� ��û�ϴ� packet�� ����
			break;
		case 2:
			//Ŭ���̾�Ʈ�� �������� �α��� ���� �����͸� ����
			break;
		case 3:
			//�α��� ������ �´��� �˻�
			break;
		}
	}
	
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
	
	
}
