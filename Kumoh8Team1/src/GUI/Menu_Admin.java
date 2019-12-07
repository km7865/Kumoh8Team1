// 관리자 메뉴

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_Admin extends JFrame {

	private JPanel contentPane;

	public Menu_Admin() {
		setTitle("관리자 메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("로그아웃");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				frame.setCursor();
				dispose();
			}
		});
		btnNewButton_1.setBounds(634, 10, 85, 30);
		contentPane.add(btnNewButton_1);

		JButton button = new JButton("선발일정 등록");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xlsm", "xlsb", "xls", "xml", "xltx",
						"xltm", "xlt", "xlam", "xla", "xps");
				chooser.setFileFilter(filter);

				int ret = chooser.showSaveDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		button.setBounds(45, 87, 285, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("생활관비 등록");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xlsm", "xlsb", "xls", "xml", "xltx",
						"xltm", "xlt", "xlam", "xla", "xps");
				chooser.setFileFilter(filter);

				int ret = chooser.showSaveDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		button_1.setBounds(353, 87, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("입사자 등록 및 조회");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Enroll_and_Check();
			}
		});
		button_2.setBounds(45, 162, 285, 50);
		contentPane.add(button_2);

		JButton button_3 = new JButton("입사선발자 결과등록");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Result_Enroll();
			}
		});
		button_3.setBounds(353, 162, 285, 50);
		contentPane.add(button_3);

		JButton button_4 = new JButton("결핵진단서 업로드 및 제출확인");
		button_4.setBounds(45, 235, 285, 50);
		contentPane.add(button_4);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Menu_Admin();
	}
}