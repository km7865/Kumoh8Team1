package tableClass;

import java.io.Serializable;

//사용자 테이블
public class User implements Serializable
{
	private static final long serialVersionUID = 2L;
	
	private String userID;		//사용자 ID
	private String password;	//password
	private String separaterUser;	//사용자 구분
	private String name;		//성명
	
	public User()
	{
	}
	
	public User(String userID, String password, String separaterUser, String name)
	{
		this.userID= userID; this.password = password; 
		this.separaterUser = separaterUser; this.name = name;
	}
	

	public User(String userID, String password)
	{
		this.userID= userID; this.password = password; 
	}
	
	public User(String separaterUser)
	{
		this.separaterUser = separaterUser;
	}
	
	public String getUserID() {return userID;}
	public String getPassword() {return password;}
	public String getSeparaterUser() {return separaterUser;}
	public String getName() {return name;}
	
	public void setUserID(String userID) {this.userID = userID;}
	public void setPassword(String password) {this.password = password;}
	public void setSeparaterUser(String separaterUser) {this.separaterUser = separaterUser;}
	public void setName(String name) {this.name = name;} 
	
}
