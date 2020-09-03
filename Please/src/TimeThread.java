import java.awt.*;

import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class TimeThread extends JLabel implements Runnable {

	private Thread myThread;
	private JPanel base;					// PlayBasePanel ������ ���� ��ü
	private JLabel lblGame;					// GameOver Label�� ���� ��ü
	private JButton btnInput, btnRetry;		// ��ư���� ���� ��ü
	private JTextField txtInput;			// �ؽ�Ʈ�ʵ带 ���� ��ü
	private int nSleep, m, s;				// �⺻ ����(�� �ð�, ��, ��)
	private ImageIcon originIcon, lblIcon;
	private Image originImg, changedImage;
	private Font font;

	public TimeThread() {
		nSleep = 1000;	// �ð��� �ٲ�� �� 1��
		// ���� �ð� �ʱ�ȭ
		m = 10;	
		s = 0;
		
		font = new Font("Verdana", Font.BOLD, 20);
		
		// ��� �̹��� ������ ����
		originIcon = new ImageIcon("images/TimeBoard.png");
		originImg = originIcon.getImage();
		changedImage = originImg.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		lblIcon = new ImageIcon(changedImage);
		setIcon(lblIcon);
		
		setText("" + m + " : 0" + s);	// ���� �ð� 10 : 00 ���̰� �ϱ�
		setFont(font);
		setForeground(Color.black);
		// label�� �۾� ��ġ�� �ϱ�
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
		myThread = new Thread(this); // ������ ����
	}

	public void start() {
		myThread.start();
	}

	public void stop() {
		myThread.stop();
	}

	public void run() {

		setVisible(true);
		
		try {
			myThread.sleep(nSleep);
		} catch (Exception e) {
		} // 1�� ���� �ϱ�
		
		for (m = 9; m >= 0; m--) {
			for (s = 59; s >= 0; s--) {
				if (s < 10) {
					setText("0" + m + " : 0" + s);
				} else {
					setText("0" + m + " : " + s);
				} // �ʰ� 1�ڸ� ���� 0s�� ���̰� ��.
				
				try { // �� 1��
					myThread.sleep(nSleep);
				} catch (Exception e) {
				}
			}

		} // ���� �ð� ��� ǥ��
		
		// ���� �ð��� ��� ����ϸ�
		base.setFocusable(false); // Ű���� �Է� �� �ް� �ϱ�
		
		tryView();				// ���� ���� ȭ�� ���

		// ���� �ð��� ���������� ����
		setFont(font);
		setText("00 : 00");
		setForeground(Color.red);
	}

	public void setBase(JPanel B) {
		base = B;
	}	// ���� ����Ǵ� ������ base ���� ���

	public void setLblGame(JLabel Go) {
		lblGame = Go;
	} // GameOver label ���޹ޱ�

	public void setEndView(JTextField name, JButton input, JButton re) {
		txtInput = name;
		btnInput = input;
		btnRetry = re;
	} // ������ ������� �� ������ ������Ʈ�� ���� ����

	public void tryView() {
		lblGame.setVisible(true);
		txtInput.setVisible(true);
		btnInput.setVisible(true);
		btnRetry.setVisible(true);
	} // ������ ����Ǹ� ������ ������Ʈ�� ���̰� �ϱ�

	public int getMinute() {
		return m;
	} // �� ����

	public int getSecond() {
		return s;
	} // �� ���� 

	public void initTime() {
		m = 9;
		s = 60;
	} // �ð� �ʱ�ȭ

} // ������ �ð� ��
