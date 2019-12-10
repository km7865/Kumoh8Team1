package AdminGUI;
import GUI.*;
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

import Network.Protocol;
import tableClass.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Pay_State_Check extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private Socket socket;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;


	public Pay_State_Check(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois,Socket sk) {
		socket = sk;
		p = p_t;
		writer = oos;
		reader = ois;
		setVisible(true);
		setTitle("�Ի缱���� ���� ���� ����");
		this.setResizable(false); // �ִ�ȭ ���� ����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("�й�");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 86, 90, 45);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.doClick(); // ���� Ŭ���� Ȯ�� ��ư ������
			}
		});
		textField.setBounds(140, 87, 260, 45);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("�������� �й��� �Է��� �ּ���!");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(30, 33, 350, 43);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton("Ȯ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() != null && textField.getText().length() == 8)
				{
					try {
						p.makePacket(28, 1, 0, textField.getText());
						writer.writeObject(p);
						writer.flush();
						writer.reset();
						p = (Protocol) reader.readObject();

					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}

					if (p.getSubType() == 2) {
						if(p.getCode() == 1)
						{
							JOptionPane.showMessageDialog(null, "���λ��°� ���������� ���ε� �Ǿ����ϴ�.");
							dispose();
						}

						else
						{
							String err = (String) p.getBody();
							JOptionPane.showMessageDialog(null, err);
							dispose();
						}  
					}  
				}

				else
					JOptionPane.showMessageDialog(null, "�Է°��� �ùٸ��� �ʽ��ϴ�.");
			}
		});
		btnNewButton.setBounds(355, 180, 117, 38);
		contentPane.add(btnNewButton);
	}
}