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
// 카페 192.168.209.250
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

	public DBManager(ObjectOutputStream oos, ObjectInputStream ois) throws ClassNotFoundException	//생성자
	{
		try{
			// 1. 드라이버 로딩
			// 드라이버 인터페이스를 구현한 클래스를 로딩
			// mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
			// mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
			// 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
			Class.forName(JDBC_DRIVER);
			writer = oos;
			reader = ois; 
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void getConnection() throws SQLException
	{
		// 2. 연결하기
		// 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
		// mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
		// @param  getConnection(url, userName, password);
		// @return Connection

		conn = DriverManager.getConnection(URL, ID, PW);
		//System.out.println("연결 성공");
	}
	
	public void closeConnection() throws SQLException
	{
		try {
			//자원 반환
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (conn.isClosed())
				conn.close();
		}
	}
	////////////////////////////////////////////////////////////////////////////
	//기능

	public void loginCheck(Protocol<User> protocol)
	{
		Protocol<User> p = protocol;
		User user;
		String loginId = p.getBody().getUserID();
		String loginPw = p.getBody().getPassword();
		try {
			stmt = conn.createStatement();  	// ③ SQL문 구성/전송(실행)/결과 획득
			String SQL = "SELECT * FROM dorm.사용자";
			rs = stmt.executeQuery(SQL);

			//사용자 테이블의 모든 ID 검색 혹은 일치하는 ID가 있다면 PW 일치 확인 
			while(rs.next()) { 	
				if (loginId.equals(rs.getString("사용자ID"))) {
					if (loginPw.equals(rs.getString("password"))) {
						System.out.println("로그인성공");
						user = new User(rs.getString("사용자ID"),rs.getString("password"),
								 rs.getString("사용자구분"), rs.getString("성명"));
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
			//while문 종료 후 패킷 전송
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
			System.out.println("변경된 row: "+ row);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 4. pstmt.set<데이터타입>(? 순서, 값) ex).setString(), .setInt ... pstmt.setString(1, "1234");
		//pstmt.setString(2, "메뚜기"); pstmt.setString(3, "grasshopper"); 
		// 5. SQL 문장을 실행하고 결과를 리턴 - SQL 문장 실행 후, 변경된 row 수 int type 리턴 int r = pstmt.executeUpdate(); System.out.println("변경된 row : " + r);
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
			System.out.println("행 : "+row);
		} catch (SQLException e){
			e.printStackTrace();
		} finally { //사용순서와 반대로 close 함 if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } } if (con != null) { try { con.close(); } catch (SQLException e) { e.printStackTrace(); } }
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
			stmt = conn.createStatement();  	// ③ SQL문 구성/전송(실행)/결과 획득
			String SQL = "SELECT * FROM dorm.학생";
			rs = stmt.executeQuery(SQL);
			// int executeUpdate(insert /delete/update)
			//int uCount = stmt.executeUpdate(" ");

			/*
        	while (rs.next()) { 				// ④ 결과집합에서 다음 행 획득
        		Student st = new Student();
        		st.setStudentId(rs.getString( "학번"));
        		st.setName(rs.getString("성명")); 		// ⑤ 행의 칼럼 획득 getX  name 속성만 받음

        		System.out.println("학번 : " + st.getStudentId() +  "성명 : " + st.getName());
        	}
			 */

			//결과를 담을 ArrayList생성

		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	private static void checkWarnings(SQLWarning w) throws SQLException {
		if (w != null) {
			while (w != null) {
				System.out.println("SQL 상태:" + w.getSQLState());
				System.out.println("자바 예외 메시지:" + w.getMessage());
				System.out.println("DBMS 에러 코드:" + w.getErrorCode());
				w.getNextWarning();
			}
		}
	}
}

