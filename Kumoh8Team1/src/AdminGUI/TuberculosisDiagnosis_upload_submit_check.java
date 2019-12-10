// 결핵진단서 업로드 및 제출확인

package AdminGUI;
import GUI.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Network.Protocol;
import tableClass.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class TuberculosisDiagnosis_upload_submit_check extends JFrame {
	private Socket socket;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private static String ip;
	private JPanel contentPane;


	public TuberculosisDiagnosis_upload_submit_check(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t, String ip_t,Socket sk) {
		socket = sk;
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		ip = ip_t;
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("결핵진단서 업로드 및 제출확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("결핵진단서 업로드");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TuberculosisDiagnosis_upload_student_id(p, writer, reader, ip, socket);
			}
		});
		btnNewButton.setBounds(30, 40, 145, 40);
		contentPane.add(btnNewButton);

		JButton button = new JButton("결핵진단서 제출확인");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TuberculosisDiagnosis_submit_check(p, writer, reader, socket);
			}
		});
		button.setBounds(195, 40, 145, 40);
		contentPane.add(button);
	}
}