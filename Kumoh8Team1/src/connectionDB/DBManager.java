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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;


//import com.mysql.cj.xdevapi.Statement;
// jdbc:mysql://192.168.209.250:3306/dorm
// ī�� 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "localhost" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private User currentUser;
	private String today;	//���� ��¥
	private int year = 2019;
	private int semester = 2;
	
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
					currentUser = user;
					currentUser.setSeparaterUser(rs.getString("����ڱ���"));
					currentUser.setName(rs.getString("����"));

					if(rs.getString("����ڱ���").equals("1") == true)
					{
						String st_number = rs.getString("�����ID");
						SQL = "SELECT * FROM dorm.�л� where �й� = " + st_number;
						rs = stmt.executeQuery(SQL);
						
						rs.next();
						Student student = new Student(rs.getString("�й�"), rs.getString("����"));
			
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
			System.out.println(currentUser.getUserID());
			String sql = "select * from dorm.��û  where �й�='" + currentUser.getUserID() + "' and �⵵='2019' and �б�='2'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				protocol.makePacket(11, 2, 2, "��û������ ����");
			}
			else {	//��û ������ ���� ���
				protocol.makePacket(11, 2, 1,null);
			}
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
	}
	/**/
	public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)	//�Ի��û
	{
		try
		{
			System.out.println(app.getDormitoryWish1() + " " + app.getMealDivision1());
			Integer count;
			String sql = "select * from ��û";
			rs =stmt.executeQuery(sql);
			if (rs.next()) {
				rs.last();
				count = rs.getRow()+1;
			}
			else
				count = 0;
			String applicationCount="201902".concat(count.toString());	//��û��ȣ ����
			
			
			sql = "select �й�, convert(sum(case ������� when 'A+' then convert(4.5*����,float) when 'A' then convert(4.0*����,float) " 
				+ "  when 'B+' then convert(3.5*����,float) when 'B' then convert(3.0*����,float) " 
				 +"   when 'C+' then convert(2.5*����,float) when 'C' then convert(2.0*����,float) "
				+"	when 'D+' then convert(1.5*����,float) when 'D' then convert(1.0*����,float) "
				  +"  when 'F' then convert(0.0*����,float) end) /sum(����),decimal(3,2)) as ������� "
				+"from ����  where �й� = '" + currentUser.getUserID() + "'" +  " group by �й�";

			rs = stmt.executeQuery(sql);

			rs.next();

			Double grade = rs.getDouble("�������");


			//�Ÿ� ������ ��� �κ� �߰�(currentUser �й����� �л��ּ� ��ȸ�� app ��ü �Ÿ������� ����)
			String address;
			sql = "SELECT �л��ּ� FROM �л�  WHERE �й�='" + currentUser.getUserID() + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
			address = rs.getString("�л��ּ�");
			
			double distancePoint = 0.0;
			if (address.contains("���ֵ�") || address.contains("�︪��")) {
				distancePoint = 0.4;
			} else if (address.contains("����") || address.contains("���") || address.contains("��õ") || address.contains("����")
					|| address.contains("��û") || address.contains("����") || address.contains("����") || address.contains("����")) {
				distancePoint = 0.3;
			} else if (address.contains("����")|| address.contains("�λ�") || address.contains("���") || address.contains("��󳲵�")) {
				distancePoint = 0.2;
			} else if ((address.contains("���ϵ�") && !address.contains("����")) || address.contains("�뱸")) {
				distancePoint = 0.1;
			} 
			app.setDistancePoint(distancePoint);
			app.setFinallyValue(grade + distancePoint);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
			
			sql = "insert into ��û values ('" + applicationCount + "', '" + currentUser.getUserID() +  "', 2019, 2, "; 
			if (app.getMealDivision1() != null)	sql += "'" + app.getMealDivision1() + "',"; else sql += "null,";
			if (app.getMealDivision2() != null)	sql += "'" + app.getMealDivision2() + "',"; else sql += "null,";
			if (app.getMealDivision3() != null)	sql += "'" + app.getMealDivision3() + "',"; else sql += "null,";
			if (app.getMealDivisionYear() != null)	sql += "'" + app.getMealDivisionYear() + "',"; else sql += "null,";
			sql += grade + ", " + distancePoint + ", " + app.getFinallyValue() + ", ";
			if (app.getDormitoryWish1() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWish2() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWish3() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWishYear() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			sql += "'" + today + "', '��û', " + 0 +  ", 'O');";
			
			stmt.executeUpdate(sql);
			
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
				protocol.makePacket(12,2,2, "�ش����� ����");
			}
		}
		catch(SQLException e)
		{
			protocol.makePacket(12, 2, 2, "��ȸ ����");
		}
	}
	/**/
	//�Ի��û���� ��ȸ
	public void inquireDormitoryApplication(Protocol protocol, Student student)
	{
		try
		{
			String sql = "select * from ��û where �й�=" + student.getStudentId() + "and �⵵=2019 and �б�=2;";
			rs = stmt.executeQuery(sql);
			dormitoryApplication[] array = new dormitoryApplication[rs.getRow()];	//�ش� �й��� �ش��ϴ� ��û��ȣ ����� ������ �迭 ����
			//rs�� �� �ε��� ���� �ϳ���©�� �迭�� ����
			 
			int i=0;
			while(rs.next()) 
			{
				array[i]= new dormitoryApplication(rs.getString("��û��ȣ"), rs.getString("�й�"));
				array[i].setYear("2019"); array[i].setSemester("2"); 
				array[i].setMealDivision1(rs.getString("1�����ĺ񱸺�"));array[i].setMealDivision1(rs.getString("2�����ĺ񱸺�"));
				array[i].setMealDivision1(rs.getString("3�����ĺ񱸺�"));array[i].setMealDivision1(rs.getString("1��ĺ񱸺�"));
				array[i].setGrade(rs.getFloat("����")); array[i].setGrade(rs.getFloat("�Ÿ�������")); 
				array[i].setDormitoryWish1(rs.getString("1����"));array[i].setDormitoryWish2(rs.getString("2����"));
				array[i].setDormitoryWish3(rs.getString("1����"));array[i].setDormitoryWishYear(rs.getString("1������"));
				array[i].setApplicationDay(rs.getString("��û��"));array[i].setApplicationState(rs.getString("��û����"));
				array[i].setStandbyNumber(rs.getString("����ȣ"));array[i].setAcceptanceOfAgreement("yes"); array[i].setFinallyValue(rs.getFloat("����") + rs.getFloat("�Ÿ�������")); 
				i++;
			}
			protocol.makePacket(13, 2, 1, array);
		}
		catch(SQLException e)
		{
			protocol.makePacket(13, 2, 1, "��ȸ ����");
		}
	}
	
	//maintype 14, ������ ���
	   public void selectDetailedStatement_Bill(Protocol protocol)
	   {
	      int main = protocol.getMainType();
	      int sub = protocol.getSubType();

	      final String bank = "��������";
	      Random rnd = new Random();
	      String accountNum = "302-" + Integer.toString(rnd.nextInt(1000000000)); // ���¹�ȣ
	      try {

	         Date today = null; // �ý��� ��¥
	         Date to_date = null; // ���� ��¥


	          SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	          today = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(sdfNow.format(new Date()));
	       
	          // �񱳳�¥ ����
	          to_date = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse("20200121 10:00:00");

	         if (today.before(to_date)) {//�ý��� ��¥�� 1��21�� 10�� ������ ���
	            protocol.makePacket(main, sub + 1, 2, "�Ի缱���� ���� ���� �����Դϴ�.");
	         }
	         else{
	            String sql = "select * from �Ի缱����, ��û where ��û.��û��ȣ =�Ի缱����.��û��ȣ and ��û.�й� = " + currentUser.getUserID();
	            rs = stmt.executeQuery(sql);
	            if (rs.getRow() == 1) // �Ի缱���ڰ� �ƴϰ� �ش� ��û��ȣ�� ���ߵ� ���� �ϳ��ϰ�� ��
	            { // ������ ��� �ʿ��� �Ӽ�
	               sql = "select ����, �����ID, �հ� from �����, �Ի缱���� where �����ID=�й� and �й�= " + currentUser.getUserID()
	                     + " and (���λ���='o' or ���λ���='O')";
	               rs = stmt.executeQuery(sql); // sql���� �� rs �� �ű��.

	               String[] array = new String[3];
	               // ResultSet�� ��� ����� Array�� ���
	               array[0] = rs.getString(" ����"); // �̸�
	               array[1] = rs.getString("�����ID");// �й�
	               array[3] = bank;
	               array[4] = accountNum;
	               array[5] = rs.getString("�հ�");// �Աݾ�
	               
	               protocol.makePacket(main, sub + 1, 1, array);
	            } else
	               
	               protocol.makePacket(main, sub + 1, 2, "�ش� ������ �����ϴ�");
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	
	
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
		public void updateState_TuberculosisDiagnosis(Protocol protocol)	//�������ܼ��� �����ߴٴ� ���������� ������ �������ܼ� ���� ���¸� ������Ʈ ��Ų��
		{
			try
			{
				String sql = "update �Ի缱���� set �������ܼ����⿩��=" + "O" + "where �й�=" + currentUser.getUserID();
				rs = stmt.executeQuery(sql);
				protocol.makePacket(15,4,1, "�������ܼ� ���� ����");
			}
			catch(SQLException e)
			{
				e.getStackTrace();
				protocol.makePacket(15, 4, 2, "�������ܼ� ���� ����");
			}/**/
		}
	
	//���⼭���� �����ڸ޴�
	public void insertSchedule(Protocol protocol, SelectionSchedule schedule)
	{
		String sql;
		int cnt = 0;
		
        try {
        	String year = Integer.toString(schedule.getYear());
			String semester = Integer.toString(schedule.getSemester());
        	sql = "select * from dorm.�������� where �⵵='" + year + "' and �б�='" + semester + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next())
				cnt += 1;
			rs.beforeFirst();
			String program_code = year + semester + Integer.toString(cnt);
			String start_date = schedule.getStart_date();
			String end_date = schedule.getEnd_date();
			String content = schedule.getContent();
			
			sql="INSERT INTO dorm.�������� VALUES(" + year + ", " + semester + ", '"  + program_code + "', '" 
					+ start_date + "', '"  + end_date + "', '"  + content + "')";
			stmt.executeUpdate(sql);
			protocol.makePacket(21, 2, 1, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(21, 2, 2, "�������� ����� ���������� �̷������ �ʾҽ��ϴ�.");
		}
        
        
	}
	
	public void insertDormitoryCost(Protocol protocol)	//���Ұ� ���� �� �޽ĺ� ���
	{
		try
		{
			String sql;
			Dormitory_cost cost[] = (Dormitory_cost[])protocol.getBody();
			System.out.println(protocol.getMainType() + " " + protocol.getSubType());
			for(int i = 0; i < cost.length; i++)
			{
				sql = "select �з��ڵ� from dorm.��Ȱ�� where ��Ȱ���� = '" + cost[i].getKind_code() + "'";
				System.out.println(cost[i].getKind_code());
				rs = stmt.executeQuery(sql);
				rs.next();
				cost[i].setKind_code(rs.getString("�з��ڵ�"));
				System.out.println(cost[i].getKind_code());
				sql = "update dorm.��Ȱ���� set ��Ȱ���з��ڵ� = " + cost[i].getKind_code()
						+ ", ������_1�б�=" + cost[i].getMng_cost1() + ", ������_�ϰ���� = " + cost[i].getMng_cost2()
						+ ", ������_2�б� = " + cost[i].getMng_cost3() + ", ������_������� = " + cost[i].getMng_cost4()
						+ ", 7�Ͻĺ�_1�б� = " + cost[i].getSd_food_cost1() + ", 7�Ͻĺ�_�ϰ���� = " + cost[i].getSd_food_cost2()
						+ ", 7�Ͻĺ�_2�б� = " + cost[i].getSd_food_cost3() + ", 7�Ͻĺ�_������� = " + cost[i].getSd_food_cost4()
						+ ", 5�Ͻĺ�_1�б� = " + cost[i].getFd_food_cost1() + ", 5�Ͻĺ�_�ϰ���� = " + cost[i].getFd_food_cost2()
						+ ", 5�Ͻĺ�_2�б� = " + cost[i].getFd_food_cost3() + ", 5�Ͻĺ�_������� = " + cost[i].getFd_food_cost4()
						+ " where ��Ȱ���з��ڵ� = " + cost[i].getKind_code();
					stmt.executeUpdate(sql);
			}
			
			protocol.makePacket(22, 2, 1, "���� ����");
			System.out.println("���强��");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			protocol.makePacket(22, 2, 2, "��Ȱ���� ������ ���� �߻�");
		}
		
	}
	public void enrollJoiner(Protocol protocol)
	{
		String sql = "update �Ի缱���� set ��Ͽ���='O' where ���λ���='O' and �������ܼ����⿩��='O'";
		try {
			stmt.executeUpdate(sql);
			protocol.makePacket(23, 2, 1, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(23, 2, 2, "����� ���������� �̷������ �ʾҽ��ϴ�.");
		}
		
		
	}
	//sdfg
	public void joinerCheck(Protocol protocol)
	{	
		int cnt = 0;
		String sql1 = "select �й�, ��Ȱ���з��ڵ�, ȣ���ڵ�, ħ���ȣ from dorm.�Ի缱���� where ��Ͽ���='O'";
		try {
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				cnt += 1;
			String[][] arr = new String[cnt][5];
			rs.beforeFirst();
			int i = 0;
			while(rs.next())
			{
				arr[i][0] = rs.getString("�й�");			
				arr[i][3] = rs.getString("ȣ���ڵ�");
				arr[i][4] = rs.getString("ħ���ȣ");
				i++;
			}
			i = 0;
			String sql2 = "select ��Ȱ���� from dorm.��Ȱ�� where �з��ڵ� in (select ��Ȱ���з��ڵ� from �Ի缱����"
					+ " where ��Ͽ���='O')";
			rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				arr[i][2] = rs.getString("��Ȱ����");
				i++;
			}
			i = 0;
			sql2 = "select ���� from dorm.�л� where �й� in (select �й� from dorm.�Ի缱���� " + 
					" where ��Ͽ���='O')";
			rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				arr[i][1] = rs.getString("����");
				i++;
			}
			i = 0;
			protocol.makePacket(24, 2, 1, arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(24, 2, 2, "üũ�� ���������� �̷������ �ʾҽ��ϴ�.");
		}
	}
	
	public void checkTuberculosisDiagnosis(Protocol protocol)
	{
		int cnt = 0;
		String sql="select ��û��ȣ, �й�, �������ܼ����⿩�� from dorm.�Ի缱����";
        try {
        	rs = stmt.executeQuery(sql);
        	while(rs.next())
				cnt += 1;
			String[][] arr = new String[cnt][3];
			rs.beforeFirst();
			int i = 0;
			while(rs.next())
			{
				arr[i][0] = rs.getString("��û��ȣ");
				arr[i][1] = rs.getString("�й�");
				arr[i][2] = rs.getString("�������ܼ����⿩��");
				i++;
			}
			
			Arrays.sort(arr, new Comparator<String[]>() {
	            @Override
	            public int compare(final String[] entry1, final String[] entry2) {
	            	if( ((Comparable)entry2[1]).compareTo(entry1[1]) < 0 )
	                    return 1;
	                else
	                    return -1;
	            }
	        });
			protocol.makePacket(26, 2, 1, arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(26, 2, 2, "���⿩�θ� ���������� �������� ���߽��ϴ�.");
		}
	}
	
	public void dicisionTuberculosisDiagnosisSubmit(Protocol protocol)
	{
		String sql="select �й� from dorm.�Ի缱���� where �������ܼ����⿩�� = 'X'";
		String studentNumber = (String)protocol.getBody();
		try {
			rs = stmt.executeQuery(sql);
			if(!rs.next())
				protocol.makePacket(27, 2, 2, "����л��� �ƴմϴ�.");
			else
			{
				rs.first();
				if(studentNumber.equals(rs.getString("�й�")))
					protocol.makePacket(27, 2, 1, null);
				else
					protocol.makePacket(27, 2, 2, "����л��� �ƴմϴ�.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(26, 2, 2, "���⿩�θ� ���������� �������� ���߽��ϴ�.");
		}
	}
	
	//�Ի缱���� ������ �˰���
		//ȣ�� ��ü�� ��Ȱ������ 2���� �迭�� ����
		//��û���̺��� �ش�⵵ �б⿡ �ش��ϴ� ��û ������ �ϴ� ���´�	->arrayList�� �ϴ� ����
		//while������ arraylist�� �ִ� �л����� �Ѹ� �˻��Ѵ�
		//1������ ��Ȱ�� ȣ���� �˻��� ������ �ȵ� ���� �˻��Ѵ�
		//������ �ȵ������� �������� ħ��� ȣ���� ����
		//���� �ش� ��ȭ�ΰ��� ȣ���� ��� ������ �������� 2�������� �Ѿ��
		//2������ ����������� �˻��ϰ�
		//2������ ������ �������� 3�������� �Ѿ��
		//3������ ������ �������� ����ȣ�� �����Ѵ�

		public void enrollSelectedStudent(Protocol protocol)		//�Ի缱���� ������
		{
			String sql;
			ResultSet StudentRs;
			try
			{
				ArrayList<dormitoryApplication> applicationList = new ArrayList<dormitoryApplication>();
				Iterator iterator = applicationList.iterator();	//applicationList�� Ž���� iterator
				
				sql = "select * frome ��û where �⵵=2019 and �б�=2";
				StudentRs = stmt.executeQuery(sql);	//��û�� ��� �л����� ������� ����
				int i=0;
				while(StudentRs.next())	//����row�� ���� ������ arraylist�� �л� ��ü�� ����� �ִ´�
				{
					applicationList.add(new dormitoryApplication(rs.getString("��û��ȣ"), rs.getString("�й�")));
					applicationList.get(i).setMealDivision1(rs.getString("1�����ĺ񱸺�"));applicationList.get(i).setMealDivision1(rs.getString("2�����ĺ񱸺�"));
					applicationList.get(i).setMealDivision1(rs.getString("3�����ĺ񱸺�"));applicationList.get(i).setMealDivision1(rs.getString("1��ĺ񱸺�"));
					applicationList.get(i).setGrade(rs.getFloat("����")); applicationList.get(i).setGrade(rs.getFloat("�Ÿ�������")); 
					applicationList.get(i).setDormitoryWish1(rs.getString("1����"));applicationList.get(i).setDormitoryWish2(rs.getString("2����"));
					applicationList.get(i).setDormitoryWish3(rs.getString("1����"));applicationList.get(i).setDormitoryWishYear(rs.getString("1������"));
					applicationList.get(i).setApplicationDay(rs.getString("��û��"));applicationList.get(i).setApplicationState(rs.getString("��û����"));
					applicationList.get(i).setStandbyNumber(rs.getString("����ȣ"));applicationList.get(i).setAcceptanceOfAgreement("yes"); 
					applicationList.get(i).setFinallyValue(rs.getFloat("����") + rs.getFloat("�Ÿ�������")); 
					i++;
				}
				Collections.sort(applicationList);	//arrayList�� �л����� ���������� �����Ѵ�
			
				ArrayList<DormitoryRoom> puleum1 = new ArrayList<DormitoryRoom>();	//Ǫ��
				Iterator <DormitoryRoom>p1 = puleum1.iterator();
				ArrayList<DormitoryRoom> puleum2 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p2 = puleum2.iterator();
				ArrayList<DormitoryRoom> puleum3 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p3 = puleum3.iterator();
				ArrayList<DormitoryRoom> puleum4 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p4 = puleum4.iterator();
				ArrayList<DormitoryRoom> oleum1 = new ArrayList<DormitoryRoom>();	//����
				Iterator <DormitoryRoom>o1 = oleum1.iterator();
				ArrayList<DormitoryRoom> oleum2 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>o2 = oleum2.iterator();
				ArrayList<DormitoryRoom> oleum3 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>o3 = oleum3.iterator();
				ArrayList<DormitoryRoom> sinpyeongM = new ArrayList<DormitoryRoom>();	//����� ����
				Iterator <DormitoryRoom>SM = sinpyeongM.iterator();
				ArrayList<DormitoryRoom> sinpyeongF = new ArrayList<DormitoryRoom>();	//����� ����
				Iterator <DormitoryRoom>SF = sinpyeongF.iterator();
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"1\"";	//Ǫ��1��
				rs = stmt.executeQuery(sql);		//��Ȱ�� ȣ���� �������°� �ȵȰ͵� �ϴ� ����
				while(rs.next())
				{
					puleum1.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"2\"";	//Ǫ��2��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum2.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"3\"";	//Ǫ��3��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum3.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"4\"";	//Ǫ��4��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum4.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"5\"";	//����1��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum1.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"6\"";	//����2��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum2.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"7\"";	//����3��
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum3.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"8\"";	//������
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					sinpyeongM.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				
				sql = "select * from ��Ȱ��ȣ�� where ��������=\"X\" and ��Ȱ���з��ڵ�=\"9\"";	//������
				rs = stmt.executeQuery(sql);
			
				while(rs.next())
				{
					sinpyeongF.add(new DormitoryRoom(rs.getString("��Ȱ���з��ڵ�"), rs.getString("ȣ���ڵ�"), rs.getString("ħ���ȣ")));
				}
				
				ArrayList<ArrayList<DormitoryRoom>> roomList = new ArrayList<ArrayList<DormitoryRoom>>();
				roomList.add(puleum1);roomList.add(puleum2);roomList.add(puleum3);roomList.add(puleum4); 
				roomList.add(oleum1);roomList.add(oleum2);roomList.add(oleum3);roomList.add(sinpyeongM);roomList.add(sinpyeongF);

				i=0;	//��û index�� ��Ÿ�� ����
				int stanbyNumber=1;
				int FoodCost=0;
				while(StudentRs.next())		//list�� ���� �л��� ���� ������
				{
					int j=0;
					String Dormitory = applicationList.get(i).getDormitoryWishYear();	//1�������� �������� �����Ѵ�
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1������ �ش��ϴ� ��Ȱ���� arraylist�� Ž��
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//�������°� X�� ���̸� �л����� ����
						{
							if(applicationList.get(i).getMealDivisionYear()=="5�Ͻ�")	//5����� ���
								FoodCost =YearFiveFoodCost(applicationList.get(i).getDormitoryWishYear());
							else if(applicationList.get(i).getMealDivisionYear()=="7�Ͻ�")
								FoodCost = YearSevenFoodCost(applicationList.get(i).getDormitoryWishYear());
							
							sql = "update ��Ȱ��ȣ�� set ��������=\"O\" where ��Ȱ���з��ڵ� = " + Dormitory + "and ȣ���ڵ� = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//��Ȱ��ȣ�� �������� ������Ʈ
							
							//�Ի缱���ڿ� �ش��л����� insert		
							sql = "insert into �Ի缱���� (��û��ȣ, �й�, ��Ȱ���з��ڵ�,ȣ���ڵ�,ħ���ȣ,������,�ĺ�,�հ�,���λ���,��Ͽ���,�������ܼ����⿩��, 1�⿩��)"
									+ " values(" +StudentRs.getString("�й�") + "," + StudentRs.getString("�й�") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + "," +Integer.toString(YearManageCost(Dormitory)) 
									+ ", " +Integer.toString(FoodCost)+ ", " + Integer.toString(YearManageCost(Dormitory)+ FoodCost) +","  
									+ "X, X, X, O;";
							stmt.executeUpdate(sql);
							j=1;
							i++;
							//��û ���̺� ��û���� update����ߵ�
							break;
						}
					}
					if(j==1)
						continue;	//�л����� ������������ ���� �л� �������� �Ѿ��
					
					
					Dormitory = applicationList.get(i).getDormitoryWish1();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1������ �ش��ϴ� ��Ȱ���� arraylist�� Ž��
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//�������°� X�� ���̸� �л����� ����
						{
							
							sql = "update ��Ȱ��ȣ�� set ��������=\"O\" where ��Ȱ���з��ڵ� = " + Dormitory + "and ȣ���ڵ� = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//��Ȱ��ȣ�� �������� ������Ʈ
							//�Ի缱���ڿ� �ش��л����� insert		
							if(applicationList.get(i).getMealDivision1()=="5�Ͻ�")	//5����� ���
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7�Ͻ�")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							
							sql = "insert into �Ի缱���� (��û��ȣ, �й�, ��Ȱ���з��ڵ�,ȣ���ڵ�,ħ���ȣ,������,�ĺ�,�հ�,���λ���,��Ͽ���,�������ܼ����⿩��)"
									+ " values(" +StudentRs.getString("�й�") + "," + StudentRs.getString("�й�") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + ", " + Integer.toString(ManageCost(Dormitory,"2")) + ","
									+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", "
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=2;
							i++;
							//��û ���̺� ��û���� update����ߵ�
							break;
						}
					}
					if(j==2)
						continue;	//�ش� �л��� ������ �������� ���� �л����� �Ѿ��
					//1�������� ȣ���� �������� �������� 2������ üũ�Ѵ�
					
					Dormitory = applicationList.get(i).getDormitoryWish2();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1������ �ش��ϴ� ��Ȱ���� arraylist�� Ž��
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//�������°� X�� ���̸� �л����� ����
						{
							if(applicationList.get(i).getMealDivision1()=="5�Ͻ�")	//5����� ���
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7�Ͻ�")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							sql = "update ��Ȱ��ȣ�� set ��������=\"O\" where ��Ȱ���з��ڵ� = " + Dormitory + "and ȣ���ڵ� = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//��Ȱ��ȣ�� �������� ������Ʈ
						//�Ի缱���ڿ� �ش��л����� insert		
							sql = "insert into �Ի缱���� (��û��ȣ, �й�, ��Ȱ���з��ڵ�,ȣ���ڵ�,ħ���ȣ,������,�ĺ�,�հ�,���λ���,��Ͽ���,�������ܼ����⿩��)"
									+ " values(" +StudentRs.getString("�й�") + "," + StudentRs.getString("�й�") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + "," + Integer.toString(ManageCost(Dormitory,"2")) + ","
											+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", "
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=3;
							i++;	
							//��û ���̺� ��û���� update����ߵ�
							break;
						}
					}
					if(j==3)
						continue;
					
					Dormitory = applicationList.get(i).getDormitoryWish3();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1������ �ش��ϴ� ��Ȱ���� arraylist�� Ž��
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//�������°� X�� ���̸� �л����� ����
						{
							if(applicationList.get(i).getMealDivision1()=="5�Ͻ�")	//5����� ���
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7�Ͻ�")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							sql = "update ��Ȱ��ȣ�� set ��������=\"O\" where ��Ȱ���з��ڵ� = " + Dormitory + "and ȣ���ڵ� = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//��Ȱ��ȣ�� �������� ������Ʈ
							//ResultSet subRs=stmt.executeQuery("select ��û��ȣ from ��û wehere �й�=" + StudentRs.getString("�й�") + "and �⵵=2019 and �б�=2");	
							//�Ի缱���ڿ� �ش��л����� insert		
							sql = "insert into �Ի缱���� (��û��ȣ, �й�, ��Ȱ���з��ڵ�,ȣ���ڵ�,ħ���ȣ,������,�ĺ�,�հ�,���λ���,��Ͽ���,�������ܼ����⿩��)"
									+ " values(" +applicationList.get(i).getApplicatonNumber()+ "," + StudentRs.getString("�й�") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + ", " + Integer.toString(ManageCost(Dormitory,"2")) + ","
											+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", " 
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=4;
							i++;
							stmt.executeUpdate("update ��û set ��û����=�հ� where ��û��ȣ = " + applicationList.get(i).getApplicatonNumber());//��û ���̺� ��û���� update
							
							break;
						}
					}
					if(j==4)	//3�������� �����Ѱ�� ���� �л����� �Ѿ��
						continue;
					//3�������� ������ ������ �л��� ���
					stmt.executeUpdate("update ��û set ��û����=���� and ����ȣ = " +Integer.toString(stanbyNumber));		//����ȣ ó��
					stanbyNumber ++;
				}//end of while
				protocol.makePacket(25, 1, 1, null);	//������ �����ٴ°� Ŭ���̾�Ʈ���� ��Ŷ���� ����
				
			}//end of try
			catch(SQLException e)
			{
				e.getStackTrace();
				protocol.makePacket(25,2,2, "�Ի缱���� ������ ����");
			}
			finally
			{
				/*
				try
				{
					StudentRs.close();
				}
				catch(SQLException e)
				{
					e.getStackTrace();
				}
				*/
			}
		}
		/**/
		//������ ����ϴ� �Լ�
		//5�Ͻ�
		public int FivedayFoodCost(String dormitoryCode, String semester)	//semester - 1�б�, 2�б�..
		{
			try
			{
				String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
				int cost=0;
				if(semester!= "�ϰ����" &&semester!="�������")
					cost = rs.getInt("5�Ͻĺ�_" + semester + "�б�");
				else if(semester== "�ϰ����")
					cost=rs.getInt("5�Ͻĺ�_�ϰ����");
				else 
					cost=rs.getInt("5�Ͻĺ�_�������");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//7�Ͻ�
		public int SevendayFoodCost(String dormitoryCode, String semester)	//semester - 1�б�, 2�б�..
		{
			try
			{
				String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
				int cost=0;
				if(semester!= "�ϰ����" &&semester!="�������")
					cost = rs.getInt("7�Ͻĺ�_" + semester + "�б�");
				else if(semester== "�ϰ����")
					cost=rs.getInt("7�Ͻĺ�_�ϰ����");
				else 
					cost=rs.getInt("7�Ͻĺ�_�������");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//���б� ������
		public int ManageCost(String dormitoryCode, String semester)	//semester - 1�б�, 2�б�..
		{
			try
			{
				String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
				int cost=0;
				if(semester!= "�ϰ����" &&semester!="�������")
					cost = rs.getInt("������_" + semester + "�б�");
				else if(semester== "�ϰ����")
					cost=rs.getInt("������_�ϰ����");
				else 
					cost=rs.getInt("������_�������");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
				return 0;
		}
		//1�� ����� 5�Ͻ�
		public int YearFiveFoodCost(String dormitoryCode)	//semester - 1�б�, 2�б�..
		{
			try
			{
				String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
				int cost=0;
				cost = rs.getInt("5�Ͻ�_1�б�") +rs.getInt("5�Ͻ�_2�б�") +rs.getInt("5�Ͻ�_�ϰ����") +rs.getInt("5�Ͻ�_�������");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//1�� ����� 
		public int YearSevenFoodCost(String dormitoryCode)	//semester - 1�б�, 2�б�..
		{
			try
			{
				String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
				int cost=0;
				cost = rs.getInt("7�Ͻ�_1�б�") +rs.getInt("7�Ͻ�_2�б�") +rs.getInt("7�Ͻ�_�ϰ����") +rs.getInt("7�Ͻ�_�������");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//1�� ����� ������
			public int YearManageCost(String dormitoryCode)	//semester - 1�б�, 2�б�..
			{
				try
				{
					String sql = "select * from ��Ȱ���� where ��Ȱ���з��ڵ�" + dormitoryCode ;
					int cost=0;
					cost = rs.getInt("������_1�б�") +rs.getInt("������_2�б�") +rs.getInt("������_�ϰ����") +rs.getInt("������_�������");
					return cost;
				}
				catch(SQLException e)
				{
					e.getStackTrace();
				}
				return 0;
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

