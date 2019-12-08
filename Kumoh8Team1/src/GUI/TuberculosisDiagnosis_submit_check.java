// 결핵진단서 제출확인

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

public class TuberculosisDiagnosis_submit_check extends JFrame {
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TuberculosisDiagnosis_submit_check frame = new TuberculosisDiagnosis_submit_check();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TuberculosisDiagnosis_submit_check() {
		this.setResizable(false); // 최대화 단추 없애기
		setVisible(true);
		setTitle("결핵진단서 제출확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 테이블에 출력할 컬럼 이름 배열
		String columnNames[] = {"이름", "학번"};

		// 테이블에 출력할 데이터 배열
		int i = 4; // 범위
		String data[][] = new String[1000][i]; // 데이터 들어갈 범위
		data[0][0] = "홍길동"; // 데이터 들어갈 부분

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
}