// ������ ���

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
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setTitle("������ ���");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// ���̺� ����� �÷� �̸� �迭
		String columnNames[] = { "�̸�", "�й�", "����", "���¹�ȣ", "�Աݾ�", "���λ���"};

		// ���̺� ����� ������ �迭
		int i = 4; // ����
		String data[][] = new String[1000][i]; // ������ �� ����
		data[0][0] = "��������"; // ������ �� �κ�

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable tbl = new JTable(model);
		tbl.setRowHeight(25);
		//String a = (String)tbl.getValueAt(0, 0);    ���̺� �� ��������

		// JTable tbl = new JTable(data,columnNames);
		// Table�� JScrollPane���� ����ؾ� �÷� �̸��� ��µȴ�! ����Ұ�
		JScrollPane scroll = new JScrollPane(tbl);
		scroll.getVerticalScrollBar().setUnitIncrement(100); // ��ũ�� �ӵ�
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scroll);
		scroll.setSize(772, 583);
		scroll.setLocation(12, 10);
	}
}