import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Rank extends JPanel {

	private JButton btnRestart, btnExit; // 재시작을 위해 스타트패널로 가는 restart 버튼과 게임 종료를 위한 exit 버튼
	private JLabel lblTitle, lblSub, lblName[] = new JLabel[5], lblScore[] = new JLabel[5]; // RANKING, NAME SCORE를 띄워줄
																							// 라벨들과 랭킹에 띄워줄 name,
																							// score들을 저장하는 lblName,
																							// lblScore
	private RankData[] rDatas; // RankData 객채 배열 저장
	private int newScore; // 플레이한 새로운 점수를 저장하기 위한 변수
	private String newName; // 새로운 이름 저장을 위한 newName
	private ImageIcon restart1, restart2, exit1, exit2; // 재시작, 종료 버튼에 이미지를 씌워주기 위한 이미지아이콘 각각 2개씩

	public Rank() {
		Color backcolor = new Color(246, 223, 170);
		setPreferredSize(new Dimension(600, 700));
		setBackground(backcolor);
		setLayout(null);

		// 상단 제목 라벨
		lblTitle = new JLabel("RANKING"); // RANKING 제목 라벨 생성
		lblTitle.setBounds(50, 50, 500, 90); // 좌표 최상단, 크기 지정
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 50)); // 폰트지정
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER); // 중앙 정렬
		add(lblTitle); // Rank에 추가해준다.

		// 부제목 라벨
		lblSub = new JLabel("     NAME          SCORE"); // NAME, SCORE를 띄워줄 서브 타이틀 라벨 생성
		lblSub.setBounds(50, 165, 500, 60); // title 아래에 좌표 설정
		lblSub.setOpaque(true); // 라벨 자체의 background를 나타내기 위한 true
		lblSub.setFont(new Font("Verdana", Font.BOLD, 35));
		lblSub.setForeground(Color.red); // 글자색 변경
		add(lblSub);

		// 파일 입출력

		// --------------------------- 파일 입력 받아오기 --------------------------
		int i = 0;
		try {
			// 입력 스트림 생성
			FileReader filereader = new FileReader(".\\rankscore.txt"); // rankscore, rankname txt 파일들을 불러옵니다.
			FileReader filereader2 = new FileReader(".\\rankname.txt");

			// 입력 버퍼 생성
			BufferedReader br_s = new BufferedReader(filereader); // score을 위한 s , name을 위한 n 버퍼생성
			BufferedReader br_n = new BufferedReader(filereader2);

			rDatas = new RankData[6]; // 랭킹은 5위까지지만 추 후 추가될 새로운 점수,이름을 저장하기 위해 6으로 생성
			String sline; // score을 한줄씩

			// 두 파일의 줄 수가 같아서 while문에 하나 넣어도 같이 끝나도록 하였습니다.
			while ((sline = br_s.readLine()) != null) { // readLine을 사용해서 rankscore.txt의 내용을 한줄씩 sline에 저장한다.
				rDatas[i] = new RankData(); // 객체 배열의 해당 인덱스 객체 생성
				rDatas[i].name = br_n.readLine(); // 해당 점수라인의 이름을 받아 저장
				rDatas[i].score = Integer.parseInt(sline); // 점수를 받아 인트로 바꿔 저장
				i++;
			}
			br_s.close();
			br_n.close(); // 파일 닫아준다.
		} catch (FileNotFoundException e) { // 예외처리
		} catch (IOException e) {
			System.out.println(e);
		}
		// -----------------------------------------------------------------

		// --------------------------받아온 랭킹 데이터 정렬---------------------------
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

		// --------------------------정렬 완료된 데이터 5위까지 라벨 생성하여 띄워주기---------------------
		for (i = 0; i < 5; i++) {
			// NAME 랭킹 라벨
			lblName[i] = new JLabel(i + 1 + ".  " + rDatas[i].name);
			lblName[i].setBounds(50, 225 + 60 * (i), 500, 60);
			lblName[i].setFont(new Font("Verdana", Font.BOLD, 35));
			add(lblName[i]);
			// SCORE 랭킹 라벨
			lblScore[i] = new JLabel(rDatas[i].score + "\r\n");
			lblScore[i].setBounds(340, 225 + 60 * (i), 500, 60);
			lblScore[i].setFont(new Font("Verdana", Font.BOLD, 35));
			add(lblScore[i]);
		}

		// -------------------------- 이미지 아이콘 셋팅 -----------------------------------
		// 스타트패널로 가는 메인버튼 2가지
		ImageIcon originIcon = new ImageIcon("images/main1.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/main2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart2 = new ImageIcon(changedImg);

		// 종료를 위한 exit 버튼 2가지
		originIcon = new ImageIcon("images/exit1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/exit2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit2 = new ImageIcon(changedImg);

		// 재시작 버튼
		btnRestart = new JButton("□ MAIN", restart1);
		btnRestart.setBounds(50, 550, 235, 100);
		btnRestart.setBorderPainted(false);
		btnRestart.setContentAreaFilled(false); // 테두리를 없애주고, 기본 버튼 틀을 투명하게 바꿔준다.
		btnRestart.setForeground(backcolor);
		btnRestart.setRolloverIcon(restart2); // 마우스 올리면 흰색 이미지 변화
		btnRestart.setPressedIcon(restart2); // 버튼 누르면 흰색 이미지로 변화
		add(btnRestart);

		// 종료 버튼
		btnExit = new JButton("Exit", exit1);
		btnExit.setBounds(325, 550, 235, 100);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setForeground(backcolor);
		btnExit.setRolloverIcon(exit2);
		btnExit.setPressedIcon(exit2);
		add(btnExit);
	}

	// 랭킹 진입 여부를 위한 라스트 스코어 get 메소드
	public int getLastScore() {
		return rDatas[5].score;
	}

	// ----------------- 현재 랭킹 데이터의 마지막 인덱스에 게임 플레이한 새로운 데이터를 받아와 정렬하여 파일에 다시
	// 덮어씌워줍니다. --------
	public void setNewRank(int score, String name) {
		newScore = score; // 플레이한 새로운 점수
		newName = name; // 사용자가 입력한 새로운 이름
		rDatas[5] = new RankData();
		rDatas[5].score = newScore;
		rDatas[5].name = newName;
		int tmp;
		String tmp2;

		// ------------- 정렬 0~5 총 6개의 데이터를 정렬합니다.------------
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

		// -------- 파일에 다시 덮어씌우기 (정렬된 랭크데이터 입력) 정렬하면 1~5등이 가려지기때문에 5개의 데이터 다시 파일에 입력
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

	// -------------------새로운 플레이 데이터를 받아서 정렬된 rDatas 객체 배열을 Rank 패널에 다시 업로드하는 메소드
	// -----------
	public void setList() {
		for (int i = 0; i < 5; i++) {
			// NAME 랭킹 라벨
			lblName[i].setText(i + 1 + ".  " + rDatas[i].name + "\r\n");
			// SCORE 랭킹 라벨
			lblScore[i].setText(rDatas[i].score + "\r\n");
		}
	}

	// ------------------- 리스너를 위해 버튼들 넘겨주기 위한 get 메소드들--------------
	public JButton getRestart() {
		return btnRestart;
	}

	public JButton getExit() {
		return btnExit;
	}

}