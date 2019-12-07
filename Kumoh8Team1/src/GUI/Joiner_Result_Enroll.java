// �Ի缱���� ������

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
		setTitle("�Ի缱���� ������");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.BLACK);
		textArea_1.setFont(new Font("����", Font.BOLD, 18));
		textArea_1.setEditable(false);
		textArea_1.setText("\u25C8 �Ի缱���� ������");
		textArea_1.setBackground(SystemColor.control);
		textArea_1.setBounds(35, 27, 210, 20);
		contentPane.add(textArea_1);

		JButton button = new JButton("��");
		button.setBounds(35, 165, 182, 48);
		contentPane.add(button);

		JButton button_1 = new JButton("�ƴϿ�");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "���θ޴��� �̵��մϴ�.");
				dispose();
			}
		});
		button_1.setBounds(274, 165, 182, 48);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("����", Font.BOLD, 16));
		textField.setText("��� ��� �Ͻðڽ��ϱ�?");
		textField.setBounds(35, 57, 421, 80);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}