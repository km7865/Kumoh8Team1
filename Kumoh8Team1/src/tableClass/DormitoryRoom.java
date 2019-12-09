package tableClass;

import java.io.Serializable;
import java.util.Comparator;

//��Ȱ��ȣ�� ���̺�
public class DormitoryRoom implements Serializable
{
	private static final long serialVersionUID = 8L;
	private String dormitoryCode;	//��Ȱ���з��ڵ�, not null
	private String roomCode;		//ȣ���ڵ�, not null
	private String bedCode;			//ħ���ȣ, not null
	private String assignmentState;	//��������, O,X, not null
	
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