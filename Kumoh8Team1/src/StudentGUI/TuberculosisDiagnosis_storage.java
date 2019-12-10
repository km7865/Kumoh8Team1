// 결핵진단서 제출 (학생)
package StudentGUI;

import Network.*;
import tableClass.*;

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
	   private static ObjectOutputStream writer;
	   private static ObjectInputStream reader;
	   private JPanel contentPane;
	   JLabel label = new JLabel("");
	   private JTextField textField;
	   private static String filePath;
	   private static String ip;


	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               TuberculosisDiagnosis_storage frame = new TuberculosisDiagnosis_storage(p, writer, reader, ip);
	               frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }

	   public TuberculosisDiagnosis_storage(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois, String ip_t) {
		  p = p_t;
		  writer = oos;
		  reader = ois;
		  ip = ip_t;
		  
	      this.setResizable(false); // 최대화 단추 없애기
	      setTitle("결핵진단서 제출");
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setBounds(100, 100, 815, 675);
	      setVisible(true);
	      contentPane = new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(null);
	      label.setBounds(5, 59, 791, 536);
	      label.setHorizontalAlignment(SwingConstants.CENTER);
	      label.setFont(new Font("굴림", Font.PLAIN, 35));
	      contentPane.add(label);

	      JButton btnNewButton = new JButton("파일 찾기");
	      btnNewButton.setBounds(56, 605, 140, 25);
	      btnNewButton.addActionListener(new OpenActionListener());
	      contentPane.add(btnNewButton);
	      textField = new JTextField(); // 파일 경로
	      textField.setEditable(false);
	      textField.setBounds(194, 605, 520, 25);
	      contentPane.add(textField);
	      textField.setColumns(10);

	      JButton btnNewButton_1 = new JButton("제출");
	      btnNewButton_1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            // Socket theSocket = null;
	            // String host;
	        	 if (filePath != null)
	        	 {
	        		 try {
	        			 System.out.println(p.getMainType() + " " + p.getSubType());
	                	 p.makePacket(15, 3, 0, p.getBody());
	                	 writer.writeObject(p);
	                	 writer.flush();
	                	 writer.reset();

	                	 File file = new File(filePath);
	                     if (!file.exists()) {
	                         System.out.println("File not Exist.");
	                         System.exit(0);
	                     }
	                      
	                     long fileSize = file.length();
	                     System.out.println(fileSize);
	                     long totalReadBytes = 0;
	                     byte[] buffer = new byte[10000];
	                     int readBytes;
	                     double startTime = 0;
	                     
	                     FileInputStream fis = new FileInputStream(file);
	                     Socket socket_t = new Socket(ip, 5001);
	                     if(!socket_t.isConnected()){
	                         System.out.println("Socket Connect Error.");
	                         System.exit(0);
	                     }
	                      
	                     startTime = System.currentTimeMillis();
	                     OutputStream os = socket_t.getOutputStream();
	                     while ((readBytes = fis.read(buffer)) > 0) {
	                     	System.out.println(readBytes);
	                         os.write(buffer, 0, readBytes);
	                         totalReadBytes += readBytes;
	                         System.out.println("In progress: " + totalReadBytes + "/"
	                                 + fileSize + " Byte(s) ("
	                                 + (totalReadBytes * 100 / fileSize) + " %)");
	                     }
	                     
	                     System.out.println("File transfer completed.");
	                     fis.close();
	                     os.close();
	                     socket_t.close();
	                     System.out.println(p.getMainType() + " " + p.getSubType());
	                     p = (Protocol) reader.readObject();
	                     System.out.println(p.getMainType() + " " + p.getSubType());
	                 } catch (UnknownHostException e1) {
	                     // TODO Auto-generated catch block
	                     e1.printStackTrace();
	                 } catch (IOException e1) {
	                     // TODO Auto-generated catch block
	                     e1.printStackTrace();
	                 } catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

	                   if (p.getSubType() == 4) {
	                      if (p.getCode() == 1)
	                    	  JOptionPane.showMessageDialog(null, "결핵진단서 전송이 정상적으로 이루어 졌습니다.");
	                         
	                      else if (p.getCode() == 2) {
	                         String err = (String) p.getBody();
	                         JOptionPane.showMessageDialog(null, err); // 제출대상 아님 or 제출기간 아님
	                      }
	                   }
	                   else
	                	   JOptionPane.showMessageDialog(null, "파일을 선택해 주세요!");
	        	 }
	             
	        	 else
	                 JOptionPane.showMessageDialog(null, "파일을 올려주세요.");
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
	            JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
	            return;
	         }
	         filePath = chooser.getSelectedFile().getPath();
	         label.setIcon(new ImageIcon(filePath));
	         textField.setText(filePath);
	      }
	   }
	}