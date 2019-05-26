import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This adds different blocs into a grid, until there is a path from the left
 * side of the grid to the right side of the grid. There is a visual representation in the console
 * when the program is run. 
 * @author ajaykrishnavajjala
 *
 */
public class JuniorACSLProgram {
	private String[][] grid;
	private int row;
	private int col;
	private int startingPoint;
	private String[] blockedCells;
	private ArrayList<String> cells = new ArrayList<String>();

	public JuniorACSLProgram(String input) {
		String[] value = input.split(",");
		this.row = Integer.parseInt(value[0]);
		this.col = Integer.parseInt(value[1]);
		this.startingPoint = Integer.parseInt(value[2]);
		int numBlockedCells = Integer.parseInt(value[3]);
		this.blockedCells = new String[numBlockedCells];
		int start = 4;
		this.grid = new String[this.row][this.col];
		this.fillGrid();
		for (int i = 0; i < this.blockedCells.length; i++) {
			this.blockedCells[i] = value[start];
			cells.add((value[start] + "").trim());
			start++;
		}
		this.blockGrid();
	}

	private void fillGrid() {
		int k = 1;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				this.grid[i][j] = k + "";
				k++;
			}
		}
	}

	private void blockGrid() {
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.cells.contains(this.grid[i][j])) {
					this.grid[i][j] = "[]";
				}
			}
		}
	}

	public String toString() {
		String finalStr = "";
		int maxNum = 0;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.grid[i][j].length() >= maxNum) {
					maxNum = this.grid[i][j].length();
				}
			}
		}
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.grid[i][j].length() < maxNum) {
					int difference = maxNum - this.grid[i][j].length();
					for (int x = 0; x < difference; x++) {
						finalStr += " ";
					}
				}
				finalStr += this.grid[i][j] + " ";
			}
			finalStr += "\n";
		}
		return finalStr;
	}

	private void addA() {
		if (this.safe("A")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if ((j + 2) <= this.col - 1) {
							int y = 0;

							for (int x = j; x < (j + 3); x++) {
								if (y == 1) {
									this.grid[i][x] = "A";
								} else {
									this.grid[i][x] = "O";
								}
								y++;
							}
							if ((j + 3) < this.col) {
								this.startingPoint = Integer.parseInt(grid[i][j + 3]);
								break;
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}

	private void addB() {
		if (this.safe("B")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if (((j + 1) <= this.col - 1) && ((i + 1) <= (this.row - 1))) {
							boolean wall = false;
							if (this.grid[i][j].equals("[]")) {
								wall = true;
								break;
							}
							int h = 0;
							for (int x = j; x < (j + 2); x++) {
								if (h == 0) {
									if (this.grid[i + 1][x].equals("[]")) {
										wall = true;
										;
									}

								} else {
									if (this.grid[i + 1][x].equals("[]")) {
										wall = true;
										break;
									}
								}
								h++;
							}
							if (wall) {
								break;
							}
							this.grid[i][j] = "O";
							i++;
							int y = 0;
							for (int x = j; x < (j + 2); x++) {
								if (y == 0) {
									this.grid[i][x] = "B";
								} else {
									this.grid[i][x] = "O";
								}
								y++;
							}
							if ((j + 2) <= this.col - 1) {
								if (this.grid[i][j + 2].equals("[]") == false) {
									this.startingPoint = Integer.parseInt(this.grid[i][j + 2]);
									break;
								} else {
									break;
								}

							}
						}
					}
				}
			}
		}
	}

	private void addC() {
		if (this.safe("C")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if (((i + 2) <= this.row - 1) && ((j + 1) <= this.col - 1)) {
							int y = 0;
							for (int x = j; x < (j + 2); x++) {
								if (y == 0) {
									this.grid[i][x] = "C";
								} else {
									this.grid[i][x] = "O";
								}
								y++;
							}
							j += 1;
							i++;
							this.grid[i][j] = "O";
							i++;
							this.grid[i][j] = "O";
						}
						if (j + 1 < this.col && i + 2 < this.row) {
							this.startingPoint = Integer.parseInt(this.grid[i][j + 1]);
							break;
						} else if (j + 3 < this.col) {
							this.startingPoint = Integer.parseInt(this.grid[i][j + 1]);
							break;
						}
					}
				}
			}
		}
	}

	private boolean safe(String letter) {
		boolean ans = true;
		if (letter.equals("A")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if ((j + 2) <= this.col - 1) {
							for (int x = j; x < (j + 3); x++) {
								if (this.grid[i][x].equals("[]")) {
									ans = false;
									break;
								}
							}
						}
					}
				}
			}
		}
		if (letter.equals("B")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if (((j + 1) <= this.col - 1) && ((i + 1) <= (this.row - 1))) {
							if (this.grid[i][j].equals("[]")) {
								ans = false;
								break;
							}
							i++;
							for (int x = j; x < (j + 2); x++) {
								if (this.grid[i][x].equals("[]")) {
									if ((i + 1) < this.row) {
										ans = true;
									} else {
										ans = false;
									}
									break;
								}
								break;
							}
						}
					}
				}
			}
		}
		if (letter.equals("C")) {
			for (int i = 0; i < this.grid.length; i++) {
				for (int j = 0; j < this.grid[i].length; j++) {
					if (this.grid[i][j].equals(this.startingPoint + "")) {
						if (((i + 2) <= this.row - 1) && ((j + 1) <= this.col - 1)) {
							for (int x = j; x < (j + 2); x++) {
								if (this.grid[i][x].equals("[]")) {
									ans = false;
									break;
								}
							}
							j += 1;
							i++;
							if (this.grid[i][j].equals("[]")) {
								ans = false;
								break;
							}
							i++;
							if (this.grid[i][j].equals("[]")) {
								ans = false;
								break;
							}
						}
					}
				}
			}
		}
		return ans;
	}

	private boolean checkStraight() {
		boolean ans = false;
		int row = 0;
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.grid[i][j].equals(this.startingPoint + "")) {
					row = i;
					break;
				}
			}
		}
		for (int i = 0; i < this.grid[row].length; i++) {
			if (this.grid[row][i].equals("[]")) {
				ans = false;
				break;
			}
		}
		return ans;
	}

	public String results() {
		String finalStr = "";
		int y = 0;

		while (true) {
			if ((this.grid[y][this.grid[0].length - 1].equals("O") || this.grid[y][this.grid[0].length - 1].equals("A")
					|| this.grid[y][this.grid[0].length - 1].equals("B")
					|| this.grid[y][this.grid[0].length - 1].equals("C")) == true) {
				break;
			}
			if (this.checkStraight()) {
				for (int i = 0; i < 4; i++) {
					this.addA();
				}
				break;
			}
			this.addA();
			this.addB();
			this.addC();
			y++;
		}
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				if (this.grid[i][j].equals("A") || this.grid[i][j].equals("B") || this.grid[i][j].equals("C")) {
					finalStr += this.grid[i][j];
				}
			}
		}
		return finalStr;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File(
				"/Users/ajaykrishnavajjala/eclipse-workspace/ACSL Junior and Intermediate Contest 3/src/data.txt"));
		String finalStr = "";
		finalStr += "Sample Inputs \n\n";
		int i = 1;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			JuniorACSLProgram program = new JuniorACSLProgram(line);
			finalStr += i + ") " + program.results() + "\n";
			finalStr += program.toString() + "\n";
			i++;
		}
		System.out.println(finalStr);
		input.close();
	}
}
