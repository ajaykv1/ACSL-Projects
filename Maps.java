import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This will calculate gas costs, mileage, miles per hour, and many other things
 * for travelling from one place to another.
 * @author ajaykrishnavajjala
 *
 */
public class Maps {
	
	public int a = 0;
	public int b = 450;
	public int c = 590;
	public int d = 710;
	public int e = 1030;
	public int f = 1280;
	public int g = 1360;
	public int mpg = 0;
	public int mph = 0;
	public double gasCost = 0.0;
	public String[] inputString;
	
	public Maps(String str) {
		this.inputString = str.split(",");
		this.setMPG();
		this.setMPH();
		this.setGasCost();
	}
	public void setMPG() {
		switch(inputString[2].toLowerCase()) {
			case "c":
				this.mpg = 28;
				break;
			case "m":
				this.mpg = 25;
				break;
			case "f":
				this.mpg = 22;
				break;
			case "v":
				this.mpg = 20;
				break;
			default:
				this.mpg = 0;
		}
	}
	
	public void setMPH() {
		switch(inputString[3].toLowerCase()) {
			case "i":
				this.mph = 65;
				break;
			case "h":
				this.mph = 60;
				break;
			case "m":
				this.mph = 55;
				break;
			case "s":
				this.mph = 45;
				break;
			default:
				this.mph = 0;
		}
	}
	public void setGasCost() {
		this.gasCost = Double.parseDouble(inputString[4]);
	}
	public int getTotalDistanceTraveled() {
		int distTravelled = 0;
		int end = 0;
		int start = 0;
		switch(inputString[1].toLowerCase()) {
			case "a":
				end = this.a;
				break;
			case "b":
				end = this.b;
				break;
			case "c":
				end = this.c;
				break;
			case "d":
				end = this.d;
				break;
			case "e":
				end = this.e;
				break;
			case "f":
				end = this.f;
				break;
			case "g":
				end = this.g;
				break;
			default:
				end = 0;
				break;
		}
		switch(inputString[0].toLowerCase()) {
		case "a":
			start = this.a;
			break;
		case "b":
			start = this.b;
			break;
		case "c":
			start = this.c;
			break;
		case "d":
			start = this.d;
			break;
		case "e":
			start = this.e;
			break;
		case "f":
			start = this.f;
			break;
		case "g":
			start = this.g;
			break;
		default:
			start = 0;
			break;
	}
		
		if (inputString.length > 0) {
			distTravelled = end - start;
		}
		return distTravelled;
	}
	public String getTime() {
		String finalStr = "";
		int time = this.getTotalDistanceTraveled()/this.mph;
		double doubleTime = (double) this.getTotalDistanceTraveled()/this.mph;
		double mins = (doubleTime-time);
		int minutes = (int)(mins*60);
		if (time < 10) {
			finalStr = String.format("0%s:%s", time, minutes);
		}
		else {
			finalStr = String.format("%s:%s", time, minutes);
		}
		return finalStr;
	}
	public double getTotalGasCost() {
		double gasCostDouble = 0.0;
		double gasCostDoubleOne = ((double)this.getTotalDistanceTraveled() / this.mpg);
		gasCostDouble = ((gasCostDoubleOne * this.gasCost));
		return gasCostDouble;
	}
	public String getFinalOutput() {
		String finalStr = "";
		finalStr = String.format("%s, %s, %.2f", this.getTotalDistanceTraveled(), this.getTime(), this.getTotalGasCost());
		return finalStr;
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner read = new Scanner(new File("/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Problem/src/data.txt"));
		String finalOutput = "";
		while(read.hasNextLine()) {
			String line = read.nextLine();
			Maps x = new Maps(line);
			finalOutput += x.getFinalOutput() + "\n";
		}
		System.out.println(finalOutput);
	}

}
