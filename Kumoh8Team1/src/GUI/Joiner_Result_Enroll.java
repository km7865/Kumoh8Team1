// 입사선발자 결과등록

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.DropMode;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Joiner_Result_Enroll extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joiner_Result_Enroll frame = new Joiner_Result_Enroll();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Joiner_Result_Enroll() {
		setTitle("입사선발자 결과등록");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.BLACK);
		textArea_1.setFont(new Font("돋움", Font.BOLD, 18));
		textArea_1.setEditable(false);
		textArea_1.setText("\u25C8 입사선발자 결과등록");
		textArea_1.setBackground(SystemColor.control);
		textArea_1.setBounds(35, 27, 210, 20);
		contentPane.add(textArea_1);

		JButton button = new JButton("예");
		button.setBounds(35, 165, 182, 48);
		contentPane.add(button);

		JButton button_1 = new JButton("아니요");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "메인메뉴로 이동합니다.");
				dispose();
			}
		});
		button_1.setBounds(274, 165, 182, 48);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("굴림", Font.BOLD, 16));
		textField.setText("결과 등록 하시겠습니까?");
		textField.setBounds(35, 57, 421, 80);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}