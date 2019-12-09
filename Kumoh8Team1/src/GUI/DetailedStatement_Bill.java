// �Ի��û ������ȸ �� ������ ���

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

import Network.Protocol;
import tableClass.*;

public class DetailedStatement_Bill extends JFrame {
	private JPanel contentPane;
	
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public DetailedStatement_Bill(Protocol p, ObjectOutputStream oos, ObjectInputStream ois) {
		writer = oos;
		reader = ois;
		
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
				//new Join_Application_DetailedStatement(p); // ���� �ʿ�!!
			}
		});
		button.setBounds(30, 40, 140, 40);
		contentPane.add(button);

		JButton button_1 = new JButton("������ ���");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Bill();
			}
		});
		button_1.setBounds(195, 40, 130, 40);
		contentPane.add(button_1);
	}
}