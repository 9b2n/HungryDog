import java.awt.Point;

public class RankData {

	public String name; // 유저 이름
	public int score; // 유저 점수

	public RankData() {

	}

	public RankData(RankData obj) {
		name = obj.name;
		score = obj.score;
	} // constructor

}
