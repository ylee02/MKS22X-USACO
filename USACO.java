import java.util.*;
import java.io.*;
public class USACO {


	public static int bronze(String filename) {
		try {
			File fil = new File(filename);
			Scanner in = new Scanner(fil);

				String[] count = in.nextLine().split(" ");
				int[][] data = new int[Integer.parseInt(count[0])][Integer.parseInt(count[1])];
			for (int i = 0; i < data.length; i++) {
				String[] temp = in.nextLine().split(" ");
				for (int y = 0; y < data[0].length; y++) {
					data[i][y] = Integer.parseInt(temp[y]);
				}
			}
			int[][] coms = new int[Integer.parseInt(count[3])][3];
			for (int i = 0; i < coms.length; i++) {
				String[] temp = in.nextLine().split(" ");
				for (int y = 0; y < coms[0].length; y++) {
					coms[i][y]= Integer.parseInt(temp[y]);
				}
			}

			for (int i = 0; i < coms.length; i++) {
				stompHelp(coms[i], data);
			}
			return findVolume(data, Integer.parseInt(count[2])) *72*72;
		}catch(FileNotFoundException e) {
			System.exit(1);
			return -1;
		}


	}

	public static void stompHelp(int[] coms, int[][] data) {
		int maxEle = 0;
		for (int i = coms[0] -1; i < coms[0] + 2; i++) {
			for (int y = coms[1] - 1; y < coms[1] + 2; y++) {
				maxEle = Math.max(maxEle, data[i][y]);
			}
		}
		maxEle -= coms[2];
		for (int i = coms[0] -1; i < coms[0] + 2; i++) {
			for (int y = coms[1] - 1; y < coms[1] + 2; y++) {
				if (data[i][y] > maxEle) {
					data[i][y] = maxEle;
				}
			}
		}
	}

	public static int findVolume(int[][] data, int e) {
		int ans = 0;
		for (int i = 0; i < data.length; i++){
			for (int y = 0; y < data[0].length; y++) {
				if (e - data[i][y] >0) {
					ans += e - data[i][y];
				}
			}
		}
		return ans;
	}

	public static int silver(String filename) {
		try {
			File fil = new File(filename);
			Scanner in = new Scanner(fil);

			String[] count = in.nextLine().split(" ");
			int[][] data = new int[Integer.parseInt(count[0])][Integer.parseInt(count[1])];
			int t = Integer.parseInt(count[2]);
			for(int i = 0; i < data.length; i++) {
				String temp = in.nextLine();
				for (int y = 0; y < data[0].length; y++) {
					if (temp.charAt(y) == '*') {
						data[i][y] = -1;
					}
				}
			}
			count = in.nextLine().split(" ");
			data[Integer.parseInt(count[0]) - 1][Integer.parseInt(count[1]) - 1] = 1;
			silverH(t, data);
			System.out.println(data[Integer.parseInt(count[2]) - 1][Integer.parseInt(count[3]) - 1] + "  ");
			return data[Integer.parseInt(count[2]) - 1][Integer.parseInt(count[3]) - 1];
		}catch(FileNotFoundException e) {

			System.exit(1);
			return -1;
		}
	}

	public static boolean isInBounds(int r, int c, int[][]data) {
		try {
			int test = data[r][c];
			return true;
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
	}

	public static int[][] silverH(int t, int[][]data) {
		int[] moves = {1, 0, 0, 1, -1, 0, 0, -1};
		int[][] n = new int[data.length][data[0].length];
		for (int i = 0; i < t; i++) {
			for (int y = 0; y < data.length; y++) {
				for (int x = 0; x < data[0].length; x++) {
					System.out.println(Arrays.deepToString(data));
					if (data[y][x] == -1) {
						n[y][x] = -1;
					} else {
						int ans = 0;
						for (int j = 0; j < 7; j++) {
							if (isInBounds( y + moves[j], x + moves[j + 1], data)) {
								if (data[y][x] != -1) {
									ans += data[y][x];
								}
							}
						}
						n[y][x] = ans;
					}
				}
			}
		}
		return n;
	}


}
