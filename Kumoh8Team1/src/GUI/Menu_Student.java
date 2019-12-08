// 학생 메뉴

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Menu_Student extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public Menu_Student(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois) {
		writer = oos;
		reader = ois;
		
		this.setResizable(false); // 최대화 단추 없애기
		setTitle("학생메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("결핵진단서 제출");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(15, 1);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				if (p.getSubType() == 2) {
					if (p.getCode() == 1)
						new TuberculosisDiagnosis_storage();
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
					}
				}
			}
		});

		button.setBounds(50, 325, 285, 50);
		contentPane.add(button);
		JButton button_1 = new JButton("입사 신청");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(11, 1);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				if (p.getSubType() == 2) {
					if (p.getCode() == 1)
						new Join_Promise(writer, reader);
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
					}
				}
			}
		});
		button_1.setBounds(50, 246, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("입사신청 내역조회 및 고지서 출력"); // 13, 14의 서브타입 1, 2를 한번에 처리, 즉 14의 1, 2는 사용하지 않음
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(13, 1);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				if (p.getSubType() == 2) {
					if (p.getCode() == 1)
						new DetailedStatement_Bill(p, writer, reader);
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
					}
				}
			}
		});
		button_2.setBounds(360, 325, 285, 50);
		contentPane.add(button_2);

		JButton button_4 = new JButton("호실 조회");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(12, 1);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (p.getSubType() == 2) {
					if (p.getCode() == 1)
						new DormitoryNumber_check(p, writer, reader);
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
					}
				}
			}
		});
		button_4.setBounds(360, 246, 285, 50);
		contentPane.add(button_4);

		JButton btnNewButton = new JButton("로그아웃");
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

		Student student = (Student)p_t.getBody();
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("성명");
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(50, 50, 135, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); // 성명
		textField_1.setEditable(false);
		textField_1.setText(student.getName());
		textField_1.setBounds(185, 50, 150, 40);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setText("학번");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(360, 50, 135, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField(); // 학번
		textField_3.setEditable(false);
		textField_3.setText(student.getStudentId());
		textField_3.setColumns(10);
		textField_3.setBounds(495, 50, 150, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setText("학년");
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(50, 100, 135, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField(); // 학년
		textField_5.setEditable(false);
		textField_5.setText(Integer.toString(student.getGrade()));
		textField_5.setColumns(10);
		textField_5.setBounds(185, 100, 150, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("학과");
		textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(360, 100, 135, 40);
		contentPane.add(textField_6);

		textField_7 = new JTextField(); // 학과
		textField_7.setEditable(false);
		textField_7.setText(student.getDepartmentName());
		textField_7.setColumns(10);
		textField_7.setBounds(495, 100, 150, 40);
		contentPane.add(textField_7);
		setVisible(true);
		setSize(720, 460);
	}

	public static void main(String[] args) {
		new Menu_Student(p, writer, reader);
	}
}