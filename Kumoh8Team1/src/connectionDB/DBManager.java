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
// 카페 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "192.168.208.38" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private String ID;
	private String PW;

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	public DBManager(String id, String pw)	//생성자
	{
		try{
			// 1. 드라이버 로딩
			// 드라이버 인터페이스를 구현한 클래스를 로딩
			// mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
			// mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
			// 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
			//			String dbID, dbPW;
			//			Scanner scan = new Scanner(System.in);
			//			
			//			System.out.print("db manager id :");
			//			dbID = scan.nextLine();
			//			System.out.print("db manager password :");
			//			dbPW = scan.nextLine();		//db manager 생성시 id와 pw를 입력받아서 원격으로 db에 로그인한다

			conn = DriverManager.getConnection(URL, id, pw);
			Class.forName(JDBC_DRIVER);
			stmt = conn.createStatement();

		}catch (Exception e){
			e.printStackTrace();
		}
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

	public void loginCheck(Protocol protocol, User user) throws SQLException
	{
		String loginId = user.getUserID();
		String loginPw = user.getPassword();

		String SQL = "SELECT * FROM dorm.사용자";
		rs = stmt.executeQuery(SQL);

		//사용자 테이블의 모든 ID 검색 혹은 일치하는 ID가 있다면 PW 일치 확인 
		while(rs.next()) { 	
			if (loginId.equals(rs.getString("사용자ID"))) 
			{	//아이디가 맞는 경우
				if (loginPw.equals(rs.getString("password"))) 
				{
					System.out.println("로그인성공");
					user = new User(rs.getString("사용자ID"),rs.getString("password"),
							rs.getString("사용자구분"), rs.getString("성명"));
					protocol.makePacket(1,2,1, user);
					return;
				} 
			}
		}
		protocol.makePacket(1,2,2, "해당정보 없음");
		}

	public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)
	{
		try
		{
			String sql = "select * from 신청";
			rs =stmt.executeQuery(sql);
			Integer count=rs.getRow()+1;
			String applicationCount="201902".concat(count.toString());	//신청번호 생성
			
			sql ="select 학번, convert(sum(case 성적등급 " 
					 + "when \"A+\" then convert(4.5*학점,float) when \"A\" then convert(4.0*학점,float)"
					  +  "when \"B+\" then convert(3.5*학점,float) when \"B\" then convert(3.0*학점,float)"
					   + "when \"C+\" then convert(2.5*학점,float) when \"C\" then convert(2.0*학점,float)"
					  + "when \"D+\" then convert(1.5*학점,float) when \"D\" then convert(1.0*학점,float)" 
					 +" when \"F\" then convert(0.0*학점,float) end) /sum(학점),decimal(3,2)) as 평점평균"
					+ "group by 학번;" + "from 성적;";
			rs = stmt.executeQuery(sql);
			Double grade = rs.getDouble("평점평균");
			
			Date date = new Date();
			String today = date.toString();
	       
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			today = sdf.toString();
			
			sql = "insert into 신청 (신청번호, 학번, 년도, 학기, 생활관분류코드, 식비구분, 학점, 거리가산점, 지망, 신청일, 신청상태, 1년여부, 입사서약동의여부)"
				     + "values(" + applicationCount + ", " + app.getStandbyNumber() + ", 2019, 2, " + app.getDormitoryCode() +", "
				     + app.getMealDivision() + ", " + grade.toString() + "," + "거리가산점" +", " + app.getdormitoryWish() + ", " 
				     + today + ", 신청" + app.getOneYearWhether() + ", yes";
			rs = stmt.executeQuery(sql);
			protocol.makePacket(11, 2, 1, null);
		}
		catch(SQLException e)
		{
			protocol.makePacket(11, 2, 2, "저장 실패했습니다");
		}
		
	}
	
	public void roomCheck(Protocol protocol, User user) throws SQLException
	{
		String id = user.getUserID();
		String loginPw = user.getPassword();
		DormitoryRoom dRoom = new DormitoryRoom();  

		String SQL = "SELECT * FROM 신청 natural join on 입사선발자 WHERE 학번=" + id;
		rs = stmt.executeQuery(SQL);
		//사용자 테이블의 모든 ID 검색 혹은 일치하는 ID가 있다면 PW 일치 확인 
		if (rs.next()) {
			dRoom.setDormitoryCode(rs.getString("생활관분류코드"));
			dRoom.setRoomCode(rs.getString("호실코드"));
			dRoom.setBedCode(rs.getString("침대번호"));
			dRoom.setAssignmentState(rs.getString("배정상태"));
			protocol.makePacket(12, 2, 0, dRoom);
		} else {
			protocol.makePacket(1,2,2, "해당정보 없음");
		}
		
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
				System.out.println("변경된 row: "+ row);


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 4. pstmt.set<데이터타입>(? 순서, 값).setString(), .setInt ... pstmt.setString(1, "1234");
			//pstmt.setString(2, "메뚜기"); pstmt.setString(3, "grasshopper"); 
			// . SQL 문장을 실행하고 결과를 리턴 - SQL 문장 실행 후, 변경된 row 수 int type 리턴 int r = pstmt.executeUpdate(); System.out.println("변경된 row : " + r);
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
				stmt = conn.createStatement();  	//  SQL문 구성/전송(실행)/결과 획득
				String SQL = "SELECT * FROM dorm.학생";
				rs = stmt.executeQuery(SQL);
				// int executeUpdate(insert /delete/update)
				//int uCount = stmt.executeUpdate(" ");

				
        	while (rs.next()) { 				/  결과집합에서 다음 행 획득
        		Student st = new Student();
        		st.setStudentId(rs.getString( "학번"));
        		st.setName(rs.getString("성명")); 		// 행의 칼럼 획득 getX  name 속성만 받음

        		System.out.println("학번 : " + st.getStudentId() +  "성명 : " + st.getName());
        	}
				 

				//결과를 담을 ArrayList생성

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
*/
		private static void checkWarnings(SQLWarning w) throws SQLException {
			if (w != null) {
				while (w != null) {
					System.out.println("SQL 상태:" + w.getSQLState());
					System.out.println("자바 예외 메세지:" + w.getMessage());
					System.out.println("DBMS 에러 코드:" + w.getErrorCode());
					w.getNextWarning();
				}
			}
		}
	}

