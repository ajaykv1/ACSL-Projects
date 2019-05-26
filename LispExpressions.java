import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This will return certain parts of a lisp expression based on the command given in the text file
 * @author ajaykrishnavajjala
 *
 */
public class LispExpressions {

	private String firstLine;
	private String secondLine;
	private String thirdLine;
	private String fourthLine;
	private String fifthLine;

	public LispExpressions(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int count = 0;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			switch (count) {
			case 0:
				this.firstLine = line;
				break;
			case 1:
				this.secondLine = line;
				break;
			case 2:
				this.thirdLine = line;
				break;
			case 3:
				this.fourthLine = line;
				break;
			case 4:
				this.fifthLine = line;
				break;
			}
			count += 1;
		}
		input.close();
	}

	public String finalOutputs() {
		String finalStr = "";
		String[] outputs = new String[5];
		// First Output Line
		String[] splitArray1 = this.firstLine.substring(2, this.firstLine.length() - 1).split(" ");
		String firstStr = splitArray1[0] + " ";
		if (splitArray1[0].length() > 1) {
			for (int i = 1; i < splitArray1.length; i++) {
				if (splitArray1[i].length() == 1) {
					continue;
				} else {
					firstStr += splitArray1[i];
					break;
				}
			}
		}
		outputs[0] = firstStr;
		// Second Output Line
		String[] splitArray2 = this.secondLine.substring(2, this.secondLine.length() - 1).split(" ");
		String secondStr = "'(";
		int indexOfSecondElement = -1;
		if (splitArray2[0].length() > 1) {
			for (int i = 1; i < splitArray2.length; i++) {
				if (splitArray2[i].length() == 1) {
					continue;
				} else {
					indexOfSecondElement = i + 1;
					break;
				}
			}
		} else {
			indexOfSecondElement = 1;
		}
		for (int i = indexOfSecondElement; i < splitArray2.length; i++) {
			secondStr += splitArray2[i] + " ";
		}
		secondStr = secondStr.substring(0, secondStr.length() - 1);
		secondStr += ")";
		outputs[1] = secondStr;
		// Third Output Line
		String[] splitArray3 = this.thirdLine.substring(2, this.thirdLine.length() - 1).split(" ");
		String thirdStr = "";
		if (splitArray3[splitArray3.length - 1].length() > 1) {
			for (int i = splitArray3.length - 2; i >= 0; i--) {
				if (splitArray3[i].length() == 1) {
					continue;
				} else {
					thirdStr += splitArray3[i];
					thirdStr += " " + splitArray3[splitArray3.length - 1];
					break;
				}
			}
		} else {
			thirdStr = splitArray3[splitArray3.length - 1];
		}
		outputs[2] = thirdStr;
		// Fourth Output Line
		String[] splitArray4 = this.fourthLine.substring(2, this.fourthLine.length() - 1).split(" ");
		String fourthStr = "'(";
		int indexOfLastElement = -1;
		if (splitArray4[splitArray4.length - 1].length() > 1) {
			for (int i = splitArray4.length - 2; i >= 0; i--) {
				if (splitArray4[i].length() == 1) {
					continue;
				} else {
					indexOfLastElement = i - 1;
					break;
				}
			}
		} else {
			indexOfLastElement = splitArray4.length - 2;
		}
		for (int i = 0; i <= indexOfLastElement; i++) {
			fourthStr += splitArray4[i] + " ";
		}
		fourthStr = fourthStr.substring(0, fourthStr.length() - 1);
		fourthStr += ")";
		outputs[3] = fourthStr;
		// Fifth Output Line
		String[] splitArray5 = this.fifthLine.substring(2, this.fifthLine.length() - 1).split(" ");
		String fifthStr = "";
		int count = 0;
		for (int i = 0; i < splitArray5.length; i++) {
			if (splitArray5[i].contains("(")) {
				count += 1;
			}
		}
		fifthStr += count + "";
		outputs[4] = fifthStr;

		for (int i = 0; i < outputs.length; i++) {
			finalStr += outputs[i] + "\n";
		}
		return finalStr;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem LISP Expressions/src/LISP.txt");
		LispExpressions s = new LispExpressions(file);
		System.out.println(s.finalOutputs());
	}
}
