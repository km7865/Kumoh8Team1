// 고지서 출력

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Network.Protocol;
import tableClass.*;

public class Bill extends JFrame {
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Bill() {
		this.setResizable(false); // 최대화 단추 없애기
		setTitle("고지서 출력");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// 테이블에 출력할 컬럼 이름 배열
		String columnNames[] = { "이름", "학번", "은행", "계좌번호", "입금액", "납부상태"};

		// 테이블에 출력할 데이터 배열
		int i = 4; // 범위
		String data[][] = new String[1000][i]; // 데이터 들어갈 범위
		data[0][0] = "농협은행"; // 데이터 들어갈 부분

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable tbl = new JTable(model);
		tbl.setRowHeight(25);
		//String a = (String)tbl.getValueAt(0, 0);    테이블 값 가져오기

		// JTable tbl = new JTable(data,columnNames);
		// Table은 JScrollPane위에 출력해야 컬럼 이름이 출력된다! 명심할것
		JScrollPane scroll = new JScrollPane(tbl);
		scroll.getVerticalScrollBar().setUnitIncrement(100); // 스크롤 속도
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scroll);
		scroll.setSize(772, 583);
		scroll.setLocation(12, 10);
	}
}