package AdminGUI;
// ������ �޴�
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Network.Protocol;
import tableClass.*;
import StudentGUI.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Menu_Admin extends JFrame {
	private JPanel contentPane;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public Menu_Admin(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setTitle("������ �޴�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 745, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("�α׾ƿ�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				frame.setCursor();
				dispose();
			}
		});
		btnNewButton_1.setBounds(634, 10, 85, 30);
		contentPane.add(btnNewButton_1);

		JButton button = new JButton("�������� ���");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Selection_Schedule_Enroll(p, writer, reader);
			}
		});
		button.setBounds(45, 87, 285, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("��Ȱ���� ���");	//���� �ڵ������Ʈ
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LivingHall_Cost_Enroll(p, writer, reader);
			}
		});
		button_1.setBounds(353, 87, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("�Ի��� ��� �� ��ȸ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Enroll_and_Check(p, writer, reader);
			}
		});
		button_2.setBounds(45, 162, 285, 50);
		contentPane.add(button_2);

		JButton button_3 = new JButton("�Ի缱���� ������");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Result_Enroll(p,writer,reader);
			}
		});
		button_3.setBounds(353, 162, 285, 50);
		contentPane.add(button_3);

		JButton button_4 = new JButton("�������ܼ� ���ε� �� ����Ȯ��");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TuberculosisDiagnosis_upload_submit_check(p, writer, reader);
			}
		});
		button_4.setBounds(45, 235, 285, 50);
		contentPane.add(button_4);
		
		String name = (String)(p.getBody());
		JLabel lblNewLabel = new JLabel(name + "�� ȯ���մϴ�!");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(49, 18, 160, 40);
		contentPane.add(lblNewLabel);

		setVisible(true);
	}

	public static void main(String[] args) {	
		new Menu_Admin(p, writer, reader);
	}
}
