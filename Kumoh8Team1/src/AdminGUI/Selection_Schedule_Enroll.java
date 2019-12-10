// 선발일정 등록

package AdminGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.*;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Selection_Schedule_Enroll extends JFrame {
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	   private JPanel contentPane;
	   private JButton btnNewButton;
	   private JTextField textField;
	   private JTextField textField_1;
	   private JTextField textField_2;
	   private JTextField textField_3;

	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               Selection_Schedule_Enroll frame = new Selection_Schedule_Enroll(p, writer, reader);
	               frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }

	   public Selection_Schedule_Enroll(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		   p = p_t;
		   writer = writer_t;
		   reader = reader_t;
		   this.setResizable(false); // 최대화 단추 없애기
	      setVisible(true);
	      setTitle("선발일정 등록");
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setBounds(100, 100, 820, 680);
	      contentPane = new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(null);

	      JTextArea textArea = new JTextArea();
	      textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	      textArea.setBackground(SystemColor.control);
	      textArea.setEditable(false);
	      textArea.setForeground(SystemColor.desktop);
	      textArea.setText("★ 공지내용");
	      textArea.setBounds(12, 130, 160, 30);
	      contentPane.add(textArea);

	      JTextArea txtrAsdasd = new JTextArea(); // 공지내용
	      txtrAsdasd.setBounds(12, 170, 778, 458);
	      contentPane.add(txtrAsdasd);

	      btnNewButton = new JButton("선발일정 등록");
	      btnNewButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 String start_date = textField_1.getText();
	        	 String end_date = textField_3.getText();
	        	 String content = txtrAsdasd.getText();
	        	 if(start_date.compareTo(end_date) != 1 && start_date.length() == 8
	        			 && end_date.length() == 8)	//true
	        	 {
	        		 String start_date_year = start_date.substring(0, 4);
	        		 String start_date_month = start_date.substring(4, 6);
	        		 String start_date_day = start_date.substring(6, 8);
	        		 String end_date_year = start_date.substring(0, 4);
	        		 String end_date_month = start_date.substring(4, 6);
	        		 String end_date_day = start_date.substring(6, 8);

	        		 start_date = start_date_year + "-" + start_date_month + "-" + start_date_day;
	        		 end_date = end_date_year + "-" + end_date_month + "-" + end_date_day;
	        		 
	        		 SelectionSchedule selectionSchedule = new SelectionSchedule(0, 0, null,
	        				 start_date, end_date, content);
	        		 
	        		try
	 				{
	        			 p.makePacket(21, 1, 0, selectionSchedule);
	        			 writer.writeObject(p);
	 					 writer.flush();
	 					 writer.reset();
	 					 p = (Protocol)reader.readObject();
	 					 
	 					if (p.getSubType() == 2) {
	 						if (p.getCode() == 1) {
	 							JOptionPane.showMessageDialog(null, "선발일정이 정상적으로 등록되었습니다.");
	 							dispose();
	 						} else if (p.getCode() == 2) {
	 							String err = (String) p.getBody();
	 							JOptionPane.showMessageDialog(null, err); // ?
	 							dispose();
	 						}

	 					}

	 				} catch (IOException e1) {
	 					e1.printStackTrace();
	 				} catch (ClassNotFoundException e1) {
	 					// TODO Auto-generated catch block
	 					e1.printStackTrace();
	 				}
	        	 }
	        	 else	//false
	        		 JOptionPane.showMessageDialog(null, "8글자로 입력해주세요.");
	         }
	      });
	      btnNewButton.setBounds(680, 10, 110, 30);
	      contentPane.add(btnNewButton);
	      
	      textField = new JTextField();
	      textField.setText("\uAC8C\uC2DC\uC77C");
	      textField.setHorizontalAlignment(SwingConstants.CENTER);
	      textField.setFont(new Font("굴림", Font.PLAIN, 16));
	      textField.setEditable(false);
	      textField.setColumns(10);
	      textField.setBackground(Color.LIGHT_GRAY);
	      textField.setBounds(12, 65, 85, 40);
	      contentPane.add(textField);
	      
	      textField_1 = new JTextField("");		//게시일
	      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
	      textField_1.setFont(new Font("굴림", Font.PLAIN, 16));
	      textField_1.setColumns(10);
	      textField_1.setBounds(96, 65, 295, 40);
	      contentPane.add(textField_1);
	      
	      textField_2 = new JTextField();
	      textField_2.setText("\uC885\uB8CC\uC77C");
	      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
	      textField_2.setFont(new Font("굴림", Font.PLAIN, 16));
	      textField_2.setEditable(false);
	      textField_2.setColumns(10);
	      textField_2.setBackground(Color.LIGHT_GRAY);
	      textField_2.setBounds(390, 65, 85, 40);
	      contentPane.add(textField_2);
	      
	      textField_3 = new JTextField("");		//종료일
	      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
	      textField_3.setFont(new Font("굴림", Font.PLAIN, 16));
	      textField_3.setColumns(10);
	      textField_3.setBounds(474, 65, 313, 40);
	      contentPane.add(textField_3);
	   }
}