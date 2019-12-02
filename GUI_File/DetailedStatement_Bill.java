// ÀÔ»ç½ÅÃ» ³»¿ªÁ¶È¸ ¹× °íÁö¼­ Ãâ·Â

package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailedStatement_Bill extends JFrame {

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
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailedStatement_Bill frame = new DetailedStatement_Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DetailedStatement_Bill() {
		setVisible(true);
		setTitle("\uC785\uC0AC\uC2E0\uCCAD \uB0B4\uC5ED\uC870\uD68C \uBC0F \uACE0\uC9C0\uC11C \uCD9C\uB825");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 970, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setText("\u25C8 \uD559\uC801\uB0B4\uC5ED");
		textField.setForeground(Color.RED);
		textField.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(12, 37, 169, 40);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setText("\uD559\uBC88");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(12, 85, 125, 40);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText("\uC131\uBA85");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(12, 131, 125, 40);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(136, 131, 190, 40);
		contentPane.add(textField_3);

		textField_4 = new JTextField("");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(136, 85, 190, 40);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setText("\uD559\uACFC");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(325, 85, 125, 40);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setText("\uD559\uB144(\uD559\uAE30\uCC28)");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(325, 131, 125, 40);
		contentPane.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(449, 131, 190, 40);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(449, 85, 190, 40);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setText("\uCD5C\uADFC\uD559\uC801");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setBounds(638, 85, 125, 40);
		contentPane.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setText("\uD559\uC801\uBCC0\uB3D9\uC77C");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBackground(Color.LIGHT_GRAY);
		textField_10.setBounds(638, 131, 125, 40);
		contentPane.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(762, 131, 190, 40);
		contentPane.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(762, 85, 190, 40);
		contentPane.add(textField_12);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ãâ·ÂÁßÀÔ´Ï´Ù...");
			}
		});
		button.setBackground(SystemColor.control);
		button.setIcon(new ImageIcon("C:\\Users\\tmakd\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\icon.PNG"));
		button.setBounds(12, 562, 132, 91);
		contentPane.add(button);

		textField_13 = new JTextField();
		textField_13.setText("\u25C8 \uACE0\uC9C0\uC11C \uCD9C\uB825");
		textField_13.setForeground(Color.RED);
		textField_13.setFont(new Font("±¼¸²", Font.PLAIN, 17));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(12, 512, 169, 40);
		contentPane.add(textField_13);
	}
}