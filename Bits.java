import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bits {
	int index1 = 0;
	int index2 = 0;
	int index3 = 0;
	int count = 0;
	String[] inputString;
	String[] chooseOutputs;

	public Bits(String input) {
		this.inputString = input.split(",");
		setChooseOutputsLength(this.inputString[1]);
		fillOutputs();
		setIndex();
	}

	public void setChooseOutputsLength(String num) {
		int length = Integer.parseInt(num);
		this.chooseOutputs = new String[length];
	}

	public void fillOutputs() {
		int x = 2;
		for (int i = 0; i < this.chooseOutputs.length; i++) {
			this.chooseOutputs[i] = this.inputString[x];
			x++;
		}
	}

	public void setIndex() {
		for (int i = 0; i < this.inputString[0].length(); i++) {
			if (this.inputString[0].charAt(i) == '*' && this.count == 0) {
				this.index1 = i;
				this.count += 1;
			} else if (this.inputString[0].charAt(i) == '*' && count == 1) {
				this.index2 = i;
				this.count += 1;
			} else if (this.inputString[0].charAt(i) == '*' && count == 2) {
				this.index3 = i;
				this.count += 1;
			}
		}
	}

	public String[] getStrings() {
		String finalStr = "";
		if (count == 1) {
			finalStr += (this.inputString[0].replace(this.inputString[0].charAt(this.index1), '1')) + ",";
			finalStr += (this.inputString[0].replace(this.inputString[0].charAt(this.index1), '0'));

		} else if (count == 2) {
			StringBuilder sb = new StringBuilder(this.inputString[0]);
			for (int i = 0; i <= 3; i++) {
				String binaryString = "";
				if (i < 2) {
					binaryString = "0" + Integer.toBinaryString(i);
					sb.setCharAt(this.index1, binaryString.charAt(0));
					sb.setCharAt(this.index2, binaryString.charAt(binaryString.length() - 1));
					finalStr += sb + ",";
				} else if (i >= 2 && i <= 3) {
					binaryString = Integer.toBinaryString(i);
					sb.setCharAt(this.index1, binaryString.charAt(0));
					sb.setCharAt(this.index2, binaryString.charAt(binaryString.length() - 1));
					finalStr += sb + ",";
				}
			}
		} else if (count == 3) {
			StringBuilder sb = new StringBuilder(this.inputString[0]);
			for (int i = 0; i <= 7; i++) {
				String binaryString = "";
				if (i == 1) {
					binaryString += ("00" + Integer.toBinaryString(i));
					sb.setCharAt(this.index1, binaryString.charAt(0));
					sb.setCharAt(this.index2, binaryString.charAt(1));
					sb.setCharAt(this.index3, binaryString.charAt(binaryString.length() - 1));
					finalStr += sb + ",";
				} else if (i < 4) {
					binaryString += ("0" + Integer.toBinaryString(i));
					sb.setCharAt(this.index1, binaryString.charAt(0));
					sb.setCharAt(this.index2, binaryString.charAt(1));
					sb.setCharAt(this.index3, binaryString.charAt(binaryString.length() - 1));
					finalStr += sb + ",";
				} else if (i >= 4 && i <= 7) {
					binaryString += (Integer.toBinaryString(i));
					sb.setCharAt(this.index1, binaryString.charAt(0));
					sb.setCharAt(this.index2, binaryString.charAt(1));
					sb.setCharAt(this.index3, binaryString.charAt(binaryString.length() - 1));
					finalStr += sb + ",";
				}
			}
		}
		String[] finalStrings = finalStr.split(",");
		return finalStrings;
	}

	public String getFinalOutput() {
		String[] finalStrings = this.getStrings();
		String[] results = this.chooseOutputs;
		String finalStr = "";
		for (int i = 0; i < results.length; i++) {
			for (int j = 0; j < finalStrings.length; j++) {
				if (finalStrings[j].equals(results[i])) {
					finalStr += finalStrings[j] + ", ";
				}
			}
		}
		if (finalStr.length() == 0) {
			return "NONE";
		}
		return finalStr.substring(0, finalStr.length() - 2);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(
				new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Bits/src/bits.txt"));
		String output = "OUTPUTS:\n";
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Bits b = new Bits(line);
			output += b.getFinalOutput() + "\n";
		}
		System.out.println(output);
		input.close();

	}
}