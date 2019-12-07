package tableClass;

import Network.*;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//import com.mysql.cj.xdevapi.Statement;
// jdbc:mysql://192.168.209.250:3306/dorm
// ī�� 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "localhost" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	public static final String ID = "test1";
	public static final String PW = "1234";

	private ObjectOutputStream writer;
	private ObjectInputStream reader;

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	public DBManager(ObjectOutputStream oos, ObjectInputStream ois) throws ClassNotFoundException	//������
	{
		try{
			// 1. ����̹� �ε�
			// ����̹� �������̽��� ������ Ŭ������ �ε�
			// mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
			// mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
			// ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
			Class.forName(JDBC_DRIVER);
			writer = oos;
			reader = ois; 
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void getConnection() throws SQLException
	{
		// 2. �����ϱ�
		// ����̹� �Ŵ������� Connection ��ü�� �޶�� ��û�Ѵ�.
		// mysql�� "jdbc:mysql://localhost/�����db�̸�" �̴�.
		// @param  getConnection(url, userName, password);
		// @return Connection

		conn = DriverManager.getConnection(URL, ID, PW);
		//System.out.println("���� ����");
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

	public void loginCheck(Protocol<User> protocol)
	{
		Protocol<User> p = protocol;
		User user;
		String loginId = p.getBody().getUserID();
		String loginPw = p.getBody().getPassword();
		try {
			stmt = conn.createStatement();  	// �� SQL�� ����/����(����)/��� ȹ��
			String SQL = "SELECT * FROM dorm.�����";
			rs = stmt.executeQuery(SQL);

			//����� ���̺��� ��� ID �˻� Ȥ�� ��ġ�ϴ� ID�� �ִٸ� PW ��ġ Ȯ�� 
			while(rs.next()) { 	
				if (loginId.equals(rs.getString("�����ID"))) {
					if (loginPw.equals(rs.getString("password"))) {
						System.out.println("�α��μ���");
						user = new User(rs.getString("�����ID"),rs.getString("password"),
								rs.getString("����"), rs.getString("����ڱ���"));
						protocol = new Protocol<User>(1,2,user);
						protocol.setCode(1);
						break;
					} else {
						protocol = new Protocol<User>(1,2,2);
					}
//					System.out.print(String.format("%15s", id) + "  |  ");
//					System.out.print(String.format("%15s", pw) + "  |  ");
//					System.out.print(String.format("%10s", name) + "  |  ");
//					System.out.println(String.format("%3s",type) + "  |");
				}
				if (!rs.next())
					protocol = new Protocol<User>(1,2,2);
			}
			//while�� ���� �� ��Ŷ ����
			writer.writeObject(protocol);
			writer.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		} // 4. pstmt.set<������Ÿ��>(? ����, ��) ex).setString(), .setInt ... pstmt.setString(1, "1234");
		//pstmt.setString(2, "�޶ѱ�"); pstmt.setString(3, "grasshopper"); 
		// 5. SQL ������ �����ϰ� ����� ���� - SQL ���� ���� ��, ����� row �� int type ���� int r = pstmt.executeUpdate(); System.out.println("����� row : " + r);
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
			stmt = conn.createStatement();  	// �� SQL�� ����/����(����)/��� ȹ��
			String SQL = "SELECT * FROM dorm.�л�";
			rs = stmt.executeQuery(SQL);
			// int executeUpdate(insert /delete/update)
			//int uCount = stmt.executeUpdate(" ");

			/*
        	while (rs.next()) { 				// �� ������տ��� ���� �� ȹ��
        		Student st = new Student();
        		st.setStudentId(rs.getString( "�й�"));
        		st.setName(rs.getString("����")); 		// �� ���� Į�� ȹ�� getX  name �Ӽ��� ����

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
				System.out.println("�ڹ� ���� �޽���:" + w.getMessage());
				System.out.println("DBMS ���� �ڵ�:" + w.getErrorCode());
				w.getNextWarning();
			}
		}
	}
}

