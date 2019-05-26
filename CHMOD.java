
import java.util.Scanner;

public class CHMOD {
	public static int integerfrmbinary(String str){
	    double j=0;
	    for(int i=0;i<str.length();i++){
	        if(str.charAt(i)== '1'){
	         j=j+ Math.pow(2,str.length()-1-i);
	     }

	    }
	    return (int) j;
	}
	public static String getVal(String nums) {
		String finalStr = "";
		String[] str = nums.split(",");
		for (int i = 0; i < str.length; i++) {
			int num = Integer.parseInt(str[i]);
			if (Integer.toBinaryString(num).length() == 2) {
				finalStr += "0" + Integer.toBinaryString(num) + " ";
			} else if (Integer.toBinaryString(num).length() == 1) {
				finalStr += "00" + Integer.toBinaryString(num) + " ";
			} else {
				finalStr += Integer.toBinaryString(num) + " ";
			}
		}
		return finalStr;
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter numbers: ");
		String input = sc.nextLine();
		String[] str = input.split(",");
		String finalStr = "";
		String finalxtr = "";
		String finalxxr = "";
		if (str[0].length() == 3) {
			if (str[0].charAt(0) == '1' || str[0].charAt(0) == '-') {
			for (int i = 0; i < str.length; i++) {
				finalxtr += (integerfrmbinary(str[i])) + ",";
				finalxxr += integerfrmbinary(str[i]);
			}
			String[] xtr = finalxtr.split(",");
			System.out.println(xtr.length);
			for (int x = 0; x < xtr.length; x++) {
				finalStr += (getVal(xtr[x]));
			}
			finalxxr += " and ";
			String x = getMod(finalStr);
			System.out.println(finalxxr + x.substring(12, x.length()));
			}
			
		}
		else {
			for (int i = 0; i < str.length; i++) {
				int num = Integer.parseInt(str[i]);
				finalStr += (getVal(str[i]));
				
			}
			finalStr += "and";
			System.out.println(getMod(finalStr));
			
		}
	}
	static String getMod(String finalStr) {
		String[] strF = finalStr.split(" ");
		for (int i = 0; i < strF.length; i++) {
			String mod = "";
			if (strF[i].charAt(0) == '0') {
				mod += "-";
			} else if (strF[i].charAt(0) == '1') {
				mod += "r";
			}

			if (strF[i].charAt(1) == '0') {
				mod += "-";
			} else if (strF[i].charAt(1) == '1') {
				mod += "w";
			}

			if (strF[i].charAt(2) == '0') {
				mod += "-";
			} else if (strF[i].charAt(2) == '1') {
				mod += "x";
			}
			strF[i] = mod;
		}
		String modStr = "";
		for (int i = 0; i < strF.length; i++) {
			modStr += strF[i] + " ";
		}
		return (finalStr + modStr);
	}

}