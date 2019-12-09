package tableClass;

import java.io.Serializable;
import java.util.Comparator;

//생활관호실 테이블
public class DormitoryRoom implements Serializable
{
	private static final long serialVersionUID = 8L;
	private String dormitoryCode;	//생활관분류코드, not null
	private String roomCode;		//호실코드, not null
	private String bedCode;			//침대번호, not null
	private String assignmentState;	//배정상태, O,X, not null
	
	public DormitoryRoom()
	{
		dormitoryCode = null; roomCode = null;
		bedCode = null; assignmentState = "X";
	}
	public DormitoryRoom(String dormitoryCode, String roomCode)
	{
		this.dormitoryCode = dormitoryCode; this.roomCode = roomCode;
		assignmentState = "X";
	}
	public DormitoryRoom(String dormitoryCode, String roomCode, String bedCode)
	{
		this.dormitoryCode = dormitoryCode; this.roomCode = roomCode;
		this.bedCode= bedCode; assignmentState = "X";
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