package Network;

public class ProtocolManager 
{
	private Protocol protocol;

	public void workProtocol()
	{
		switch(protocol.getMainType())
		{
		case 11:		//�α���
			//do �α���;
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
	
	public void Login()
	{
		switch(protocol.getSubType())
		{
			case 1:		//�α��� ���� ��û
			
		}
	}
}
