import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PlayBasePanel extends JPanel {

	private JLabel lblGO, lblAC; // ���ӿ���, ��Ŭ���� ��
	private JTextField txtInput; // �г��� �Է� ���� �ؽ�Ʈ�ʵ�
	private JButton btnInput, btnRetry; // �Է�, �ٽ��ϱ� ��ư
	private ImageIcon retry1, retry2, input1, input2, gameover, clear; // ������ ���� ���

	public PlayBasePanel() {
		// PlayBasePanel �⺻����
		setPreferredSize(new Dimension(600, 700));
		setLayout(null);

		ImageIcon originIcon = new ImageIcon("images/GameOver.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		gameover = new ImageIcon(changedImg);
		lblGO = new JLabel(gameover, lblGO.CENTER);
		lblGO.setVisible(false); // �Ⱥ��̰� �ϱ�
		lblGO.setOpaque(false);
		lblGO.setBounds(55, 100, 500, 350);
		add(lblGO); // ���ӿ��� �� ������ �ٿ��� �гο� ���̱�

		originIcon = new ImageIcon("images/clear.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(430, 200, Image.SCALE_SMOOTH);
		clear = new ImageIcon(changedImg);
		lblAC = new JLabel(clear, lblAC.CENTER);
		lblAC.setVisible(false); // �Ⱥ��̰� �ϱ�
		lblAC.setOpaque(false);
		lblAC.setBounds(75, 100, 450, 400);
		add(lblAC); // ��Ŭ���� �� ������ �ٿ��� �гο� ���̱�

		txtInput = new JTextField(3); // ũ�� 3��ŭ
		txtInput.setBounds(75, 503, 150, 65);
		txtInput.setFont(new Font("Verdana", Font.BOLD, 30));
		txtInput.setAlignmentX(CENTER_ALIGNMENT);
		txtInput.setDocument(new JTextFieldLimit(3)); // �Է� ���� ���ڼ� �ִ� 3����
		txtInput.setVisible(false); // �Ⱥ��̰� �ϱ�
		add(txtInput); // �ؽ�Ʈ�ʵ� ���̱�

		originIcon = new ImageIcon("images/input1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		input1 = new ImageIcon(changedImg); // ��ǲ ��ư �̹���
		originIcon = new ImageIcon("images/input2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		input2 = new ImageIcon(changedImg); // ��ǲ ��ư ȣ���� �̹���

		btnInput = new JButton("�Է�", input1);
		btnInput.setBounds(240, 500, 160, 75);
		btnInput.setBorderPainted(false); // �ܰ��� �Ⱥ��̰� �ϱ�
		btnInput.setContentAreaFilled(false); // �簢�� ����? �Ⱥ��̰� �ϱ�
		btnInput.setRolloverIcon(input2);
		btnInput.setPressedIcon(input2);
		btnInput.setVisible(false);
		add(btnInput); // ��ǲ��ư �⺻ ���� �� ȣ���� ���� �� ���̱�

		originIcon = new ImageIcon("images/main1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
		retry1 = new ImageIcon(changedImg); // �ٽý��� ��ư �̹���
		originIcon = new ImageIcon("images/main2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
		retry2 = new ImageIcon(changedImg); // �ٽý��� ��ư ȣ���� �̹���

		btnRetry = new JButton("�ٽ��ϱ�", retry1);
		btnRetry.setBounds(410, 500, 160, 75);
		btnRetry.setVisible(false);
		btnRetry.setBorderPainted(false);
		btnRetry.setContentAreaFilled(false);
		btnRetry.setRolloverIcon(retry2);
		btnRetry.setPressedIcon(retry2);
		add(btnRetry); // �ٽ��ϱ� ��ư ���� �� ȣ���� ���� �� ���̱�
	}

	// ----------------- �г��� �Է¹޴� �ؽ�Ʈ�ʵ忡 3���� ���� �α� ���� Ŭ���� ------------------
	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		public JTextFieldLimit(int limit) { // �޾ƿ� limit ���� ( ������ 3 �Ѱ��� )
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit) // length�� 3���� ���� ��
				super.insertString(offset, str, attr);
		}
	}

	// ��, ��ư, �ؽ�Ʈ��ư �����Լ���--------------------------------
	public JLabel getLblGO() {
		return lblGO;
	}

	public JLabel getLblAC() {
		return lblAC;
	}

	public JButton getBtnInput() {
		return btnInput;
	}

	public JTextField getTxtInput() {
		return txtInput;
	}

	public JButton getBtnRetry() {
		return btnRetry;
	}
}
