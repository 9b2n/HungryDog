import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
	private JButton btnStart, btnRank, btnBGM; // ���ӽ�ŸƮ, ��ŷ������, bgm ������ ���� ��ư��
	private JLabel labTitle, labdog1, labdog2, labdog3, labdog4; // ����� �߰��� ���۹� ������ ���� ��������
	private ImageIcon title; // ���� ����� ���� �̹���������
	private ImageIcon start1, start2, ranking1, ranking2, dog1, dog2, dog3, dog4, bgmOn, bgmOff; // �� ��ư��� �󺧵鿡 �����ֱ� ����
																									// �̹��� ������

	public StartPanel() { // �����ϸ� �ߴ� ������ ���� �г�

		setPreferredSize(new Dimension(600, 700)); // ��ü â ũ�� ����
		Color backcolor = new Color(195, 224, 166); // �÷��� ����
		setBackground(backcolor); // ��׶��� �÷�
		setLayout(null); // setbounds �� �������ֱ� ����

		// ------------------- bgm ��ư�� ���� �̹��� ������ �ҷ�����, ũ�� �����Ͽ� �������ݴϴ�.
		// ------------------------
		ImageIcon originIcon = new ImageIcon("images/bgmOn.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
		bgmOn = new ImageIcon(changedImg);
		btnBGM = new JButton("ON", bgmOn);

		originIcon = new ImageIcon("images/bgmOff.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(60, 50, Image.SCALE_SMOOTH);
		bgmOff = new ImageIcon(changedImg);
		btnBGM.setSelectedIcon(bgmOff);

		btnBGM.setOpaque(false);
		btnBGM.setFocusPainted(false);
		btnBGM.setBorderPainted(false);

		btnBGM.setBackground(backcolor);
		btnBGM.setForeground(backcolor);
		btnBGM.setBounds(500, 20, 75, 50);
		btnBGM.setContentAreaFilled(false);
		add(btnBGM);

		// --------------------------- ���ӽ�ŸƮ, ��ŷ�������� ���� ���� ��ư �̹��� ������ ����
		// ------------------
		originIcon = new ImageIcon("images/start1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
		start1 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/start2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
		start2 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/ranking1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ranking1 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/ranking2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ranking2 = new ImageIcon(changedImg);

		// ------------------------ �߰��� ���۹��� ���� �������� �̹��� ���� -------------------
		originIcon = new ImageIcon("images/cute_front.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(47, 45, Image.SCALE_SMOOTH);
		dog1 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_left.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(45, 43, Image.SCALE_SMOOTH);
		dog2 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_right.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		dog3 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_back.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		dog4 = new ImageIcon(changedImg);

		// ---------------------- ���� �̹��� ���� ------------------------
		originIcon = new ImageIcon("images/Title.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(500, 200, Image.SCALE_SMOOTH);
		title = new ImageIcon(changedImg);

		labTitle = new JLabel(title); // ���� �� ����
		labTitle.setBounds(50, 75, 500, 200);
		labTitle.setVisible(true);
		labTitle.setHorizontalAlignment(SwingConstants.CENTER); // �߾� ����
		add(labTitle);

		// ------------ �߰��� �����¿� ���۹� �����ϴ� �������� �� ���� �� �̹��� ����� -------------------
		Color curcolor = new Color(226, 115, 111); // ��� �÷��� ����

		labdog1 = new JLabel("��", dog1, SwingUtilities.RIGHT);
		labdog1.setFont(new Font("aŸ�Ӹӽ�", Font.BOLD, 30));
		labdog1.setForeground(curcolor);
		labdog1.setBounds(90, 350, 100, 50);
		add(labdog1);
		labdog2 = new JLabel(" ��", dog2, SwingUtilities.RIGHT);
		labdog2.setFont(new Font("aŸ�Ӹӽ�", Font.BOLD, 30));
		labdog2.setForeground(curcolor);
		labdog2.setBounds(205, 350, 100, 50);
		add(labdog2);
		labdog3 = new JLabel(" ��", dog3, SwingUtilities.RIGHT);
		labdog3.setFont(new Font("aŸ�Ӹӽ�", Font.BOLD, 30));
		labdog3.setForeground(curcolor);
		labdog3.setBounds(320, 350, 100, 50);
		add(labdog3);
		labdog4 = new JLabel("��", dog4, SwingUtilities.RIGHT);
		labdog4.setFont(new Font("aŸ�Ӹӽ�", Font.BOLD, 30));
		labdog4.setForeground(curcolor);
		labdog4.setBounds(420, 350, 100, 50);
		add(labdog4);

		// ----------------------------- ���� ���� ��ư�� ��ŷ�������� ���� ��ư ���� �� ���� ---------------
		btnStart = new JButton("GO!", start1);
		btnStart.setBounds(50, 500, 310, 150);
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setForeground(backcolor);
		btnStart.setRolloverIcon(start2); // ȣ������ ���� ��ư�̹��� ��
		btnStart.setPressedIcon(start2); // ��ư �������� ���� �̹��� ��
		add(btnStart);

		btnRank = new JButton("RANKING", ranking1);
		btnRank.setBounds(400, 500, 150, 150);
		btnRank.setBorderPainted(false);
		btnRank.setContentAreaFilled(false);
		btnRank.setForeground(backcolor);
		btnRank.setRolloverIcon(ranking2);
		btnRank.setPressedIcon(ranking2);
		add(btnRank);
	}

	// ---------------- ��ư �����ʸ� ���� �� �޼ҵ�� -----------------
	public JButton getStartButton() {
		return btnStart;
	}

	public JButton getRankButton() {
		return btnRank;
	}

	public JButton getBGMButton() {
		return btnBGM;
	}

	// ---------- bgm �̹��� ������ ������ ���� �޼ҵ� -----------------
	public void changeBgmIcon() {
		if (btnBGM.getText() == "ON") {
			btnBGM.setIcon(bgmOff);
		} else {
			btnBGM.setIcon(bgmOn);
		}
	}
}