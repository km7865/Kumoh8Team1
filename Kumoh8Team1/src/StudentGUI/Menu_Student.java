// �л� �޴�

package StudentGUI;
import GUI.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;

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

	private Socket socket;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private static String ip;
	private Student student;
	private JTextField textField_8;
	private SelectionSchedule schedule;

	public Menu_Student(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois, String ip_t,Socket sk) {
		
		socket = sk;
		p = p_t;
		student = (Student) p.getBody();
		writer = oos;
		reader = ois;
		ip = ip_t;

		try {
			writer.writeObject(new Protocol(2,1,0,null));
			p = (Protocol)reader.readObject();
		} catch (IOException | ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (p.getMainType() == 2) {
			if (p.getSubType() == 2) {
				if (p.getCode() == 1) {
					schedule = (SelectionSchedule)p.getBody();
				}
			}
		}

		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
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
				try {
					p.makePacket(27, 1, 0, student.getStudentId());
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
						new TuberculosisDiagnosis_storage(p, writer, reader, ip, socket);
					else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // ������ �ƴ� or ����Ⱓ �ƴ�
					}
				}
			}
		});

		button.setBounds(50, 569, 285, 50);
		contentPane.add(button);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);        

		JButton button_1 = new JButton("�Ի� ��û");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (today.compareTo(schedule.getStart_date()) >= 0  && 
							today.compareTo(schedule.getEnd_date()) <= 0) 
					{
						p = new Protocol(11, 1, 0, null);
						writer.writeObject(p);
						writer.flush();
						writer.reset();
						p = (Protocol) reader.readObject();

						if (p.getSubType() == 2) {
							if (p.getCode() == 1) {
								new Join_Promise(student, writer, reader, socket);
							}
							else if (p.getCode() == 2) {
								String err = (String) p.getBody();
								JOptionPane.showMessageDialog(null, err); // ������ �ƴ�
							}
						}
					} 
					else 
					{
						JOptionPane.showMessageDialog(null, "��û �Ⱓ�� �ƴմϴ�."); //��û �Ⱓ �ƴ�
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		button_1.setBounds(50, 490, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("�Ի��û ������ȸ �� ������ ���"); // 13, 14�� ����Ÿ�� 1, 2�� �ѹ��� ó��, �� 14�� 1, 2�� ������� ����
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(13, 1, 0, null);
					writer.writeObject(p);
					writer.flush();
					writer.reset();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				if (p.getSubType() == 2) {
					if (p.getCode() == 1) {
						new DetailedStatement_Bill(p, student, writer, reader, socket);
					} else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // ������ �ƴ� or ����Ⱓ �ƴ�
					}
				}
			}
		});
		button_2.setBounds(360, 569, 285, 50);
		contentPane.add(button_2);

		JButton button_4 = new JButton("ȣ�� ��ȸ");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(12, 1, 0, null);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (p.getSubType() == 2) {
					if (p.getCode() == 1) {
						new DormitoryNumber_check(student, (SelectedStudent) p.getBody(), writer, reader, socket);
					} else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // ������ �ƴ� or ����Ⱓ �ƴ�
					}
				}
			}
		});
		button_4.setBounds(360, 490, 285, 50);
		contentPane.add(button_4);

		Student student = (Student) p_t.getBody();

		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("����");
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(50, 320, 135, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); // ����
		textField_1.setEditable(false);
		textField_1.setText(student.getName());
		textField_1.setBounds(185, 320, 150, 40);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setText("�й�");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(360, 320, 135, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField(); // �й�
		textField_3.setEditable(false);
		textField_3.setText(student.getStudentId());
		textField_3.setColumns(10);
		textField_3.setBounds(495, 320, 150, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setText("�г�");
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(50, 370, 135, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField(); // �г�
		textField_5.setEditable(false);
		textField_5.setText(Integer.toString(student.getGrade()));
		textField_5.setColumns(10);
		textField_5.setBounds(185, 370, 150, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("�а�");
		textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(360, 370, 135, 40);
		contentPane.add(textField_6);

		textField_7 = new JTextField(); // �а�
		textField_7.setEditable(false);
		textField_7.setText(student.getDepartmentName());
		textField_7.setColumns(10);
		textField_7.setBounds(495, 370, 150, 40);
		contentPane.add(textField_7);

		JLabel startDayLabel = new JLabel();
		if (schedule != null) startDayLabel.setText(schedule.getStart_date());
		startDayLabel.setFont(new Font("����", Font.PLAIN, 13));
		startDayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startDayLabel.setBounds(50, 10, 100, 40);
		contentPane.add(startDayLabel);

		JLabel label = new JLabel("~");
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(145, 9, 40, 40);
		contentPane.add(label);

		JLabel endDayLabel = new JLabel();
		if (schedule != null) endDayLabel.setText(schedule.getEnd_date());
		endDayLabel.setFont(new Font("����", Font.PLAIN, 13));
		endDayLabel.setHorizontalAlignment(SwingConstants.LEFT);
		endDayLabel.setBounds(185, 10, 100, 40);
		contentPane.add(endDayLabel);

		//setEditable �߰�
		textField_8 = new JTextField();
		if (schedule != null) textField_8.setText(schedule.getContent());
		textField_8.setEditable(false);
		textField_8.setBounds(50, 60, 595, 240);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		setVisible(true);
		setSize(720, 670);
	}

}