// �Ի��� ��� �� ��ȸ

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Joiner_Enroll_and_Check extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joiner_Enroll_and_Check frame = new Joiner_Enroll_and_Check();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Joiner_Enroll_and_Check() {
		setVisible(true);
		setTitle("�Ի��� ��� �� ��ȸ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("�Ի��� ���");
		btnNewButton.setBounds(30, 40, 110, 40);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("�Ի��� ��ȸ");
		button.setBounds(195, 40, 110, 40);
		contentPane.add(button);
	}
}