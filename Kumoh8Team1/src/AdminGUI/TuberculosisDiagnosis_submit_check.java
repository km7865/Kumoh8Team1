// 결핵진단서 제출확인

package AdminGUI;
import GUI.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Network.Protocol;
import tableClass.*;

public class TuberculosisDiagnosis_submit_check extends JFrame {
	private JPanel contentPane;
	private Socket socket;
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;


	public TuberculosisDiagnosis_submit_check(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t,Socket sk) {
		socket = sk;
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("결핵진단서 제출확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			p.makePacket(26, 1, 0, null);
			writer.writeObject(p);
			writer.flush();
			writer.reset();
			p = (Protocol) reader.readObject();
			System.out.println(p.getMainType() + " " + p.getSubType());
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		if(p.getCode() == 1)
		{
			// 테이블에 출력할 컬럼 이름 배열
			String columnNames[] = {"신청번호", "학번", "결핵진단서 제출상태"};

			// 테이블에 출력할 데이터 배열
			int i = 4; // 범위
			String data[][] = (String [][])p.getBody(); // 데이터 들어갈 부분

			DefaultTableModel model = new DefaultTableModel(data, columnNames);
			JTable tbl = new JTable(model);
			tbl.setRowHeight(25);

			// JTable tbl = new JTable(data,columnNames);
			// Table은 JScrollPane위에 출력해야 컬럼 이름이 출력된다! 명심할것
			JScrollPane scroll = new JScrollPane(tbl);
			scroll.getVerticalScrollBar().setUnitIncrement(100); // 스크롤 속도
			scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			getContentPane().add(scroll);
			scroll.setSize(772, 583);
			scroll.setLocation(12, 10);
		}
		else
		{
			String err = (String) p.getBody();
			JOptionPane.showMessageDialog(null, err);
			dispose();
		}
	}
}