import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainFrame extends JFrame {
	CardLayout card = new CardLayout(); // frame ������Ʈ ��ġ ����
	StartPanel first; // ����������
	Rank rank; // ��ũ������
	PlayBasePanel base[] = new PlayBasePanel[9]; // �÷��� ������
	PlayPanel play; // �÷���ȭ��
	JButton b = new JButton(); // ��ư�����ʸ� ���� ��ư�� ������ ����
	JLabel lblStage, lblScore, lblMove; // ���� ���� ��Ȳ�� ǥ�����ִ� label��
	JPanel PlayBar = new JPanel(); // ���� ���� ��Ȳ ǥ�� Panel
	TimeThread trdTime; // �����ð��� ǥ�����ִ� label
	String intxt; // textField���� �о�� �ؽ�Ʈ
	int level, nMove, nScore, a[][]; // ���� ���� ���� a : �� �迭 ���� �޴� �迭
	MapArray mapArray; // �� ����
	ImageIcon originIcon, Icon;
	Image originImg, changedImage;
	PlayMusic BGM, Bark; // BGM�� ĳ���� �̵��� ȿ����

	public MainFrame() {

		// play bar values init
		level = 1;
		nMove = 0;
		nScore = 0;

		Font font = new Font("Verdana", Font.BOLD, 20); // �ߺ����� ��� �� ��Ʈ ����

		// init frame
		setTitle("����� �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setBackground(Color.black);
		setLayout(card);

		// ------------startȭ�� �����-----------------
		first = new StartPanel();

		b = first.getStartButton(); // start��ư�� �׼Ǵޱ�
		b.addActionListener(new ButtonListener());

		b = first.getRankButton(); // rank��ư�� �׼Ǵޱ�
		b.addActionListener(new ButtonListener());

		b = first.getBGMButton(); // rank��ư�� �׼Ǵޱ�
		b.addActionListener(new ButtonListener());
		getContentPane().add(first); // frame�� startPanel �߰�
		// --------------------------------------------------

		for (int i = 0; i < 9; i++) {
			base[i] = new PlayBasePanel(); // stage ����ŭ �����
			base[i].addKeyListener(new KeyboardListener()); // Panel���� Ű������ �ο�
			b = base[i].getBtnRetry(); // �ٽý��� ��ư�� �׼Ǵޱ�
			b.addActionListener(new ButtonListener());
			b = base[i].getBtnInput(); // �Է¹�ư�� �׼Ǵޱ�
			b.addActionListener(new ButtonListener());
			getContentPane().add(base[i]); // ������� frame�� �߰�
		} // playBasePanel �ʱ�ȭ

		// ------------rankȭ�� �����--------------------------
		rank = new Rank();

		b = rank.getRestart(); // restart��ư�� �׼Ǵޱ�
		b.addActionListener(new ButtonListener());

		b = rank.getExit(); // exit��ư�� �׼Ǵޱ�
		b.addActionListener(new ButtonListener());
		getContentPane().add(rank);
		// --------------------------------------------------

		// --------PlayBar�� �ٿ��� Label�� ����--------------------
		setIconStage();
		lblStage = new JLabel(Icon); // stage ������ ���̱�
		setIconScore();
		lblScore = new JLabel("" + nScore, Icon, SwingConstants.CENTER); // score��� ������ ���̱�
		setIconMove();
		lblMove = new JLabel("" + nMove, Icon, SwingConstants.CENTER); // move��� ������ ���̱�

		lblStage.setBounds(0, 0, 100, 100); // lblStage �ʱ�ȭ
		lblStage.setOpaque(true);
		lblStage.setBackground(Color.white);

		lblScore.setBounds(100, 0, 200, 100); // lblScore �ʱ�ȭ
		lblScore.setFont(font);
		lblScore.setForeground(Color.black);
		lblScore.setOpaque(true);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setBackground(Color.blue);

		lblMove.setBounds(300, 0, 150, 100); // lblMove �ʱ�ȭ
		lblMove.setFont(font);
		lblMove.setForeground(Color.black);
		lblMove.setOpaque(true);
		lblMove.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMove.setBackground(Color.red);

		PlayBar.setLayout(null);
		PlayBar.setBounds(0, 0, 600, 100); // PlayBar ��ġ

		PlayBar.add(lblStage);
		PlayBar.add(lblScore);
		PlayBar.add(lblMove); // ���� ���� ǥ�� label�� ���̱�
		// ------------------------------------------------------

		BGM = new PlayMusic();
		BGM.startMusic(); // ������� �޾Ƽ� ���
		Bark = new PlayMusic(); // ȿ���� ��ü ����

		pack();
		setVisible(true);
	}

	public class ButtonListener implements ActionListener { // ��ư������

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource(); // ��ư ��ü �ޱ�

			if (b.getText().equals("GO!")) { // ���۹�ư ������

				BGM.stopMusic(); // ������� ����

				// -------------1�ܰ� �ʹ迭 �޾Ƽ� PlayPanel �����-------------
				mapArray = new MapArray(level);
				a = mapArray.getArray();
				play = new PlayPanel(a);
				// ----------TimeThread �����ؼ� PlayBar�� ���̱�-------------
				trdTime = new TimeThread();
				trdTime.setBounds(450, 0, 150, 100);
				trdTime.setOpaque(true);
				trdTime.setBackground(Color.white);
				trdTime.start(); // �ð� ���
				trdTime.setBase(base[level - 1]); // PlayBasePanel���� �Ѱ��ֱ�
				trdTime.setLblGame(base[level - 1].getLblGO()); // GameOver Label �Ѱ��ֱ�
				trdTime.setEndView(base[level - 1].getTxtInput(), base[level - 1].getBtnInput(),
						base[level - 1].getBtnRetry()); // 1�������� PlayBasePanel ���� ȭ�� ����
				PlayBar.add(trdTime);

				base[level - 1].add(PlayBar); // 1�������� base�� PlayBar �߰�
				base[level - 1].add(play); // 1�������� base�� play(Panel) �߰�

				card.next(getContentPane()); // ù �������� ȭ������ �Ѿ

				base[level - 1].setFocusable(true); // Ű���� �Է� ���� �� �ְ� ���ֱ�
				base[level - 1].requestFocus(); // ù�������� ��Ŀ�� ���߱�

			} else if (b.getText().equals("�� MAIN")) {
				card.first(getContentPane()); // ���� ȭ������
			} else if (b.getText().equals("RANKING")) {
				rank.setList(); // ��ŷ����Ʈ ���� ��
				card.last(getContentPane()); // ��ŷ ȭ������ �Ѿ��
			} else if (b.getText().equals("Exit")) { // exit��ư ������ â�ݱ�
				System.exit(0);
			} else if (b.getText().equals("�ٽ��ϱ�")) {
				card.first(getContentPane()); // ���� ȭ������ ����
				initPlayBasePanels(); // PlayBasePanel�� �ٽ� �ʱ�ȭ �ϱ�
			} else if (b.getText().equals("�Է�")) {
				JTextField txtfield = base[level - 1].getTxtInput(); // �ؽ�Ʈ�ʵ� ���޹޾Ƽ� ���ڿ� �ޱ�
				String intxt = txtfield.getText();

				if (intxt.length() != 3) { // 3���ڰ� �ƴϸ� �� ĭ���� ����� ���â ����
					txtfield.setText("");
					JOptionPane.showMessageDialog(txtfield, "�г����� 3���ڷ� �Է����ּ���.");
				} else { // 3���ڸ�
					txtfield.setText(intxt.toUpperCase());
					intxt = intxt.toUpperCase(); // �빮�ڷ� ������ֱ�.
					rank.setNewRank(nScore, intxt); // ��ũ �гο� ���� ���� �Ѱ��ְ�
					rank.setList(); // ��������Ʈ �����ϱ�
					if (rank.getLastScore() == nScore) // ��ŷ ���� Ȯ��â ����
						JOptionPane.showMessageDialog(txtfield, "��ŷ�� �������� ���߽��ϴ�.");
					else
						JOptionPane.showMessageDialog(txtfield, "��ŷ�� �����߽��ϴ�.");

					card.last(getContentPane()); // ��ũ ȭ������ ����
					initPlayBasePanels(); // PlayBasePanel�� �ٽ� �ʱ�ȭ
				}
			} // if(b.getText().equals()) ~ else if()
			else if (b.getText().equals("ON")) {
				first.changeBgmIcon(); // ��ư ��� Off�� �ٲٱ�
				b.setText("OFF");
				BGM.stopMusic(); // ���� ����
			} else if (b.getText().equals("OFF")) {
				first.changeBgmIcon(); // ��ư ��� On���� �ٲٱ�
				b.setText("ON");
				BGM.start(); // ���� Ű��
			}

		} // actionPerformed

	} // ButtonListener class

	public class KeyboardListener implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			int keyEvent = e.getKeyCode();

			if (keyEvent >= 37 && keyEvent <= 40) {

				play.move(keyEvent); // �����̱�
				play.view(keyEvent); // ȭ�� �����ֱ�

				if (!(play.getIsMovable())) { // �������� ���� ��Ȳ�̶��
					nMove--; // ������ Ƚ�� �������� �ʰ� �ϱ�
					play.setIsMovable(true);
				} // if
				Bark.moveMusic(); // ������ �� �Ҹ� ���

				lblMove.setText("" + ++nMove); // ������ Ƚ�� ������ ���

				if (play.isGameOver()) { // ���ӿ����ƴٸ�?
					base[level - 1].setFocusable(false); // Ű���� �Է� �� �ް� �ϰ�
					trdTime.stop(); // ������ ���� ��Ŵ.
					trdTime.tryView(); // ����ȭ�� ���
					trdTime = null;
				}

				else if (play.isGameClear()) { // �������� Ŭ�����ߴٸ�

					level = level + 1; // ���� �÷��ְ�

					if (level == 10) { // �������� ������ 10�̸�
						level--;
						base[level - 1].setFocusable(false); // Ű��Ŀ�� �����ְ�
						trdTime.setLblGame(base[level - 1].getLblAC()); // ����ȭ�� ���� ALLClear�� ����
						trdTime.setEndView(base[level - 1].getTxtInput(), base[level - 1].getBtnInput(),
								base[level - 1].getBtnRetry()); // ���� ȭ�� ����(��ư, �ؽ�Ʈ �ʵ�)
						trdTime.stop(); // �ð� ���߰�
						trdTime.tryView(); // ���� ȭ�� ���
						trdTime = null;
					} else {

						base[level - 2].remove(play); // �� �ܰ� �������� ȭ�� ���ֱ�

						nScore = nScore + ((trdTime.getMinute() * 60 + trdTime.getSecond()) * level - nMove); // �������
						nMove = 0; // ������ Ƚ�� �ʱ�ȭ

						// PlayBar ����
						setIconStage();
						lblStage.setIcon(Icon);
						lblScore.setText("" + nScore);
						lblMove.setText("" + nMove);

						// �� �迭 �ٽ� �ް� PlayPanel �籸��---------
						mapArray = new MapArray(level);
						a = mapArray.getArray();
						play = new PlayPanel(a);
						// ------------------------------------

						// -------Time Thread Label �ʱ�ȭ
						trdTime.setBase(base[level - 1]);
						trdTime.setLblGame(base[level - 1].getLblGO());
						trdTime.setEndView(base[level - 1].getTxtInput(), base[level - 1].getBtnInput(),
								base[level - 1].getBtnRetry());
						trdTime.initTime();
						// -------------------------------------

						// �����ܰ� base ����
						base[level - 1].add(PlayBar);
						base[level - 1].add(play);

						card.next(getContentPane()); // �����ܰ�� �Ѿ��
						base[level - 1].requestFocus(); // �����ܰ�� ��Ŀ�� ���߱�
					}
				} // if isGameClear

			} // ��, ��, ��, �� Ű�� ������
			else if (keyEvent == 90) {
				play.undo(); // ���� ���������� ���ư���
				play.view(keyEvent); // ȭ�� ���
				Bark.moveMusic(); // ������ ȿ���� ���
			} // Z Ű�� ������
		} // keyReleased()

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

	} // KeyboradListener class

	void initPlayBasePanels() {

		for (int i = 0; i < level; i++) {
			base[i].getBtnInput().setVisible(false);
			base[i].getBtnRetry().setVisible(false);
			base[i].getTxtInput().setText("");
			base[i].getTxtInput().setVisible(false);
			base[i].getLblGO().setVisible(false);
			base[i].getLblAC().setVisible(false);
			base[i].remove(play);
			base[i].setFocusable(true);
		} // ���� �������������� BasePanel���� ������Ʈ�� �Ⱥ��̰� �ϰ�
			// ���� ���� �ʱ�ȭ
		nMove = 0;
		nScore = 0;
		level = 1;

		setIconStage(); // �������� �ܰ踦 ǥ���� �������� �ʱ�ȭ
		lblStage.setIcon(Icon);

		lblScore.setText("" + nScore);
		lblScore.setText("" + nMove);
	}

	void setIconStage() { // �������� ���� ������ ǥ�����ִ� ���������� �ٲ�
		String strLevel = String.valueOf(level);
		originIcon = new ImageIcon("images/stage" + strLevel + ".png"); // �ܰ� ǥ��!
		originImg = originIcon.getImage();
		changedImage = originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Icon = new ImageIcon(changedImage);
	}

	void setIconScore() { // �������� score ��� ���������� �ٲ�
		originIcon = new ImageIcon("images/ScoreBoard.png"); // �ܰ� ǥ��!
		originImg = originIcon.getImage();
		changedImage = originImg.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
		Icon = new ImageIcon(changedImage);
	}

	void setIconMove() { // �������� score ��� ���������� �ٲ�
		originIcon = new ImageIcon("images/MoveBoard.png"); // �ܰ� ǥ��!
		originImg = originIcon.getImage();
		changedImage = originImg.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		Icon = new ImageIcon(changedImage);
	}

	public static void main(String[] args) {

		new MainFrame();
	}

}
