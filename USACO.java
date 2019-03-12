import java.util.*;
import java.io.*;
public class USACO {
	public static int bronze(String filename) {
		File fil = new File(filename);
		Scanner in = new Scanner(fil);
		String[] count = in.nextLine().split(" ");
		int[][] data = new int[Integer.parseInt(count[0])][Integer.parseInt(count[1])];
		for (int i = 0; i < data.length; i++) {
			count = in.nextLine().split(" ");
			for (int y = 0; y < data[0].length; y++) {
				data[i][y] = Integer.parseInt(count[y]);
			}
		}
	}