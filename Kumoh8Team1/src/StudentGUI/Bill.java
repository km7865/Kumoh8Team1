// ������ ���

package StudentGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Network.*;
import tableClass.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;

public class Bill extends JFrame {
	private JPanel contentPane;
	private JTextField textField_5;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_title;

	private static Protocol p;
	private static Protocol p2;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private String[] s;
	private dormitoryApplication[] appList;

	public Bill(Protocol p_t, dormitoryApplication[] a, ObjectOutputStream oos, ObjectInputStream ois) {
		p = p_t; // �л� ���� ���� ��������
		appList = a;
		writer = oos;
		reader = ois;
		s = (String[])p.getBody();
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setTitle("������ ���");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String name = s[0];	//�̸�
		String sId = s[1];	//�й�
		String money = s[2];//�ݾ�

		JLabel lblNewLabel = new JLabel("����");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 85, 95, 40);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("�й�");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBounds(50, 130, 95, 40);
		contentPane.add(label);

		JLabel label_1 = new JLabel("����");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(50, 175, 95, 40);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("���¹�ȣ");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(50, 220, 95, 40);
		contentPane.add(label_2);
		JLabel label_3 = new JLabel("�Աݾ�");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(50, 265, 95, 40);
		contentPane.add(label_3);

		textField_1 = new JTextField(name); // ����
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(new Color(255, 228, 225));
		textField_1.setForeground(Color.BLACK);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(147, 85, 220, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField(sId); // �й�
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBackground(new Color(255, 228, 225));
		textField_2.setForeground(Color.BLACK);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(147, 130, 220, 40);
		contentPane.add(textField_2);

		textField_5 = new JTextField(money); // �Աݾ�
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBackground(new Color(255, 228, 225));
		textField_5.setForeground(Color.BLACK);
		textField_5.setEditable(false);
		textField_5.setBounds(147, 266, 220, 40);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_title = new JTextField();
		textField_title.setForeground(Color.RED);
		textField_title.setText("������ ���");
		textField_title.setEditable(false);
		textField_title.setHorizontalAlignment(SwingConstants.CENTER);
		textField_title.setBounds(50, 20, 317, 50);
		contentPane.add(textField_title);
		textField_title.setColumns(10);
	}
}
