package connectionDB;

import tableClass.User;
import Network.*;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


//import com.mysql.cj.xdevapi.Statement;
// jdbc:mysql://192.168.209.250:3306/dorm
// ī�� 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "192.168.208.38" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private String ID;
	private String PW;

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	public DBManager()	//������
	{
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
			
			conn = DriverManager.getConnection(URL, "test5", "1234");
			Class.forName(JDBC_DRIVER);
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
			
		String SQL = "SELECT * FROM dorm.�����";
		rs = stmt.executeQuery(SQL);

		//����� ���̺��� ��� ID �˻� Ȥ�� ��ġ�ϴ� ID�� �ִٸ� PW ��ġ Ȯ�� 
		while(rs.next()) { 	
			if (loginId.equals(rs.getString("�����ID"))) 
			{	//���̵� �´� ���
				if (loginPw.equals(rs.getString("password"))) 
				{
					System.out.println("�α��μ���");
					user = new User(rs.getString("�����ID"),rs.getString("password"),
							 rs.getString("����ڱ���"), rs.getString("����"));
					protocol.makePacket(1,2,1, user);
				} else 
				{
					protocol.makePacket(1,2,2,"�ش����� ����");
				}
//					System.out.print(String.format("%15s", id) + "  |  ");
//					System.out.print(String.format("%15s", pw) + "  |  ");
//					System.out.print(String.format("%10s", name) + "  |  ");
//					System.out.println(String.format("%3s",type) + "  |");
				}
				if (!rs.next())	//�˻������� id�� ���� ���
				{
					protocol.makePacket(1,2,2, "�ش����� ����");
				}
		}
		protocol.makePacket(1,2,2, "�ش����� ����");
}

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

			/*
        	while (rs.next()) { 				/  ������տ��� ���� �� ȹ��
        		Student st = new Student();
        		st.setStudentId(rs.getString( "�й�"));
        		st.setName(rs.getString("����")); 		// ���� Į�� ȹ�� getX  name �Ӽ��� ����

        		System.out.println("�й� : " + st.getStudentId() +  "���� : " + st.getName());
        	}
			 */

			//����� ���� ArrayList����

		}catch(Exception e) {
			e.printStackTrace();
		}
	}



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

