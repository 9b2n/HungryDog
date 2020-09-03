
public class MapArray {

	int Array[][] = new int[12][12];

	public MapArray(int level) { // ���� ������ �°� �ʹ迭 ����

		switch (level) {
		case 1:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5 }, { 5, 5, 1, 4, 0, 0, 1, 5, 5, 5, 5, 5 },
					{ 5, 5, 1, 0, 2, 2, 1, 5, 1, 1, 1, 5 }, { 5, 5, 1, 0, 2, 0, 1, 5, 1, 3, 1, 5 },
					{ 5, 5, 1, 1, 1, 0, 1, 1, 1, 3, 1, 5 }, { 5, 5, 5, 1, 1, 0, 0, 0, 0, 3, 1, 5 },
					{ 5, 5, 5, 1, 0, 0, 0, 1, 0, 0, 1, 5 }, { 5, 5, 5, 1, 0, 0, 0, 1, 1, 1, 1, 5 },
					{ 5, 5, 5, 1, 1, 1, 1, 1, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };
			break;
		case 2:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5 },
					{ 5, 5, 1, 0, 0, 0, 0, 0, 1, 1, 1, 5 }, { 5, 1, 1, 2, 1, 1, 1, 0, 0, 0, 1, 5 },
					{ 5, 1, 0, 4, 0, 2, 0, 0, 2, 0, 1, 5 }, { 5, 1, 0, 3, 3, 1, 0, 2, 0, 1, 1, 5 },
					{ 5, 1, 1, 3, 3, 1, 0, 0, 0, 1, 5, 5 }, { 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };
			break;
		case 3:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, },
					{ 5, 5, 5, 1, 1, 1, 1, 1, 5, 5, 5, 5, }, { 5, 5, 5, 1, 4, 0, 1, 1, 1, 5, 5, 5, },
					{ 5, 5, 5, 1, 0, 2, 0, 0, 1, 1, 5, 5, }, { 5, 5, 1, 1, 1, 0, 1, 0, 0, 1, 5, 5, },
					{ 5, 5, 1, 3, 1, 0, 1, 1, 0, 1, 5, 5, }, { 5, 5, 1, 3, 2, 0, 0, 1, 0, 1, 5, 5, },
					{ 5, 5, 1, 3, 0, 0, 0, 2, 0, 1, 5, 5, }, { 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, }, };
			break;
		case 4:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, },
					{ 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 5, }, { 5, 5, 5, 1, 1, 0, 0, 1, 0, 4, 1, 5, },
					{ 5, 5, 5, 1, 0, 0, 0, 1, 0, 0, 1, 5, }, { 5, 5, 5, 1, 2, 0, 2, 0, 2, 0, 1, 5, },
					{ 5, 5, 5, 1, 0, 2, 1, 1, 0, 0, 1, 5, }, { 5, 1, 1, 1, 0, 2, 0, 1, 0, 1, 1, 5, },
					{ 5, 1, 3, 3, 3, 3, 3, 0, 0, 1, 5, 5, }, { 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, } };
			break;
		case 5:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, // 5�ܰ� ���� ������ ����
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 5, 5 },
					{ 5, 5, 1, 1, 1, 0, 0, 0, 0, 1, 5, 5 }, { 5, 1, 1, 3, 0, 2, 1, 1, 0, 1, 1, 5 },
					{ 5, 1, 3, 3, 2, 0, 2, 0, 0, 4, 1, 5 }, { 5, 1, 3, 3, 0, 2, 0, 2, 0, 1, 1, 5 },
					{ 5, 1, 1, 1, 1, 1, 1, 0, 0, 1, 5, 5 }, { 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };

			break;
		case 6:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, // 6�ܰ� ���� ������ ����
					{ 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5 }, { 5, 5, 1, 0, 0, 1, 1, 0, 0, 0, 1, 5 },
					{ 5, 5, 1, 0, 0, 0, 2, 0, 0, 0, 1, 5 }, { 5, 5, 1, 2, 0, 1, 1, 1, 0, 2, 1, 5 },
					{ 5, 5, 1, 0, 1, 3, 3, 3, 1, 0, 1, 5 }, { 5, 1, 1, 0, 1, 3, 3, 3, 1, 0, 1, 1 },
					{ 5, 1, 0, 2, 0, 0, 2, 0, 0, 2, 0, 1 }, { 5, 1, 0, 0, 0, 0, 0, 1, 0, 4, 0, 1 },
					{ 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };

			break;
		case 7:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, // 7�ܰ� ���� ������ ����
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 5, 5 },
					{ 5, 5, 5, 5, 1, 0, 0, 0, 0, 1, 5, 5 }, { 5, 5, 1, 1, 1, 2, 2, 2, 0, 1, 5, 5 },
					{ 5, 5, 1, 4, 0, 2, 3, 3, 0, 1, 1, 5 }, { 5, 5, 1, 0, 2, 3, 3, 3, 1, 1, 1, 5 },
					{ 5, 5, 1, 1, 1, 1, 0, 0, 1, 5, 5, 5 }, { 5, 5, 5, 5, 5, 1, 1, 1, 1, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };

			break;
		case 8:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, // 8�ܰ� ���� ������ ����
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 1, 1, 1, 1, 5, 5, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 5, 5, 1, 0, 0, 0, 1 },
					{ 1, 0, 2, 0, 1, 1, 1, 1, 2, 0, 0, 1 }, { 1, 0, 0, 2, 3, 3, 3, 3, 0, 2, 0, 1 },
					{ 1, 1, 0, 0, 0, 0, 1, 0, 4, 0, 1, 1 }, { 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };

			break;
		case 9:
			Array = new int[][] { { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, // 9�ܰ� ���� ������ ����
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 1, 1, 1, 1, 1, 1, 5, 5, 5 },
					{ 5, 5, 1, 1, 0, 0, 0, 0, 1, 1, 5, 5 }, { 5, 5, 1, 0, 2, 0, 2, 2, 0, 1, 5, 5 },
					{ 5, 5, 1, 3, 3, 3, 3, 3, 3, 1, 5, 5 }, { 5, 5, 1, 0, 2, 2, 0, 2, 0, 1, 5, 5 },
					{ 5, 5, 1, 1, 1, 0, 4, 1, 1, 1, 5, 5 }, { 5, 5, 5, 5, 1, 1, 1, 1, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 } };

			break;

		}
	}

	public int[][] getArray() {
		return Array;
	}

}