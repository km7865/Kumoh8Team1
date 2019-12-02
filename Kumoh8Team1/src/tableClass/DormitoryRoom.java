package tableClass;

public class DormitoryRoom
{
	private String dormitoryCode;	//생활관분류코드, not null
	private String roomCode;		//호실코드, not null
	private String bedCode;			//침대번호, A,B,C,D ,not nulll
	private String assignmentState;	//배정상태, O,X, not null	-> boolean으로 해도 좋을거 같음
	
	public DormitoryRoom()
	{
		dormitoryCode = null; roomCode = null;
		bedCode = null; assignmentState = null;
	}
	public DormitoryRoom(String dormitoryCode, String roomCode)
	{
		this.dormitoryCode = dormitoryCode; this.roomCode = roomCode;
	}
	public DormitoryRoom(DormitoryRoom some)
	{
		this.dormitoryCode = some.getDormitoryCode();
		this.roomCode = some.getRoomCode();
		this.bedCode = some.getBedCode();
		this.assignmentState = some.getAssignmentState();
	}

	public String getDormitoryCode() {return dormitoryCode;}
	public String getRoomCode() {return roomCode;}
	public String getBedCode() {return bedCode;}
	public String getAssignmentState() {return assignmentState;}

	public void setDormitoryCode(String dormitoryCode) {this.dormitoryCode = dormitoryCode;}
	public void setRoomCode(String roomCode) {this.roomCode = roomCode;}
       public void setBedCode(String bedCode) {this.bedCode = bedCode;}
	public void setAssignmentState(String assignmentState) {this.assignmentState = assignmentState;}
}
