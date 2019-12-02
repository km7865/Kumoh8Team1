// 결핵진단서 제출

package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

public class TuberculosisDiagnosis_storage extends JFrame {
	private JPanel contentPane;
	JLabel label = new JLabel("");
	private JTextField textField;
	private static String filePath;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TuberculosisDiagnosis_storage frame = new TuberculosisDiagnosis_storage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TuberculosisDiagnosis_storage() {
		setTitle("결핵진단서 제출");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 815, 675);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		label.setBounds(5, 5, 791, 590);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 35));
		contentPane.add(label);

		JButton btnNewButton = new JButton("파일 찾기");
		btnNewButton.setBounds(56, 605, 140, 25);
		btnNewButton.addActionListener(new OpenActionListener());
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(194, 605, 520, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("제출");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket theSocket = null;
				String host;
				InputStream is;
				BufferedReader reader, userInput;
				OutputStream os;
				BufferedWriter writer;
				String theLine;

				File file = new File(filePath); // filepath : 클라이언트가 선택한 파일
				long length = file.length();
				System.out.println(file.toString() + " length : " + length);

				byte[] buf = new byte[1024];

				host = "192.168.212.248"; // 로컬 호스트를 사용

				try {
					theSocket = new Socket(host, 5000); // echo 서버에 접속한다.

					is = new FileInputStream(file);
					os = theSocket.getOutputStream();
					writer = new BufferedWriter(new OutputStreamWriter(os));
					int count;
					while ((count = is.read(buf)) != -1) {
						os.write(buf, 0, count);

						System.out.println("서버에 전송중 패킷 크기 : " + count);
					}
				} catch (IOException e1) {
				} finally {
					try {
						theSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(680, 23, 91, 23);
		contentPane.add(btnNewButton_1);
	}

	class OpenActionListener implements ActionListener {
		JFileChooser chooser;

		OpenActionListener() {
			chooser = new JFileChooser();
		}

		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "gif", "jpeg", "bmp", "png",
					"psd", "ai", "sketch", "tif", "tiff", "tga", "webp", "jpg");
			chooser.setFileFilter(filter);

			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			filePath = chooser.getSelectedFile().getPath();
			label.setIcon(new ImageIcon(filePath));
			textField.setText(filePath);
		}
	}
}