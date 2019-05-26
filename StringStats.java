import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This program returns multiple stats about a string. For example, it will return the number of 
 * letters, the letter that appears the most amount of time, the length, and much more.
 * @author ajaykrishnavajjala
 *
 */
public class StringStats {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> a = populate();
		int vowelCount = 0;
		int uppercaseCount = 0;		
		Scanner input = new Scanner(new File("/Users/ajaykrishnavajjala/eclipse-workspace/Junior Contest 2/data.txt"));
		String[] words = null;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			 words = line.split(" ");
		}
		String alph = "";
		String alphabett = "";
		for (int i = 0; i < words.length; i++) {
			if (Character.isLetter(words[i].charAt(words[i].length()-1)) == false) {
				words[i] = words[i].substring(0, words[i].length()-1);
			}
			alph += words[i];
			alphabett += words[i] + " ";
		}
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < alph.length(); i++) {
			if (map.containsKey("" + alph.charAt(i)) == false) {
				map.put("" + alph.toLowerCase().charAt(i), 1);
			}
			else {
				map.put("" + alph.charAt(i), map.get("" + alph.charAt(i)) + 1);
			}
		}
		for (int i = 0; i < alph.length(); i++) {
			if (a.contains(alph.charAt(i) + "")) {
				vowelCount ++;
			}
		}
		for (int i = 0; i < alph.length(); i++) {
			if (Character.isUpperCase(alph.charAt(i))) {
				uppercaseCount ++;
			}
		}
		int maxNum = 0;
		for (int value: map.values()) {
			if (value >= maxNum) {
				maxNum = value;
			}
		}
		String[] al = alphabett.split(" ");
		int maxLen = 0;
		String wor = "";
		for (String str : al) {
			if (str.length() >= maxLen) {
				maxLen = str.length();
				wor = str;
			}
		}
		System.out.println(map.size());
		System.out.println(vowelCount);
		System.out.println(uppercaseCount);
		System.out.println(maxNum);
		System.out.println(wor);
		input.close();
	}
	public static ArrayList<String> populate(){
		ArrayList<String> a = new ArrayList<>();
		a.add("a");
		a.add("e");
		a.add("i");
		a.add("o");
		a.add("u");
		return a;
	}
}
