// 입사신청 내역조회

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Network.Protocol;
import tableClass.*;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class Join_Application_DetailedStatement extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	public Join_Application_DetailedStatement(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois) {
		p = p_t;
		writer = oos;
		reader = ois;
		
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("입사신청 내역조회");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 990, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField = new JTextField();
		textField.setText("학적내역");
		textField.setForeground(Color.RED);
		textField.setFont(new Font("굴림", Font.PLAIN, 17));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(12, 10, 169, 40);
		contentPane.add(textField);
		textField_1 = new JTextField();
		textField_1.setText("학번");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(12, 60, 125, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("성명");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(638, 60, 125, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField(); // 성명
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(763, 60, 190, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField(); // 학번
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(136, 60, 190, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("학과");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(325, 60, 125, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("학년");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(12, 110, 125, 40);
		contentPane.add(textField_6);

		textField_7 = new JTextField(); // 학년
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(136, 110, 190, 40);
		contentPane.add(textField_7);

		textField_8 = new JTextField(); // 학과
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(449, 60, 190, 40);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setText("주소");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setBounds(325, 110, 125, 40);
		contentPane.add(textField_9);

		textField_10 = new JTextField(); // 주소
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(449, 110, 507, 40);
		contentPane.add(textField_10);
		
		// 테이블에 출력할 컬럼 이름 배열
		String columnNames[] = {"No.", "생활관구분", "지망", "식비구분", "결과"};

		// 테이블에 출력할 데이터 배열
		int i = 4; // 범위
		String data[][] = new String[4][i]; // 데이터 들어갈 범위
		data[0][0] = "1"; // 데이터 들어갈 부분

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable tbl = new JTable(model);
		tbl.setRowHeight(25);

		// JTable tbl = new JTable(data,columnNames);
		// Table은 JScrollPane위에 출력해야 컬럼 이름이 출력된다! 명심할것
		JScrollPane scroll = new JScrollPane(tbl);
		scroll.getVerticalScrollBar().setUnitIncrement(100); // 스크롤 속도
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scroll);
		scroll.setSize(940, 165);
		scroll.setLocation(12, 170);
	}
}