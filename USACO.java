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

	/*public static int silver(String filename) {
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
			//System.out.println(Arrays.deepToString(data));
			data =silverH(t, data);

			//System.out.println(data[Integer.parseInt(count[2]) - 1][Integer.parseInt(count[3]) - 1] + "  ");
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
					//System.out.println(Arrays.deepToString(n));
					if (data[y][x] == -1 || data[y][x] == 1) {
						n[y][x] = data[y][x];
					} else {

						int ans = 0;
						for (int j = 0; j < 7; j+=2) {
							if (isInBounds( y + moves[j], x + moves[j + 1], data)) {
								//System.out.println("ok");
								if (data[y][x] != -1 && data[y+moves[j]][x+moves[j+1]] != -1) {
									ans += data[y+ moves[j]][x+ moves[j + 1]];
								}
							}
						}
						n[y][x] = ans;
					}
				}
			}
		}
		return n;
	}*/
	public static int silver(String filename) throws FileNotFoundException{
		File fil = new File(filename);
		Scanner in = new Scanner(fil);
		String str = in.nextLine();
		String[] values = str.split(" ", -2);
		int N2 = Integer.parseInt(values[0]);
		int M = Integer.parseInt(values[1]);
		int T = Integer.parseInt(values[2]);
		String[][] data = new String[N2][M];
		for (int i = 0; i < N2; i++){
		  str = in.nextLine();
		  for (int c = 0; c < str.length(); c++){
			data[i][c] = str.substring(c, c + 1);
		  }
		}
		str = in.nextLine();
		values = str.split(" ", -2);
		int R1 = Integer.parseInt(values[0]) - 1;
		int C1 = Integer.parseInt(values[1]) - 1;
		int R2 = Integer.parseInt(values[2]) - 1;
		int C2 = Integer.parseInt(values[3]) - 1;
		int[][] ans = new int[data.length][data[0].length];
		for (int r = 0; r < ans.length; r++){
		  for (int c = 0; c < ans[0].length; c++){
			if (data[r][c].equals(".")){
			  ans[r][c] = 0; 
			}else{
			  ans[r][c] = -99999999;
			}
		  }
		}
		ans[R1][C1] = 1;
		ArrayList<Integer> loc;
		int[][] moves = {{0,1},{0,-1},{1,0},{-1,0}};
		while(T > 0){
		  loc = new ArrayList<>();
		  for(int r = 0; r < data.length; r++){
			for (int c = 0; c < data[0].length; c++){
			  if (ans[r][c] > 0){
				loc.add(r);
				loc.add(c);
			  }
			}
		  }
		  for (int a = 0; a < loc.size(); a += 2){
			for (int b = 0; b < moves.length; b++){
			  if (inbounds(loc.get(a) + moves[b][0], loc.get(a+1) + moves[b][1], ans)){
				ans[loc.get(a) + moves[b][0]][loc.get(a+1) + moves[b][1]] += ans[loc.get(a)][loc.get(a+1)];
			  }
			}
			ans[loc.get(a)][loc.get(a+1)] = 0;
		  }
		  T--;
		}
		return ans[R2][C2];
  }

  private static boolean inbounds(int x, int y, int[][] board){
    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length){
      return false;
    }
    return true;
  }


}
