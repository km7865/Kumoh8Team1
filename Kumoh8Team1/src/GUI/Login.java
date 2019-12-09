

package GUI;

import tableClass.*;
import Network.*;

import java.io.*;
import java.util.*;
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

public class Login extends JFrame {
	private static JTextField textLogin;
	private JPasswordField textPassword;
	private JButton btnOk;
	private Login owner;
	private JTextArea textArea;
	private static Protocol p;
	private static Socket socket;
	private static OutputStream os;
	private static ObjectOutputStream writer;
	private static InputStream is;
	private static ObjectInputStream reader;

	public static void main(String[] args) {
		String host = null;
		Scanner sc = new Scanner(System.in);

		System.out.print("서버의 IP 입력 : ");
		host = sc.next();
		try
		{
			socket = new Socket(host, 5000);
			os = socket.getOutputStream();
			writer = new ObjectOutputStream(os);
			is = socket.getInputStream();
			reader = new ObjectInputStream(is);
			//p = new Protocol_test(1, 2, "클라이언트", "접속");
			//writer.writeObject(p);		
			// writer.flush();
			//p = (Protocol_test)reader.readObject();
			//System.out.println(p.getMsg2());

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Login frame = new Login();
						frame.setVisible(true);
						frame.setCursor();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		catch (IOException e) {
			// TODO 
			e.printStackTrace();
		}

	}

	public void setCursor() {
		textLogin.requestFocusInWindow();
	}

	public Login() {

		super("로그인");

		owner = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 270);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(60, 92, 60, 40);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
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
				btnOk.doClick();
			}
		});
		getContentPane().add(textPassword);

		btnOk = new JButton("로그인");
		btnOk.setBounds(416, 92, 80, 90);
		btnOk.addActionListener(new ActionListener() {

			@Override				
			public void actionPerformed(ActionEvent e) {
				int userType = 0;
				try
				{
					p = new Protocol<User>(1, 1, new User(textLogin.getText(), textPassword.getText()));
					writer.writeObject(p);
					writer.flush();
					p = (Protocol)reader.readObject();

				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if(p.getSubType() == 2)
				{
					if(p.getCode() != 3)
					{
						//사용자 타입에 따라 학생 메뉴 / 관리자 메뉴
						userType = p.getCode();
						if(userType == 1) {
							new Menu_Student(p, writer, reader);
							dispose();
						}
						else if(userType == 2 || userType == 3) {
							new Menu_Admin(p, writer, reader);
							dispose();
						}
						else {
							//오류 메세지 출력
						}
					}
					else if(p.getCode() == 3)
					{
						JOptionPane.showMessageDialog(owner, "로그인 실패");
						textPassword.setText("");
						textPassword.requestFocus();
					}
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