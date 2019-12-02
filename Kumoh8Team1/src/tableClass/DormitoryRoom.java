package tableClass;
//생활관 호실 테이블
public class DormitoryRoom
{
	private String dormitoryCode;	//��Ȱ���з��ڵ�, not null
	private String roomCode;		//ȣ���ڵ�, not null
	private String bedCode;			//ħ���ȣ, A,B,C,D ,not nulll
	private String assignmentState;	//��������, O,X, not null	-> boolean���� �ص� ������ ����
	
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
