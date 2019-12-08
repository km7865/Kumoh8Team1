// Í≤∞ÌïµÏßÑÎã®?Ñú ?†úÏ∂? (?ïô?Éù)

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;

import Network.Protocol;
import tableClass.*;

import tableClass.User;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

public class TuberculosisDiagnosis_storage extends JFrame {
	private static Protocol p;
	private static OutputStream os;
	private static ObjectOutputStream writer;
	private static InputStream is;
	private static ObjectInputStream reader;
	private JPanel contentPane;
	JLabel label = new JLabel("");
	private JTextField textField;
	private static String filePath;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TuberculosisDiagnosis_storage frame = new TuberculosisDiagnosis_storage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TuberculosisDiagnosis_storage() {
		this.setResizable(false); // ÏµúÎ??ôî ?ã®Ï∂? ?óÜ?ï†Í∏?
		setTitle("Í≤∞ÌïµÏßÑÎã®?Ñú ?†úÏ∂?");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 815, 675);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		label.setBounds(5, 59, 791, 536);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Íµ¥Î¶º", Font.PLAIN, 35));
		contentPane.add(label);

		JButton btnNewButton = new JButton("?åå?ùº Ï∞æÍ∏∞");
		btnNewButton.setBounds(56, 605, 140, 25);
		btnNewButton.addActionListener(new OpenActionListener());
		contentPane.add(btnNewButton);
		textField = new JTextField(); // ?åå?ùº Í≤ΩÎ°ú
		textField.setEditable(false);
		textField.setBounds(194, 605, 520, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("?†úÏ∂?");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Socket theSocket = null;
				// String host;
				if (filePath != null) {
					File file = new File(filePath); // filepath : ?Å¥?ùº?ù¥?ñ∏?ä∏Í∞? ?Ñ†?Éù?ïú ?åå?ùº
					long length = file.length();
					System.out.println(file.toString() + " length : " + length + "byte");
					try {
						p = new Protocol<File>(15, 3, file);
						writer.writeObject(p);
						writer.flush();
						p = (Protocol) reader.readObject();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (p.getSubType() == 4) {
						if (p.getCode() == 1)
							JOptionPane.showMessageDialog(null, "?†Ñ?Ü°?ù¥ ?†ï?ÉÅ?†Å?úºÎ°? ?ù¥Î£®Ïñ¥ Ï°åÏäµ?ãà?ã§.");
						else if (p.getCode() == 2) {
							String err = (String) p.getBody();
							JOptionPane.showMessageDialog(null, err); // ?†úÏ∂úÎ??ÉÅ ?ïÑ?ãò or ?†úÏ∂úÍ∏∞Í∞? ?ïÑ?ãò
						}
					}
				} else
					JOptionPane.showMessageDialog(null, "?åå?ùº?ùÑ ?Ñ†?Éù?ï¥ Ï£ºÏÑ∏?öî!");
			}
		});
		btnNewButton_1.setBounds(680, 23, 91, 23);
		contentPane.add(btnNewButton_1);
	}

	class OpenActionListener implements ActionListener {
		JFileChooser chooser;

		OpenActionListener() {
			chooser = new JFileChooser();
		}

		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "gif", "jpeg", "bmp", "png", "psd",
					"ai", "sketch", "tif", "tiff", "tga", "webp", "jpg");
			chooser.setFileFilter(filter);
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "?åå?ùº?ùÑ ?Ñ†?Éù?ïòÏß? ?ïä?ïò?äµ?ãà?ã§!", "Í≤ΩÍ≥†", JOptionPane.WARNING_MESSAGE);
				return;
			}
			filePath = chooser.getSelectedFile().getPath();
			label.setIcon(new ImageIcon(filePath));
			textField.setText(filePath);
		}
	}
}