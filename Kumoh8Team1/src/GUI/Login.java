// 로그인
package GUI;

import java.io.*;
import java.net.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

// JOptionPane.showMessageDialog(owner, "로그인 성공"); //this : 부모 윈도우 중앙에 표시,  this가 안되므로 위에서 owner = this,  null : 화면 중앙

public class Login extends JFrame {
	private static JTextField textLogin;
	private JPasswordField textPassword;
	private JButton btnOk;
	private Login owner; // 텍스트 상자
	private JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setCursor();
					// frame.setResizable(false); // 최대화 단추 없애기
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setCursor() {
		textLogin.requestFocusInWindow(); // 로그인 창을 띄우면 아이디 텍스트필드에 자동으로 커서가 간다.
	}

	public Login() {
		super("로그인"); // 제목

		owner = this; // 생성자

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 270);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(60, 92, 60, 40);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setBounds(60, 142, 60, 40);
		getContentPane().add(lblNewLabel_1);

		textLogin = new JTextField();
		textLogin.setBounds(146, 93, 259, 39);
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		textPassword = new JPasswordField();
		textPassword.setBounds(146, 143, 260, 40);
		textPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOk.doClick(); // 엔터
			}
		});
		getContentPane().add(textPassword);

		btnOk = new JButton("\uB85C\uADF8\uC778");
		btnOk.setBounds(416, 92, 80, 90);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textLogin.getText().equals("20150129") && textPassword.getText().equals("1234")) {
					new Menu_Student();
					dispose();
				} else if (textLogin.getText().equals("F20150129") && textPassword.getText().equals("1234")) {
					new Menu_Admin();
					dispose();
				} else {
					JOptionPane.showMessageDialog(owner, "로그인 실패");
					textPassword.setText("");
					textPassword.requestFocus(); // 마우스
				}
			}
		});
		getContentPane().add(btnOk);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("<\uC2DC\uC2A4\uD15C \uC0AC\uC6A9\uC548\uB0B4>\r\n\r\n \u25A0 \uC544\uC774\uB514(\uD559\uC0DD : \uD559\uBC88, \uAD50\uC9C1\uC6D0 : \uAD50\uC9C1\uC6D0\uCF54\uB4DC) * \uC544\uC774\uB514\uB294 \uB300\uBB38\uC790\uB9CC \uC785\uB825 \uAC00\uB2A5");
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(32, 20, 497, 62);
		getContentPane().add(textArea);
	}
}