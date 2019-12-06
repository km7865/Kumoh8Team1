package UserDefinedException;

/*
 * unchecked ����
 * �����Ͻ� üũ�ϴ� ���� x
 */

public class ServerException extends RuntimeException	//unchecked ���� (�����Ͻ� )
{
	//serialize id�� �����϶�� ��� �ߴµ� ����� ���ص� �Ǵ°� �ƴѰ�
	
	private String exceptionCode;	//�����ڵ�	->����Ÿ��_[1,2,3...]
	private String content;			// �ڵ庰 �� ����
	
	public ServerException()
	{
		exceptionCode = null;
		content = null;
	}
	public ServerException(String exceptionCode, String content)
	{
		this.exceptionCode=exceptionCode;
		this.content = content;
	}
	
	public String getExceptionCode() {return exceptionCode;}
	public String getContent() {return content;}
	
	public void setExceptionCode(String exceptionCode) {this.exceptionCode=exceptionCode;}
	public void setContent(String content) {this.content = content;}
}
