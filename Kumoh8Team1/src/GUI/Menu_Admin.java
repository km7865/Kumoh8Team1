// 占쏙옙占쏙옙占쏙옙 占쌨댐옙
package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu_Admin extends JFrame {

	private JPanel contentPane;

	public Menu_Admin() {
		setTitle("\uAD00\uB9AC\uC790 \uBA54\uB274");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("\uB85C\uADF8\uC544\uC6C3");
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

		JButton button = new JButton("\uC120\uBC1C\uC77C\uC815 \uB4F1\uB85D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Selection_Schedule_Enroll();
			}
		});
		button.setBounds(45, 87, 285, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("\uC0DD\uD65C\uAD00\uBE44 \uB4F1\uB85D");
		button_1.setBounds(353, 87, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("\uC785\uC0AC\uC790 \uB4F1\uB85D \uBC0F \uC870\uD68C");
		button_2.setBounds(45, 162, 285, 50);
		contentPane.add(button_2);

		JButton button_3 = new JButton("\uC785\uC0AC\uC120\uBC1C\uC790 \uACB0\uACFC\uB4F1\uB85D");
		button_3.setBounds(353, 162, 285, 50);
		contentPane.add(button_3);

		JButton button_4 = new JButton("\uACB0\uD575\uC9C4\uB2E8\uC11C \uC5C5\uB85C\uB4DC \uBC0F \uC81C\uCD9C\uD655\uC778");
		button_4.setBounds(45, 235, 285, 50);
		contentPane.add(button_4);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Menu_Admin();
	}
}