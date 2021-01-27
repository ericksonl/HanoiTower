/* Author: Liam Erickson
 * File: HanoiTower.java
 * Date: May 8th, 2020
 */


import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class HanoiTower {

	private static Scanner sc = new Scanner(System.in);
	private static Stack stack1 = new Stack();
	private static Stack stack2 = new Stack();
	private static Stack stack3 = new Stack();
	private static int disks;
	private static int moves;

	public static void main(String[] args) {
		System.out.println(
				"\t\t\t***************************\n" + "\t\t\t\tHanoi Tower\n" + "\t\t\t***************************");
		System.out.println("The object of this game is to move all disks from stack 1 to stack 3.\n" + "\tRules:\n"
				+ "\t1. Only one disk may be moved at a time.\n"
				+ "\t2. No disk may be placed on top of a smaller disk.\n"
				+ "\t3. Each move consists of taking the upper disk of one stack and placing it on top of another stack.\n");
		System.out.println(
				"To move a disk, first type the tower number<enter> and the tower you would like to move it to<enter>.\nEx."
						+ " To move the top disk from tower 1 to tower 2, you would type '1'<enter> '2'<enter>");
		System.out.println("Let the games begin....\n\n");
		System.out.println("How many disks would you like to play with? (Must be at least 1)");
		try {
			disks = sc.nextInt();
			while (disks <= 0) {
				System.out.println("Error: Unacceptable Input! Must be at LEAST one disk");
				System.out.println("How many disks would you like to play with? (Must be at least 1)");
				disks = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			System.out.println("Error: Unacceptable Input!");
			System.exit(0);
		}
		begin(disks);
	}

	public static void begin(int disksIn) {
		for (int i = disksIn; i > 0; i--) {
			stack1.push(i);
		}
		rings();
		userInput();
	}

	private static void rings() {

		// cell 1
		String[] cell1 = new String[disks];
		int i = 0;
		while (i < cell1.length) {
			cell1[i] = "    |";
			i++;
		}
		// cell 2
		String[] cell2 = new String[disks];
		i = 0;
		while (i < cell2.length) {
			cell2[i] = "\t\t    |";
			i++;
		}
		// cell 3
		String[] cell3 = new String[disks];
		i = 0;
		while (i < cell3.length) {
			cell3[i] = "\t\t    |";
			i++;
		}
		// end cells

		i = stack1.size() - 1;
		while (i >= 0) {
			cell1[i] = "   [" + stack1.get(i) + "]";
			i--;
		}
		i = stack2.size() - 1;
		while (i >= 0) {
			cell2[i] = "\t\t   [" + stack2.get(i) + "]";
			i--;
		}
		i = stack3.size() - 1;
		while (i >= 0) {
			cell3[i] = "\t\t   [" + stack3.get(i) + "]";
			i--;
		}
		
		i = disks - 1;
		while (i >= 0) {
			System.out.println(cell1[i] + cell2[i] + cell3[i]);
			i--;
		}
		System.out.println("---------" + "\t" + "---------" + "\t" + "---------");
		System.out.println(" Tower 1 " + "\t" + " Tower 2 " + "\t" + " Tower 3 ");
	}

	private static void userInput() {
		if (stack3.size() == disks) {
			System.out.print("\nCongrats!!! You Win!!!\nIt took you " + moves + " moves!");
			System.exit(0);
		}
		System.out.println("What tower's top disk would you like to move?");
		try {
			int towerIn = sc.nextInt();
			while (towerIn < 0 || towerIn > 3) {
				System.out.println("Error: Must be tower 1, 2 or 3!");
				System.out.println("What tower's top disk would you like to move?");
				towerIn = sc.nextInt();
			}
			System.out.print("Where would you like to move it?");
			int towerTo = sc.nextInt();
			while (towerTo < 0 || towerTo > 3) {
				System.out.println("Error: Must be tower 1, 2 or 3!");
				System.out.println("What tower's top disk would you like to move?");
				towerTo = sc.nextInt();
			}
			move(towerIn, towerTo);
		} catch (InputMismatchException e) {
			System.out.print("Error: Unacceptable Input!");
		}

	}

	private static void move(int towerIn, int towerTo) {

		// Tower 1 to another
		if (towerIn == 1 && towerTo == 2) {
			try {
				if (stack2.empty() == true) {
					stack2.push(stack1.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack2.peek() < (int) stack1.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack2.push(stack1.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}
		} else if (towerIn == 1 && towerTo == 3) {
			try {
				if (stack3.empty() == true) {
					stack3.push(stack1.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack3.peek() < (int) stack1.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack3.push(stack1.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}

			// tower 2 to another
		} else if (towerIn == 2 && towerTo == 1) {
			try {
				if (stack1.empty() == true) {
					stack1.push(stack2.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack1.peek() < (int) stack2.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack1.push(stack2.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}
		} else if (towerIn == 2 && towerTo == 3) {
			try {
				if (stack3.empty() == true) {
					stack3.push(stack2.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack3.peek() < (int) stack2.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack3.push(stack2.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}

			// Tower 3 to another
		} else if (towerIn == 3 && towerTo == 1) {
			try {
				if (stack1.empty() == true) {
					stack1.push(stack3.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack1.peek() < (int) stack3.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack1.push(stack3.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}
		} else if (towerIn == 3 && towerTo == 2) {
			try {
				if (stack2.empty() == true) {
					stack2.push(stack3.pop());
					moves++;
					rings();
					userInput();
				} else if ((int) stack2.peek() < (int) stack3.peek()) {
					System.out.println("Error: You cannot move a larger disk onto a smaller disk!");
					userInput();
				} else {
					stack2.push(stack3.pop());
					moves++;
					rings();
					userInput();
				}
			} catch (EmptyStackException e) {
				System.out.println("There are no disks on this tower!");
				userInput();
			}
			
			//Error
		} else {
			System.out.println("Error: An error has occured. Please try again");
			userInput();
		}
	}
}
