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
   private String today;   //���� ��¥
   private String year = "2019";
   private String semester ="2";

   public DBManager(String id, String pw)   //������
   {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
      today = sdf.format(date);

      /*String today_1= today+"-";
      String dateArray[] = today.split("-");      //dateArray[] -> 2019 -> 12 -> 10

      year=dateArray[0];
      if(Integer.parseInt(dateArray[1]))
       */
      try{
         // 1. ����̹� �ε�
         // ����̹� �������̽��� ������ Ŭ������ �ε�
         // mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
         // mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
         // ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
         //         String dbID, dbPW;
         //         Scanner scan = new Scanner(System.in);
         //         
         //         System.out.print("db manager id :");
         //         dbID = scan.nextLine();
         //         System.out.print("db manager password :");
         //         dbPW = scan.nextLine();      //db manager ������ id�� pw�� �Է¹޾Ƽ� �������� db�� �α����Ѵ�

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

   public void loginCheck(Protocol protocol, User user) throws SQLException   //�α������� �˻�
   {
      String loginId = user.getUserID();
      String loginPw = user.getPassword();

      currentUser =user;   //db manager���� ���� client�� ������ �����ϰ� �ִ´�

      String SQL = "SELECT * FROM dorm.�����";
      rs = stmt.executeQuery(SQL);

      //����� ���̺��� ��� ID �˻� Ȥ�� ��ġ�ϴ� ID�� �ִٸ� PW ��ġ Ȯ�� 
      while(rs.next()) {    
         if (loginId.equals(rs.getString("�����ID"))) 
         {   //���̵� �´� ���
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

   public void inquireSchedule(Protocol protocol) {
      //�⺻ ���������� ���� ���α׷� �ڵ� : �⵵ + �б� + 01
      SelectionSchedule ss = new SelectionSchedule();
      String programCode = year + semester + "1";
      String sql = "select * from dorm.��������  where ���α׷��ڵ�='" + programCode + "'"; 
      try {
         rs = stmt.executeQuery(sql);
         if (rs.next()) {
            ss.setProgram_code(rs.getString("���α׷��ڵ�"));
            ss.setStart_date(rs.getString("�Խ���"));
            ss.setEnd_date(rs.getString("������"));
            ss.setContent(rs.getString("��������"));
            protocol.makePacket(2,2,1, ss);
         }
         else {
            protocol.makePacket(2,2,2, "���� �������� ����");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   //�Ի��û ��û�� ���� ����
   public void checkDormitoryApplication(Protocol protocol)
   {
      try   //�ش� �б⿡ �л��� ��û ������ �ִ��� Ȯ���Ѵ�
      {
         System.out.println(currentUser.getUserID());
         String sql = "select * from dorm.��û  where �й�='" + currentUser.getUserID() + "' and �⵵='2019' and �б�='2'";
         rs = stmt.executeQuery(sql);

         if(rs.next()) {
            protocol.makePacket(11, 2, 2, "��û������ ����");
         }
         else {   //��û ������ ���� ���
            protocol.makePacket(11, 2, 1,null);
         }
      }
      catch(SQLException e)
      {
         e.getStackTrace();
      }
   }
   //�Ի��û ���� ����
   public void insertDormitoryApplication(Protocol protocol, dormitoryApplication app)   //�Ի��û
   {
      try
      {
         String applicationCount= year + semester;
         int tmpAppNum = 0;
         String sql = "select * from ��û order by ��û��ȣ";
         rs =stmt.executeQuery(sql);
         if (rs.next()) {
            rs.last();
            tmpAppNum = Integer.parseInt(rs.getString("��û��ȣ"));
            tmpAppNum++;
            applicationCount = Integer.toString(tmpAppNum);
         }
         else {
            applicationCount += "1";   //��û��ȣ ����
         }

         sql = "select �й�, convert(sum(case ������� when 'A+' then convert(4.5*����,float) when 'A' then convert(4.0*����,float) " 
               + "  when 'B+' then convert(3.5*����,float) when 'B' then convert(3.0*����,float) " 
               +"   when 'C+' then convert(2.5*����,float) when 'C' then convert(2.0*����,float) "
               +"   when 'D+' then convert(1.5*����,float) when 'D' then convert(1.0*����,float) "
               +"  when 'F' then convert(0.0*����,float) end) /sum(����),decimal(3,2)) as ������� "
               +"from ����  where �й� = '" + currentUser.getUserID() + "'" +  " group by �й�";

         rs = stmt.executeQuery(sql);

         Double grade;
         if (rs.next())
            grade = rs.getDouble("�������");
         else
            grade = 0.0;

         //�Ÿ� ������ ��� �κ� �߰�(currentUser �й����� �л��ּ� ��ȸ�� app ��ü �Ÿ������� ����)
         String address;
         sql = "SELECT �л��ּ� FROM �л�  WHERE �й�='" + currentUser.getUserID() + "'";
         rs = stmt.executeQuery(sql);
         rs.next();
         address = rs.getString("�л��ּ�");
         Date date = new Date();
         today = Integer.toString((date.getYear() + 1900)) + "-" + Integer.toString(date.getMonth() + 1) + "-" + Integer.toString(date.getDate());

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

         sql = "insert into ��û values ('" + applicationCount + "', '" + currentUser.getUserID() +  "', 2019, 2, "; 
         if (app.getMealDivision1() != null)   sql += "'" + app.getMealDivision1() + "',"; else sql += "null,";
         if (app.getMealDivision2() != null)   sql += "'" + app.getMealDivision2() + "',"; else sql += "null,";
         if (app.getMealDivision3() != null)   sql += "'" + app.getMealDivision3() + "',"; else sql += "null,";
         if (app.getMealDivisionYear() != null)   sql += "'" + app.getMealDivisionYear() + "',"; else sql += "null,";
         sql += grade + ", " + distancePoint + ", " + app.getFinallyValue() + ", ";
         if (app.getDormitoryWish1() != null ) sql += "'" + app.getDormitoryWish1() + "',"; else sql += "null,";
         if (app.getDormitoryWish2() != null ) sql += "'" + app.getDormitoryWish2() + "',"; else sql += "null,";
         if (app.getDormitoryWish3() != null ) sql += "'" + app.getDormitoryWish3() + "',"; else sql += "null,";
         if (app.getDormitoryWishYear() != null ) sql += "'" + app.getDormitoryWishYear() + "',"; else sql += "null,";
         sql += "'" + today + "', '��û', " + 0 +  ", 'O');";
         System.out.println(sql);
         stmt.executeUpdate(sql);

         protocol.makePacket(11, 4, 1, null);
      }
      catch(SQLException e)
      {
         protocol.makePacket(11, 4, 2, "���� ����");
      }
   }

   //ȣ����ȸ
   public void roomCheck(Protocol protocol)
   {
      try
      {
         String id = currentUser.getUserID();
         String loginPw = currentUser.getPassword();
         SelectedStudent ss = new SelectedStudent();  

         String SQL = "SELECT * FROM �Ի缱���� WHERE �й�='" + id + "'";
         System.out.println(SQL);
         rs = stmt.executeQuery(SQL);

         if (rs.next()) {
            ss.setDormitoryCode(rs.getString("��Ȱ���з��ڵ�"));
            ss.setRoom_code(rs.getString("ȣ���ڵ�"));
            ss.setBed_code(rs.getString("ħ���ȣ"));
            ss.setPay_status(rs.getString("���λ���"));
            ss.setSubmissionTuberculosis(rs.getString("�������ܼ����⿩��"));
            ss.setRegister_status(rs.getString("��Ͽ���"));
            protocol.makePacket(12,2,1, ss);
            System.out.println("������ȸ");
         } else {
            protocol.makePacket(12,2,2, "�ش����� ����");
         }
      }
      catch(SQLException e)
      {
         protocol.makePacket(12,2,2, "��ȸ ����");
      }
   }
   //�Ի��û���� ��ȸ
   public void inquireDormitoryApplication(Protocol protocol)
   {
      try
      {
         String sql = "select * from ��û  where �й�='" + currentUser.getUserID() + "' and �⵵=" + year +  " and �б�=" + semester;
         rs = stmt.executeQuery(sql);
         rs.last();
         dormitoryApplication[] array = new dormitoryApplication[rs.getRow()];   //�ش� �й��� �ش��ϴ� ��û��ȣ ����� ������ �迭 ����
         rs.first();
         rs.previous();
         //rs�� �� �ε��� ���� �ϳ���©�� �迭�� ���� 
         int i=0;
         while(rs.next())
         {
            array[i]= new dormitoryApplication(rs.getString("��û��ȣ"), rs.getString("�й�"));
            array[i].setYear("2019"); array[i].setSemester("2"); 
            array[i].setMealDivision1(rs.getString("1�����ĺ񱸺�"));array[i].setMealDivision2(rs.getString("2�����ĺ񱸺�"));
            array[i].setMealDivision3(rs.getString("3�����ĺ񱸺�"));array[i].setMealDivisionYear(rs.getString("1��ĺ񱸺�"));
            array[i].setGrade(rs.getFloat("����")); array[i].setGrade(rs.getFloat("�Ÿ�������")); 
            array[i].setDormitoryWish1(rs.getString("1����"));array[i].setDormitoryWish2(rs.getString("2����"));
            array[i].setDormitoryWish3(rs.getString("3����"));array[i].setDormitoryWishYear(rs.getString("1������"));
            array[i].setApplicationDay(rs.getString("��û��"));array[i].setApplicationState(rs.getString("��û����"));
            array[i].setStandbyNumber(rs.getString("����ȣ"));array[i].setAcceptanceOfAgreement(rs.getString("�Ի缭�ൿ�ǿ���")); array[i].setFinallyValue(rs.getFloat("����")); 
            i++;
         }
         if(i==0)   //�Ի��û������ ������
            protocol.makePacket(13, 2, 2, "�Ի��û������ �����ϴ�");
         protocol.makePacket(13, 2, 1, array);
      }
      catch(SQLException e)
      {
         protocol.makePacket(13, 2, 2, "��ȸ ����");
      }
   }

   //maintype 14, ������ ���
   public void printBill(Protocol protocol)
   {
      try
      {
         String id = currentUser.getUserID();
         String loginPw = currentUser.getPassword();
         SelectedStudent ss = new SelectedStudent();  

         String SQL = "SELECT * FROM �Ի缱���� WHERE �й�='" + id + "'";
         System.out.println(SQL);
         rs = stmt.executeQuery(SQL);

         if (rs.next()) {
            ss.setDormitoryCode(rs.getString("��Ȱ���з��ڵ�"));
            ss.setRoom_code(rs.getString("ȣ���ڵ�"));
            ss.setBed_code(rs.getString("ħ���ȣ"));
            ss.setPay_status(rs.getString("���λ���"));
            ss.setSubmissionTuberculosis(rs.getString("�������ܼ����⿩��"));
            ss.setRegister_status(rs.getString("��Ͽ���"));
            ss.setMng_cost(rs.getInt("������"));
            ss.setFood_cost(rs.getInt("�ĺ�"));
            ss.setTotal_cost(rs.getInt("�հ�"));
            protocol.makePacket(14,2,1, ss);
            System.out.println("������ȸ");
         } else {
            protocol.makePacket(14,2,2, "�Ի缱���ڰ� �ƴϹǷ� �������� �����ϴ�.");
         }
      }
      catch(SQLException e)
      {
         protocol.makePacket(14,2,2, "������ ��ȸ ����");
      }
   }


   //�������ܼ� ��� ������ �л��� �Ի缱���� �ƴ��� ��ȸ�غ��� 
   public void checkSelectedStudent(Protocol protocol)
   {
      int main = protocol.getMainType();
      int sub = protocol.getSubType();
      try
      {
         String sql = "select * from �Ի缱����  where �й� = " + currentUser.getUserID();
         rs = stmt.executeQuery(sql);
         if(rs.next())   //�Ի缱���ڰ� �ƴϰ� �ش� ��û��ȣ�� ���ߵ� ���� �ϳ��ϰ�츸
            protocol.makePacket(main,sub+1, 1, null);   //��û ����
         else
            protocol.makePacket(main, sub+1, 2, "�Ի缱�� ����ڰ� �ƴ�");  
      }
      catch(SQLException e)
      {
         protocol.makePacket(main, sub, 2, "��ȸ ����");
      }
   }
   //�������ܼ� ���� ����
   public void updateState_TuberculosisDiagnosis(Protocol protocol)   //�������ܼ��� �����ߴٴ� ���������� ������ �������ܼ� ���� ���¸� ������Ʈ ��Ų��
   {
      try
      {
         String sql = "update dorm.�Ի缱���� set �������ܼ����⿩��='O' where �������ܼ����⿩��='X' and ��û��ȣ =" +(String)protocol.getBody();
         stmt.executeUpdate(sql);
         protocol.makePacket(15,4,1, "�������ܼ� ���� ����");
      }
      catch(SQLException e)
      {
         e.printStackTrace();
         protocol.makePacket(15, 4, 2, "�������ܼ� ���� ����");
      }/**/
   }

   //���⼭���� �����ڸ޴�
   //�������� ���
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
         String program_code = year + semester;
         if (schedule.getProgram_code() != null) program_code += schedule.getProgram_code(); 
         else program_code = Integer.toString(cnt);
         
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

   public void insertDormitoryCost(Protocol protocol)   //���Ұ� ���� �� �޽ĺ� ���
   {
      try
      {
         String sql;
         Dormitory_cost cost[] = (Dormitory_cost[])protocol.getBody();
         for(int i = 0; i < cost.length; i++)
         {
            sql = "select �з��ڵ� from dorm.��Ȱ�� where ��Ȱ���� = '" + cost[i].getKind_code() + "'";
            System.out.println(cost[i].getKind_code());
            rs = stmt.executeQuery(sql);
            rs.next();
            cost[i].setKind_code(rs.getString("�з��ڵ�"));
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
   //�Ի��ڵ��
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
      String studentNumber = (String)protocol.getBody();
      String sql="select �й�, ��û��ȣ from dorm.�Ի缱���� where �������ܼ����⿩�� = 'X' and �й� = " + studentNumber;

      try {
         rs = stmt.executeQuery(sql);
         if(rs.next()) {
            System.out.println("studentNumber : " + studentNumber);
            System.out.println(rs.getString("�й�"));
            protocol.makePacket(27, 2, 1, rs.getString("��û��ȣ")); 
         }
         else
         {
            protocol.makePacket(27, 2, 2, "��� �л��� �ƴմϴ�.");
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         protocol.makePacket(27, 2, 2, "�������� ������ �߻��߽��ϴ�.");
      }
   }

   public void dicisionCostUploadSubmit(Protocol protocol)
   {
      String studentNumber = (String)protocol.getBody();
      String sql="select �й�, ��û��ȣ from dorm.�Ի缱���� where ���λ��� = 'X' and �й� = " + studentNumber;
      try {
         rs = stmt.executeQuery(sql);
         if(!rs.next())
            protocol.makePacket(28, 2, 2, "����л��� �ƴմϴ�.111111111111111");
         else
         {
            rs.first();
            if(studentNumber.equals(rs.getString("�й�")))
            {
               sql="update dorm.�Ի缱���� set ���λ���='O' where �й� ='" + studentNumber + "'";
               stmt.executeUpdate(sql);
               protocol.makePacket(28, 2, 1, null);
            }

            else
               protocol.makePacket(28, 2, 2, "����л��� �ƴմϴ�.2222222222222");
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         protocol.makePacket(28, 2, 2, "�������� ������ �߻��߽��ϴ�.");
      }
   }

   public void enroll_test(Protocol protocol)
   {
      // 1������
      int cnt = 0;
      int waitNumber = 0;
      String oneSemesterCost[] = new String[3];
      String oneYearCost[] = new String[12];
      String sql;   
      sql = "select ��û��ȣ, �й�, ����, 1������, 1��ĺ񱸺� from dorm.��û where �⵵=2019 and �б�=2 and ��û����='��û' and 1������ is not null";
      try {
         rs = stmt.executeQuery(sql);
         while(rs.next())
            cnt += 1;
         rs.beforeFirst();
         String oneYearHuman[][] = new String [cnt][5];
         cnt = 0;
         int i = 0;
         while(rs.next())
         {
            oneYearHuman[i][0] = rs.getString("��û��ȣ");
            oneYearHuman[i][1] = rs.getString("�й�");
            oneYearHuman[i][2] = rs.getString("����");
            oneYearHuman[i][3] = rs.getString("1������");
            oneYearHuman[i][4] = rs.getString("1��ĺ񱸺�");
            i++;
         }
         i = 0;

         Arrays.sort(oneYearHuman, new Comparator<String[]>() {
            @Override      //2, 1�� ��������, 1, 2�� ��������
            public int compare(final String[] entry1, final String[] entry2) {
               if( ((Comparable)entry1[2]).compareTo(entry2[2]) < 0 )
                  return 1;
               else
                  return -1;
            }
         });

         sql = "select * from dorm.��Ȱ��ȣ�� where ��������='X'";
         rs = stmt.executeQuery(sql);
         while(rs.next())
            cnt += 1;
         rs.beforeFirst();
         String oneYearRoom[][] = new String[cnt][4];
         cnt = 0;
         while(rs.next())
         {
            oneYearRoom[i][0] = rs.getString("��Ȱ���з��ڵ�");
            oneYearRoom[i][1] = rs.getString("ȣ���ڵ�");
            oneYearRoom[i][2] = rs.getString("ħ���ȣ");
            oneYearRoom[i][3] = rs.getString("��������");
            i++;
         }
         i = 0;

         Arrays.sort(oneYearRoom, new Comparator<String[]>() {
            @Override      //2, 1�� ��������, 1, 2�� ��������
            public int compare(final String[] entry1, final String[] entry2) {
               if( ((Comparable)entry2[0]).compareTo(entry1[0]) < 0 )
                  return 1;
               else
                  return -1;
            }
         });

         System.out.println("����� �߰��ٸ�");
         for(int j = 0; j < oneYearHuman.length; j++)
         {
            for(int k = 0; k < oneYearRoom.length; k++)
            {
               if(oneYearHuman[j][3].equals(oneYearRoom[k][0]) && oneYearRoom[k][3].equals("X"))
               {
            	   sql = "insert into dorm.�Ի缱���� values('" + oneYearHuman[j][0] + "', '" + oneYearHuman[j][1]
                           + "', '" + oneYearRoom[k][1] + "', '" + oneYearRoom[k][2] + "', null, null, null, 'X'"
                                 + ", 'X', 'X', '" + oneYearHuman[j][3] + "', 'O', '" + oneYearHuman[j][4] + "')";
                  stmt.executeUpdate(sql);

                  sql = "select * from dorm.��Ȱ���� where ��Ȱ���з��ڵ�=" + oneYearHuman[j][3];
                  rs = stmt.executeQuery(sql);
                  rs.next();

                  oneYearCost[0] = rs.getString("������_1�б�");
                  oneYearCost[1] = rs.getString("������_�ϰ����");
                  oneYearCost[2] = rs.getString("������_2�б�");
                  oneYearCost[3] = rs.getString("������_�������");
                  oneYearCost[4] = rs.getString("7�Ͻĺ�_1�б�");
                  oneYearCost[5] = rs.getString("7�Ͻĺ�_�ϰ����");
                  oneYearCost[6] = rs.getString("7�Ͻĺ�_2�б�");
                  oneYearCost[7] = rs.getString("7�Ͻĺ�_�������");
                  oneYearCost[8] = rs.getString("5�Ͻĺ�_1�б�");
                  oneYearCost[9] = rs.getString("5�Ͻĺ�_�ϰ����");
                  oneYearCost[10] = rs.getString("5�Ͻĺ�_2�б�");
                  oneYearCost[11] = rs.getString("5�Ͻĺ�_�������");

                  int mng_sum = Integer.parseInt(oneYearCost[0]) + Integer.parseInt(oneYearCost[1])
                  + Integer.parseInt(oneYearCost[2]) + Integer.parseInt(oneYearCost[3]);

                  int fd_sum = Integer.parseInt(oneYearCost[4]) + Integer.parseInt(oneYearCost[5])
                  + Integer.parseInt(oneYearCost[6]) + Integer.parseInt(oneYearCost[7]);

                  int sd_sum = Integer.parseInt(oneYearCost[8]) + Integer.parseInt(oneYearCost[9])
                  + Integer.parseInt(oneYearCost[10]) + Integer.parseInt(oneYearCost[11]);

                  if(oneYearHuman[j][4].equals("5�Ͻ�"))
                  {
                     sql = "update dorm.�Ի缱���� set ������=" + mng_sum + ", �ĺ�=" + fd_sum + ", �հ�=" + (mng_sum+fd_sum)
                           + " where ��û��ȣ = '" + oneYearHuman[j][0] + "'";

                  }
                  else if(oneYearHuman[j][4].equals("7�Ͻ�"))
                  {
                     sql = "update dorm.�Ի缱���� set ������=" + mng_sum + ", �ĺ�=" + sd_sum + ", �հ�=" + (mng_sum+sd_sum)
                           + " where ��û��ȣ = '" + oneYearHuman[j][0] + "'";
                  }
                  else if(oneYearHuman[j][4].equals("�Ļ����"))
                     sql = "update dorm.�Ի缱���� set ������=" + mng_sum + ", �հ�=" + (mng_sum) + " where ��û��ȣ = '" + oneYearHuman[j][0] + "'";

                  stmt.executeUpdate(sql);

                  sql = "update dorm.��Ȱ��ȣ�� set ��������='O' where ��Ȱ���з��ڵ�='" + oneYearRoom[k][0]
                        + "'and ȣ���ڵ� ='" + oneYearRoom[k][1] + "'and ħ���ȣ='" + oneYearRoom[k][2] + "'";
                  stmt.executeUpdate(sql);

                  oneYearRoom[k][3] = "O";

                  sql = "update dorm.��û set ��û����='�հ�' where ��û��ȣ='" + oneYearHuman[j][0] + "'";
                  stmt.executeUpdate(sql);

                  System.out.println(oneYearHuman[j][0] + "���!");
                  break;
               }

               else if (k == oneYearRoom.length - 1 &&
                     !(oneYearHuman[j][3].equals(oneYearRoom[k][0]) && oneYearRoom[k][3].equals("X")))
               {
                  waitNumber += 1;
                  sql = "update dorm.��û set ����ȣ=" + waitNumber + " where ��û��ȣ='" + oneYearHuman[j][0] + "'";
                  stmt.executeUpdate(sql);
               }
            }      
         }

         //���б� ����
         cnt = 0;
         sql = "select ��û��ȣ, �й�, �б�, ����, 1����, 2����, 3����, 1�����ĺ񱸺�, 2�����ĺ񱸺�, 3�����ĺ񱸺�"
               + " from dorm.��û where �⵵=2019 and �б�=2 and ��û����='��û' and 1������ is null";
         rs = stmt.executeQuery(sql);
         while(rs.next())
            cnt += 1;
         rs.beforeFirst();
         String oneSemesterHuman[][] = new String [cnt][10];
         cnt = 0;
         int l = 0;
         while(rs.next())
         {
            oneSemesterHuman[l][0] = rs.getString("��û��ȣ");
            oneSemesterHuman[l][1] = rs.getString("�й�");
            oneSemesterHuman[l][2] = rs.getString("�б�");
            oneSemesterHuman[l][3] = rs.getString("����");
            oneSemesterHuman[l][4] = rs.getString("1����");
            oneSemesterHuman[l][5] = rs.getString("2����");
            oneSemesterHuman[l][6] = rs.getString("3����");
            oneSemesterHuman[l][7] = rs.getString("1�����ĺ񱸺�");
            oneSemesterHuman[l][8] = rs.getString("2�����ĺ񱸺�");
            oneSemesterHuman[l][9] = rs.getString("3�����ĺ񱸺�");
            l++;
            System.out.println("1�б� �����");
         }
         l = 0;

         Arrays.sort(oneSemesterHuman, new Comparator<String[]>() {
            @Override      //2, 1�� ��������, 1, 2�� ��������
            public int compare(final String[] entry1, final String[] entry2) {
               if( ((Comparable)entry1[2]).compareTo(entry2[2]) < 0 )
                  return 1;
               else
                  return -1;
            }
         });

         sql = "select * from dorm.��Ȱ��ȣ�� where ��������='X'";
         rs = stmt.executeQuery(sql);
         while(rs.next())
            cnt += 1;
         rs.beforeFirst();
         String oneSemesterRoom[][] = new String[cnt][4];
         cnt = 0;
         System.out.println("i��: " + i);
         while(rs.next())
         {
            oneSemesterRoom[i][0] = rs.getString("��Ȱ���з��ڵ�");
            oneSemesterRoom[i][1] = rs.getString("ȣ���ڵ�");
            oneSemesterRoom[i][2] = rs.getString("ħ���ȣ");
            oneSemesterRoom[i][3] = rs.getString("��������");
            i++;
         }

         Arrays.sort(oneSemesterRoom, new Comparator<String[]>() {
            @Override      //2, 1�� ��������, 1, 2�� ��������
            public int compare(final String[] entry1, final String[] entry2) {
               if( ((Comparable)entry2[0]).compareTo(entry1[0]) < 0 )
                  return 1;
               else
                  return -1;
            }
         });


         int c = 0;
         for(int j = 0; j < oneSemesterHuman.length; j++)
         {
            for(int m = 4; m < 7; m++)
            {
               for(int k = 0; k < oneSemesterRoom.length; k++)
               {
                  if(oneSemesterHuman[j][m]!= null && oneSemesterHuman[j][m].equals(oneSemesterRoom[k][0]) && oneSemesterRoom[k][3].equals("X"))
                  {
                     System.out.println(j + " " + m + " " + k);
                     sql = "insert into dorm.�Ի缱���� values('" + oneSemesterHuman[j][0] + "', '" + oneSemesterHuman[j][1]
                             + "', '" + oneSemesterRoom[k][1] + "', '" + oneSemesterRoom[k][2] + "', null, null, null, 'X'"
                                   + ", 'X', 'X', '" + oneSemesterHuman[j][m] + "', 'X', '" + oneSemesterHuman[j][m+3] + "')";
                     stmt.executeUpdate(sql);

                     if(oneSemesterHuman[j][2].equals("1"))
                     {
                        sql = "select ������_1�б�, 7�Ͻĺ�_1�б�, 5�Ͻĺ�_1�б�"
                              + " from dorm.��Ȱ���� where ��Ȱ���з��ڵ�=" + oneSemesterHuman[j][m];

                        rs = stmt.executeQuery(sql);
                        rs.next();

                        oneSemesterCost[0] = rs.getString("������_1�б�");
                        oneSemesterCost[1] = rs.getString("7�Ͻĺ�_1�б�");
                        oneSemesterCost[2] = rs.getString("5�Ͻĺ�_1�б�");
                     }

                     else if(oneSemesterHuman[j][2].equals("2"))
                     {
                        sql = "select ������_2�б�, 7�Ͻĺ�_2�б�, 5�Ͻĺ�_2�б�"
                              + " from dorm.��Ȱ���� where ��Ȱ���з��ڵ�=" + oneSemesterHuman[j][m];

                        rs = stmt.executeQuery(sql);
                        rs.next();

                        oneSemesterCost[0] = rs.getString("������_2�б�");
                        oneSemesterCost[1] = rs.getString("7�Ͻĺ�_2�б�");
                        oneSemesterCost[2] = rs.getString("5�Ͻĺ�_2�б�");
                     }

                     int mng = Integer.parseInt(oneSemesterCost[0]);
                     int fd = Integer.parseInt(oneSemesterCost[1]);
                     int sd = Integer.parseInt(oneSemesterCost[2]);

                     if(oneSemesterHuman[j][m+3].equals("5�Ͻ�"))
                     {
                        sql = "update dorm.�Ի缱���� set ������=" + mng + ", �ĺ�=" + fd + ", �հ�=" + (mng+fd)
                              + " where ��û��ȣ = '" + oneSemesterHuman[j][0] + "'";   
                     }
                     else if(oneSemesterHuman[j][m+3].equals("7�Ͻ�"))
                     {
                        sql = "update dorm.�Ի缱���� set ������=" + mng + ", �ĺ�=" + sd + ", �հ�=" + (mng+sd)
                              + " where ��û��ȣ = '" + oneSemesterHuman[j][0] + "'";
                     }
                     else if(oneSemesterHuman[j][m+3].equals("�Ļ����"))
                        sql = "update dorm.�Ի缱���� set ������=" + mng + ", �հ�=" + mng + " where ��û��ȣ = '" + oneSemesterHuman[j][0] + "'";

                     stmt.executeUpdate(sql);

                     sql = "update dorm.��Ȱ��ȣ�� set ��������='O' where ��Ȱ���з��ڵ�='" + oneSemesterRoom[k][0]
                           + "'and ȣ���ڵ� ='" + oneSemesterRoom[k][1] + "'and ħ���ȣ='" + oneSemesterRoom[k][2] + "'";
                     stmt.executeUpdate(sql);

                     oneSemesterRoom[k][3] = "O";

                     sql = "update dorm.��û set ��û����='�հ�' where ��û��ȣ='" + oneSemesterHuman[j][0] + "'";
                     stmt.executeUpdate(sql);

                     System.out.println(oneSemesterHuman[j][0] + "���!");
                     c = 1;
                     break;
                  }

                  else if (m == 6 && k == oneSemesterRoom.length - 1 &&
                        !(oneSemesterHuman[j][m] != null && oneSemesterHuman[j][m].equals(oneSemesterRoom[k][0]) && oneSemesterRoom[k][3].equals("X")))
                  {
                     waitNumber += 1;
                     sql = "update dorm.��û set ����ȣ=" + waitNumber + " where ��û��ȣ='" + oneSemesterHuman[j][0] + "'";
                     stmt.executeUpdate(sql);
                  }
               }
               if(c == 1)
               {
                  c = 0;
                  break;
               }
            }      
         }

         protocol.makePacket(25, 2, 1, null);

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         protocol.makePacket(25, 2, 2, "�������� ������ �߻��߽��ϴ�.");
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