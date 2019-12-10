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
// 카페 192.168.209.250
public class DBManager {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://" + "localhost" + ":3306" + "/dorm" + "?characterEncoding=UTF-8&serverTimezone=UTC";

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private User currentUser;
	private String today;	//오늘 날짜
	private int year = 2019;
	private int semester = 2;
	
	public DBManager(String id, String pw)	//생성자
	{
		Date date = new Date();
		today = date.toString();
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.toString();
		
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

		currentUser =user;	//db manager에서 현재 client의 정보를 저장하고 있는다
		
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
						Student student = new Student(rs.getString("학번"), rs.getString("성명"));
			
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
			System.out.println(currentUser.getUserID());
			String sql = "select * from dorm.신청  where 학번='" + currentUser.getUserID() + "' and 년도='2019' and 학기='2'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				protocol.makePacket(11, 2, 2, "신청정보가 있음");
			}
			else {	//신청 정보가 없는 경우
				protocol.makePacket(11, 2, 1,null);
			}
		}
		catch(SQLException e)
		{
			e.getStackTrace();
		}
	}
	/**/
	public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)	//입사신청
	{
		try
		{
			System.out.println(app.getDormitoryWish1() + " " + app.getMealDivision1());
			Integer count;
			String sql = "select * from 신청";
			rs =stmt.executeQuery(sql);
			if (rs.next()) {
				rs.last();
				count = rs.getRow()+1;
			}
			else
				count = 0;
			String applicationCount="201902".concat(count.toString());	//신청번호 생성
			
			
			sql = "select 학번, convert(sum(case 성적등급 when 'A+' then convert(4.5*학점,float) when 'A' then convert(4.0*학점,float) " 
				+ "  when 'B+' then convert(3.5*학점,float) when 'B' then convert(3.0*학점,float) " 
				 +"   when 'C+' then convert(2.5*학점,float) when 'C' then convert(2.0*학점,float) "
				+"	when 'D+' then convert(1.5*학점,float) when 'D' then convert(1.0*학점,float) "
				  +"  when 'F' then convert(0.0*학점,float) end) /sum(학점),decimal(3,2)) as 평점평균 "
				+"from 성적  where 학번 = '" + currentUser.getUserID() + "'" +  " group by 학번";

			rs = stmt.executeQuery(sql);

			rs.next();

			Double grade = rs.getDouble("평점평균");


			//거리 가산점 계산 부분 추가(currentUser 학번으로 학생주소 조회후 app 객체 거리가산점 설정)
			String address;
			sql = "SELECT 학생주소 FROM 학생  WHERE 학번='" + currentUser.getUserID() + "'";
			rs = stmt.executeQuery(sql);
			rs.next();
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
			app.setFinallyValue(grade + distancePoint);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(date);
			
			sql = "insert into 신청 values ('" + applicationCount + "', '" + currentUser.getUserID() +  "', 2019, 2, "; 
			if (app.getMealDivision1() != null)	sql += "'" + app.getMealDivision1() + "',"; else sql += "null,";
			if (app.getMealDivision2() != null)	sql += "'" + app.getMealDivision2() + "',"; else sql += "null,";
			if (app.getMealDivision3() != null)	sql += "'" + app.getMealDivision3() + "',"; else sql += "null,";
			if (app.getMealDivisionYear() != null)	sql += "'" + app.getMealDivisionYear() + "',"; else sql += "null,";
			sql += grade + ", " + distancePoint + ", " + app.getFinallyValue() + ", ";
			if (app.getDormitoryWish1() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWish2() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWish3() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			if (app.getDormitoryWishYear() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
			sql += "'" + today + "', '신청', " + 0 +  ", 'O');";
			
			stmt.executeUpdate(sql);
			
			protocol.makePacket(11, 2, 1, null);
		}
		catch(SQLException e)
		{
			protocol.makePacket(11, 2, 2, "저장 실패");
		}
		
	}
	
	//호실조회
	public void roomCheck(Protocol protocol)
	{
		try
		{
			String id = currentUser.getUserID();
			String loginPw = currentUser.getPassword();
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
				protocol.makePacket(12,2,2, "해당정보 없음");
			}
		}
		catch(SQLException e)
		{
			protocol.makePacket(12, 2, 2, "조회 실패");
		}
	}
	/**/
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
				array[i]= new dormitoryApplication(rs.getString("신청번호"), rs.getString("학번"));
				array[i].setYear("2019"); array[i].setSemester("2"); 
				array[i].setMealDivision1(rs.getString("1지망식비구분"));array[i].setMealDivision1(rs.getString("2지망식비구분"));
				array[i].setMealDivision1(rs.getString("3지망식비구분"));array[i].setMealDivision1(rs.getString("1년식비구분"));
				array[i].setGrade(rs.getFloat("학점")); array[i].setGrade(rs.getFloat("거리가산점")); 
				array[i].setDormitoryWish1(rs.getString("1지망"));array[i].setDormitoryWish2(rs.getString("2지망"));
				array[i].setDormitoryWish3(rs.getString("1지망"));array[i].setDormitoryWishYear(rs.getString("1년지망"));
				array[i].setApplicationDay(rs.getString("신청일"));array[i].setApplicationState(rs.getString("신청상태"));
				array[i].setStandbyNumber(rs.getString("대기번호"));array[i].setAcceptanceOfAgreement("yes"); array[i].setFinallyValue(rs.getFloat("학점") + rs.getFloat("거리가산점")); 
				i++;
			}
			protocol.makePacket(13, 2, 1, array);
		}
		catch(SQLException e)
		{
			protocol.makePacket(13, 2, 1, "조회 실패");
		}
	}
	
	//maintype 14, 고지서 출력
	   public void selectDetailedStatement_Bill(Protocol protocol)
	   {
	      int main = protocol.getMainType();
	      int sub = protocol.getSubType();

	      final String bank = "국민은행";
	      Random rnd = new Random();
	      String accountNum = "302-" + Integer.toString(rnd.nextInt(1000000000)); // 계좌번호
	      try {

	         Date today = null; // 시스템 날짜
	         Date to_date = null; // 시작 날짜


	          SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	          today = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(sdfNow.format(new Date()));
	       
	          // 비교날짜 생성
	          to_date = new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse("20200121 10:00:00");

	         if (today.before(to_date)) {//시스템 날짜가 1월21일 10시 이전일 경우
	            protocol.makePacket(main, sub + 1, 2, "입사선발이 되지 않은 상태입니다.");
	         }
	         else{
	            String sql = "select * from 입사선발자, 신청 where 신청.신청번호 =입사선발자.신청번호 and 신청.학번 = " + currentUser.getUserID();
	            rs = stmt.executeQuery(sql);
	            if (rs.getRow() == 1) // 입사선발자가 아니고 해당 신청번호로 선발된 값이 하나일경우 만
	            { // 고지서 출력 필요한 속성
	               sql = "select 성명, 사용자ID, 합계 from 사용자, 입사선발자 where 사용자ID=학번 and 학번= " + currentUser.getUserID()
	                     + " and (납부상태='o' or 납부상태='O')";
	               rs = stmt.executeQuery(sql); // sql실행 값 rs 로 옮긴다.

	               String[] array = new String[3];
	               // ResultSet에 담긴 결과를 Array에 담기
	               array[0] = rs.getString(" 성명"); // 이름
	               array[1] = rs.getString("사용자ID");// 학번
	               array[3] = bank;
	               array[4] = accountNum;
	               array[5] = rs.getString("합계");// 입금액
	               
	               protocol.makePacket(main, sub + 1, 1, array);
	            } else
	               
	               protocol.makePacket(main, sub + 1, 2, "해당 정보가 없습니다");
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (ParseException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	
	
	//결핵진단서 출력 이전에 학생이 입사선발이 됐는지 조회해본다 , 코드 재활용 가능???????ㅇ0ㅇ????
	public void checkSelectedStudent(Protocol protocol)
	{
		int main = protocol.getMainType();
		int sub = protocol.getSubType();
		try
		{
			String sql = "select * from 입사선발자, 신청 where 신청.신청번호 =입사선발자.신청번호 and 신청.학번 = " + currentUser.getUserID();
			rs = stmt.executeQuery(sql);
			if(rs.getRow()==1)	//입사선발자가 아니고 해당 신청번호로 선발된 값이 하나일경우만
				protocol.makePacket(main,sub+1, 1, null);	//요청 수락
			else
				protocol.makePacket(main, sub+1, 2, "입사선발 대상자가 아님");	
		}
		catch(SQLException e)
		{
			protocol.makePacket(main, sub, 2, "조회 실패");
		}
	}
	//결핵진단서 파일 저장
		public void updateState_TuberculosisDiagnosis(Protocol protocol)	//결핵진단서를 제출했다는 프로토콜을 받으면 결핵진단서 제출 상태를 업데이트 시킨다
		{
			try
			{
				String sql = "update 입사선발자 set 결핵진단서제출여부=" + "O" + "where 학번=" + currentUser.getUserID();
				rs = stmt.executeQuery(sql);
				protocol.makePacket(15,4,1, "결핵진단서 저장 성공");
			}
			catch(SQLException e)
			{
				e.getStackTrace();
				protocol.makePacket(15, 4, 2, "결핵진단서 저장 실패");
			}/**/
		}
	
	//여기서부터 관리자메뉴
	public void insertSchedule(Protocol protocol, SelectionSchedule schedule)
	{
		String sql;
		int cnt = 0;
		
        try {
        	String year = Integer.toString(schedule.getYear());
			String semester = Integer.toString(schedule.getSemester());
        	sql = "select * from dorm.선발일정 where 년도='" + year + "' and 학기='" + semester + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next())
				cnt += 1;
			rs.beforeFirst();
			String program_code = year + semester + Integer.toString(cnt);
			String start_date = schedule.getStart_date();
			String end_date = schedule.getEnd_date();
			String content = schedule.getContent();
			
			sql="INSERT INTO dorm.선발일정 VALUES(" + year + ", " + semester + ", '"  + program_code + "', '" 
					+ start_date + "', '"  + end_date + "', '"  + content + "')";
			stmt.executeUpdate(sql);
			protocol.makePacket(21, 2, 1, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(21, 2, 2, "선발일정 등록이 정상적으로 이루어지지 않았습니다.");
		}
        
        
	}
	
	public void insertDormitoryCost(Protocol protocol)	//생할관 사용료 및 급식비 등록
	{
		try
		{
			String sql;
			Dormitory_cost cost[] = (Dormitory_cost[])protocol.getBody();
			System.out.println(protocol.getMainType() + " " + protocol.getSubType());
			for(int i = 0; i < cost.length; i++)
			{
				sql = "select 분류코드 from dorm.생활관 where 생활관명 = '" + cost[i].getKind_code() + "'";
				System.out.println(cost[i].getKind_code());
				rs = stmt.executeQuery(sql);
				rs.next();
				cost[i].setKind_code(rs.getString("분류코드"));
				System.out.println(cost[i].getKind_code());
				sql = "update dorm.생활관비 set 생활관분류코드 = " + cost[i].getKind_code()
						+ ", 관리비_1학기=" + cost[i].getMng_cost1() + ", 관리비_하계방학 = " + cost[i].getMng_cost2()
						+ ", 관리비_2학기 = " + cost[i].getMng_cost3() + ", 관리비_동계방학 = " + cost[i].getMng_cost4()
						+ ", 7일식비_1학기 = " + cost[i].getSd_food_cost1() + ", 7일식비_하계방학 = " + cost[i].getSd_food_cost2()
						+ ", 7일식비_2학기 = " + cost[i].getSd_food_cost3() + ", 7일식비_동계방학 = " + cost[i].getSd_food_cost4()
						+ ", 5일식비_1학기 = " + cost[i].getFd_food_cost1() + ", 5일식비_하계방학 = " + cost[i].getFd_food_cost2()
						+ ", 5일식비_2학기 = " + cost[i].getFd_food_cost3() + ", 5일식비_동계방학 = " + cost[i].getFd_food_cost4()
						+ " where 생활관분류코드 = " + cost[i].getKind_code();
					stmt.executeUpdate(sql);
			}
			
			protocol.makePacket(22, 2, 1, "저장 성공");
			System.out.println("저장성공");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			protocol.makePacket(22, 2, 2, "생활관비 저장중 오류 발생");
		}
		
	}
	public void enrollJoiner(Protocol protocol)
	{
		String sql = "update 입사선발자 set 등록여부='O' where 납부상태='O' and 결핵진단서제출여부='O'";
		try {
			stmt.executeUpdate(sql);
			protocol.makePacket(23, 2, 1, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(23, 2, 2, "등록이 정상적으로 이루어지지 않았습니다.");
		}
		
		
	}
	//sdfg
	public void joinerCheck(Protocol protocol)
	{	
		int cnt = 0;
		String sql1 = "select 학번, 생활관분류코드, 호실코드, 침대번호 from dorm.입사선발자 where 등록여부='O'";
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
			String sql2 = "select 생활관명 from dorm.생활관 where 분류코드 in (select 생활관분류코드 from 입사선발자"
					+ " where 등록여부='O')";
			rs = stmt.executeQuery(sql2);
			while(rs.next())
			{
				arr[i][2] = rs.getString("생활관명");
				i++;
			}
			i = 0;
			sql2 = "select 성명 from dorm.학생 where 학번 in (select 학번 from dorm.입사선발자 " + 
					" where 등록여부='O')";
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
			protocol.makePacket(24, 2, 2, "체크가 정상적으로 이루어지지 않았습니다.");
		}
	}
	
	public void checkTuberculosisDiagnosis(Protocol protocol)
	{
		int cnt = 0;
		String sql="select 신청번호, 학번, 결핵진단서제출여부 from dorm.입사선발자";
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
				arr[i][1] = rs.getString("학번");
				arr[i][2] = rs.getString("결핵진단서제출여부");
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
			protocol.makePacket(26, 2, 2, "제출여부를 정상적으로 가져오지 못했습니다.");
		}
	}
	
	public void dicisionTuberculosisDiagnosisSubmit(Protocol protocol)
	{
		String sql="select 학번 from dorm.입사선발자 where 결핵진단서제출여부 = 'X'";
		String studentNumber = (String)protocol.getBody();
		try {
			rs = stmt.executeQuery(sql);
			if(!rs.next())
				protocol.makePacket(27, 2, 2, "대상학생이 아닙니다.");
			else
			{
				rs.first();
				if(studentNumber.equals(rs.getString("학번")))
					protocol.makePacket(27, 2, 1, null);
				else
					protocol.makePacket(27, 2, 2, "대상학생이 아닙니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			protocol.makePacket(26, 2, 2, "제출여부를 정상적으로 가져오지 못했습니다.");
		}
	}
	
	//입사선발자 결과등록 알고리즘
		//호실 객체를 생활관마다 2차원 배열로 생성
		//신청테이블에서 해당년도 학기에 해당하는 신청 내역을 싹다 들고온다	->arrayList에 싹다 저장
		//while문으로 arraylist에 있는 학생들을 한명씩 검색한다
		//1지망의 생활관 호실을 검색해 배정이 안된 방을 검색한다
		//배정이 안돼있으면 랜덤으로 침대와 호실을 배정
		//만약 해당 생화로간의 호실이 모두 배정이 돼있으면 2지망으로 넘어간다
		//2지망도 같은방식으로 검사하고
		//2지망도 배정이 끝났으면 3지망으로 넘어간다
		//3지망도 배정이 끝났으면 대기번호를 배정한다

		public void enrollSelectedStudent(Protocol protocol)		//입사선발자 결과등록
		{
			String sql;
			ResultSet StudentRs;
			try
			{
				ArrayList<dormitoryApplication> applicationList = new ArrayList<dormitoryApplication>();
				Iterator iterator = applicationList.iterator();	//applicationList를 탐색할 iterator
				
				sql = "select * frome 신청 where 년도=2019 and 학기=2";
				StudentRs = stmt.executeQuery(sql);	//신청한 모든 학생들을 결과값에 저장
				int i=0;
				while(StudentRs.next())	//다음row가 있을 때까지 arraylist에 학생 객체를 만들어 넣는다
				{
					applicationList.add(new dormitoryApplication(rs.getString("신청번호"), rs.getString("학번")));
					applicationList.get(i).setMealDivision1(rs.getString("1지망식비구분"));applicationList.get(i).setMealDivision1(rs.getString("2지망식비구분"));
					applicationList.get(i).setMealDivision1(rs.getString("3지망식비구분"));applicationList.get(i).setMealDivision1(rs.getString("1년식비구분"));
					applicationList.get(i).setGrade(rs.getFloat("학점")); applicationList.get(i).setGrade(rs.getFloat("거리가산점")); 
					applicationList.get(i).setDormitoryWish1(rs.getString("1지망"));applicationList.get(i).setDormitoryWish2(rs.getString("2지망"));
					applicationList.get(i).setDormitoryWish3(rs.getString("1지망"));applicationList.get(i).setDormitoryWishYear(rs.getString("1년지망"));
					applicationList.get(i).setApplicationDay(rs.getString("신청일"));applicationList.get(i).setApplicationState(rs.getString("신청상태"));
					applicationList.get(i).setStandbyNumber(rs.getString("대기번호"));applicationList.get(i).setAcceptanceOfAgreement("yes"); 
					applicationList.get(i).setFinallyValue(rs.getFloat("학점") + rs.getFloat("거리가산점")); 
					i++;
				}
				Collections.sort(applicationList);	//arrayList의 학생들을 총점순으로 정렬한다
			
				ArrayList<DormitoryRoom> puleum1 = new ArrayList<DormitoryRoom>();	//푸름
				Iterator <DormitoryRoom>p1 = puleum1.iterator();
				ArrayList<DormitoryRoom> puleum2 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p2 = puleum2.iterator();
				ArrayList<DormitoryRoom> puleum3 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p3 = puleum3.iterator();
				ArrayList<DormitoryRoom> puleum4 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>p4 = puleum4.iterator();
				ArrayList<DormitoryRoom> oleum1 = new ArrayList<DormitoryRoom>();	//오름
				Iterator <DormitoryRoom>o1 = oleum1.iterator();
				ArrayList<DormitoryRoom> oleum2 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>o2 = oleum2.iterator();
				ArrayList<DormitoryRoom> oleum3 = new ArrayList<DormitoryRoom>();
				Iterator <DormitoryRoom>o3 = oleum3.iterator();
				ArrayList<DormitoryRoom> sinpyeongM = new ArrayList<DormitoryRoom>();	//신평관 남자
				Iterator <DormitoryRoom>SM = sinpyeongM.iterator();
				ArrayList<DormitoryRoom> sinpyeongF = new ArrayList<DormitoryRoom>();	//신평관 여자
				Iterator <DormitoryRoom>SF = sinpyeongF.iterator();
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"1\"";	//푸름1동
				rs = stmt.executeQuery(sql);		//생활관 호실중 배정상태가 안된것들 싹다 넣음
				while(rs.next())
				{
					puleum1.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"2\"";	//푸름2동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum2.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"3\"";	//푸름3동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum3.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"4\"";	//푸름4동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					puleum4.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"5\"";	//오름1동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum1.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"6\"";	//오름2동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum2.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"7\"";	//오름3동
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					oleum3.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"8\"";	//신평남자
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					sinpyeongM.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				
				sql = "select * from 생활관호실 where 배정상태=\"X\" and 생활관분류코드=\"9\"";	//신평여자
				rs = stmt.executeQuery(sql);
			
				while(rs.next())
				{
					sinpyeongF.add(new DormitoryRoom(rs.getString("생활관분류코드"), rs.getString("호실코드"), rs.getString("침대번호")));
				}
				
				ArrayList<ArrayList<DormitoryRoom>> roomList = new ArrayList<ArrayList<DormitoryRoom>>();
				roomList.add(puleum1);roomList.add(puleum2);roomList.add(puleum3);roomList.add(puleum4); 
				roomList.add(oleum1);roomList.add(oleum2);roomList.add(oleum3);roomList.add(sinpyeongM);roomList.add(sinpyeongF);

				i=0;	//신청 index를 나타낼 변수
				int stanbyNumber=1;
				int FoodCost=0;
				while(StudentRs.next())		//list의 다음 학생이 있을 때까지
				{
					int j=0;
					String Dormitory = applicationList.get(i).getDormitoryWishYear();	//1년지망을 원순위로 배정한다
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1지망에 해당하는 생활관의 arraylist를 탐색
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//배정상태가 X인 방이면 학생에게 배정
						{
							if(applicationList.get(i).getMealDivisionYear()=="5일식")	//5잀식인 경우
								FoodCost =YearFiveFoodCost(applicationList.get(i).getDormitoryWishYear());
							else if(applicationList.get(i).getMealDivisionYear()=="7일식")
								FoodCost = YearSevenFoodCost(applicationList.get(i).getDormitoryWishYear());
							
							sql = "update 생활관호실 set 배정상태=\"O\" where 생활관분류코드 = " + Dormitory + "and 호실코드 = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//생활관호실 배정상태 업데이트
							
							//입사선발자에 해당학생정보 insert		
							sql = "insert into 입사선발자 (신청번호, 학번, 생활관분류코드,호실코드,침대번호,관리비,식비,합계,납부상태,등록여부,결핵진단서제출여부, 1년여부)"
									+ " values(" +StudentRs.getString("학번") + "," + StudentRs.getString("학번") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + "," +Integer.toString(YearManageCost(Dormitory)) 
									+ ", " +Integer.toString(FoodCost)+ ", " + Integer.toString(YearManageCost(Dormitory)+ FoodCost) +","  
									+ "X, X, X, O;";
							stmt.executeUpdate(sql);
							j=1;
							i++;
							//신청 테이블에 신청상태 update해줘야됨
							break;
						}
					}
					if(j==1)
						continue;	//학생에게 배정해줬으니 다음 학생 배정으로 넘어간다
					
					
					Dormitory = applicationList.get(i).getDormitoryWish1();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1지망에 해당하는 생활관의 arraylist를 탐색
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//배정상태가 X인 방이면 학생에게 배정
						{
							
							sql = "update 생활관호실 set 배정상태=\"O\" where 생활관분류코드 = " + Dormitory + "and 호실코드 = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//생활관호실 배정상태 업데이트
							//입사선발자에 해당학생정보 insert		
							if(applicationList.get(i).getMealDivision1()=="5일식")	//5잀식인 경우
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7일식")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							
							sql = "insert into 입사선발자 (신청번호, 학번, 생활관분류코드,호실코드,침대번호,관리비,식비,합계,납부상태,등록여부,결핵진단서제출여부)"
									+ " values(" +StudentRs.getString("학번") + "," + StudentRs.getString("학번") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + ", " + Integer.toString(ManageCost(Dormitory,"2")) + ","
									+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", "
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=2;
							i++;
							//신청 테이블에 신청상태 update해줘야됨
							break;
						}
					}
					if(j==2)
						continue;	//해당 학생에 배정을 끝냈으면 다음 학생으로 넘어간다
					//1지망에서 호실을 배정하지 못했으면 2지망을 체크한다
					
					Dormitory = applicationList.get(i).getDormitoryWish2();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1지망에 해당하는 생활관의 arraylist를 탐색
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//배정상태가 X인 방이면 학생에게 배정
						{
							if(applicationList.get(i).getMealDivision1()=="5일식")	//5잀식인 경우
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7일식")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							sql = "update 생활관호실 set 배정상태=\"O\" where 생활관분류코드 = " + Dormitory + "and 호실코드 = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//생활관호실 배정상태 업데이트
						//입사선발자에 해당학생정보 insert		
							sql = "insert into 입사선발자 (신청번호, 학번, 생활관분류코드,호실코드,침대번호,관리비,식비,합계,납부상태,등록여부,결핵진단서제출여부)"
									+ " values(" +StudentRs.getString("학번") + "," + StudentRs.getString("학번") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + "," + Integer.toString(ManageCost(Dormitory,"2")) + ","
											+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", "
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=3;
							i++;	
							//신청 테이블에 신청상태 update해줘야됨
							break;
						}
					}
					if(j==3)
						continue;
					
					Dormitory = applicationList.get(i).getDormitoryWish3();
					for(int a=0;a<roomList.get(Integer.parseInt(Dormitory)).size();a++)	//1지망에 해당하는 생활관의 arraylist를 탐색
					{
						if(roomList.get(Integer.parseInt(Dormitory)).get(a).getAssignmentState() =="X")	//배정상태가 X인 방이면 학생에게 배정
						{
							if(applicationList.get(i).getMealDivision1()=="5일식")	//5잀식인 경우
								FoodCost = FivedayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");
							else if(applicationList.get(i).getMealDivision1()=="7일식")
								FoodCost = SevendayFoodCost(applicationList.get(i).getDormitoryWish1(),"2");	
							sql = "update 생활관호실 set 배정상태=\"O\" where 생활관분류코드 = " + Dormitory + "and 호실코드 = " + roomList.get(Integer.parseInt(Dormitory)).get(a);
							stmt.executeUpdate(sql);	//생활관호실 배정상태 업데이트
							//ResultSet subRs=stmt.executeQuery("select 신청번호 from 신청 wehere 학번=" + StudentRs.getString("학번") + "and 년도=2019 and 학기=2");	
							//입사선발자에 해당학생정보 insert		
							sql = "insert into 입사선발자 (신청번호, 학번, 생활관분류코드,호실코드,침대번호,관리비,식비,합계,납부상태,등록여부,결핵진단서제출여부)"
									+ " values(" +applicationList.get(i).getApplicatonNumber()+ "," + StudentRs.getString("학번") + "," + Integer.parseInt(Dormitory) + ","
									+ roomList.get(Integer.parseInt(Dormitory)).get(a).getRoomCode() + ", " + Integer.toString(ManageCost(Dormitory,"2")) + ","
											+ Integer.toString(FoodCost) + ", " +Integer.toString(ManageCost(Dormitory,"2") + FoodCost) + ", " 
									+ "X, X, X,O;";
							stmt.executeQuery(sql);
							j=4;
							i++;
							stmt.executeUpdate("update 신청 set 신청상태=합격 where 신청번호 = " + applicationList.get(i).getApplicatonNumber());//신청 테이블에 신청상태 update
							
							break;
						}
					}
					if(j==4)	//3지망에서 배정한경우 다음 학생으로 넘어간다
						continue;
					//3지망까지 배정을 못받은 학생의 경우
					stmt.executeUpdate("update 신청 set 신청상태=예비 and 대기번호 = " +Integer.toString(stanbyNumber));		//대기번호 처리
					stanbyNumber ++;
				}//end of while
				protocol.makePacket(25, 1, 1, null);	//배정이 끝났다는걸 클라이언트에게 패킷으로 보냄
				
			}//end of try
			catch(SQLException e)
			{
				e.getStackTrace();
				protocol.makePacket(25,2,2, "입사선발자 결과등록 실패");
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
		//기숙사비 계산하는 함수
		//5일식
		public int FivedayFoodCost(String dormitoryCode, String semester)	//semester - 1학기, 2학기..
		{
			try
			{
				String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
				int cost=0;
				if(semester!= "하계방학" &&semester!="동계방학")
					cost = rs.getInt("5일식비_" + semester + "학기");
				else if(semester== "하계방학")
					cost=rs.getInt("5일식비_하계방학");
				else 
					cost=rs.getInt("5일식비_동계방학");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//7일식
		public int SevendayFoodCost(String dormitoryCode, String semester)	//semester - 1학기, 2학기..
		{
			try
			{
				String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
				int cost=0;
				if(semester!= "하계방학" &&semester!="동계방학")
					cost = rs.getInt("7일식비_" + semester + "학기");
				else if(semester== "하계방학")
					cost=rs.getInt("7일식비_하계방학");
				else 
					cost=rs.getInt("7일식비_동계방학");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//한학기 관리비
		public int ManageCost(String dormitoryCode, String semester)	//semester - 1학기, 2학기..
		{
			try
			{
				String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
				int cost=0;
				if(semester!= "하계방학" &&semester!="동계방학")
					cost = rs.getInt("관리비_" + semester + "학기");
				else if(semester== "하계방학")
					cost=rs.getInt("관리비_하계방학");
				else 
					cost=rs.getInt("관리비_동계방학");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
				return 0;
		}
		//1년 사용자 5일식
		public int YearFiveFoodCost(String dormitoryCode)	//semester - 1학기, 2학기..
		{
			try
			{
				String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
				int cost=0;
				cost = rs.getInt("5일식_1학기") +rs.getInt("5일식_2학기") +rs.getInt("5일식_하계방학") +rs.getInt("5일식_동계방학");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//1년 사용자 
		public int YearSevenFoodCost(String dormitoryCode)	//semester - 1학기, 2학기..
		{
			try
			{
				String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
				int cost=0;
				cost = rs.getInt("7일식_1학기") +rs.getInt("7일식_2학기") +rs.getInt("7일식_하계방학") +rs.getInt("7일식_동계방학");
				return cost;
			}
			catch(SQLException e)
			{
				e.getStackTrace();
			}
			return 0;
		}
		//1년 사용자 관리비
			public int YearManageCost(String dormitoryCode)	//semester - 1학기, 2학기..
			{
				try
				{
					String sql = "select * from 생활관비 where 생활관분류코드" + dormitoryCode ;
					int cost=0;
					cost = rs.getInt("관리비_1학기") +rs.getInt("관리비_2학기") +rs.getInt("관리비_하계방학") +rs.getInt("관리비_동계방학");
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

