import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Rank extends JPanel {

	private JButton btnRestart, btnExit; // ������� ���� ��ŸƮ�гη� ���� restart ��ư�� ���� ���Ḧ ���� exit ��ư
	private JLabel lblTitle, lblSub, lblName[] = new JLabel[5], lblScore[] = new JLabel[5]; // RANKING, NAME SCORE�� �����
																							// �󺧵�� ��ŷ�� ����� name,
																							// score���� �����ϴ� lblName,
																							// lblScore
	private RankData[] rDatas; // RankData ��ä �迭 ����
	private int newScore; // �÷����� ���ο� ������ �����ϱ� ���� ����
	private String newName; // ���ο� �̸� ������ ���� newName
	private ImageIcon restart1, restart2, exit1, exit2; // �����, ���� ��ư�� �̹����� �����ֱ� ���� �̹��������� ���� 2����

	public Rank() {
		Color backcolor = new Color(246, 223, 170);
		setPreferredSize(new Dimension(600, 700));
		setBackground(backcolor);
		setLayout(null);

		// ��� ���� ��
		lblTitle = new JLabel("RANKING"); // RANKING ���� �� ����
		lblTitle.setBounds(50, 50, 500, 90); // ��ǥ �ֻ��, ũ�� ����
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 50)); // ��Ʈ����
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // �߾� ����
		add(lblTitle); // Rank�� �߰����ش�.

		// ������ ��
		lblSub = new JLabel("     NAME          SCORE"); // NAME, SCORE�� ����� ���� Ÿ��Ʋ �� ����
		lblSub.setBounds(50, 165, 500, 60); // title �Ʒ��� ��ǥ ����
		lblSub.setOpaque(true); // �� ��ü�� background�� ��Ÿ���� ���� true
		lblSub.setFont(new Font("Verdana", Font.BOLD, 35));
		lblSub.setForeground(Color.red); // ���ڻ� ����
		add(lblSub);

		// ���� �����

		// --------------------------- ���� �Է� �޾ƿ��� --------------------------
		int i = 0;
		try {
			// �Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(".\\rankscore.txt"); // rankscore, rankname txt ���ϵ��� �ҷ��ɴϴ�.
			FileReader filereader2 = new FileReader(".\\rankname.txt");

			// �Է� ���� ����
			BufferedReader br_s = new BufferedReader(filereader); // score�� ���� s , name�� ���� n ���ۻ���
			BufferedReader br_n = new BufferedReader(filereader2);

			rDatas = new RankData[6]; // ��ŷ�� 5���������� �� �� �߰��� ���ο� ����,�̸��� �����ϱ� ���� 6���� ����
			String sline; // score�� ���پ�

			// �� ������ �� ���� ���Ƽ� while���� �ϳ� �־ ���� �������� �Ͽ����ϴ�.
			while ((sline = br_s.readLine()) != null) { // readLine�� ����ؼ� rankscore.txt�� ������ ���پ� sline�� �����Ѵ�.
				rDatas[i] = new RankData(); // ��ü �迭�� �ش� �ε��� ��ü ����
				rDatas[i].name = br_n.readLine(); // �ش� ���������� �̸��� �޾� ����
				rDatas[i].score = Integer.parseInt(sline); // ������ �޾� ��Ʈ�� �ٲ� ����
				i++;
			}
			br_s.close();
			br_n.close(); // ���� �ݾ��ش�.
		} catch (FileNotFoundException e) { // ����ó��
		} catch (IOException e) {
			System.out.println(e);
		}
		// -----------------------------------------------------------------

		// --------------------------�޾ƿ� ��ŷ ������ ����---------------------------
		int tmp;
		String tmp2;
		for (i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				if (rDatas[j].score < rDatas[j + 1].score) {
					tmp = rDatas[j].score;
					tmp2 = rDatas[j].name;
					rDatas[j].score = rDatas[j + 1].score;
					rDatas[j].name = rDatas[j + 1].name;
					rDatas[j + 1].score = tmp;
					rDatas[j + 1].name = tmp2;
				}
			}
		}

		// --------------------------���� �Ϸ�� ������ 5������ �� �����Ͽ� ����ֱ�---------------------
		for (i = 0; i < 5; i++) {
			// NAME ��ŷ ��
			lblName[i] = new JLabel(i + 1 + ".  " + rDatas[i].name);
			lblName[i].setBounds(50, 225 + 60 * (i), 500, 60);
			lblName[i].setFont(new Font("Verdana", Font.BOLD, 35));
			add(lblName[i]);
			// SCORE ��ŷ ��
			lblScore[i] = new JLabel(rDatas[i].score + "\r\n");
			lblScore[i].setBounds(340, 225 + 60 * (i), 500, 60);
			lblScore[i].setFont(new Font("Verdana", Font.BOLD, 35));
			add(lblScore[i]);
		}

		// -------------------------- �̹��� ������ ���� -----------------------------------
		// ��ŸƮ�гη� ���� ���ι�ư 2����
		ImageIcon originIcon = new ImageIcon("images/main1.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/main2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart2 = new ImageIcon(changedImg);

		// ���Ḧ ���� exit ��ư 2����
		originIcon = new ImageIcon("images/exit1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/exit2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit2 = new ImageIcon(changedImg);

		// ����� ��ư
		btnRestart = new JButton("�� MAIN", restart1);
		btnRestart.setBounds(50, 550, 235, 100);
		btnRestart.setBorderPainted(false);
		btnRestart.setContentAreaFilled(false); // �׵θ��� �����ְ�, �⺻ ��ư Ʋ�� �����ϰ� �ٲ��ش�.
		btnRestart.setForeground(backcolor);
		btnRestart.setRolloverIcon(restart2); // ���콺 �ø��� ��� �̹��� ��ȭ
		btnRestart.setPressedIcon(restart2); // ��ư ������ ��� �̹����� ��ȭ
		add(btnRestart);

		// ���� ��ư
		btnExit = new JButton("Exit", exit1);
		btnExit.setBounds(325, 550, 235, 100);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setForeground(backcolor);
		btnExit.setRolloverIcon(exit2);
		btnExit.setPressedIcon(exit2);
		add(btnExit);
	}

	// ��ŷ ���� ���θ� ���� ��Ʈ ���ھ� get �޼ҵ�
	public int getLastScore() {
		return rDatas[5].score;
	}

	// ----------------- ���� ��ŷ �������� ������ �ε����� ���� �÷����� ���ο� �����͸� �޾ƿ� �����Ͽ� ���Ͽ� �ٽ�
	// ������ݴϴ�. --------
	public void setNewRank(int score, String name) {
		newScore = score; // �÷����� ���ο� ����
		newName = name; // ����ڰ� �Է��� ���ο� �̸�
		rDatas[5] = new RankData();
		rDatas[5].score = newScore;
		rDatas[5].name = newName;
		int tmp;
		String tmp2;

		// ------------- ���� 0~5 �� 6���� �����͸� �����մϴ�.------------
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5 - i; j++) {
				if (rDatas[j].score < rDatas[j + 1].score) {
					tmp = rDatas[j].score;
					tmp2 = rDatas[j].name;
					rDatas[j].score = rDatas[j + 1].score;
					rDatas[j].name = rDatas[j + 1].name;
					rDatas[j + 1].score = tmp;
					rDatas[j + 1].name = tmp2;
				}
			}
		}

		// -------- ���Ͽ� �ٽ� ������ (���ĵ� ��ũ������ �Է�) �����ϸ� 1~5���� �������⶧���� 5���� ������ �ٽ� ���Ͽ� �Է�
		BufferedWriter out1 = null;
		BufferedWriter out2 = null;

		try {
			out1 = new BufferedWriter(new FileWriter(".\\rankscore.txt"));
			out2 = new BufferedWriter(new FileWriter(".\\rankname.txt"));

			for (int i = 0; i < 5; i++) {
				Integer.toString(rDatas[i].score);
				out1.write(Integer.toString(rDatas[i].score) + "\n");
				out2.write(rDatas[i].name + "\n");
			}
		} catch (IOException e) {
		} finally {
			try {
				if (out1 != null)
					out1.close();
				if (out2 != null)
					out2.close();
			} catch (IOException e) {
			}
		}
	}

	// -------------------���ο� �÷��� �����͸� �޾Ƽ� ���ĵ� rDatas ��ü �迭�� Rank �гο� �ٽ� ���ε��ϴ� �޼ҵ�
	// -----------
	public void setList() {
		for (int i = 0; i < 5; i++) {
			// NAME ��ŷ ��
			lblName[i].setText(i + 1 + ".  " + rDatas[i].name + "\r\n");
			// SCORE ��ŷ ��
			lblScore[i].setText(rDatas[i].score + "\r\n");
		}
	}

	// ------------------- �����ʸ� ���� ��ư�� �Ѱ��ֱ� ���� get �޼ҵ��--------------
	public JButton getRestart() {
		return btnRestart;
	}

	public JButton getExit() {
		return btnExit;
	}

}