// 호실 조회

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.*;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class DormitoryNumber_check extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JButton btnNewButton;
	private JTextField textField_3;
	private JTextField textField_6;

	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public DormitoryNumber_check(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois) {
		p = p_t;
		writer = oos;
		reader = ois;
		
		this.setResizable(false); // 최대화 단추 없애기
		setTitle("호실조회");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 1010, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText("\u25C8 학적내역");
		textField.setForeground(Color.RED);
		textField.setFont(new Font("굴림", Font.PLAIN, 17));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(35, 32, 169, 40);
		contentPane.add(textField);

		textField_1 = new JTextField(""); // 학번
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(159, 82, 190, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField(); // 학과
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(472, 82, 190, 40);
		contentPane.add(textField_2);

		textField_4 = new JTextField(); // 성명
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(785, 82, 190, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField(); // 학년
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(159, 128, 190, 40);
		contentPane.add(textField_5);

		textField_7 = new JTextField();
		textField_7.setText("\u25C8 신청 생활관 및 호실 확인");
		textField_7.setForeground(Color.RED);
		textField_7.setFont(new Font("굴림", Font.PLAIN, 17));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(35, 178, 250, 40);
		contentPane.add(textField_7);

		textField_8 = new JTextField(""); // 선발결과
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_8.setColumns(10);
		textField_8.setBounds(315, 228, 190, 40);
		contentPane.add(textField_8);

		textField_9 = new JTextField(""); // 납부여부
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_9.setColumns(10);
		textField_9.setBounds(785, 228, 190, 40);
		contentPane.add(textField_9);

		textField_10 = new JTextField(""); // 식비구분
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEditable(false);
		textField_10.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_10.setColumns(10);
		textField_10.setBounds(315, 267, 190, 40);
		contentPane.add(textField_10);

		textField_11 = new JTextField(""); // 생활관
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setEditable(false);
		textField_11.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_11.setColumns(10);
		textField_11.setBounds(785, 267, 190, 40);
		contentPane.add(textField_11);

		textField_12 = new JTextField(""); // 호실유형
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setEditable(false);
		textField_12.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_12.setColumns(10);
		textField_12.setBounds(315, 307, 190, 40);
		contentPane.add(textField_12);

		textField_13 = new JTextField(""); // 호실 / 침대번호
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setEditable(false);
		textField_13.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_13.setColumns(10);
		textField_13.setBounds(785, 307, 190, 40);
		contentPane.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setText("학번");
		textField_14.setBackground(Color.LIGHT_GRAY);
		textField_14.setBounds(35, 82, 125, 40);
		contentPane.add(textField_14);
		textField_14.setColumns(10);

		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setText("학과");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_15.setColumns(10);
		textField_15.setBackground(Color.LIGHT_GRAY);
		textField_15.setBounds(348, 82, 125, 40);
		contentPane.add(textField_15);

		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setText("성명");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_17.setColumns(10);
		textField_17.setBackground(Color.LIGHT_GRAY);
		textField_17.setBounds(661, 82, 125, 40);
		contentPane.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setText("학년");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_18.setColumns(10);
		textField_18.setBackground(Color.LIGHT_GRAY);
		textField_18.setBounds(35, 128, 125, 40);
		contentPane.add(textField_18);

		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_20.setText("선발결과");
		textField_20.setBackground(Color.LIGHT_GRAY);
		textField_20.setBounds(35, 228, 280, 40);
		contentPane.add(textField_20);
		textField_20.setColumns(10);

		textField_21 = new JTextField();
		textField_21.setEditable(false);
		textField_21.setText("납부여부");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_21.setColumns(10);
		textField_21.setBackground(Color.LIGHT_GRAY);
		textField_21.setBounds(504, 228, 280, 40);
		contentPane.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setText("식비구분");
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_22.setColumns(10);
		textField_22.setBackground(Color.LIGHT_GRAY);
		textField_22.setBounds(35, 267, 280, 40);
		contentPane.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setEditable(false);
		textField_23.setText("생활관");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_23.setColumns(10);
		textField_23.setBackground(Color.LIGHT_GRAY);
		textField_23.setBounds(504, 267, 280, 40);
		contentPane.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setText("호실유형");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_24.setColumns(10);
		textField_24.setBackground(Color.LIGHT_GRAY);
		textField_24.setBounds(35, 307, 280, 40);
		contentPane.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setEditable(false);
		textField_25.setText("호실 / 침대번호");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_25.setColumns(10);
		textField_25.setBackground(Color.LIGHT_GRAY);
		textField_25.setBounds(504, 307, 280, 40);
		contentPane.add(textField_25);

		btnNewButton = new JButton("조회");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "호실 조회 중입니다 ...");
			}
		});
		btnNewButton.setBounds(865, 32, 110, 30);
		contentPane.add(btnNewButton);

		textField_3 = new JTextField();
		textField_3.setText("주소");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(348, 128, 125, 40);
		contentPane.add(textField_3);

		textField_6 = new JTextField(); // 주소
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(472, 128, 503, 40);
		contentPane.add(textField_6);
	}
}
