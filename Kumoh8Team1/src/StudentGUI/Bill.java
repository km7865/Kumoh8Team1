// 고지서 출력

package StudentGUI;

import GUI.*;
import tableClass.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private Socket socket;
	private Student student;
	private SelectedStudent selectedStudent;

	public Bill(Student s, SelectedStudent ss, ObjectOutputStream oos, ObjectInputStream ois,Socket sk) {

		socket = sk;
		student = s;
		selectedStudent = ss;
		writer = oos;
		reader = ois;

		this.setResizable(false); // 최대화 단추 없애기
		setTitle("고지서 출력");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("성명");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 85, 95, 40);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("학번");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setBounds(50, 130, 95, 40);
		contentPane.add(label);

		JLabel label_1 = new JLabel("생활관 관리비");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(50, 175, 95, 40);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("식비");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(50, 220, 95, 40);
		contentPane.add(label_2);
		JLabel label_3 = new JLabel("총액");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(50, 265, 95, 40);
		contentPane.add(label_3);

		textField_1 = new JTextField(); // 성명
		textField_1.setText(student.getName());
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(new Color(255, 228, 225));
		textField_1.setForeground(Color.BLACK);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(147, 85, 220, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField(); // 학번
		textField_2.setText(student.getStudentId());
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBackground(new Color(255, 228, 225));
		textField_2.setForeground(Color.BLACK);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(147, 130, 220, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField(Integer.toString(ss.getMng_cost())); // 생활관 관리비
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBackground(new Color(255, 228, 225));
		textField_3.setForeground(Color.BLACK);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(147, 175, 220, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField(Integer.toString(ss.getFood_cost())); // 식비
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBackground(new Color(255, 228, 225));
		textField_4.setForeground(Color.BLACK);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(147, 220, 220, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField(Integer.toString(selectedStudent.getTotal_cost())); // 입금액(총액)
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBackground(new Color(255, 228, 225));
		textField_5.setForeground(Color.BLACK);
		textField_5.setEditable(false);
		textField_5.setBounds(147, 266, 220, 40);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_title = new JTextField();
		textField_title.setForeground(Color.RED);
		textField_title.setText("고지서 출력");
		textField_title.setEditable(false);
		textField_title.setHorizontalAlignment(SwingConstants.CENTER);
		textField_title.setBounds(50, 20, 317, 50);
		contentPane.add(textField_title);
		textField_title.setColumns(10);
	}
}