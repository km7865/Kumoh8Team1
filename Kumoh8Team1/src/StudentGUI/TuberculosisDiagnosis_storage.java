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
   


   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               TuberculosisDiagnosis_storage frame = new TuberculosisDiagnosis_storage(p, writer, reader);
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public TuberculosisDiagnosis_storage(Protocol p_t, ObjectOutputStream oos, ObjectInputStream ois) {
	  p = p_t;
	  writer = oos;
	  reader = ois;
	  
      this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
      setTitle("�������ܼ� ����");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 815, 675);
      setVisible(true);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      label.setBounds(5, 59, 791, 536);
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setFont(new Font("����", Font.PLAIN, 35));
      contentPane.add(label);

      JButton btnNewButton = new JButton("���� ã��");
      btnNewButton.setBounds(56, 605, 140, 25);
      btnNewButton.addActionListener(new OpenActionListener());
      contentPane.add(btnNewButton);
      textField = new JTextField(); // ���� ���
      textField.setEditable(false);
      textField.setBounds(194, 605, 520, 25);
      contentPane.add(textField);
      textField.setColumns(10);

      JButton btnNewButton_1 = new JButton("����");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Socket theSocket = null;
            // String host;
            if (filePath != null) {
               File file = new File(filePath); // filepath : Ŭ���̾�Ʈ�� ������ ����
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
                     JOptionPane.showMessageDialog(null, "������ ���������� �̷�� �����ϴ�.");
                  else if (p.getCode() == 2) {
                     String err = (String) p.getBody();
                     JOptionPane.showMessageDialog(null, err); // ������ �ƴ� or ����Ⱓ �ƴ�
                  }
               }
            } else
               JOptionPane.showMessageDialog(null, "������ ������ �ּ���!");
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
            JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "���", JOptionPane.WARNING_MESSAGE);
            return;
         }
         filePath = chooser.getSelectedFile().getPath();
         label.setIcon(new ImageIcon(filePath));
         textField.setText(filePath);
      }
   }
}