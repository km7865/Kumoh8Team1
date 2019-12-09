package connectionDB;

import tableClass.*;
import Network.*;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;


//import com.mysql.cj.xdevapi.Statement;
// jdbc:mysql://192.168.209.250:3306/dorm
// ī�� 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "192.168.208.38" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private User currentUser;
	private String today;	//���� ��¥
	
	public DBManager(String id, String pw)	//������
	{
		Date date = new Date();
		today = date.toString();
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.toString();
		
		try{
			// 1. ����̹� �ε�
			// ����̹� �������̽��� ������ Ŭ������ �ε�
			// mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
			// mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
			// ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
			//			String dbID, dbPW;
			//			Scanner scan = new Scanner(System.in);
			//			
			//			System.out.print("db manager id :");
			//			dbID = scan.nextLine();
			//			System.out.print("db manager password :");
			//			dbPW = scan.nextLine();		//db manager ������ id�� pw�� �Է¹޾Ƽ� �������� db�� �α����Ѵ�

			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, id, pw);
			stmt = conn.createStatement();

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException
	{
		try {
			//�ڿ� ��ȯ
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (conn.isClosed())
				conn.close();
		}
	}
	////////////////////////////////////////////////////////////////////////////
	//���

	public void loginCheck(Protocol protocol, User user) throws SQLException
	{
		String loginId = user.getUserID();
		String loginPw = user.getPassword();

		currentUser =user;	//db manager���� ���� client�� ������ �����ϰ� �ִ´�
		
		String SQL = "SELECT * FROM dorm.�����";
		rs = stmt.executeQuery(SQL);

		//����� ���̺��� ��� ID �˻� Ȥ�� ��ġ�ϴ� ID�� �ִٸ� PW ��ġ Ȯ�� 
		while(rs.next()) { 	
			if (loginId.equals(rs.getString("�����ID"))) 
			{	//���̵� �´� ���
				if (loginPw.equals(rs.getString("password"))) 
				{
					System.out.println("�α��μ���");
					
					if(rs.getString("����ڱ���").equals("1") == true)
					{
						String st_number = rs.getString("�����ID");
						SQL = "SELECT * FROM dorm.�л� where �й� = " + st_number;
						rs = stmt.executeQuery(SQL);
						
						rs.next();
						Student student = new Student(rs.getString("���б���"), rs.getString("�й�"), 
								rs.getString("����"), rs.getString("�ֹε�Ϲ�ȣ"));
						
						student.setGender(rs.getString("����"));
						student.setDepartmentName(rs.getString("�а���"));
						student.setGrade(rs.getInt("�г�"));
						student.setStudentAddress(rs.getString("�л��ּ�"));
						student.setStudentPhoneNumber(rs.getString("�л���ȭ��ȣ"));
						protocol.makePacket(1,2,1, student);
						return;
					}
					
					else
					{
						protocol.makePacket(1,2,2, rs.getString("����"));
						return;
					}
				} 
			}
		}
		protocol.makePacket(1,2,3, "�ش����� ����");
		}

	//�Ի��û ��û�� ���� ����
	public void checkDormitoryApplication(Protocol protocol)
	{
		try	//�ش� �б⿡ �л��� ��û ������ �ִ��� Ȯ���Ѵ�
		{
			String sql = "select * from ��û where �й�=" + currentUser.getUserID() + "and �⵵=2019 and �б�=2";
			rs = stmt.executeQuery(sql);
			int count =rs.getRow();
			if(count >=1)	//��û ������ �ϳ��� ������ ��û��û�� ����
				protocol.makePacket(11, 2, 2, "��û������ ����");
			else	//��û ������ ���� ���
				protocol.makePacket(11, 2, 1,null);
		}
		catch(SQLException e)
		{
			e.getStackTrace();
			protocol.makePacket(11,2,2,"��ȸ�� ���� �߻�");
		}
	}
	/**/
	public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)	//�Ի��û
	{
		try
		{
			String sql = "select * from ��û";
			rs =stmt.executeQuery(sql);
			Integer count=rs.getRow()+1;
			String applicationCount="201902".concat(count.toString());	//��û��ȣ ����
			
			sql ="select �й�, convert(sum(case ������� " 
					 + "when \"A+\" then convert(4.5*����,float) when \"A\" then convert(4.0*����,float)"
					  +  "when \"B+\" then convert(3.5*����,float) when \"B\" then convert(3.0*����,float)"
					   + "when \"C+\" then convert(2.5*����,float) when \"C\" then convert(2.0*����,float)"
					  + "when \"D+\" then convert(1.5*����,float) when \"D\" then convert(1.0*����,float)" 
					 +" when \"F\" then convert(0.0*����,float) end) /sum(����),decimal(3,2)) as �������"
					+ "group by �й�;" + "from ����;";
			rs = stmt.executeQuery(sql);
			Double grade = rs.getDouble("�������");
			
			sql = "insert into ��û (��û��ȣ, �й�, �⵵, �б�, ��Ȱ���з��ڵ�, 1�����ĺ񱸺�,2�����ĺ񱸺�,3�����ĺ񱸺�, ����, �Ÿ�������, ����, ����, ��û��, ��û����, 1�⿩��, �Ի缭�ൿ�ǿ���)"
				     + "values(" + applicationCount + ", " + app.getStandbyNumber() + ", 2019, 2, " + app.getDormitoryCode() +", "
				     + app.getMealDivision() + ", " + grade.toString() + "," + "�Ÿ�������" +", " + app.getdormitoryWish() + ", " 
				     + today + ", " + app.getOneYearWhether() + ", yes);";
			rs = stmt.executeQuery(sql);
			protocol.makePacket(11, 2, 1, null);
		}
		catch(SQLException e)
		{
			protocol.makePacket(11, 2, 2, "���� ����");
		}
		
	}
	
	//ȣ����ȸ
	public void roomCheck(Protocol protocol)
	{
		try
		{
			String id = currentUser.getUserID();
			String loginPw = currentUser.getPassword();
			DormitoryRoom dRoom = new DormitoryRoom();  

			String SQL = "SELECT * FROM ��û natural join on �Ի缱���� WHERE �й�=" + id;
			rs = stmt.executeQuery(SQL);
			//����� ���̺��� ��� ID �˻� Ȥ�� ��ġ�ϴ� ID�� �ִٸ� PW ��ġ Ȯ�� 
			if (rs.next()) {
				dRoom.setDormitoryCode(rs.getString("��Ȱ���з��ڵ�"));
				dRoom.setRoomCode(rs.getString("ȣ���ڵ�"));
				dRoom.setBedCode(rs.getString("ħ���ȣ"));
				dRoom.setAssignmentState(rs.getString("��������"));
				protocol.makePacket(12, 2, 0, dRoom);
			} else {
				protocol.makePacket(1,2,2, "�ش����� ����");
			}
		}
		catch(SQLException e)
		{
			protocol.makePacket(12, 2, 2, "��ȸ ����");
		}
	}
	/*
	//�Ի��û���� ��ȸ
	public void inquireDormitoryApplication(Protocol protocol, Student student)
	{
		try
		{
			String sql = "select * frome ��û where �й�=" + student.getStudentId() + "and �⵵=2019 and �б�=2;";
			rs = stmt.executeQuery(sql);
			dormitoryApplication[] array = new dormitoryApplication[rs.getRow()];	//�ش� �й��� �ش��ϴ� ��û��ȣ ����� ������ �迭 ����
			//rs�� �� �ε��� ���� �ϳ���©�� �迭�� ����
			 
			int i=0;
			while(rs.next()) 
			{
				array[i]= new dormitoryApplication(rs.getString("��û��ȣ"), rs.getString("�й�"), rs.getString("��Ȱ���з��ڵ�"));
				array[i].setYear("2019"); array[i].setSemester("2"); array[i].setMealDivision(rs.getString("�ĺ񱸺�"));
				array[i].setGrade(rs.getFloat("����")); array[i].setGrade(rs.getFloat("�Ÿ�������")); array[i].setdormitoryWish(rs.getString("����"));
				array[i].setApplicationDay(rs.getString("��û��")); array[i].setApplicationState(rs.getString("��û����"));
				array[i].setStandbyNumber(rs.getString("����ȣ")); array[i].setOneYearWhether(rs.getString("1�⿩��")); array[i].setAcceptanceOfAgreement("yes"); 
				i++;
			}
			protocol.makePacket(13, 2, 1, array);
		}
		catch(SQLException e)
		{
			protocol.makePacket(13, 2, 1, "��ȸ ����");
		}
	}
	*/
	//�������ܼ� ��� ������ �л��� �Ի缱���� �ƴ��� ��ȸ�غ��� , �ڵ� ��Ȱ�� ����???????��0��????
	public void checkSelectedStudent(Protocol protocol)
	{
		int main = protocol.getMainType();
		int sub = protocol.getSubType();
		try
		{
			String sql = "select * from �Ի缱����, ��û where ��û.��û��ȣ =�Ի缱����.��û��ȣ and ��û.�й� = " + currentUser.getUserID();
			rs = stmt.executeQuery(sql);
			if(rs.getRow()==1)	//�Ի缱���ڰ� �ƴϰ� �ش� ��û��ȣ�� ���ߵ� ���� �ϳ��ϰ�츸
				protocol.makePacket(main,sub+1, 1, null);	//��û ����
			else
				protocol.makePacket(main, sub+1, 2, "�Ի缱�� ����ڰ� �ƴ�");	
		}
		catch(SQLException e)
		{
			protocol.makePacket(main, sub, 2, "��ȸ ����");
		}
	}
	//�������ܼ� ���� ����
	public void storeTuberculosisDiagnosis(Protocol protocol)
	{/*
		try
		{
			String sql = "insert into �������ܼ� (�⵵, �б�, �й�, �����Ͻ�, �����Ͻ�, ���ϰ��, ���ϸ�)"
						+ "values(2019,2," + ((Tuberculosis_certificate)protocol.getBody()).getStd_number() + ","
						+ ((Tuberculosis_certificate)protocol.getBody()).getSbm_date() + ", " + ((Tuberculosis_certificate)protocol.getBody()).getCtf_date() + ","
						+ ((Tuberculosis_certificate)protocol.getBody()).getF_path() + "," + ((Tuberculosis_certificate)protocol.getBody()).get
		
		}
		catch(SQLException e)
		{
			protocol.makePacket(15, 4, 2, "���� ���� ����");
		}*/
	}
		/*
	public void update() //test
	{
		String SQL = "update world.city set name=? where ID=?";
		public void update() //test
		{
			String SQL = "update world.city set name=? where ID=?";

			try {
				pstmt = conn.prepareStatement(SQL);

				pstmt.setString(1, "Corea");
				pstmt.setInt(2, 1);

				int row = pstmt.executeUpdate();
				System.out.println("����� row: "+ row);


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 4. pstmt.set<������Ÿ��>(? ����, ��).setString(), .setInt ... pstmt.setString(1, "1234");
			//pstmt.setString(2, "�޶ѱ�"); pstmt.setString(3, "grasshopper"); 
			// . SQL ������ �����ϰ� ����� ���� - SQL ���� ���� ��, ����� row �� int type ���� int r = pstmt.executeUpdate(); System.out.println("����� row : " + r);
		}

		public void insert()	//test
		{
			String SQL = "insert into city(id, name, countrycode, district, population) values(?,?,?,?,?) ";
			try {
				pstmt = conn.prepareStatement(SQL);

				pstmt.setInt(1, 0);
				pstmt.setString(2, "Korea");
				pstmt.setString(3, "KOR");
				pstmt.setString(4, "seoul");
				pstmt.setInt(5, 26000000);


				//pstmt.executeQuery():select
				//pstmt.executeUpdate():create, insert,update,delete

				int row = pstmt.executeUpdate();
				System.out.println("�� : "+row);
			} catch (SQLException e){
				e.printStackTrace();
			} finally { //�������� �ݴ�� close �� if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } } if (con != null) { try { con.close(); } catch (SQLException e) { e.printStackTrace(); } }
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void getData() //test
		{
			try {
				stmt = conn.createStatement();  	//  SQL�� ����/����(����)/��� ȹ��
				String SQL = "SELECT * FROM dorm.�л�";
				rs = stmt.executeQuery(SQL);
				// int executeUpdate(insert /delete/update)
				//int uCount = stmt.executeUpdate(" ");

				
        	while (rs.next()) { 				/  ������տ��� ���� �� ȹ��
        		Student st = new Student();
        		st.setStudentId(rs.getString( "�й�"));
        		st.setName(rs.getString("����")); 		// ���� Į�� ȹ�� getX  name �Ӽ��� ����

        		System.out.println("�й� : " + st.getStudentId() +  "���� : " + st.getName());
        	}
				 

				//����� ���� ArrayList����

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
*/
		private static void checkWarnings(SQLWarning w) throws SQLException {
			if (w != null) {
				while (w != null) {
					System.out.println("SQL ����:" + w.getSQLState());
					System.out.println("�ڹ� ���� �޼���:" + w.getMessage());
					System.out.println("DBMS ���� �ڵ�:" + w.getErrorCode());
					w.getNextWarning();
				}
			}
		}
	}

