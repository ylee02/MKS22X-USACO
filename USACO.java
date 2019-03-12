import java.util.*;
import java.io.*;
public class USACO {
	public static int bronze(String filename) {
		File fil = new File(filename);
		Scanner in = new Scanner(fil);
		String[] count = in.nextLine().split(" ");
		int[][] data = new int[Integer.parseInt(count[0])][Integer.parseInt(count[1])];
		