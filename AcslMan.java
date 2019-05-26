import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a Hang Man game created, by using various inputs from a text file
 * @author ajaykrishnavajjala
 *
 */
public class AcslMan {

	private String inputString;
	private String[] input;
	private ArrayList<String> letters = new ArrayList<>();
	private ArrayList<String> numberOfLettersInWord = new ArrayList<>();

	public AcslMan(String str) {
		this.input = str.split(",");
		for (int i = 0; i < this.input.length; i++) {
			if (i >= 2) {
				letters.add(this.input[i]);
			}
		}
		this.inputString = this.input[0];
		whatLettersInStr();
	}

	public void whatLettersInStr() {
		for (int i = 0; i < this.inputString.length(); i++) {
			if (numberOfLettersInWord.contains("" + this.inputString.charAt(i)) == false) {
				numberOfLettersInWord.add(this.inputString.charAt(i) + "");
			}
		}
	}
	
	public int findSimilarLetters() {
		int hangmanPartsNeeded = 0;
		int index = 0;
		int count = 0;
		for (int i = 0; i < this.letters.size(); i++) {
			if (count == this.numberOfLettersInWord.size()) {
				index = i;
				break;
			}
			index = i;
			for (int j = 0; j < this.numberOfLettersInWord.size(); j++) {
				if (this.numberOfLettersInWord.get(j).equals(this.letters.get(i))) {
					count += 1;
				}
			}
		}
		if (this.numberOfLettersInWord.contains(this.letters.get(index))) {
			index += 1;
		}
		if (count == this.numberOfLettersInWord.size()) {
			hangmanPartsNeeded = index - count;
		} else {
			hangmanPartsNeeded = this.letters.size() - count;
		}
		return hangmanPartsNeeded;
	}

	public String hangManResult() {
		String finalStr = "";
		String[] hangManParts = { "  O\n", "+", "=", "[]", "=", "+\n", "  []\n" + "  /" + "\\" };
		for (int i = 0; i < findSimilarLetters(); i++) {
			if (i < findSimilarLetters() && i < hangManParts.length) {
				finalStr += hangManParts[i];
			}
		}
		if (finalStr.length() == 0) {
			return "NONE";
		}
		return finalStr;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String finalStr = "";
		Scanner input = new Scanner(
				new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Probelm ACSL MAN/src/hangman.txt"));
		int i = 1;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			AcslMan acsl = new AcslMan(line);
			finalStr += (acsl.hangManResult() + "\n\n");
			i++;
		}
		System.out.println(finalStr);
		input.close();
	}
}
