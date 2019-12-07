// 입사서약서

package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.DropMode;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Join_Promise extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join_Promise frame = new Join_Promise();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Join_Promise() {
		setTitle("입사서약서");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 975, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String s = "\uAE08\uC624\uACF5\uACFC\uB300\uD559\uAD50 \uC0DD\uD65C\uAD00\uC0DD \uC218\uCE59\r\n\r\n\uC81C1\uC870(\uBAA9\uC801)\r\n\uC774\uC9C0\uCE68\uC740 \uAE08\uC624\uACF5\uACFC\uB300\uD559\uAD50 \uC0DD\uD65C\uAD00\uADDC\uC815 \uC81C 10\uC870\uC5D0 \uC758\uAC70 \uC0DD\uD65C\uAD00\uC758 \uACF5\uB3D9\uC0DD\uD65C\uACFC \uBA74\uD559\uBD84\uC704\uAE30 \uC870\uC131\uC744 \uC704\uD558\uC5EC \uC0DD\uD65C\uAD00\uC0DD\uC774 \uC9C0\uCF1C\uC57C\uD560 \uC81C\uBC18\uC0AC\uD56D\uC744 \uADDC\uC815\uD568\uC744 \uBAA9\uC801\uC73C\uB85C \uD55C\uB2E4.\r\n\r\n\uC81C2\uC870(\uC2E0\uBD84\uD655\uC778)\r\n\uC0DD\uD65C\uAD00\uC0DD(\uC774\uD558 \u201C\uAD00\uC0DD\u201D\uC774\uB77C \uD55C\uB2E4)\uC740 \uAD00\uACC4\uC9C1\uC6D0 \uB4F1\uC758 \uC2E0\uBD84\uD655\uC778 \uC694\uAD6C\uAC00 \uC788\uC744 \uB54C \uD559\uC0DD\uC99D \uB4F1 \uC2E0\uBD84\uC99D\uC744 \uC81C\uC2DC\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C3\uC870(\uC2E4\uC810\uAC80 \uB4F1)\r\n\u2460 \uAD00\uC0DD\uC2E4 \uC810\uAC80\uC740 \uC815\uAE30 \uBC0F \uC218\uC2DC\uB85C \uC2E4\uC2DC\uD55C\uB2E4.\r\n\u2461 \uAD00\uC0DD\uC740 \uAD00\uACC4\uC9C1\uC6D0 \uB4F1\uC758 \uAD00\uC0DD\uC2E4 \uC810\uAC80\uC694\uAD6C\uAC00 \uC788\uC744 \uB54C \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uD544\uC694\uC2DC \uAD00\uC0DD \uBA74\uB2F4\uC744 \uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C4\uC870(\uD638\uC2E4 \uBC0F \uCE68\uB300\uBC88\uD638 \uBCC0\uACBD)\r\n\u2460 \uC7A0\uBC84\uB987(\uCF54\uACE8\uC774 \uB4F1), \uAD00\uC0DD\uAC04\uC758 \uBD88\uD654, \uB0B4\u00B7\uC678\uAD6D\uC778\uACFC\uC758 \uAE30\uC219 \uBD88\uD3B8 \uB4F1\uC73C\uB85C \uC778\uD55C \uBBFC\uC6D0\uD574\uACB0\uC744 \uC704\uD558\uC5EC \uC0DD\uD65C\uAD00\uC7A5\uC740 \uAD00\uC0DD\uC5D0\uAC8C \uD638\uC2E4 \uBC0F \uCE68\uB300\uBC88\uD638 \uBCC0\uACBD\uC744 \uC694\uAD6C\uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2461 \uC678\uAD6D\uC778\uACFC \uAE30\uC219 \uBC0F \uD559\uC81C(\uB300\uD559\uC6D0, \uB300\uD559, \uAD50\uD658\uD559\uC0DD, \uC5B4\uD559\uC5F0\uC218 \uB4F1) \uB4F1\uC73C\uB85C \uC778\uD558\uC5EC \uBD88\uAC00\uD53C\uD558\uAC8C \uD638\uC2E4 \uBCC0\uACBD\uC774 \uBC1C\uC0DD\uD560 \uACBD\uC6B0 \uC0DD\uD65C\uAD00\uC7A5\uC774 \uD638\uC2E4 \uBCC0\uACBD\uC744 \uC694\uAD6C\uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uC704 \uC0AC\uD56D\uC5D0 \uAD00\uB828\uD558\uC5EC \uAD00\uC0DD\uC774 \uBD88\uC751\uD558\uBA74 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uD1F4\uC0AC\uB97C \uBA85\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C5\uC870(\uCD9C\uC785\uC2DC\uAC04)\r\n\u2460 \uC0DD\uD65C\uAD00 \uCD9C\uC785\uBB38\uC758 \uAC1C\u00B7\uD3D0\uC2DC\uAC04\uC740 \uB2E4\uC74C\uACFC \uAC19\uB2E4.\r\n\t1. \uAC1C\uBB38\uC2DC\uAC04 : 05 : 00\r\n\t2. \uD3D0\uBB38\uC2DC\uAC04 : 02 : 30\r\n\u2461 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uC2DC\uD5D8\uAE30\uAC04 \uB4F1 \uC0AC\uC815\uC5D0 \uB530\uB77C \uCD9C\uC785\uBB38\uC758 \uAC1C\u00B7\uD3D0 \uC2DC\uAC04\uC744 \uD0C4\uB825\uC801\uC73C\uB85C \uC6B4\uC601\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C6\uC870(\uC0DD\uD65C\uC218\uCE59 \uC900\uC218)\r\n\uAD00\uC0DD\uC740 \uC0DD\uD65C\uAD00\uC758 \uACF5\uC911\uC9C8\uC11C \uBC0F \uBA74\uD559\uBD84\uC704\uAE30 \uC870\uC131\uC744 \uC704\uD558\uC5EC \uB2E4\uC74C \uC0AC\uD56D\uC744 \uC900\uC218\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n1. \uC9C8\uC11C \uC788\uB294 \uACF5\uB3D9\uC0DD\uD65C\uB85C \uAC74\uC804\uD55C \uC0DD\uD65C\uAD00 \uBB38\uD654\uB97C \uB9CC\uB4E4\uAE30 \uC704\uD574 \uB178\uB825\uD55C\uB2E4.\r\n2. \uBAA8\uB4E0 \uC2DC\uC124\uC744 \uD56D\uC0C1 \uCCAD\uACB0\uD788 \uC0AC\uC6A9\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n3. \uC0DD\uD65C\uAD00 \uC6B4\uC601 \uAC1C\uC120\uC744 \uC704\uD55C \uB9CC\uC871\uB3C4\uC870\uC0AC \uC124\uBB38\uC5D0 \uC131\uC2E4\uD788 \uC751\uD574\uC57C \uD55C\uB2E4.\r\n4. \uC0DD\uD65C\uAD00 \uC0AC\uC6A9 \uAD8C\uB9AC\uB97C \uC678\uBD80\uC778\uC5D0\uAC8C \uC591\uB3C4\uD558\uAC70\uB098 \uC678\uBD80\uC778\uC744 \uB3D9\uBC18\uD558\uC5EC \uC219\uBC15\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n5. \uC0DD\uD65C\uAD00 \uC2DC\uC124\uC744 \uC784\uC758\uB85C \uBCC0\uACBD\uD558\uAC70\uB098 \uBC30\uC815\uB41C \uAD00\uC0DD\uC2E4\uC744 \uC784\uC758\uB85C \uAD50\uD658\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n6. \uC0DD\uD65C\uAD00\uC5D0 \uC704\uD5D8\uBB3C\uC744 \uBC18\uC785\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n7. \uAC1C\uC778\uC6A9 \uC804\uC5F4\uAE30, \uB09C\uBC29\uB3C4\uAD6C \uBC0F \uCDE8\uC0AC\uB3C4\uAD6C\uB97C \uC0AC\uC6A9\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n8. \uC74C\uC8FC, \uCE74\uB4DC\uB180\uC774 \uB4F1 \uC0DD\uD65C\uAD00 \uC9C8\uC11C\uB97C \uBB38\uB780\uCF00 \uD558\uB294 \uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4. \r\n9. \uC545\uAE30 \uC5F0\uC8FC, \uACE0\uC131\uBC29\uAC00 \uB4F1 \uC18C\uC74C\uC73C\uB85C \uB2E4\uB978 \uAD00\uC0DD\uC5D0\uAC8C \uD53C\uD574\uB97C \uB07C\uCE58\uC9C0 \uC54A\uB294\uB2E4.\r\n10. \uC560\uC644\uC6A9 \uB3D9\uBB3C\uC744 \uD0A4\uC6B0\uC9C0 \uC54A\uB294\uB2E4.\r\n11. \uC601\uB9AC \uBAA9\uC801\uC758 \uC0C1\uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n12. \uC0DD\uD65C\uAD00 \uCD9C\uC785\uC2DC\uAC04\uC744 \uC900\uC218 \uD55C\uB2E4.\r\n13. \uC5EC\uD559\uC0DD\uACFC \uB0A8\uD559\uC0DD\uC774 \uC0C1\uD638 \uC0DD\uD65C\uACF5\uAC04\uC744 \uCE68\uD574\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n14. \uD761\uC5F0\uC740 \uC9C0\uC815\uB41C \uC678\uBD80 \uD761\uC5F0\uAD6C\uC5ED\uC5D0\uC11C\uB9CC \uD55C\uB2E4.\r\n15. \uADF8\uBC16\uC5D0 \uB300\uD559\uC0DD\uC73C\uB85C\uC11C \uD488\uC704\uAC00 \uC190\uC0C1\uB418\uB294 \uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n\r\n\uC81C7\uC870(\uC678\uCD9C, \uC678\uBC15)\r\n\u2460 \uC678\uCD9C, \uC678\uBC15\uC740 \uC81C\uD55C\uC744 \uB450\uC9C0 \uC54A\uB294\uB2E4.\r\n\u2461 \uC678\uCD9C, \uC678\uBC15 \uAE30\uAC04 \uB0B4\uC5D0 \uC0DD\uD65C\uAD00 \uC678\uC5D0\uC11C \uC77C\uC5B4\uB09C \uBAA8\uB4E0 \uC77C\uC5D0 \uB300\uD558\uC5EC\uB294 \uBCF8\uC778 \uC2A4\uC2A4\uB85C \uCC45\uC784\uC744 \uC9C4\uB2E4.\r\n\r\n\uC81C8\uC870(\uBA74\uD68C)\r\n\uBA74\uD68C\uB294 \uAD00\uC0DD\uB3D9 \uC678\uBD80\uC5D0\uC11C \uD558\uC5EC\uC57C\uD558\uBA70 \uBA74\uD68C \uC2DC \uAD00\uC0DD\uC2E4 \uCD9C\uC785\uC744 \uAE08\uD55C\uB2E4. \uB2E4\uB9CC, \uAC00\uC871\uC758 \uBC29\uBB38 \uC2DC \uC0DD\uD65C\uAD00\uC7A5\uC758 \uC2B9\uC778\uC744 \uBC1B\uC544 \uCD9C\uC785\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C9\uC870(\uBCC0\uC0C1) \uAD00\uC0DD\uC774 \uACE0\uC758 \uB610\uB294 \uACFC\uC2E4\uB85C \uC0DD\uD65C\uAD00 \uC2DC\uC124\uBB3C\uC744 \uBD84\uC2E4 \uB610\uB294 \uD6FC\uC190\uD560 \uACBD\uC6B0 \uC774\uB97C \uBCC0\uC0C1 \uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C10\uC870(\uBC8C\uC810\uBD80\uACFC \uBC0F \uD1F4\uC0AC\uC870\uCE58)\r\n\u2460 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uC0DD\uD65C\uC218\uCE59 \uC704\uBC18\uC790\uC5D0\uAC8C [\uBCC4\uD45C1]\uC758 \uBC8C\uC810\uAE30\uC900\uD45C\uC5D0 \uB530\uB77C \uBC8C\uC810\uC744 \uBD80\uACFC\uD560 \uC218 \uC788\uC73C\uBA70, \uBC8C\uC810\uAE30\uC900\uD45C\uC5D0 \uBA85\uC2DC\uB418\uC9C0 \uC54A\uC740 \uC0AC\uD56D\uC740 \uC0DD\uD65C\uAD00\uC7A5\uC774 \uBC8C\uC810\uC744 \uACB0\uC815\uD55C\uB2E4.\r\n\u2461 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uBC8C\uC810\uC774 10\uC810 \uC774\uC0C1\uC778 \uAD00\uC0DD\uC5D0\uAC8C \uC790\uCCB4\uC2EC\uC758\uC704\uC6D0\uD68C \uD68C\uC758\uB97C \uAC70\uCCD0 \uD1F4\uC0AC\uCC98\uBD84\uC744 \uBA85\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C11\uC870(\uC0C1\uC810\uC81C\uB3C4)\r\n\u2460 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uAD00\uC0DD\uC774 \uD0C0 \uAD00\uC0DD\uC758 \uBAA8\uBC94\uC774 \uB418\uB294 \uD589\uB3D9\uC744 \uD558\uAC70\uB098 \uC120\uD589\uC774 \uC788\uC73C\uBA74 [\uBCC4\uD45C1] \uC0C1\uC810\uAE30\uC900\uC5D0 \uB530\uB77C \uC0C1\uC810\uC744 \uBD80\uC5EC\uD560 \uC218 \uC788\uB2E4.\r\n\u2461 \uAD00\uC0DD\uC740 \uC81C1\uD56D\uC5D0 \uB530\uB77C \uC0C1\uC810\uC744 \uCDE8\uB4DD\uD558\uB824\uBA74 \uC9C0\uC815\uB41C \uC11C\uC2DD\uC758 \uC0C1\uC810 \uCDE8\uB4DD \uC2E0\uCCAD\uC11C\uC640 \uC99D\uBE59 \uC11C\uB958\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uC2E4\uC5D0 \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uAD00\uC0DD\uC740 \uBD09\uC0AC\uD65C\uB3D9\uC73C\uB85C \uC0C1\uC810\uC744 \uCDE8\uB4DD\uD558\uB824\uBA74 \uC0AC\uC804\uC5D0 \uBD09\uC0AC\uD65C\uB3D9 \uB0B4\uC6A9\uACFC \uC2DC\uAC04\uC744 \uC0DD\uD65C\uAD00\uC7A5\uC744 \uD1B5\uD558\uC5EC \uACB0\uC815\uD55C \uD6C4 \uC9C0\uC815\uB41C \uC11C\uC2DD\uC758 \uBD09\uC0AC\uD65C\uB3D9 \uBC0F \uC0C1\uC810\uCDE8\uB4DD \uC2E0\uCCAD\uC11C\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uC2E4\uB85C \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2463 \uBD09\uC0AC\uD65C\uB3D9\uC73C\uB85C \uCDE8\uB4DD\uD560 \uC218 \uC788\uB294 \uC0C1\uC810\uC740 \uC5F0\uAC04 5\uC810\uC73C\uB85C \uC81C\uD55C\uD55C\uB2E4. \uB2E4\uB9CC, \uBC8C\uC810\uAC10\uBA74(\uBC8C \uC810\uAE30\uC900 \u201C\uB098\u201D\uC720\uD615)\uC744 \uBAA9\uC801\uC73C\uB85C \uD558\uB294 \uBD09\uC0AC\uD65C\uB3D9\uC740 \uC81C\uD55C\uC744 \uB450\uC9C0 \uC544\uB2C8\uD55C\uB2E4.\r\n\u2464 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uB204\uC801 \uC0C1\uC810\uC774 10\uC810 \uC774\uC0C1\uC778 \uAD00\uC0DD\uC5D0\uB294 \uB2E4\uC74C \uD559\uAE30\uC5D0 \uC6B0\uC120 \uC785\uC0AC\uD560 \uC218 \uC788\uB294 \uC790\uACA9\uC744 \uBD80\uC5EC\uD560 \uC218 \uC788\uB2E4. \uB2E4\uB9CC, \uB204\uC801 \uC0C1\uC810\uC73C\uB85C \uC6B0\uC120 \uC785\uC0AC\uD558\uBA74 \uB204\uC801 \uC0C1\uC810\uC740 \uC18C\uBA78\uB41C\uB2E4.\r\n\r\n\uC81C12\uC870(\uC81C\uCD9C\uC11C\uB958)\r\n\u2460 \uC0DD\uD65C\uAD00 \uC785\uC0AC\uC790\uB294 \uC785\uC0AC \uC804 \uAC74\uAC15\uC9C4\uB2E8\uC11C(\uACB0\uD575\uAC80\uC0AC)\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uD300\uC5D0 \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2461 \uAC74\uAC15\uC9C4\uB2E8\uC11C(\uACB0\uD575\uAC80\uC0AC) \uBBF8\uC81C\uCD9C \uC2DC \uC0DD\uD65C\uAD00\uC7A5\uC740 \uBC30\uC815\uB41C \uD638\uC2E4\uC5D0 \uC785\uC2E4\uC744 \uBD88\uD5C8\uD55C\uB2E4.\r\n\r\n\uC81C13\uC870(\uC804\uAE30\uC0AC\uC6A9 \uBC0F \uC81C\uD55C \uD488\uBAA9)\r\n\u2460 \uC804\uAE30\uC758 \uC0AC\uC6A9\uC744 \uC81C\uD55C\uC801\uC73C\uB85C \uD5C8\uC6A9\uD55C\uB2E4.\r\n\u2461 \uC81C\uD55C\uB418\uB294 \uD488\uBAA9\uC740 [\uBCC4\uD45C2]\uACFC \uAC19\uB2E4.\r\n\u2462 [\uBCC4\uD45C2]\uC5D0\uC11C \uD5C8\uC6A9\uB418\uC9C0 \uC54A\uC740 \uC804\uAE30\uC81C\uD488 \uB4F1\uC740 \uBC18\uC785 \uBC0F \uC0AC\uC6A9\uC744 \uAE08\uD558\uC5EC \uBC1C\uACAC \uC2DC\uB294 \uD68C\uC218\uD558\uACE0 \uBC8C\uC810\uC744 \uBD80\uACFC\uD55C\uB2E4.\r\n\u2463 \uD5C8\uC6A9\uB418\uC9C0 \uC54A\uC740 \uC804\uAE30\uC6A9\uD488 \uB4F1\uC744 \uBC18\uC785 \uB610\uB294 \uC0AC\uC6A9\uD574\uC57C \uD560 \uD2B9\uBCC4\uD55C \uC0AC\uC720\uAC00 \uC788\uB294 \uACBD\uC6B0\uC5D0\uB294 \uC0AC\uC804\uC5D0 \uC0DD\uD65C\uAD00\uC7A5\uC758 \uD5C8\uAC00\uB97C \uC5BB\uC5B4\uC57C \uD55C\uB2E4.";

		JTextArea textArea = new JTextArea("\uAE08\uC624\uACF5\uACFC\uB300\uD559\uAD50 \uC0DD\uD65C\uAD00\uC0DD \uC218\uCE59\r\n\r\n\uC81C1\uC870(\uBAA9\uC801)\r\n\uC774\uC9C0\uCE68\uC740 \uAE08\uC624\uACF5\uACFC\uB300\uD559\uAD50 \uC0DD\uD65C\uAD00\uADDC\uC815 \uC81C 10\uC870\uC5D0 \uC758\uAC70 \uC0DD\uD65C\uAD00\uC758 \uACF5\uB3D9\uC0DD\uD65C\uACFC \uBA74\uD559\uBD84\uC704\uAE30 \uC870\uC131\uC744 \uC704\uD558\uC5EC \uC0DD\uD65C\uAD00\uC0DD\uC774 \uC9C0\uCF1C\uC57C\uD560 \uC81C\uBC18\uC0AC\uD56D\uC744 \uADDC\uC815\uD568\uC744 \uBAA9\uC801\uC73C\uB85C \uD55C\uB2E4.\r\n\r\n\uC81C2\uC870(\uC2E0\uBD84\uD655\uC778)\r\n\uC0DD\uD65C\uAD00\uC0DD(\uC774\uD558 \u201C\uAD00\uC0DD\u201D\uC774\uB77C \uD55C\uB2E4)\uC740 \uAD00\uACC4\uC9C1\uC6D0 \uB4F1\uC758 \uC2E0\uBD84\uD655\uC778 \uC694\uAD6C\uAC00 \uC788\uC744 \uB54C \uD559\uC0DD\uC99D \uB4F1 \uC2E0\uBD84\uC99D\uC744 \uC81C\uC2DC\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C3\uC870(\uC2E4\uC810\uAC80 \uB4F1)\r\n\u2460 \uAD00\uC0DD\uC2E4 \uC810\uAC80\uC740 \uC815\uAE30 \uBC0F \uC218\uC2DC\uB85C \uC2E4\uC2DC\uD55C\uB2E4.\r\n\u2461 \uAD00\uC0DD\uC740 \uAD00\uACC4\uC9C1\uC6D0 \uB4F1\uC758 \uAD00\uC0DD\uC2E4 \uC810\uAC80\uC694\uAD6C\uAC00 \uC788\uC744 \uB54C \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uD544\uC694\uC2DC \uAD00\uC0DD \uBA74\uB2F4\uC744 \uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C4\uC870(\uD638\uC2E4 \uBC0F \uCE68\uB300\uBC88\uD638 \uBCC0\uACBD)\r\n\u2460 \uC7A0\uBC84\uB987(\uCF54\uACE8\uC774 \uB4F1), \uAD00\uC0DD\uAC04\uC758 \uBD88\uD654, \uB0B4\u00B7\uC678\uAD6D\uC778\uACFC\uC758 \uAE30\uC219 \uBD88\uD3B8 \uB4F1\uC73C\uB85C \uC778\uD55C \uBBFC\uC6D0\uD574\uACB0\uC744 \uC704\uD558\uC5EC \uC0DD\uD65C\uAD00\uC7A5\uC740 \uAD00\uC0DD\uC5D0\uAC8C \uD638\uC2E4 \uBC0F \uCE68\uB300\uBC88\uD638 \uBCC0\uACBD\uC744 \uC694\uAD6C\uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2461 \uC678\uAD6D\uC778\uACFC \uAE30\uC219 \uBC0F \uD559\uC81C(\uB300\uD559\uC6D0, \uB300\uD559, \uAD50\uD658\uD559\uC0DD, \uC5B4\uD559\uC5F0\uC218 \uB4F1) \uB4F1\uC73C\uB85C \uC778\uD558\uC5EC \uBD88\uAC00\uD53C\uD558\uAC8C \uD638\uC2E4 \uBCC0\uACBD\uC774 \uBC1C\uC0DD\uD560 \uACBD\uC6B0 \uC0DD\uD65C\uAD00\uC7A5\uC774 \uD638\uC2E4 \uBCC0\uACBD\uC744 \uC694\uAD6C\uD560 \uC218 \uC788\uC73C\uBA70 \uAD00\uC0DD\uC740 \uC774\uC5D0 \uC751\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uC704 \uC0AC\uD56D\uC5D0 \uAD00\uB828\uD558\uC5EC \uAD00\uC0DD\uC774 \uBD88\uC751\uD558\uBA74 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uD1F4\uC0AC\uB97C \uBA85\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C5\uC870(\uCD9C\uC785\uC2DC\uAC04)\r\n\u2460 \uC0DD\uD65C\uAD00 \uCD9C\uC785\uBB38\uC758 \uAC1C\u00B7\uD3D0\uC2DC\uAC04\uC740 \uB2E4\uC74C\uACFC \uAC19\uB2E4.\r\n\t1. \uAC1C\uBB38\uC2DC\uAC04 : 05 : 00\r\n\t2. \uD3D0\uBB38\uC2DC\uAC04 : 02 : 30\r\n\u2461 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uC2DC\uD5D8\uAE30\uAC04 \uB4F1 \uC0AC\uC815\uC5D0 \uB530\uB77C \uCD9C\uC785\uBB38\uC758 \uAC1C\u00B7\uD3D0 \uC2DC\uAC04\uC744 \uD0C4\uB825\uC801\uC73C\uB85C \uC6B4\uC601\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C6\uC870(\uC0DD\uD65C\uC218\uCE59 \uC900\uC218)\r\n\uAD00\uC0DD\uC740 \uC0DD\uD65C\uAD00\uC758 \uACF5\uC911\uC9C8\uC11C \uBC0F \uBA74\uD559\uBD84\uC704\uAE30 \uC870\uC131\uC744 \uC704\uD558\uC5EC \uB2E4\uC74C \uC0AC\uD56D\uC744 \uC900\uC218\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n1. \uC9C8\uC11C \uC788\uB294 \uACF5\uB3D9\uC0DD\uD65C\uB85C \uAC74\uC804\uD55C \uC0DD\uD65C\uAD00 \uBB38\uD654\uB97C \uB9CC\uB4E4\uAE30 \uC704\uD574 \uB178\uB825\uD55C\uB2E4.\r\n2. \uBAA8\uB4E0 \uC2DC\uC124\uC744 \uD56D\uC0C1 \uCCAD\uACB0\uD788 \uC0AC\uC6A9\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n3. \uC0DD\uD65C\uAD00 \uC6B4\uC601 \uAC1C\uC120\uC744 \uC704\uD55C \uB9CC\uC871\uB3C4\uC870\uC0AC \uC124\uBB38\uC5D0 \uC131\uC2E4\uD788 \uC751\uD574\uC57C \uD55C\uB2E4.\r\n4. \uC0DD\uD65C\uAD00 \uC0AC\uC6A9 \uAD8C\uB9AC\uB97C \uC678\uBD80\uC778\uC5D0\uAC8C \uC591\uB3C4\uD558\uAC70\uB098 \uC678\uBD80\uC778\uC744 \uB3D9\uBC18\uD558\uC5EC \uC219\uBC15\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n5. \uC0DD\uD65C\uAD00 \uC2DC\uC124\uC744 \uC784\uC758\uB85C \uBCC0\uACBD\uD558\uAC70\uB098 \uBC30\uC815\uB41C \uAD00\uC0DD\uC2E4\uC744 \uC784\uC758\uB85C \uAD50\uD658\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n6. \uC0DD\uD65C\uAD00\uC5D0 \uC704\uD5D8\uBB3C\uC744 \uBC18\uC785\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n7. \uAC1C\uC778\uC6A9 \uC804\uC5F4\uAE30, \uB09C\uBC29\uB3C4\uAD6C \uBC0F \uCDE8\uC0AC\uB3C4\uAD6C\uB97C \uC0AC\uC6A9\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n8. \uC74C\uC8FC, \uCE74\uB4DC\uB180\uC774 \uB4F1 \uC0DD\uD65C\uAD00 \uC9C8\uC11C\uB97C \uBB38\uB780\uCF00 \uD558\uB294 \uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4. \r\n9. \uC545\uAE30 \uC5F0\uC8FC, \uACE0\uC131\uBC29\uAC00 \uB4F1 \uC18C\uC74C\uC73C\uB85C \uB2E4\uB978 \uAD00\uC0DD\uC5D0\uAC8C \uD53C\uD574\uB97C \uB07C\uCE58\uC9C0 \uC54A\uB294\uB2E4.\r\n10. \uC560\uC644\uC6A9 \uB3D9\uBB3C\uC744 \uD0A4\uC6B0\uC9C0 \uC54A\uB294\uB2E4.\r\n11. \uC601\uB9AC \uBAA9\uC801\uC758 \uC0C1\uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n12. \uC0DD\uD65C\uAD00 \uCD9C\uC785\uC2DC\uAC04\uC744 \uC900\uC218 \uD55C\uB2E4.\r\n13. \uC5EC\uD559\uC0DD\uACFC \uB0A8\uD559\uC0DD\uC774 \uC0C1\uD638 \uC0DD\uD65C\uACF5\uAC04\uC744 \uCE68\uD574\uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n14. \uD761\uC5F0\uC740 \uC9C0\uC815\uB41C \uC678\uBD80 \uD761\uC5F0\uAD6C\uC5ED\uC5D0\uC11C\uB9CC \uD55C\uB2E4.\r\n15. \uADF8\uBC16\uC5D0 \uB300\uD559\uC0DD\uC73C\uB85C\uC11C \uD488\uC704\uAC00 \uC190\uC0C1\uB418\uB294 \uD589\uC704\uB97C \uD558\uC9C0 \uC54A\uB294\uB2E4.\r\n\r\n\uC81C7\uC870(\uC678\uCD9C, \uC678\uBC15)\r\n\u2460 \uC678\uCD9C, \uC678\uBC15\uC740 \uC81C\uD55C\uC744 \uB450\uC9C0 \uC54A\uB294\uB2E4.\r\n\u2461 \uC678\uCD9C, \uC678\uBC15 \uAE30\uAC04 \uB0B4\uC5D0 \uC0DD\uD65C\uAD00 \uC678\uC5D0\uC11C \uC77C\uC5B4\uB09C \uBAA8\uB4E0 \uC77C\uC5D0 \uB300\uD558\uC5EC\uB294 \uBCF8\uC778 \uC2A4\uC2A4\uB85C \uCC45\uC784\uC744 \uC9C4\uB2E4.\r\n\r\n\uC81C8\uC870(\uBA74\uD68C)\r\n\uBA74\uD68C\uB294 \uAD00\uC0DD\uB3D9 \uC678\uBD80\uC5D0\uC11C \uD558\uC5EC\uC57C\uD558\uBA70 \uBA74\uD68C \uC2DC \uAD00\uC0DD\uC2E4 \uCD9C\uC785\uC744 \uAE08\uD55C\uB2E4. \uB2E4\uB9CC, \uAC00\uC871\uC758 \uBC29\uBB38 \uC2DC \uC0DD\uD65C\uAD00\uC7A5\uC758 \uC2B9\uC778\uC744 \uBC1B\uC544 \uCD9C\uC785\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C9\uC870(\uBCC0\uC0C1) \uAD00\uC0DD\uC774 \uACE0\uC758 \uB610\uB294 \uACFC\uC2E4\uB85C \uC0DD\uD65C\uAD00 \uC2DC\uC124\uBB3C\uC744 \uBD84\uC2E4 \uB610\uB294 \uD6FC\uC190\uD560 \uACBD\uC6B0 \uC774\uB97C \uBCC0\uC0C1 \uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\r\n\uC81C10\uC870(\uBC8C\uC810\uBD80\uACFC \uBC0F \uD1F4\uC0AC\uC870\uCE58)\r\n\u2460 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uC0DD\uD65C\uC218\uCE59 \uC704\uBC18\uC790\uC5D0\uAC8C [\uBCC4\uD45C1]\uC758 \uBC8C\uC810\uAE30\uC900\uD45C\uC5D0 \uB530\uB77C \uBC8C\uC810\uC744 \uBD80\uACFC\uD560 \uC218 \uC788\uC73C\uBA70, \uBC8C\uC810\uAE30\uC900\uD45C\uC5D0 \uBA85\uC2DC\uB418\uC9C0 \uC54A\uC740 \uC0AC\uD56D\uC740 \uC0DD\uD65C\uAD00\uC7A5\uC774 \uBC8C\uC810\uC744 \uACB0\uC815\uD55C\uB2E4.\r\n\u2461 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uBC8C\uC810\uC774 10\uC810 \uC774\uC0C1\uC778 \uAD00\uC0DD\uC5D0\uAC8C \uC790\uCCB4\uC2EC\uC758\uC704\uC6D0\uD68C \uD68C\uC758\uB97C \uAC70\uCCD0 \uD1F4\uC0AC\uCC98\uBD84\uC744 \uBA85\uD560 \uC218 \uC788\uB2E4.\r\n\r\n\uC81C11\uC870(\uC0C1\uC810\uC81C\uB3C4)\r\n\u2460 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uAD00\uC0DD\uC774 \uD0C0 \uAD00\uC0DD\uC758 \uBAA8\uBC94\uC774 \uB418\uB294 \uD589\uB3D9\uC744 \uD558\uAC70\uB098 \uC120\uD589\uC774 \uC788\uC73C\uBA74 [\uBCC4\uD45C1] \uC0C1\uC810\uAE30\uC900\uC5D0 \uB530\uB77C \uC0C1\uC810\uC744 \uBD80\uC5EC\uD560 \uC218 \uC788\uB2E4.\r\n\u2461 \uAD00\uC0DD\uC740 \uC81C1\uD56D\uC5D0 \uB530\uB77C \uC0C1\uC810\uC744 \uCDE8\uB4DD\uD558\uB824\uBA74 \uC9C0\uC815\uB41C \uC11C\uC2DD\uC758 \uC0C1\uC810 \uCDE8\uB4DD \uC2E0\uCCAD\uC11C\uC640 \uC99D\uBE59 \uC11C\uB958\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uC2E4\uC5D0 \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2462 \uAD00\uC0DD\uC740 \uBD09\uC0AC\uD65C\uB3D9\uC73C\uB85C \uC0C1\uC810\uC744 \uCDE8\uB4DD\uD558\uB824\uBA74 \uC0AC\uC804\uC5D0 \uBD09\uC0AC\uD65C\uB3D9 \uB0B4\uC6A9\uACFC \uC2DC\uAC04\uC744 \uC0DD\uD65C\uAD00\uC7A5\uC744 \uD1B5\uD558\uC5EC \uACB0\uC815\uD55C \uD6C4 \uC9C0\uC815\uB41C \uC11C\uC2DD\uC758 \uBD09\uC0AC\uD65C\uB3D9 \uBC0F \uC0C1\uC810\uCDE8\uB4DD \uC2E0\uCCAD\uC11C\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uC2E4\uB85C \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2463 \uBD09\uC0AC\uD65C\uB3D9\uC73C\uB85C \uCDE8\uB4DD\uD560 \uC218 \uC788\uB294 \uC0C1\uC810\uC740 \uC5F0\uAC04 5\uC810\uC73C\uB85C \uC81C\uD55C\uD55C\uB2E4. \uB2E4\uB9CC, \uBC8C\uC810\uAC10\uBA74(\uBC8C\uC810\uAE30\uC900 \u201C\uB098\u201D\uC720\uD615)\uC744 \uBAA9\uC801\uC73C\uB85C \uD558\uB294 \uBD09\uC0AC\uD65C\uB3D9\uC740 \uC81C\uD55C\uC744 \uB450\uC9C0 \uC544\uB2C8\uD55C\uB2E4.\r\n\u2464 \uC0DD\uD65C\uAD00\uC7A5\uC740 \uB204\uC801 \uC0C1\uC810\uC774 10\uC810 \uC774\uC0C1\uC778 \uAD00\uC0DD\uC5D0\uB294 \uB2E4\uC74C \uD559\uAE30\uC5D0 \uC6B0\uC120 \uC785\uC0AC\uD560 \uC218 \uC788\uB294 \uC790\uACA9\uC744 \uBD80\uC5EC\uD560 \uC218 \uC788\uB2E4. \uB2E4\uB9CC, \uB204\uC801 \uC0C1\uC810\uC73C\uB85C \uC6B0\uC120 \uC785\uC0AC\uD558\uBA74 \uB204\uC801 \uC0C1\uC810\uC740 \uC18C\uBA78\uB41C\uB2E4.\r\n\r\n\uC81C12\uC870(\uC81C\uCD9C\uC11C\uB958)\r\n\u2460 \uC0DD\uD65C\uAD00 \uC785\uC0AC\uC790\uB294 \uC785\uC0AC \uC804 \uAC74\uAC15\uC9C4\uB2E8\uC11C(\uACB0\uD575\uAC80\uC0AC)\uB97C \uC0DD\uD65C\uAD00 \uD589\uC815\uD300\uC5D0 \uC81C\uCD9C\uD558\uC5EC\uC57C \uD55C\uB2E4.\r\n\u2461 \uAC74\uAC15\uC9C4\uB2E8\uC11C(\uACB0\uD575\uAC80\uC0AC) \uBBF8\uC81C\uCD9C \uC2DC \uC0DD\uD65C\uAD00\uC7A5\uC740 \uBC30\uC815\uB41C \uD638\uC2E4\uC5D0 \uC785\uC2E4\uC744 \uBD88\uD5C8\uD55C\uB2E4.\r\n\r\n\uC81C13\uC870(\uC804\uAE30\uC0AC\uC6A9 \uBC0F \uC81C\uD55C \uD488\uBAA9)\r\n\u2460 \uC804\uAE30\uC758 \uC0AC\uC6A9\uC744 \uC81C\uD55C\uC801\uC73C\uB85C \uD5C8\uC6A9\uD55C\uB2E4.\r\n\u2461 \uC81C\uD55C\uB418\uB294 \uD488\uBAA9\uC740 [\uBCC4\uD45C2]\uACFC \uAC19\uB2E4.\r\n\u2462 [\uBCC4\uD45C2]\uC5D0\uC11C \uD5C8\uC6A9\uB418\uC9C0 \uC54A\uC740 \uC804\uAE30\uC81C\uD488 \uB4F1\uC740 \uBC18\uC785 \uBC0F \uC0AC\uC6A9\uC744 \uAE08\uD558\uC5EC \uBC1C\uACAC \uC2DC\uB294 \uD68C\uC218\uD558\uACE0 \uBC8C\uC810\uC744 \uBD80\uACFC\uD55C\uB2E4.\r\n\u2463 \uD5C8\uC6A9\uB418\uC9C0 \uC54A\uC740 \uC804\uAE30\uC6A9\uD488 \uB4F1\uC744 \uBC18\uC785 \uB610\uB294 \uC0AC\uC6A9\uD574\uC57C \uD560 \uD2B9\uBCC4\uD55C \uC0AC\uC720\uAC00 \uC788\uB294 \uACBD\uC6B0\uC5D0\uB294 \uC0AC\uC804\uC5D0 \uC0DD\uD65C\uAD00\uC7A5\uC758 \uD5C8\uAC00\uB97C \uC5BB\uC5B4\uC57C \uD55C\uB2E4.\r\n\r\n\r\n\r\n\uC704 \uB0B4\uC6A9\uC744 \uC219\uC9C0\uD558\uC600\uC73C\uBA70 \uAC01 \uC870\uD56D\uC5D0 \uC788\uB294 \uB0B4\uC6A9\uC744 \uC900\uC218\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?\r\n\r\n");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.getVerticalScrollBar().setUnitIncrement(50); //스크롤 속도
		scrollPane.setBounds(71, 57, 815, 568);

		contentPane.add(scrollPane);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.BLACK);
		textArea_1.setFont(new Font("돋움", Font.BOLD, 18));
		textArea_1.setEditable(false);
		textArea_1.setText("\u25C8 서약서");
		textArea_1.setBackground(SystemColor.control);
		textArea_1.setBounds(71, 27, 140, 20);
		contentPane.add(textArea_1);

		JButton button = new JButton("동의합니다");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dormitory_Application();
				dispose();
			}
		});
		button.setBounds(205, 635, 182, 48);
		contentPane.add(button);

		JButton button_1 = new JButton("동의하지 않습니다");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "동의하지 않아 메인메뉴로 이동합니다.");
				dispose();
			}
		});
		button_1.setBounds(560, 635, 182, 48);
		contentPane.add(button_1);
	}
}