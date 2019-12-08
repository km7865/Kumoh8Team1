// �Ի��� ��ȸ

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Network.Protocol;
import tableClass.*;

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
		this.setResizable(false); // �ִ�ȭ ���� ���ֱ�
		setVisible(true);
		setTitle("�Ի��� ��ȸ");
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
			p = (Protocol) reader.readObject();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// ���̺� ����� �÷� �̸� �迭
		String columnNames[] = {"�̸�", "�й�"};

		// ���̺� ����� ������ �迭
		int i = 4; // ����
		String data[][] = new String[1000][i]; // ������ �� ����
		data[0][0] = "ȫ�浿"; // ������ �� �κ�

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable tbl = new JTable(model);
		tbl.setRowHeight(25);

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