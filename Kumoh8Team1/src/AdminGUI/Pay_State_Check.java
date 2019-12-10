// (관리자) 입사선발자 납부 상태 갱신

package AdminGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pay_State_Check extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private int code;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pay_State_Check frame = new Pay_State_Check();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pay_State_Check() {
		setVisible(true);
		setTitle("입사선발자 납부 상태 갱신");
		this.setResizable(false); // 최대화 단추 없앰
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("학번");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 86, 90, 45);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.doClick(); // 엔터 클릭시 확인 버튼 눌러짐
			}
		});
		textField.setBounds(140, 87, 260, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("제출자의 학번을 입력해 주세요!");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(30, 33, 350, 43);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (code == 1) {
					JOptionPane.showMessageDialog(null, "납부 상태가 변경되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "에러!!");
				}
			}
		});
		btnNewButton.setBounds(355, 180, 117, 38);
		contentPane.add(btnNewButton);
	}
}