import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/**
 * This will search to see if a word exists given multiple words. 
 * for example, if the word is "TEACH", and the words given are "TEACHER", "TEACH", "TECH"
 * This program would return TEACHER,TEACH because the '?' can symbolize the A
 * @author ajaykrishnavajjala
 *
 */
public class Search {
	private String[] words;
	String asterisk = "";
	String question = "";
	private List<String> data = new ArrayList<String>();

	public Search(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			data.add(line);
		}
		this.words = data.get(0).split(",");
	}
	public String finalOutput() {
		String finalStr = "";
		this.setIndexes();
		String[] aster = this.asterisk.split(",");
		String[] quest = this.question.split(",");
		int[] asterisks = new int[aster.length];
		int[] questions = new int[quest.length];
		for (int i = 0; i < asterisks.length; i++) {
			asterisks[i] = Integer.parseInt(aster[i]);
			questions[i] = Integer.parseInt(quest[i]);
			for (int j = 1; j < this.data.size(); j++) {
				String word = this.data.get(j);
				if (asterisks[i] == 0 && questions[i] == 9) {
					for (String w: this.words) {
						if (w.endsWith(word.substring(1, word.length()))) {
							finalStr += w + ",";
						}
					}
					finalStr = finalStr.substring(0,finalStr.length()-1);
					finalStr += "\n";
					continue;
				}
				if (asterisks[i] == 0 && questions[i] == word.length()-1) {
					for (String w: this.words) {
						if (w.contains(word.substring(1,word.length()-2)) && w.length() == word.length()) {
							finalStr += w + ",";
						}
					}
					finalStr = finalStr.substring(0,finalStr.length()-1);
					finalStr += "\n";
					continue;
				}
				if (asterisks[i] == word.length()-1 && questions[i] == 9) {
					for (String w: this.words) {
						if (w.startsWith(word.substring(0,word.length()-1))) {
							finalStr += w + ",";
						}
					}
					finalStr = finalStr.substring(0,finalStr.length()-1);
					finalStr += "\n";
					continue;
				}
				if (asterisks[i] == 9 && questions[i] == 0) {
					for (String w: this.words) {
						if (w.endsWith(word.substring(1, word.length()))) {
							finalStr += w + ",";
						}
					}
					finalStr = finalStr.substring(0,finalStr.length()-1);
					finalStr += "\n";
					continue;
				}
				if (asterisks[i] == 9 && questions[i] == word.length()-1) {
					for (String w: this.words) {
						if (w.startsWith(word.substring(0,word.length()-1))) {
							finalStr += w + ",";
						}
					}
					finalStr = finalStr.substring(0,finalStr.length()-1);
					finalStr += "\n";
					continue;
				}
				if (asterisks[i] == 9 && (questions[i] != 0 == false && questions[i] == word.length()-1 == false)) {
					for (String w: this.words) {
						String str1 = word.substring(0,questions[i]);
						String str2 = word.substring(questions[i], word.length());
						//System.out.println(str1 + " " + str2);
					}
				}
			}
		}
		System.out.println(finalStr);
		return finalStr;
	}
	public void setIndexes() {
		for (int i = 1; i < this.data.size(); i++) {
			int length = asterisk.length();
			for (int j = 0; j < this.data.get(i).length(); j++) {
				String word = this.data.get(i);
				if (word.charAt(j) == '*') {
					asterisk += j + ",";
				}
			}
			if (asterisk.length() == length) {
				asterisk += "9,";
			}
		}
		for (int i = 1; i < this.data.size(); i++) {
			int length = question.length();
			for (int j = 0; j < this.data.get(i).length(); j++) {
				String word = this.data.get(i);
				if (word.charAt(j) == '?') {
					question += j + ",";
				}
			}
			if (question.length() == length) {
				question += "9,";
			}
		}
		this.asterisk = this.asterisk.substring(0, this.asterisk.length()-1);
		this.question = this.question.substring(0, this.question.length()-1);
		System.out.println(asterisk);
		System.out.println(question);
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Search/src/search.txt");
		Search search = new Search(file);
		search.finalOutput();
	}
}
