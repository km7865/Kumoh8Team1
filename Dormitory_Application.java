// ¿‘ªÁ Ω≈√ª

package project;

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
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class Dormitory_Application extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JTextField textField_8;
	private JTextField textField_9;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JTextField textField_10;
	private JComboBox comboBox_5;
	private JComboBox comboBox_6;
	private JTextField textField_11;
	private JComboBox comboBox_7;
	private JComboBox comboBox_8;
	private JTextField textField_12;
	private JPanel panel;
	private JTextField textField_13;
	private JButton button1;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;

	public Dormitory_Application() {
		setTitle("\uC785\uC0AC\uC2E0\uCCAD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(230, 200, 1015, 610);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField("");
		textField.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setBounds(159, 53, 190, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(472, 53, 190, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(785, 53, 190, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(159, 99, 190, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(472, 99, 190, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(785, 99, 190, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("±º∏≤", Font.PLAIN, 17));
		textField_6.setEditable(false);
		textField_6.setForeground(Color.RED);
		textField_6.setText("\uC0DD\uD65C\uAD00 \uC785\uC0AC\uC2E0\uCCAD");
		textField_6.setBounds(35, 10, 169, 40);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("±º∏≤", Font.PLAIN, 17));
		textField_7.setText("1\uB144 \uAE30\uC219 \uBAA8\uC9D1");
		textField_7.setForeground(new Color(255, 0, 0));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(35, 176, 169, 40);
		contentPane.add(textField_7);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "º±≈√æ»«‘";
				String strv = comboBox.getSelectedItem().toString();

				if (str == strv) {
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
				} else {
					comboBox_1.setEnabled(true);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC120\uD0DD\uC548\uD568", "\uD478\uB984 2\uB3D9", "\uD478\uB984 3\uB3D9" }));
		comboBox.setBounds(159, 219, 125, 40);
		contentPane.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "º±≈√æ»«‘";
				String strv = comboBox_1.getSelectedItem().toString();
				if (str == strv) {
					comboBox_2.setEnabled(false);
				} else {
					comboBox_2.setEnabled(true);
				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD", "7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568"}));
		comboBox_1.setBounds(409, 219, 88, 40);
		contentPane.add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "5\uC77C\uC2DD", "7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568"}));
		comboBox_2.setBounds(622, 219, 88, 40);
		contentPane.add(comboBox_2);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(833, 219, 142, 40);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setText("\uD55C\uD559\uAE30 \uAE30\uC219 \uBAA8\uC9D1");
		textField_9.setForeground(Color.RED);
		textField_9.setFont(new Font("±º∏≤", Font.PLAIN, 17));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(35, 295, 169, 40);
		contentPane.add(textField_9);

		comboBox_3 = new JComboBox();
		comboBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "º±≈√æ»«‘";
				String strv = comboBox_3.getSelectedItem().toString();

				if (str == strv) {
					comboBox_4.setEnabled(false);
				} else {
					comboBox_4.setEnabled(true);
				}
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "\uC624\uB984 1\uB3D9", "\uC624\uB984 2\uB3D9", "\uC624\uB984 3\uB3D9", "-----------", "\uD478\uB984 1\uB3D9", "\uD478\uB984 2\uB3D9", "\uD478\uB984 3\uB3D9", "\uD478\uB984 4\uB3D9", "-----------", "\uC2E0\uD3C9 1\uB3D9", "\uC2E0\uD3C9 2\uB3D9"}));
		comboBox_3.setBounds(159, 339, 263, 40);
		contentPane.add(comboBox_3);

		comboBox_4 = new JComboBox();
		comboBox_4.setEnabled(false);
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "5\uC77C\uC2DD", "7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568"}));
		comboBox_4.setBounds(547, 339, 163, 40);
		contentPane.add(comboBox_4);

		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(833, 339, 142, 40);
		contentPane.add(textField_10);

		comboBox_5 = new JComboBox();
		comboBox_5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "º±≈√æ»«‘";
				String strv = comboBox_5.getSelectedItem().toString();

				if (str == strv) {
					comboBox_6.setEnabled(false);
				} else {
					comboBox_6.setEnabled(true);
				}
			}
		});
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "\uC624\uB984 1\uB3D9", "\uC624\uB984 2\uB3D9", "\uC624\uB984 3\uB3D9", "-----------", "\uD478\uB984 1\uB3D9", "\uD478\uB984 2\uB3D9", "\uD478\uB984 3\uB3D9", "\uD478\uB984 4\uB3D9", "-----------", "\uC2E0\uD3C9 1\uB3D9", "\uC2E0\uD3C9 2\uB3D9"}));
		comboBox_5.setBounds(159, 386, 263, 40);
		contentPane.add(comboBox_5);

		comboBox_6 = new JComboBox();
		comboBox_6.setEnabled(false);
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "5\uC77C\uC2DD", "7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568"}));
		comboBox_6.setBounds(547, 386, 163, 40);
		contentPane.add(comboBox_6);

		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(833, 386, 142, 40);
		contentPane.add(textField_11);

		comboBox_7 = new JComboBox();
		comboBox_7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = "º±≈√æ»«‘";
				String strv = comboBox_7.getSelectedItem().toString();

				if (str == strv) {
					comboBox_8.setEnabled(false);
				} else {
					comboBox_8.setEnabled(true);
				}
			}
		});
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "\uC624\uB984 1\uB3D9", "\uC624\uB984 2\uB3D9", "\uC624\uB984 3\uB3D9", "-----------", "\uD478\uB984 1\uB3D9", "\uD478\uB984 2\uB3D9", "\uD478\uB984 3\uB3D9", "\uD478\uB984 4\uB3D9", "-----------", "\uC2E0\uD3C9 1\uB3D9", "\uC2E0\uD3C9 2\uB3D9"}));
		comboBox_7.setBounds(159, 432, 263, 40);
		contentPane.add(comboBox_7);

		comboBox_8 = new JComboBox();
		comboBox_8.setEnabled(false);
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "-----------", "5\uC77C\uC2DD", "7\uC77C\uC2DD", "\uC2DD\uC0AC\uC548\uD568"}));
		comboBox_8.setBounds(547, 432, 163, 40);
		contentPane.add(comboBox_8);

		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(833, 432, 142, 40);
		contentPane.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setBounds(169, 482, 294, 40);
		contentPane.add(textField_13);
		textField_13.setColumns(10);

		JButton btnNewButton = new JButton("\uC2E0\uCCAD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ω≈√ª¿Ã º∫∞¯¿˚¿∏∑Œ øœ∑·µ«æ˙Ω¿¥œ¥Ÿ!!");
			}
		});

		btnNewButton.setBounds(887, 144, 90, 30);
		contentPane.add(btnNewButton);

		textField_14 = new JTextField();
		textField_14.setText("\uD559\uBC88");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBackground(Color.LIGHT_GRAY);
		textField_14.setBounds(35, 53, 125, 40);
		contentPane.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setText("\uD559\uACFC");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBackground(Color.LIGHT_GRAY);
		textField_15.setBounds(348, 53, 125, 40);
		contentPane.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setText("\uCD5C\uADFC\uD559\uC801");
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBackground(Color.LIGHT_GRAY);
		textField_16.setBounds(661, 53, 125, 40);
		contentPane.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setText("\uC131\uBA85");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBackground(Color.LIGHT_GRAY);
		textField_17.setBounds(35, 99, 125, 40);
		contentPane.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setText("\uD559\uB144(\uD559\uAE30\uCC28)");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBackground(Color.LIGHT_GRAY);
		textField_18.setBounds(348, 99, 125, 40);
		contentPane.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setText("\uD559\uC801\uBCC0\uB3D9\uC77C");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_19.setEditable(false);
		textField_19.setColumns(10);
		textField_19.setBackground(Color.LIGHT_GRAY);
		textField_19.setBounds(661, 99, 125, 40);
		contentPane.add(textField_19);

		textField_20 = new JTextField();
		textField_20.setText("\uAD6C\uBD84");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_20.setEditable(false);
		textField_20.setColumns(10);
		textField_20.setBackground(Color.LIGHT_GRAY);
		textField_20.setBounds(35, 219, 125, 40);
		contentPane.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setText("\uC2DD\uC0AC\uAD6C\uBD84\r\n(2\uD559\uAE30)");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("±º∏≤", Font.PLAIN, 13));
		textField_21.setEditable(false);
		textField_21.setColumns(10);
		textField_21.setBackground(Color.LIGHT_GRAY);
		textField_21.setBounds(284, 219, 125, 40);
		contentPane.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setText("\uC2DD\uC0AC\uAD6C\uBD84\r\n(\uB3D9\uACC4\uBC29\uD559)");
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("±º∏≤", Font.PLAIN, 13));
		textField_22.setEditable(false);
		textField_22.setColumns(10);
		textField_22.setBackground(Color.LIGHT_GRAY);
		textField_22.setBounds(497, 219, 125, 40);
		contentPane.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setText("\uC0DD\uD65C\uAD00\uBE44");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_23.setEditable(false);
		textField_23.setColumns(10);
		textField_23.setBackground(Color.LIGHT_GRAY);
		textField_23.setBounds(709, 219, 125, 40);
		contentPane.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setText("\uC0DD\uD65C\uAD00\uBE44");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_24.setEditable(false);
		textField_24.setColumns(10);
		textField_24.setBackground(Color.LIGHT_GRAY);
		textField_24.setBounds(709, 339, 125, 40);
		contentPane.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setText("\uC0DD\uD65C\uAD00\uBE44");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_25.setEditable(false);
		textField_25.setColumns(10);
		textField_25.setBackground(Color.LIGHT_GRAY);
		textField_25.setBounds(709, 386, 125, 40);
		contentPane.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setText("\uC0DD\uD65C\uAD00\uBE44");
		textField_26.setHorizontalAlignment(SwingConstants.CENTER);
		textField_26.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_26.setEditable(false);
		textField_26.setColumns(10);
		textField_26.setBackground(Color.LIGHT_GRAY);
		textField_26.setBounds(709, 432, 125, 40);
		contentPane.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setText("\uC2DD\uC0AC\uAD6C\uBD84");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_27.setEditable(false);
		textField_27.setColumns(10);
		textField_27.setBackground(Color.LIGHT_GRAY);
		textField_27.setBounds(422, 339, 125, 40);
		contentPane.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setText("\uC2DD\uC0AC\uAD6C\uBD84");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_28.setEditable(false);
		textField_28.setColumns(10);
		textField_28.setBackground(Color.LIGHT_GRAY);
		textField_28.setBounds(422, 386, 125, 40);
		contentPane.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setText("\uC2DD\uC0AC\uAD6C\uBD84");
		textField_29.setHorizontalAlignment(SwingConstants.CENTER);
		textField_29.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_29.setEditable(false);
		textField_29.setColumns(10);
		textField_29.setBackground(Color.LIGHT_GRAY);
		textField_29.setBounds(422, 432, 125, 40);
		contentPane.add(textField_29);

		textField_30 = new JTextField();
		textField_30.setText("1\uC9C0\uB9DD");
		textField_30.setHorizontalAlignment(SwingConstants.CENTER);
		textField_30.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_30.setEditable(false);
		textField_30.setColumns(10);
		textField_30.setBackground(Color.LIGHT_GRAY);
		textField_30.setBounds(35, 339, 125, 40);
		contentPane.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setText("2\uC9C0\uB9DD");
		textField_31.setHorizontalAlignment(SwingConstants.CENTER);
		textField_31.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_31.setEditable(false);
		textField_31.setColumns(10);
		textField_31.setBackground(Color.LIGHT_GRAY);
		textField_31.setBounds(35, 386, 125, 40);
		contentPane.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setText("3\uC9C0\uB9DD");
		textField_32.setHorizontalAlignment(SwingConstants.CENTER);
		textField_32.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_32.setEditable(false);
		textField_32.setColumns(10);
		textField_32.setBackground(Color.LIGHT_GRAY);
		textField_32.setBounds(35, 432, 125, 40);
		contentPane.add(textField_32);

		textField_33 = new JTextField();
		textField_33.setText("\uD734\uB300\uC804\uD654\uBC88\uD638");
		textField_33.setHorizontalAlignment(SwingConstants.CENTER);
		textField_33.setFont(new Font("±º∏≤", Font.PLAIN, 16));
		textField_33.setEditable(false);
		textField_33.setColumns(10);
		textField_33.setBackground(Color.LIGHT_GRAY);
		textField_33.setBounds(35, 482, 135, 40);
		contentPane.add(textField_33);
		setVisible(true);
		setSize(1050, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Dormitory_Application();
	}
}