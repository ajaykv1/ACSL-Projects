import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This tells the user where an open parenthasis '(' or a close prenthasis  ')' goes in an expression
 * For example, "2-5*(6+1" would return 9, because the end has to be (6+1)
 @author ajaykrishnavajjala
 */
public class Enclosure {
	private String line;
	private String operands = "+-*/";

	public Enclosure(String line) {
		this.line = line;
	}

	public int[] findLocations() {
		List<Integer> locations = new ArrayList<Integer>();
		int indexOfClose = -1;
		int indexOfOpen = -1;
		boolean closed = false;
		for (int i = 0; i < this.line.length(); i++) {
			if (this.line.charAt(i) == ')') {
				indexOfClose = i;
				closed = true;
			}
			if (this.line.charAt(i) == '(') {
				indexOfOpen = i;
				closed = false;
			}
		}
		if (closed) {
			locations.add(1);
			for (int i = 0; i <= indexOfClose; i++) {
				if (operands.contains(this.line.charAt(i) + "")) {
					if ((i + 2) != indexOfClose)
						locations.add(i + 2);
				}
			}
		} else {
			for (int i = indexOfOpen; i < this.line.length(); i++) {
				if (operands.contains(this.line.charAt(i) + "")) {
					locations.add(i + 3);
				}
			}
		}
		int[] location = new int[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			location[i] = locations.get(i);
		}
		return location;
	}

	public String getFinalOutput() {
		int[] outputs = findLocations();
		String finalStr = "";
		for (Integer location : outputs) {
			finalStr += location + ",";
		}
		return finalStr.substring(0, finalStr.length() - 1);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(
				new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Enclosure/src/enclosure.txt"));
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Enclosure x = new Enclosure(line);
			System.out.println(x.getFinalOutput());
		}
		input.close();
	}
}
