// �л� �޴�

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu_Student extends JFrame {

	private JPanel contentPane;
	JFrame frame = new JFrame();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	public Menu_Student() {
		setTitle("�л��޴�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("�������ܼ� ����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TuberculosisDiagnosis_storage();
			}
		});

		button.setBounds(50, 325, 285, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("�Ի� ��û");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join_Promise();
			}
		});
		button_1.setBounds(50, 246, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("�Ի��û ������ȸ �� ������ ���");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DetailedStatement_Bill();
			}
		});
		button_2.setBounds(360, 325, 285, 50);
		contentPane.add(button_2);

		JButton button_4 = new JButton("ȣ�� ��ȸ");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DormitoryNumber_check();
			}
		});
		button_4.setBounds(360, 246, 285, 50);
		contentPane.add(button_4);

		JButton btnNewButton = new JButton("�α׾ƿ�");
		btnNewButton.addMouseListener(new MouseAdapter() {
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				frame.setCursor();
				dispose();
			}
		});
		btnNewButton.setBounds(560, 150, 85, 30);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("����");
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(50, 50, 135, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); // ����
		textField_1.setEditable(false);
		textField_1.setBounds(185, 50, 150, 40);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setText("�й�");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(360, 50, 135, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField(); // �й�
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(495, 50, 150, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setText("�г�");
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(50, 100, 135, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField(); // �г�
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(185, 100, 150, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("�а�");
		textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(360, 100, 135, 40);
		contentPane.add(textField_6);

		textField_7 = new JTextField(); // �а�
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(495, 100, 150, 40);
		contentPane.add(textField_7);
		setVisible(true);
		setSize(720, 460);
	}

	public static void main(String[] args) {
		new Menu_Student();
	}
}