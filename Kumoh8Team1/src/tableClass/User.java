package tableClass;
//����� ���̺�
public class User 
{
	private String userID;		//����� ID
	private String password;	//password
	private String separaterUser;	//����� ����
	private String name;		//����
	
	public User(String userID, String password, String separaterUser, String name)
	{
		this.userID= userID; this.password = password; 
		this.separaterUser = separaterUser; this.name = name;
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
