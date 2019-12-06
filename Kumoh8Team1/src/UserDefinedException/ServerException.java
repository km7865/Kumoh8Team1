package UserDefinedException;

/*
 * unchecked ����
 * �����Ͻ� üũ�ϴ� ���� x
 */

public class ServerException extends RuntimeException	//unchecked ���� (�����Ͻ� )
{
	//serialize id�� �����϶�� ��� �ߴµ� ����� ���ص� �Ǵ°� �ƴѰ�
	private String content;			// �ڵ庰 �� ����
	
	public ServerException()
	{
		content = null;
	}
	public ServerException(String content)
	{
		this.content = content;
	}
	
	public String getContent() {return content;}
	
	public void setContent(String content) {this.content = content;}
}
