// 입사신청 내역조회 및 고지서 출력
package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
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
	private static ObjectOutputStream writer; 
	private static ObjectInputStream reader;

	public DetailedStatement_Bill(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		p = p_t;
		writer = writer_t;
		reader = reader_t;
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
				new Join_Application_DetailedStatement(p_t,writer, reader); // 수정 필요!!
			}
		});
		button.setBounds(30, 40, 140, 40);
		contentPane.add(button);

		JButton button_1 = new JButton("고지서 출력");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Bill(p_t, writer, reader);
			}
		});
		button_1.setBounds(195, 40, 130, 40);
		contentPane.add(button_1);
	}
}