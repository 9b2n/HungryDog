import javax.swing.*;
import java.awt.*;

public class PlayPanel extends JPanel {
	private int mapArray[][]; // ȭ�鿡 ������ �� ����
	private int player_x, player_y, BoxX[] = new int[6], BoxY[] = new int[6], GoalX[] = new int[6],
			GoalY[] = new int[6], goal_cnt = 0, cnt, undo_box_x = 0, undo_box_y = 0, nUndo = 0; // ĳ���� ��ǥ, ���ٱ͵� ��ǥ,
																								// ��׸���ǥ, ��׸� ����, ���ٱ�
																								// ����, ���� �ڽ� ��ǥ, ����÷���
	private JLabel Map[][] = new JLabel[12][12], lblCharacter, lblBowl[] = new JLabel[6], lblBox[] = new JLabel[6]; // ȭ�鿡
																													// ��ǥ����
																													// ������
																													// �󺧵�(�÷���
																													// ȭ��)
	private boolean isMovable = true, isGameOver; // ����������, ���� �����ƴ��� ��ȯ
	private ImageIcon dog_front, dog_back, dog_right, dog_left, wall, ground, bone, tree, bowl, fullbowl; // ������ �̸���

	public PlayPanel(int a[][]) {
		cnt = 0;
		mapArray = a; // �� �迭 ���� �ޱ�

		// �г� �⺻ ����
		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);

		// ---------------------------������ ����--------------------------
		ImageIcon originIcon = new ImageIcon("images/cute_front.png"); // ĳ���� ��
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		dog_front = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_back.png"); // ĳ���� ��
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		dog_back = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_left.png"); // ĳ���� ����
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		dog_left = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/cute_right.png"); // ĳ���� ������
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		dog_right = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/wall.jpg"); // ��
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		wall = new ImageIcon(changedImg);

		ground = new ImageIcon("images/ground.jpg"); // ��

		originIcon = new ImageIcon("images/bone.png"); // ���ٱ�
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		bone = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/bowl.png"); // �� ��׸�
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		bowl = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/fullbowl.png"); // ���� ��׸�
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		fullbowl = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/tree.jpg"); // �� ��
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		tree = new ImageIcon(changedImg);
		// ----------------------------------------------------------------------

