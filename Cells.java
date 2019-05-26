import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This manipulates the cell string given, and manipulates based on add, divide, and subtract.
 * @author ajaykrishnavajjala
 *
 */
public class Cells {
	String line;
	String action;
	int n = 0;
	public Cells(String line) {
		String[] lineGiven = line.split(",");
		if (lineGiven[0].equals("DIVIDE")) {
			this.action = "DIVIDE";
		}
		else {
			this.action = lineGiven[0].substring(0, lineGiven[0].length()-1);
			this.n = Integer.parseInt(lineGiven[0].charAt(lineGiven[0].length()-1) + "");
		}
		
		this.line = lineGiven[1];
	}
	public String finalOutputs() {

		String finalStr = "";
		switch(this.action.toLowerCase()) {
			case "divide":
				finalStr = divide();
				break;
			case "add":
				finalStr = add();
				break;
			case "subtract":
				finalStr = subtract();
				break;
		}
		return finalStr;
	}
	public String divide() {
		String finalStr = "";
		finalStr = this.line.substring(0, 4);
		finalStr += finalStr;
		finalStr += (" and ");
		String str = this.line.substring(4, this.line.length());
		finalStr += (str + str);
		return finalStr;
	}
	public String add() {
		String finalStr = "";
		String firstN = this.line.substring(0, n);
		finalStr = firstN;
		finalStr += firstN;
		for (int i = 0; i <= this.line.length() - finalStr.length(); i++) {
			finalStr += this.line.charAt(n);
			n++;
		}
		return finalStr;
	}
	public String subtract() {
		String finalStr = "";
		finalStr = this.line.substring(n, this.line.length());
		finalStr += this.line.substring(finalStr.length(), this.line.length());
		return finalStr;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Cells/src/cells.txt"));
		while(input.hasNextLine()) {
			String line = input.nextLine();
			Cells cells = new Cells(line);
			System.out.println(cells.finalOutputs());
		}
		input.close();
	}
}
