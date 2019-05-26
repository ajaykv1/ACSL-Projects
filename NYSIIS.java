import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This will manipulate a string in different ways based on what the string begins with, ends with,
 * and what letters it contains
 * @author ajaykrishnavajjala
 *
 */
public class NYSIIS {

	String word;
	boolean firstChars = false;
	boolean lastChars = false;
	boolean vowels = false;
	
	public NYSIIS(String word) {
		this.word = word;
	}

	public void changeFirstChars() {
		String line = this.word;
		if (firstChars == false) {
			if (line.startsWith("MAC")) {
				line = "MC" + line.substring(3, line.length());
				firstChars = true;
			} else if (line.startsWith("KN")) {
				line = "N" + line.substring(2, line.length());
				firstChars = true;
			} else if (line.startsWith("K")) {
				line = "C" + line.substring(1, line.length());
				firstChars = true;
			} else if (line.startsWith("PH") || line.startsWith("PF")) {
				line = "F" + line.substring(2, line.length());
				firstChars = true;
			} else if (line.startsWith("SCH")) {
				line = "S" + line.substring(3, line.length());
				firstChars = true;
			}
		}
		this.word = line;
	}

	public void changeLastChars() {
		String line = this.word;
		if (lastChars == false) {
			if (line.endsWith("EE") || line.endsWith("IE")) {
				line = line.substring(0, line.length() - 2) + "Y";
				lastChars = true;
			} else if (line.endsWith("DT") 
					|| line.endsWith("RT") 
					|| line.endsWith("RD") 
					|| line.endsWith("NT")
					|| line.endsWith("ND")) {
				line = line.substring(0, line.length() - 2) + "D";
				lastChars = true;
			}
		}
		this.word = line;
	}

	public void changeVowels() {
		String line = this.word;
		String finalStr = "";
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == 'A' || line.charAt(i) == 'E' 
					|| line.charAt(i) == 'I' 
					|| line.charAt(i) == 'O'
					|| line.charAt(i) == 'U') {
				finalStr += "A";
			} else {
				finalStr += line.charAt(i) + "";
			}
		}
		this.word = finalStr;
	}

	public void changeS() {
		String line = this.word;
		if (lastChars == false) {
			if (line.endsWith("S")) {
				line = line.substring(0, line.length() - 1);
				lastChars = true;
			}
		}
		this.word = line;
	}

	public void changeAy() {
		String line = this.word;
		if (lastChars == false) {
			if (line.endsWith("AY")) {
				line = line.substring(0, line.length() - 2) + "Y";
				lastChars = true;
			}
		}
		this.word = line;
	}

	public void changeA() {
		String line = this.word;
		if (lastChars == false) {
			if (line.endsWith("A")) {
				line = line.substring(0, line.length() - 1);
				lastChars = true;
			}
		}
		this.word = line;
	}

	public void replaceDoubleLetters() {
		String line = this.word;
		String finalStr = "";
		for (int i = 0; i < line.length(); i++) {
			if ((i + 1 < line.length()) && (line.charAt(i) == line.charAt(i + 1))) {
				finalStr += line.charAt(i) + "";
				i++;
			} else {
				finalStr += line.charAt(i);
			}
		}
		this.word = finalStr;
	}

	public String finalOutput() {
		changeFirstChars();
		changeLastChars();
		changeVowels();
		changeS();
		changeAy();
		changeA();
		replaceDoubleLetters();

		if (this.word.length() > 6) {
			return this.word.substring(0, 6).toUpperCase();
		} else {
			return this.word.toUpperCase();
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		String finalStr = "";
		Scanner input = new Scanner(
				new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem NYSIIS/src/nysiis.txt"));
		while (input.hasNextLine()) {
			String line = input.nextLine();
			NYSIIS x = new NYSIIS(line);
			finalStr += (x.finalOutput()) + "\n";
		}
		System.out.println(finalStr);
		input.close();
	}
}
