import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * removes digits from a number in different ways, and prints how many deletions it takes to delete
 * all the numbers.
 * @author ajaykrishnavajjala
 *
 */
public class DigitalDeletions {
	
	private String line;
	private int moves = 0;
	private boolean find = false;
	
	public DigitalDeletions(String line) {
		String[] split = line.split(",");
		String str = "";
		for (String elem: split) {
			str += elem;
		}
		this.line = str;
	}
	
	public int computeMoves() {
		while(this.line.length() > 0) {
			System.out.println(this.line);
			find0();
			if (this.find){
				this.find = false;
				this.moves++;
				continue;
			}
			if (this.line.length() == 0) {
				break;
			}
			findMax();
			this.moves++;
		}
		System.out.println(this.moves);
		return this.moves;
	}
	public void find0() {
		for (int i = 0; i < this.line.length(); i++) {
			if (Integer.parseInt(line.charAt(i) + "")  == 0) {
				this.line = this.line.substring(i+1, this.line.length());
				this.find = true;
			}
		}
	}
	
	public void findMax() {
		int max = 0;
		int maxIndex = -1;
		boolean foundMax = false;
		for (int i = 0; i < this.line.length(); i++) {
			if (Integer.parseInt(this.line.charAt(i) + "") >= max) {
				max = Integer.parseInt(this.line.charAt(i) + "");
				maxIndex = i;
				foundMax = true;
			}
		}
		if (foundMax) {
			if (max % 2 == 0) {
				max -= 2;
			}
			else {
				max -= 1;
			}
			String str = "";
			for (int i = 0; i < this.line.length(); i++) {
				if (i == maxIndex) {
					str += max;
				}
				else {
					str += this.line.charAt(i);
				}
			}
			this.line = str;
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem Digital Deletions/src/DigitalDeletions.txt"));
		String finalStr = "";
		while(input.hasNextLine()) {
			String line = input.nextLine();
			DigitalDeletions dd = new DigitalDeletions(line);
			finalStr += dd.computeMoves() + "\n";
		}
		//DigitalDeletions dd = new DigitalDeletions("7,3,5,8,0,2,5,4");
		//dd.computeMoves();
		System.out.println(finalStr);
	}
}

/*
 
 552365
 552345
 552344
 542344
 442344
 442342
 442322
 422322
 222322
 222320
 
 
 
 */











