// ������ �޴�

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Network.Protocol;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class Menu_Admin extends JFrame {
	private static Protocol p;
	private static OutputStream os;
	private static ObjectOutputStream writer;
	private static InputStream is;
	private static ObjectInputStream reader;

	private JPanel contentPane;

	public Menu_Admin() {
		setTitle("������ �޴�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("�α׾ƿ�");
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

		JButton button = new JButton("�������� ���");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xlsm", "xlsb", "xls", "xml", "xltx",
						"xltm", "xlt", "xlam", "xla", "xps");
				chooser.setFileFilter(filter);

				int ret = chooser.showSaveDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String filePath = chooser.getSelectedFile().getPath();
				
				if(filePath != null)
				{
					File file = new File(filePath); // filepath : Ŭ���̾�Ʈ�� ������ ����
					long length = file.length();
					System.out.println(file.toString() + " length : " + length + "byte");
					try
					{
						p = new Protocol<File>(15, 3, file);
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
						if(p.getCode() == 1)
							JOptionPane.showMessageDialog(null, "����� ���������� �̷�� �����ϴ�.");
						else if(p.getCode() == 2)
						{
							String err = (String)p.getBody();
							JOptionPane.showMessageDialog(null, err); //���Ͼ���� �߸���	
						}
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, "������ ������ �ּ���!");
			}
		});
		button.setBounds(45, 87, 285, 50);
		contentPane.add(button);

		JButton button_1 = new JButton("��Ȱ���� ���");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xlsm", "xlsb", "xls", "xml", "xltx",
						"xltm", "xlt", "xlam", "xla", "xps");
				chooser.setFileFilter(filter);

				int ret = chooser.showSaveDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String filePath = chooser.getSelectedFile().getPath();
				
				if(filePath != null)
				{
					File file = new File(filePath); // filepath : Ŭ���̾�Ʈ�� ������ ����
					long length = file.length();
					System.out.println(file.toString() + " length : " + length + "byte");
					try
					{
						p = new Protocol<File>(15, 3, file);
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
						if(p.getCode() == 1)
							JOptionPane.showMessageDialog(null, "����� ���������� �̷�� �����ϴ�.");
						else if(p.getCode() == 2)
						{
							String err = (String)p.getBody();
							JOptionPane.showMessageDialog(null, err); //���Ͼ���� �߸���	
						}
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, "������ ������ �ּ���!");
			}
		});
		button_1.setBounds(353, 87, 285, 50);
		contentPane.add(button_1);

		JButton button_2 = new JButton("�Ի��� ��� �� ��ȸ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Enroll_and_Check();
			}
		});
		button_2.setBounds(45, 162, 285, 50);
		contentPane.add(button_2);

		JButton button_3 = new JButton("�Ի缱���� ������");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joiner_Result_Enroll();
			}
		});
		button_3.setBounds(353, 162, 285, 50);
		contentPane.add(button_3);

		JButton button_4 = new JButton("�������ܼ� ���ε� �� ����Ȯ��");
		button_4.setBounds(45, 235, 285, 50);
		contentPane.add(button_4);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Menu_Admin();
	}
}