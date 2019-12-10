// 입사 신청
package StudentGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

import Network.Protocol;
import tableClass.*;

public class Dormitory_Application extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox_02;
	private JTextField textField_9;
	private JComboBox comboBox_11;
	private JComboBox comboBox_12;
	private JComboBox comboBox_21;
	private JComboBox comboBox_22;
	private JComboBox comboBox_31;
	private JComboBox comboBox_32;
	private JPanel panel;
	private JButton button1;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;

	private String mealDivision1;
	private String mealDivision2;
	private String mealDivision3;
	private String mealDivisionYear;

	private String dormitoryWish1;
	private String dormitoryWish2;
	private String dormitoryWish3;
	private String dormitoryWishYear;

	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private Student student;    

	public Dormitory_Application(Student s, ObjectOutputStream oos, ObjectInputStream ois) {
		student = s;
		writer = oos;
		reader = ois;

		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("입사신청");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 150, 1015, 610);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField(); // 학번
		textField.setFont(new Font("굴림", Font.PLAIN, 16));
		textField.setText(student.getStudentId());
		textField.setEditable(false);
		textField.setBounds(159, 53, 214, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); // 학과
		textField_1.setText(student.getDepartmentName());
		textField_1.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(496, 53, 214, 40);
		contentPane.add(textField_1);

		textField_3 = new JTextField(); // 성명
		textField_3.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_3.setText(student.getName());
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(496, 99, 214, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField(); // 학년
		textField_4.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_4.setText(Integer.toString(student.getGrade()));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(159, 99, 214, 40);
		contentPane.add(textField_4);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("굴림", Font.PLAIN, 17));
		textField_6.setEditable(false);
		textField_6.setForeground(Color.RED);
		textField_6.setText("생활관 입사신청");
		textField_6.setBounds(35, 10, 169, 40);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("굴림", Font.PLAIN, 17));
		textField_7.setText("1년 기숙 모집");
		textField_7.setForeground(new Color(255, 0, 0));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(35, 176, 169, 40);
		contentPane.add(textField_7);

		JComboBox comboBox_01 = new JComboBox();
		comboBox_01.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				dormitoryWishYear = comboBox_01.getSelectedItem().toString();

				if (str == dormitoryWishYear) {
					comboBox_02.setEnabled(false);
					comboBox_02.setSelectedItem(str);
				} else {
					comboBox_02.setEnabled(true);
				}
			}
		});
		comboBox_01.setModel(new DefaultComboBoxModel(
				new String[] { "선택안함","푸름관2동", "푸름관3동" }));
		comboBox_01.setBounds(159, 219, 125, 40);
		contentPane.add(comboBox_01);

		comboBox_02 = new JComboBox();
		comboBox_02.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mealDivisionYear = comboBox_02.getSelectedItem().toString();
			}
		});
		comboBox_02.setEnabled(false);
		comboBox_02.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD",
				"7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568" }));
		comboBox_02.setBounds(409, 219, 88, 40);
		contentPane.add(comboBox_02);

		textField_9 = new JTextField();
		textField_9.setText("한학기 기숙 모집");
		textField_9.setForeground(Color.RED);
		textField_9.setFont(new Font("굴림", Font.PLAIN, 17));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(35, 295, 169, 40);
		contentPane.add(textField_9);

		comboBox_11 = new JComboBox();
		comboBox_12 = new JComboBox();
		comboBox_21 = new JComboBox();
		comboBox_22 = new JComboBox();
		comboBox_31 = new JComboBox();
		comboBox_32 = new JComboBox();
		comboBox_21.setEnabled(false);
		comboBox_31.setEnabled(false);

		comboBox_11.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				dormitoryWish1 = comboBox_11.getSelectedItem().toString();
				if (dormitoryWish1 == "선택안함") {
					comboBox_12.setEnabled(false);
					comboBox_12.setSelectedItem(str);
					comboBox_21.setEnabled(false);
					comboBox_21.setSelectedItem(str);
					comboBox_22.setEnabled(false);
					comboBox_22.setSelectedItem(str);
					comboBox_31.setEnabled(false);
					comboBox_31.setSelectedItem(str);
					comboBox_32.setEnabled(false);
					comboBox_32.setSelectedItem(str);
				} else if (dormitoryWish1 == comboBox_21.getSelectedItem().toString() || dormitoryWish1 == comboBox_31.getSelectedItem().toString()) {
					JOptionPane.showMessageDialog(null, "중복입니다");
					comboBox_11.setSelectedItem(str);
				} else if (dormitoryWish1 == "오름관1동" || dormitoryWish1 == "오름관2동" || dormitoryWish1 == "오름관3동") {
					comboBox_12.removeItem("식사안함");
					comboBox_12.setEnabled(true);
				} else {
					if (comboBox_12.getItemCount() < 4) {
						comboBox_12.addItem("식사안함");
					}
					comboBox_12.setEnabled(true);
				}
			}
		});
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"선택안함","푸름관1동","푸름관2동","푸름관3동","푸름관4동","오름관1동","오름관2동","오름관3동","신평관남자","신평관여자" }));
		comboBox_11.setBounds(159, 339, 263, 40);
		contentPane.add(comboBox_11);

		comboBox_12.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				mealDivision1 = comboBox_12.getSelectedItem().toString();
				if (str == mealDivision1) {
					comboBox_21.setEnabled(false);
				} else {
					comboBox_21.setEnabled(true);
				}
			}
		});
		comboBox_12.setEnabled(false);
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD",
				"7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568" }));
		comboBox_12.setBounds(547, 339, 163, 40);
		contentPane.add(comboBox_12);

		comboBox_21.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				dormitoryWish2 = comboBox_21.getSelectedItem().toString();

				if (str.equals(dormitoryWish2)) {
					comboBox_22.setEnabled(false);
					comboBox_22.setSelectedItem(str);
					comboBox_31.setEnabled(false);
					comboBox_31.setSelectedItem(str);
					comboBox_32.setEnabled(false);
					comboBox_32.setSelectedItem(str);
				} else if (dormitoryWish2.equals(comboBox_11.getSelectedItem().toString()) || dormitoryWish2.equals(comboBox_31.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(null, "중복입니다");            
					comboBox_21.setSelectedItem(str);
				} else if (dormitoryWish2 == "오름관1동" || dormitoryWish2 == "오름관2동" || dormitoryWish2 == "오름관3동") {
					comboBox_22.removeItem("식사안함");
					comboBox_22.setEnabled(true);
				} else {
					if (comboBox_22.getItemCount() < 4) {
						comboBox_22.addItem("식사안함");
					}
					comboBox_22.setEnabled(true);
				}
			}
		});
		comboBox_21.setModel(new DefaultComboBoxModel(new String[] { "선택안함","푸름관1동","푸름관2동","푸름관3동","푸름관4동","오름관1동","오름관2동","오름관3동","신평관남자","신평관여자" }));
		comboBox_21.setBounds(159, 386, 263, 40);
		contentPane.add(comboBox_21);

		comboBox_22.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				mealDivision2 = comboBox_22.getSelectedItem().toString();

				if (str == mealDivision2) {
					comboBox_31.setEnabled(false);
					comboBox_32.setEnabled(false);
				} else {
					comboBox_31.setEnabled(true);
				}
			}
		});
		comboBox_22.setEnabled(false);
		comboBox_22.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD",
				"7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568" }));
		comboBox_22.setBounds(547, 386, 163, 40);
		contentPane.add(comboBox_22);

		comboBox_31.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "선택안함";
				dormitoryWish3 = comboBox_31.getSelectedItem().toString();

				if (str == dormitoryWish3) {
					comboBox_32.setEnabled(false);
					comboBox_32.setSelectedItem(str);
				} else if (dormitoryWish3 == comboBox_11.getSelectedItem().toString() || dormitoryWish3 == comboBox_21.getSelectedItem().toString()) {
					JOptionPane.showMessageDialog(null, "중복입니다");
					comboBox_31.setSelectedItem(str);
				} else if (dormitoryWish3 == "오름관1동" || dormitoryWish3 == "오름관2동" || dormitoryWish3 == "오름관3동") {
					comboBox_32.removeItem("식사안함");
					comboBox_32.setEnabled(true);
				} else {
					if (comboBox_32.getItemCount() < 4) {
						comboBox_32.addItem("식사안함");
					}
					comboBox_32.setEnabled(true);
				}
			}
		});

		comboBox_31.setModel(new DefaultComboBoxModel(new String[] { "선택안함","푸름관1동","푸름관2동","푸름관3동","푸름관4동","오름관1동","오름관2동","오름관3동","신평관남자","신평관여자" }));
		comboBox_31.setBounds(159, 432, 263, 40);
		contentPane.add(comboBox_31);

		comboBox_32.setEnabled(false);
		comboBox_32.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mealDivision3 = comboBox_32.getSelectedItem().toString();
			}
		});
		comboBox_32.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD",
				"7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568" }));
		comboBox_32.setBounds(547, 432, 163, 40);
		contentPane.add(comboBox_32);

		JButton btnNewButton = new JButton("신청");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dormitoryApplication app = new dormitoryApplication();

				app.setStudentId(student.getStudentId());

				if (!comboBox_01.getSelectedItem().equals("선택안함")) {
					app.setDormitoryWishYear(convertDormitoryNameToCode(comboBox_01.getSelectedItem().toString()));
					app.setMealDivisionYear(comboBox_02.getSelectedItem().toString());
				}

				if (!comboBox_11.getSelectedItem().equals("선택안함")) {
					app.setDormitoryWish1(convertDormitoryNameToCode(comboBox_11.getSelectedItem().toString()));
					app.setMealDivision1(comboBox_12.getSelectedItem().toString());
				}

				if (!comboBox_21.getSelectedItem().equals("선택안함")) {
					app.setDormitoryWish2(convertDormitoryNameToCode(comboBox_21.getSelectedItem().toString()));
					app.setMealDivision2(comboBox_22.getSelectedItem().toString());
				}
				if (!comboBox_31.getSelectedItem().equals("선택안함")) {
					app.setDormitoryWish3(convertDormitoryNameToCode(comboBox_31.getSelectedItem().toString()));
					app.setMealDivision3(comboBox_32.getSelectedItem().toString());
				}


				if (!comboBox_01.getSelectedItem().equals("선택안함") || !comboBox_11.getSelectedItem().equals("선택안함")) {
					try { 
						writer.writeObject(new Protocol(11,3,0,app));
						writer.flush();
						try {
							p = (Protocol)reader.readObject();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (p.getSubType() == 4) {
							if (p.getCode() == 1) { 
								JOptionPane.showMessageDialog(null, "신청이 성공적으로 완료되었습니다!!");
								dispose();
							}
							else if (p.getCode() == 2)
								JOptionPane.showMessageDialog(null, (String)p.getBody());
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "최소 1지망은 선택해야 합니다.");
				}
			}
		});

		btnNewButton.setBounds(620, 149, 90, 30);
		contentPane.add(btnNewButton);

		textField_14 = new JTextField();
		textField_14.setText("학번");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBackground(Color.LIGHT_GRAY);
		textField_14.setBounds(35, 53, 125, 40);
		contentPane.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setText("학과");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBackground(Color.LIGHT_GRAY);
		textField_15.setBounds(372, 53, 125, 40);
		contentPane.add(textField_15);

		textField_17 = new JTextField();
		textField_17.setText("성명");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBackground(Color.LIGHT_GRAY);
		textField_17.setBounds(372, 99, 125, 40);
		contentPane.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setText("학년");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBackground(Color.LIGHT_GRAY);
		textField_18.setBounds(35, 99, 125, 40);
		contentPane.add(textField_18);

		textField_20 = new JTextField();
		textField_20.setText("구분");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBackground(Color.LIGHT_GRAY);
		textField_20.setBounds(35, 219, 125, 40);
		contentPane.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setText("식사구분 (1년)");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("굴림", Font.PLAIN, 13));
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBackground(Color.LIGHT_GRAY);
		textField_21.setBounds(284, 219, 125, 40);
		contentPane.add(textField_21);

		textField_27 = new JTextField();
		textField_27.setText("식사구분");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_27.setEditable(false);
		textField_27.setColumns(10);
		textField_27.setBackground(Color.LIGHT_GRAY);
		textField_27.setBounds(422, 339, 125, 40);
		contentPane.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setText("식사구분");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_28.setEditable(false);
		textField_28.setColumns(10);
		textField_28.setBackground(Color.LIGHT_GRAY);
		textField_28.setBounds(422, 386, 125, 40);
		contentPane.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setText("식사구분");
		textField_29.setHorizontalAlignment(SwingConstants.CENTER);
		textField_29.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_29.setEditable(false);
		textField_29.setColumns(10);
		textField_29.setBackground(Color.LIGHT_GRAY);
		textField_29.setBounds(422, 432, 125, 40);
		contentPane.add(textField_29);

		textField_30 = new JTextField();
		textField_30.setText("1지망");
		textField_30.setHorizontalAlignment(SwingConstants.CENTER);
		textField_30.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_30.setEditable(false);
		textField_30.setColumns(10);
		textField_30.setBackground(Color.LIGHT_GRAY);
		textField_30.setBounds(35, 339, 125, 40);
		contentPane.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setText("2지망");
		textField_31.setHorizontalAlignment(SwingConstants.CENTER);
		textField_31.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_31.setEditable(false);
		textField_31.setColumns(10);
		textField_31.setBackground(Color.LIGHT_GRAY);
		textField_31.setBounds(35, 386, 125, 40);
		contentPane.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setText("3지망");
		textField_32.setHorizontalAlignment(SwingConstants.CENTER);
		textField_32.setFont(new Font("굴림", Font.PLAIN, 16));
		textField_32.setEditable(false);
		textField_32.setColumns(10);
		textField_32.setBackground(Color.LIGHT_GRAY);
		textField_32.setBounds(35, 432, 125, 40);
		contentPane.add(textField_32);
		setSize(750, 550);
	}

	public String convertDormitoryNameToCode(String s) {
		String code = null;

		if (s.equals("푸름관1동")) code = new String("1");
		else if (s.equals("푸름관2동")) code = new String("2");
		else if (s.equals("푸름관3동")) code = new String("3");
		else if (s.equals("푸름관4동")) code = new String("4");

		else if (s.equals("오름관1동")) code = new String("5");
		else if (s.equals("오름관2동")) code = new String("6");
		else if (s.equals("오름관3동")) code = new String("7");

		else if (s.equals("신평관남자")) code = new String("8");
		else if (s.equals("신평관여자")) code = new String("9");

		return code;
	}
}
