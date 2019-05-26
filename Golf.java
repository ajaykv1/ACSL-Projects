/**
 * This will evaluate the scores for each player, and tell how close they are to the par core in golf
 * @author ajaykrishnavajjala
 *
 */
public class Golf {	
	private int p1Total= 0;
	private int p2Total=0;
	private int par = 0; 
	private int p1Wins = 0;
	private int p2Wins = 0;
	private int[][] data= {{3,2,3},{4,5,5}, {5,6,6},{4,3,4}, {4,3,4},{4,4,5},{5,5,6},{3,3,3},{4,4,5}};
	//private int[][] data = {{4,3,5},{3,3,2}, {5,4,6},{5,3,4}, {4,4,4},{4,5,3},{3,2,4},{5,5,6},{4,4,4}};
	private int p1Max = 0;
	private int p2Max = 0;

	public Golf() {
		for(int i = 0; i < data.length; i++) {
			if (data[i][1] > this.p1Max){
				this.p1Max = data[i][1];
			}
			if (data[i][2] > this.p2Max) {
				this.p2Max = data[i][2];
			}
			this.par += data[i][0];
			this.p1Total += data[i][1];
			this.p2Total += data[i][2];
			if (data[i][1] < data[i][2]) {
				this.p1Wins += 1;
			}
			else if (data[i][2] < data[i][1]) {
				this.p2Wins =+ 1;
			}
		}
	}
	
	public void printResult() {
		System.out.println("OUTPUT");
		if (this.p1Total < this.p2Total) {
			System.out.printf("1. %s, %s\n", this.p1Total, this.p2Total);
			System.out.printf("2. %s under par\n", (this.par - this.p1Total));
			System.out.printf("3. %s over par\n", (this.p2Total - this.par));
			System.out.printf("4. %s\n", this.p1Wins);
			System.out.printf("5. %s\n", (this.p1Max + this.p2Max));
		}
		else if (this.p2Total < this.p1Total) {
			System.out.printf("1. %s, %s\n", this.p2Total, this.p1Total);
			System.out.printf("2. %s under par\n", (this.par - this.p2Total));
			System.out.printf("3. %s over par\n", (this.p1Total - this.par));
			System.out.printf("4. %s\n", this.p2Wins);
			System.out.printf("5. %s\n", (this.p2Max + this.p1Max));
		}
	}
	
	public static void main(String[] args) {
		Golf golf = new Golf();
		golf.printResult();
	}
}
