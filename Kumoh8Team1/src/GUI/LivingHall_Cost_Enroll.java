// 생활관비 등록

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Network.Protocol;
import tableClass.*;

public class LivingHall_Cost_Enroll extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_6;
	private JButton btnNewButton;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LivingHall_Cost_Enroll frame = new LivingHall_Cost_Enroll(p, writer, reader);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LivingHall_Cost_Enroll(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("생활관비 등록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText("년도");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("굴림", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(11, 66, 85, 40);
		contentPane.add(textField);

		textField_1 = new JTextField(""); // 년도
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(96, 66, 134, 40);
		contentPane.add(textField_1);

		textField_3 = new JTextField(""); // 학기
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(312, 66, 134, 40);
		contentPane.add(textField_3);

		textField_5 = new JTextField(""); // 게시일
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(531, 66, 134, 40);
		contentPane.add(textField_5);

		textField_7 = new JTextField(""); // 종료일
		textField_7.setEditable(false);
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_7.setColumns(10);
		textField_7.setBounds(750, 66, 134, 40);
		contentPane.add(textField_7);

		textField_2 = new JTextField();
		textField_2.setText("학기");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(229, 66, 85, 40);
		contentPane.add(textField_2);

		textField_4 = new JTextField();
		textField_4.setText("게시일");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(446, 66, 85, 40);
		contentPane.add(textField_4);

		textField_6 = new JTextField();
		textField_6.setText("종료일");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(665, 66, 85, 40);
		contentPane.add(textField_6);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBackground(SystemColor.control);
		textArea.setEditable(false);
		textArea.setForeground(SystemColor.desktop);
		textArea.setText("\u2605 공지내용");
		textArea.setBounds(12, 130, 160, 30);
		contentPane.add(textArea);

		JTextArea txtrAsdasd = new JTextArea(); // 공지내용
		txtrAsdasd.setBounds(12, 170, 872, 528);
		contentPane.add(txtrAsdasd);

		btnNewButton = new JButton("생활관비 등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "생활관비 등록 완료");
			}
		});
		btnNewButton.setBounds(775, 10, 110, 30);
		contentPane.add(btnNewButton);
	}
}