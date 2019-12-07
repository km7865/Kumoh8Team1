package UserDefinedException;

/*
 * unchecked 에외
 * 컴파일시 체크하는 예외 x
 */

public class ServerException extends RuntimeException	//unchecked 예외 (컴파일시 )
{
	//serialize id를 설정하라고 경고 뜨는데 여기는 안해도 되는거 아닌감
	private String content;			// 코드별 상세 내용
	
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
