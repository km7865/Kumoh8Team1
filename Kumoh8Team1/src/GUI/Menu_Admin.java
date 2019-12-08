// 관리자 메뉴

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
				
				String filePath = chooser.getSelectedFile().getPath();
				
				if(filePath != null)
				{
					File file = new File(filePath); // filepath : 클라이언트가 선택한 파일
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
							JOptionPane.showMessageDialog(null, "등록이 정상적으로 이루어 졌습니다.");
						else if(p.getCode() == 2)
						{
							String err = (String)p.getBody();
							JOptionPane.showMessageDialog(null, err); //파일양식이 잘못됨	
						}
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, "파일을 선택해 주세요!");
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
				
				String filePath = chooser.getSelectedFile().getPath();
				
				if(filePath != null)
				{
					File file = new File(filePath); // filepath : 클라이언트가 선택한 파일
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
							JOptionPane.showMessageDialog(null, "등록이 정상적으로 이루어 졌습니다.");
						else if(p.getCode() == 2)
						{
							String err = (String)p.getBody();
							JOptionPane.showMessageDialog(null, err); //파일양식이 잘못됨	
						}
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, "파일을 선택해 주세요!");
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