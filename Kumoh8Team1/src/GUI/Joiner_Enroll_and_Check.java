// �Ի��� ��� �� ��ȸ

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Network.Protocol;
import tableClass.*;

import javax.swing.JButton;

public class Joiner_Enroll_and_Check extends JFrame {
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joiner_Enroll_and_Check frame = new Joiner_Enroll_and_Check(p, writer, reader);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Joiner_Enroll_and_Check(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setVisible(true);
		setTitle("�Ի��� ��� �� ��ȸ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("�Ի��� ���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					p = new Protocol(23, 1);
					writer.writeObject(p);
					writer.flush();
					p = (Protocol) reader.readObject();

				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (p.getSubType() == 2) {
					if (p.getCode() == 1) {
						JOptionPane.showMessageDialog(null, "�Ի��ڵ��� ���������� ��ϵǾ����ϴ�.");
						dispose();
					} else if (p.getCode() == 2) {
						String err = (String) p.getBody();
						JOptionPane.showMessageDialog(null, err); // �Ի缱���ڰ� ���� ���
						dispose();
					}

				}
			}
		});
		btnNewButton.setBounds(30, 40, 110, 40);
		contentPane.add(btnNewButton);

		JButton button = new JButton("�Ի��� ��ȸ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Check(p, writer, reader); // �Ի��� ��ȸ
			}
		});
		button.setBounds(195, 40, 110, 40);
		contentPane.add(button);
	}
}
