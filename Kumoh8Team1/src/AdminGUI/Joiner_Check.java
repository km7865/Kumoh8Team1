// 입사자 조회

package AdminGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
import AdminGUI.*;

public class Joiner_Check extends JFrame {
	private static Protocol p;
	private static ObjectOutputStream writer;
	private static ObjectInputStream reader;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joiner_Check frame = new Joiner_Check(p, writer, reader);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Joiner_Check(Protocol p_t, ObjectOutputStream writer_t, ObjectInputStream reader_t) {
		p = p_t;
		writer = writer_t;
		reader = reader_t;
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("입사자 조회");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			p.makePacket(24, 1, 0, null);
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
			String columnNames[] = {"학번", "이름", "생활관", "호실", "침대번호"};

			// 테이블에 출력할 데이터 배열
			String[][] data = (String [][])p.getBody();		//받아온 데이터
			for (int i = 0; i < data.length; i++) {
	            System.out.println(data[i][0] + " " + data[i][1]);
	        }

			// 내용 수정 못하게 함
			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int rowIndex, int mCollindex) {
					return false;
				}
			};
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