		// -----------�ʹ迭�� ������ ���� ĳ����, ���ٱ�, ��׸��� ���� �󺧷� �׷��� ȭ���� �� ���� ���̰� �ϱ�
		// ----------�� ĭ�� 50x50�� ũ��
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (mapArray[i][j] == 4) { // ĳ���� ��ǥ ����
					player_x = j;
					player_y = i;
					mapArray[i][j] = 0;
					lblCharacter = new JLabel(dog_front);
					lblCharacter.setBounds(player_x * 50, player_y * 50, 50, 50);
					lblCharacter.setVisible(true);
					add(lblCharacter);
				} else if (mapArray[i][j] == 2) { // ���ٱ��� ������ŭ ��ǥ ����, �׸���
					BoxX[cnt] = j;
					BoxY[cnt] = i;

					lblBox[cnt] = new JLabel(bone);
					lblBox[cnt].setBounds(BoxX[cnt] * 50, BoxY[cnt] * 50, 50, 50);
					lblBox[cnt].setVisible(true);
					add(lblBox[cnt]);

					cnt++;
				} else if (mapArray[i][j] == 3) { // ��׸��� ������ŭ ��ǥ����, �׸���
					GoalX[goal_cnt] = j;
					GoalY[goal_cnt] = i;

					lblBowl[goal_cnt] = new JLabel(bowl);
					lblBowl[goal_cnt].setBounds(GoalX[goal_cnt] * 50, GoalY[goal_cnt] * 50, 50, 50);
					lblBowl[goal_cnt].setVisible(true);
					add(lblBowl[goal_cnt]);

					goal_cnt++;
				}
			}
		}

		// ---------------------------------�� �׸���------------------------------------
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) { // �� �׸��� - ĳ���ͳ� ���ٱ�, ��׸��� �� ���� �÷����� ������ �� ��ǥ�鵵 ������ �׷��ش�.
				if (mapArray[i][j] == 4 || mapArray[i][j] == 2 || mapArray[i][j] == 0 || mapArray[i][j] == 3) {
					Map[i][j] = new JLabel(ground);
					Map[i][j].setOpaque(true);
					add(Map[i][j]);
				} else if (mapArray[i][j] == 1) { // �� �׸���
					Map[i][j] = new JLabel(wall);
					Map[i][j].setOpaque(true);
					add(Map[i][j]);
				} else if (mapArray[i][j] == 5) { // �� �׸���
					Map[i][j] = new JLabel(tree);
					Map[i][j].setOpaque(true);
					add(Map[i][j]);
				}
				Map[i][j].setBounds(j * 50, i * 50, 50, 50);
				Map[i][j].setVisible(true);

			}
		} // ĳ���� ��ǥ, �ڽ� ��ǥ ����

	}

	public void move(int key) { // ĳ���Ϳ� ���ٱ�, ��׸� ��ǥ �ű�� �޼ҵ�

		switch (key) { // ����Ű ���� �޾ƿͼ� �� ���� ���� ������
		case 38: // UP-------------------------------------------------------------------------------------------
			player_y--; // ĳ���� y��ǥ�� �̸� -1 �Ű���
			nUndo = 1; // ĳ���͸� ������
			if (mapArray[player_y][player_x] == 2) // �÷��̾� �̵��� ��ǥ�� Box���
			{
				if (mapArray[player_y - 1][player_x] == 0 || mapArray[player_y - 1][player_x] == 3) // �ڽ��� �̵��ؾ��� �κ���
																									// ���̳� ���̶��
				{
					mapArray[player_y][player_x] = 0; // ĳ���� �ڸ� 0���� �����
					mapArray[player_y - 1][player_x] = 2; // ���� �ڽ��� �ٲ۴�
					undo_box_y = player_y - 1; // �ڽ� ��ġ ������ְ�
					undo_box_x = player_x;
					for (int i = 0; i < goal_cnt; i++) {
						if (BoxX[i] == player_x && BoxY[i] == player_y) {
							BoxY[i] = undo_box_y; // �ڽ� ��ǥ ����
						}
					}
					nUndo = 11; // ĳ���Ϳ� �ڽ� ��� ������
					isMovable = true;
				} else { // ���̸�
					player_y++; // ����ġ
					isMovable = false; // ��������
					nUndo = 0; // ��ε� ����
				}

			} else if (mapArray[player_y][player_x] == 1) {
				player_y++; // ����ġ
				isMovable = false; // ��������
				nUndo = 0; // ��ε� ����
			}
			break; // �Ʒ�, ����, �����ʵ� ���� ������� �ٲ��ش�.
		case 40: // DOWN-------------------------------------------------------------------------------
			player_y++;
			nUndo = 2;
			if (mapArray[player_y][player_x] == 2) {
				if (mapArray[player_y + 1][player_x] == 0 || mapArray[player_y + 1][player_x] == 3) {
					mapArray[player_y][player_x] = 0;
					mapArray[player_y + 1][player_x] = 2;
					undo_box_x = player_x;
					undo_box_y = player_y + 1;
					for (int i = 0; i < goal_cnt; i++) {
						if (BoxX[i] == player_x && BoxY[i] == player_y) {
							BoxY[i] = undo_box_y;
						}
					}
					nUndo = 21;
					isMovable = true;
				} else {
					player_y--;
					isMovable = false;
					nUndo = 0;
				}
			} else if (mapArray[player_y][player_x] == 1) {
				player_y--;
				isMovable = false;
				nUndo = 0;
			}
			break;
		case 37: // LEFT----------------------------------------------------------------------------------
			player_x--;
			nUndo = 3;
			if (mapArray[player_y][player_x] == 2) {
				if (mapArray[player_y][player_x - 1] == 0 || mapArray[player_y][player_x - 1] == 3) {
					mapArray[player_y][player_x] = 0;
					mapArray[player_y][player_x - 1] = 2;
					undo_box_x = player_x - 1;
					undo_box_y = player_y;
					for (int i = 0; i < goal_cnt; i++) {
						if (BoxX[i] == player_x && BoxY[i] == player_y) {
							BoxX[i] = undo_box_x;
						}
					}
					nUndo = 31;
					isMovable = true;
				} else {
					player_x++;
					isMovable = false;
					nUndo = 0;
				}
			} else if (mapArray[player_y][player_x] == 1) {
				player_x++;
				isMovable = false;
				nUndo = 0;
			}
			break;
		case 39: // RIGHT--------------------------------------------------------------------------------------
			player_x++; // ĳ���� ���������� �̵�(x��ǥ + 1)
			nUndo = 4;
			if (mapArray[player_y][player_x] == 2) {
				if (mapArray[player_y][player_x + 1] == 0 || mapArray[player_y][player_x + 1] == 3) {
					mapArray[player_y][player_x] = 0;
					mapArray[player_y][player_x + 1] = 2;
					undo_box_x = player_x + 1;
					undo_box_y = player_y;
					for (int i = 0; i < goal_cnt; i++) {
						if (BoxX[i] == player_x && BoxY[i] == player_y) {
							BoxX[i] = undo_box_x;
						}
					}
					nUndo = 41;
					isMovable = true;
				} else {
					player_x--;
					isMovable = false;
					nUndo = 0;
				}

			} else if (mapArray[player_y][player_x] == 1) {
				player_x--;
				isMovable = false;
				nUndo = 0;
			}
			break;
		}
	} // move

	public void undo() {
		switch (nUndo) { // nUndo���� ���� ���� ���·� �ٲ�
		case 1:
			move(40); // ĳ���͸� �Ʒ��� ��������
			break;
		case 11: // ĳ���Ϳ� ���ٱ� ��� �Ʒ��� ��������
			mapArray[undo_box_y][undo_box_x] = 0;
			mapArray[undo_box_y + 1][undo_box_x] = 2; // ���ٱ� ���� �ʿ��� �������ְ�
			for (int i = 0; i < goal_cnt; i++) { // ���ٱ� ��ǥ �ٲ��ְ�
				if (BoxX[i] == undo_box_x && BoxY[i] == undo_box_y) {
					BoxY[i] = undo_box_y + 1;
				}
			}
			move(40); // ĳ���� �����̱�
			break;
		case 2:
			move(38); // ĳ���͸� ���� �����̱�
			break;
		case 21: // ĳ���Ϳ� ���ٱ� ��� ����
			mapArray[undo_box_y][undo_box_x] = 0;
			mapArray[undo_box_y - 1][undo_box_x] = 2; // ���ٱ� ���� �����̰�
			for (int i = 0; i < goal_cnt; i++) { // ���ٱ� ��ǥ �ٲ��ְ�
				if (BoxX[i] == undo_box_x && BoxY[i] == undo_box_y) {
					BoxY[i] = undo_box_y - 1;
				}
			}
			move(38); // ĳ���� �����̱�
			break;
		case 3: // ĳ���͸� ���������� �����̱�
			move(39);
			break;
		case 31: // ĳ���Ϳ� ���ٱ� ��� ����������
			mapArray[undo_box_y][undo_box_x] = 0;
			mapArray[undo_box_y][undo_box_x + 1] = 2; // ���ٱ� ���� �������ְ�
			for (int i = 0; i < goal_cnt; i++) {
				if (BoxX[i] == undo_box_x && BoxY[i] == undo_box_y) {
					BoxX[i] = undo_box_x + 1;
				}
			}
			move(39); // ĳ���� �����̱�
			break;
		case 4:
			move(37); // ĳ���͸� �������� �����̱�
			break;
		case 41: // ĳ���Ϳ� ���ٱ� �����̱�
			mapArray[undo_box_y][undo_box_x] = 0;
			mapArray[undo_box_y][undo_box_x - 1] = 2; // ���ٱ� ���� �������ְ�
			for (int i = 0; i < goal_cnt; i++) {
				if (BoxX[i] == undo_box_x && BoxY[i] == undo_box_y) {
					BoxX[i] = undo_box_x - 1;
				}
			}
			move(37); // ĳ���� �����̱�
			break;
		}
		nUndo = 0; // �ٽ� �� �ٲٰ� �ϱ�
	} // undo

	public void view(int key) { // ȭ�鿡 ���̰� �ϱ�
		int flag = 0;
		switch (key) { // ���� ����Ű�� ���� ĳ���� ����ٲٱ�
		case 38: // ��
			lblCharacter.setIcon(dog_back);
			break;
		case 40: // �Ʒ�
			lblCharacter.setIcon(dog_front);
			break;
		case 37: // ������
			lblCharacter.setIcon(dog_left);
			break;
		case 39: // ����
			lblCharacter.setIcon(dog_right);
			break;
		}

		lblCharacter.setBounds(player_x * 50, player_y * 50, 50, 50); // �Ű��� ĳ���� �׸���

		for (int i = 0; i < goal_cnt; i++) { // ��׸� �׸���
			if (mapArray[GoalY[i]][GoalX[i]] == 2) { // ���ٱͰ� �ִٸ�(��ǥ�� ��ģ�ٸ�) ���� ��׸�
				lblBowl[i].setIcon(fullbowl);
			} else {
				lblBowl[i].setIcon(bowl); // ���ٱͰ� �ִٸ� �� ��׸�
			}
		}

		for (int i = 0; i < goal_cnt; i++) { // ���ٱ� �׸���
			flag = 0;
			for (int j = 0; j < goal_cnt; j++) {
				if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) { // ��׸��� �ִٸ� ���ٱ� ���ֱ�
					flag = 1;
					lblBox[i].setVisible(false);
				}
			}
			lblBox[i].setBounds(BoxX[i] * 50, BoxY[i] * 50, 50, 50); // ������ ���ٱ� �׸���
			if (flag == 0)
				lblBox[i].setVisible(true); // �ƴϸ� �׳� ���̰� �ϱ�
		}

	} // view()

	public boolean isGameClear() { // ���� Ŭ���� �ߴ��� ��ȯ
		int Goal_Count = 0; // ���� ��׸� ����

		for (int i = 0; i < goal_cnt; i++) {
			if (mapArray[GoalY[i]][GoalX[i]] == 2) {
				Goal_Count++; // ��ǥ������ ���ڰ� ���� ��ǥ��.
			}
		}

		if (Goal_Count == goal_cnt) { // ��׸��� ��� �����ִ��� ���� ��ȯ
			return true;
		} else
			return false;
	}

	public boolean isGameOver() { // ������ �� ���� ��Ȳ�� �����ߴ���(�ڽ��� ���� ���� ������ ���� ����)

		boolean OverFlag = false;

		for (int i = 0; i < this.cnt; i++) { // �� �ܰ��� ������ ������ŭ Ȯ��!!
			if (mapArray[BoxY[i] - 1][BoxX[i]] == 1) { // ���� ���� ��
				if (mapArray[BoxY[i]][BoxX[i] + 1] == 1) { // ���ӿ��� ���ǿ� �����Ǹ�.
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				} else if (mapArray[BoxY[i]][BoxX[i] - 1] == 1) { // ���ʵ� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!

					}
				}
			} // if(���� ���� Ȯ��)

			else if (mapArray[BoxY[i]][BoxX[i] + 1] == 1) { // ������ Ȯ��
				if (mapArray[BoxY[i] - 1][BoxX[i]] == 1) { // ���ʵ� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				}

				else if (mapArray[BoxY[i] + 1][BoxX[i]] == 1) { // �Ʒ��ʵ� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				}
			} // if( ���� ������ Ȯ�� )

			else if (mapArray[BoxY[i] + 1][BoxX[i]] == 1) { // �Ʒ��� Ȯ��
				if (mapArray[BoxY[i]][BoxX[i] + 1] == 1) { // ������ Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				} else if (mapArray[BoxY[i]][BoxX[i] - 1] == 1) { // ���ʵ� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				}
			} // if( ���� �Ʒ���! )

			else if (mapArray[BoxY[i]][BoxX[i] - 1] == 1) { // ���� Ȯ��
				if (mapArray[BoxY[i] + 1][BoxX[i]] == 1) { // �Ʒ��� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				} else if (mapArray[BoxY[i] - 1][BoxX[i]] == 1) { // ���ʵ� Ȯ��
					OverFlag = true; // ���� �̼��Դϴ�...
					for (int j = 0; j < this.cnt; j++) { // �װ� ���������� �����ִ��� Ȯ���մϴ�!
						if (BoxX[i] == GoalX[j] && BoxY[i] == GoalY[j]) // ���� ����������
							OverFlag = false; // ���Ǽ��󿡼� ����!!
					}

					if (OverFlag) { // ���ӿ����� Ȯ���Ǹ�!
						this.isGameOver = true; // ������ Ʈ��!
						break; // �ο��� ���������~!
					}
				}
			} // if( ���� ����! )

		} // for(i)

		return this.isGameOver;
	}

	public boolean getIsMovable() {
		return isMovable;
	} // ���������� ��ȯ

	public void setIsMovable(boolean init) {
		isMovable = init;
	} // ������ ���� �ʱ�ȭ
}
