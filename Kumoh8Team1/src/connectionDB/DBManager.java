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
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Date;


//import com.mysql.cj.xdevapi.Statement;
// jdbc:mysql://192.168.209.250:3306/dorm
// 카페 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "192.168.208.38" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private User currentUser;
	private int year = 2019;
	private int semester = 2;
	
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
					currentUser = user;
					currentUser.setSeparaterUser(rs.getString("사용자구분"));
					currentUser.setName(rs.getString("성명"));

					if(rs.getString("사용자구분").equals("1") == true)
					{
						String st_number = rs.getString("사용자ID");
						SQL = "SELECT * FROM dorm.학생 where 학번 = " + st_number;
						rs = stmt.executeQuery(SQL);
						
						rs.next();
						Student student = new Student(rs.getString("대학구분"), rs.getString("학번"), 
								rs.getString("성명"), rs.getString("주민등록번호"));
						
						student.setGender(rs.getString("성별"));
						student.setDepartmentName(rs.getString("학과명"));
						student.setGrade(rs.getInt("학년"));
						student.setStudentAddress(rs.getString("학생주소"));
						student.setStudentPhoneNumber(rs.getString("학생전화번호"));
						protocol.makePacket(1,2,1, student);
						return;
					}
					
					else
					{
						protocol.makePacket(1,2,2, rs.getString("성명"));
						return;
					}
				} 
			}
		}
		protocol.makePacket(1,2,3, "해당정보 없음");
		}

	//입사신청 요청에 대한 응답
	public void checkDormitoryApplication(Protocol protocol)
	{
		try	//해당 학기에 학생이 신청 정보가 있는지 확인한다
		{
			String sql = "select * from 신청 where 학번=" + currentUser.getUserID() + "and 년도=2019 and 학기=2";
			rs = stmt.executeQuery(sql);
			int count =rs.getRow();
			if(count >=1)	//신청 정보가 하나라도 있으면 신청요청에 거절
				protocol.makePacket(11, 2, 2, "신청정보가 있음");
			else	//신청 정보가 없는 경우
				protocol.makePacket(11, 2, 1,null);
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
	}
	public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)	//입사신청
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

			//거리 가산점 계산 부분 추가(currentUser 학번으로 학생주소 조회후 app 객체 거리가산점 설정)
			String address;
			sql = "SELECT 학생주소 FROM 학생 WHERE 학번='" + currentUser.getUserID() + "'";
			rs = stmt.executeQuery(sql);
			address = rs.getString("학생주소");
			
			double distancePoint = 0.0;
			if (address.contains("제주도") || address.contains("울릉군")) {
				distancePoint = 0.4;
			} else if (address.contains("서울") || address.contains("경기") || address.contains("인천") || address.contains("강원")
					|| address.contains("충청") || address.contains("전라") || address.contains("광주") || address.contains("세종")) {
				distancePoint = 0.3;
			} else if (address.contains("대전")|| address.contains("부산") || address.contains("울산") || address.contains("경상남도")) {
				distancePoint = 0.2;
			} else if ((address.contains("경상북도") && !address.contains("구미")) || address.contains("대구")) {
				distancePoint = 0.1;
			} 
			app.setDistancePoint(distancePoint);
			//

			Date date = new Date();
			String today = date.toString();
	       
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			today = sdf.toString();
			
			sql = "insert into 신청 (신청번호, 학번, 년도, 학기, 생활관분류코드, 식비구분, 학점, 거리가산점, 지망, 신청일, 신청상태, 1년여부, 입사서약동의여부)"
				     + "values(" + applicationCount + ", " + app.getStandbyNumber() + ", 2019, 2, " + app.getDormitoryCode() +", "
				     + app.getMealDivision() + ", " + grade.toString() + "," + app.getDistancePoint() +", " + app.getdormitoryWish() + ", " 
				     + today + ", 신청" + app.getOneYearWhether() + ", yes);";
			rs = stmt.executeQuery(sql);
			protocol.makePacket(11, 2, 1, null);
		}
		catch(SQLException e)
		{
			protocol.makePacket(11, 2, 2, "저장 실패했습니다");
		}
		
	}
	
	//호실조회
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
	
	//입사신청내역 조회
	public void inquireDormitoryApplication(Protocol protocol, Student student)
	{
		try
		{
			String sql = "select * from 신청 where 학번=" + student.getStudentId() + "and 년도=2019 and 학기=2;";
			rs = stmt.executeQuery(sql);
			dormitoryApplication[] array = new dormitoryApplication[rs.getRow()];	//해당 학번에 해당하는 신청번호 행들을 저장할 배열 생성
			//rs의 각 인덱스 값을 하나씩짤라서 배열에 저장
			 
			int i=0;
			while(rs.next()) 
			{
				array[i]= new dormitoryApplication(rs.getString("신청번호"), rs.getString("학번"), rs.getString("생활관분류코드"));
				array[i].setYear("2019"); array[i].setSemester("2"); array[i].setMealDivision(rs.getString("식비구분"));
				array[i].setGrade(rs.getFloat("학점")); array[i].setGrade(rs.getFloat("거리가산점")); array[i].setdormitoryWish(rs.getString("지망"));
				array[i].setApplicationDay(rs.getString("신청일")); array[i].setApplicationState(rs.getString("신청상태"));
				array[i].setStandbyNumber(rs.getString("대기번호")); array[i].setOneYearWhether(rs.getString("1년여부")); array[i].setAcceptanceOfAgreement("yes"); 
				i++;
			}
			protocol.makePacket(13, 2, 1, array);
		}
		catch(SQLException e)
		{
			protocol.makePacket(13, 2, 1, "조회 실패");
		}
	}
	
	//여기서부터 관리자메뉴
	public void insertSchedule(Protocol protocol, SelectionSchedule schedule)
	{
		String year = Integer.toString(schedule.getYear());
		String semester = Integer.toString(schedule.getSemester());
		String program_code = schedule.getProgram_code();
		String start_date = schedule.getStart_date();
		String end_date = schedule.getEnd_date();
		String content = schedule.getContent();
		
		String sql="INSERT INTO 선발일정 VALUES(" + year + ", " + semester + ", "  + program_code + ", " 
				+ start_date + ", "  + end_date + ", "  + content + ")";
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        protocol.makePacket(21, 2, 1, null);
	}
	
	public void enrollJoiner(Protocol protocol)
	{
		String sql1 = "SET SQL_SAFE_UPDATES = 0";
		String sql2 = "update 입사선발자 set 등록여부=" + "O" + "where 납부상태="+ "O" + " and 결핵진단서제출여부=" + 
				"O" + " and 년도=" + year + "and 학기=" + semester;
		String sql = sql1 + "\n" + sql2;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		protocol.makePacket(23, 2, 1, null);
	}
	
	public void joinerCheck(Protocol protocol)
	{	
		int cnt = 0;
		String sql1 = "select 학번, 생활관분류코드, 호실코드, 침대번호 from 입사선발자 where 등록여부=\"O\"";
		try {
			rs = stmt.executeQuery(sql1);
			while(rs.next())
				cnt += 1;
			String[][] arr = new String[cnt][5];
			rs.beforeFirst();
			int i = 0;
			while(rs.next())
			{
				arr[i][0] = rs.getString("학번");			
				arr[i][3] = rs.getString("호실코드");
				arr[i][4] = rs.getString("침대번호");
				i++;
			}
			i = 0;
			String sql2 = "select 생활관명 from 생활관 where 생활관분류코드 in (select 생활관분류코드 from 입사선발자"
					+ " where 등록여부=\"O\")";
			rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				arr[i][2] = rs.getString("생활관명");
				i++;
			}
			i = 0;
			sql2 = "select 성명 from 학생 where 학번 in (select 생활관분류코드 from 입사선발자 " + 
					" where 등록여부=\"O\")";
			rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				arr[i][1] = rs.getString("성명");
				i++;
			}
			i = 0;
			protocol.makePacket(24, 2, 1, arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkTuberculosisDiagnosis(Protocol protocol)
	{
		int cnt = 0;
		String sql="select 신청번호, 이름, 결핵진단서제출여부 from 입사선발자";
        try {
        	rs = stmt.executeQuery(sql);
        	while(rs.next())
				cnt += 1;
			String[][] arr = new String[cnt][3];
			rs.beforeFirst();
			int i = 0;
			while(rs.next())
			{
				arr[i][0] = rs.getString("신청번호");
				arr[i][1] = rs.getString("이름");
				arr[i][2] = rs.getString("결핵진단서 여부");
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

