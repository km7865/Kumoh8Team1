// �Ի��û ������ȸ �� ������ ���
package StudentGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.Student;
import tableClass.dormitoryApplication;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class DetailedStatement_Bill extends JFrame {
	private JPanel contentPane;

	private static Protocol p;
	private static Protocol p2;
	private static ObjectOutputStream writer; 
	private static ObjectInputStream reader;
	private Student student;
	private dormitoryApplication[] appList;

	public DetailedStatement_Bill(Protocol p_t, Protocol p_t2, ObjectOutputStream oos, ObjectInputStream ois) {
		p = p_t; // �л� ���� ���� ��������
		appList = (dormitoryApplication[])p_t2.getBody();
		writer = oos;
		reader = ois;
		
		this.setVisible(true);
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setTitle("�Ի��û ������ȸ �� ������ ���");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("�Ի��û ������ȸ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join_Application_DetailedStatement(p, appList ,writer, reader);
			}
		});
		button.setBounds(30, 40, 140, 40);
		contentPane.add(button);

		JButton button_1 = new JButton("������ ���");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Bill(p, appList, writer, reader);
			}
		});
		button_1.setBounds(195, 40, 130, 40);
		contentPane.add(button_1);
	}
}