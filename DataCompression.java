import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This takes some data, and comresses it so it becomes smaller, and makes it earsier to read
 * @author ajaykrishnavajjala
 *
 */
public class DataCompression {

	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private String inputString;

	public DataCompression(String str) {
		this.inputString = str;
		placeElementsInMap();
	}

	public void placeElementsInMap() {
		String[] input = this.inputString.split(" ");
		int count = 0;
		for (String word : input) {
			for (int i = 0; i < word.length(); i++) {
				if (Character.isLetter(word.charAt(i)) == false) {
					String punctuation = word.charAt(i) + "";
					if (map.containsKey(punctuation)) {
						map.put(punctuation, map.get(punctuation) + 1);
					} else if (map.containsKey(punctuation) == false) {
						map.put(punctuation, 1);
					}
					count++;
				}
			}
			if (count > 0) {
				word = word.substring(0, word.length() - 1);
			}
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else if (map.containsKey(word) == false) {
				map.put(word, 1);
			}
			count = 0;
		}
	}

	public String getFinalOutput() {
		String finalStr = "";
		for (String word : map.keySet()) {
			finalStr += (map.get(word) + word);
		}
		return finalStr;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String finalStr = "";
		Scanner input = new Scanner(new File(
				"/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Data Compression/src/compressData.txt"));
		int i = 1;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			DataCompression dataCompression = new DataCompression(line);
			finalStr +=  i + ") " +(dataCompression.getFinalOutput() + "\n");
			i++;
		}
		System.out.println(finalStr);
		input.close();
	}
}
