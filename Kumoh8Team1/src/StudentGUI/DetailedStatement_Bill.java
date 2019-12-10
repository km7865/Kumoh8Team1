// 입사신청 내역조회 및 고지서 출력
package StudentGUI;
import GUI.*;
import tableClass.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.Student;
import tableClass.dormitoryApplication;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class DetailedStatement_Bill extends JFrame {
	private JPanel contentPane;
	private Socket socket;
	private static Protocol p;
	private static ObjectOutputStream writer; 
	private static ObjectInputStream reader;
	private Student student;
	private dormitoryApplication[] appList;

	public DetailedStatement_Bill(Protocol p_t, Student s, ObjectOutputStream oos, ObjectInputStream ois,Socket sk) {
		
		socket = sk;
		p = p_t;
		student = s;
		appList = (dormitoryApplication[])p_t.getBody();
		writer = oos;
		reader = ois;

		this.setVisible(true);
		this.setResizable(false); // 최대화 단추 없애기
		setTitle("입사신청 내역조회 및 고지서 출력");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("입사신청 내역조회");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join_Application_DetailedStatement(student, appList, socket);
			}
		});
		button.setBounds(30, 40, 140, 40);
		contentPane.add(button);

		JButton button_1 = new JButton("고지서 출력");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = new Protocol(14,1,0,null);
				try {
					writer.writeObject(p);
					writer.flush();
					writer.reset();
					p = (Protocol)reader.readObject();
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (p.getSubType() == 2) {   
					if (p.getCode() == 1) {
						new Bill(student, (SelectedStudent)p.getBody(), writer, reader, socket);
					}
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
					}
				}
			}
		});
		button_1.setBounds(195, 40, 130, 40);
		contentPane.add(button_1);
	}
}