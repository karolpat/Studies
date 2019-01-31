package lab4;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Oper {

	private Stack<String> stack = new Stack<String>();
	private Queue<String> queue;

	public void go() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter sth.");

		String input = sc.nextLine();

		sc.close();
		// Convert input string to array of chars
		char[] inputArr = input.toCharArray();
		queue = new ArrayBlockingQueue<String>(inputArr.length);

		// Read each consecutive element of the array
		readInput(inputArr);

		// Retrieve rest elements from stack and move them to the output queue
		while (!stack.isEmpty()) {
			queue.add(stack.pop());
		}

		// When process is finished, display content of queue
		System.out.println("<____O__U__T__P__U__T____>");
		System.out.println(queue.toString());

	}

	/**
	 * Read each consecutive element of the input String and display current content
	 * of the stack and the queue
	 * 
	 * @param inputArr
	 *            - array of chars that user passed to the program
	 */
	private void readInput(char[] inputArr) {

		for (char c : inputArr) {
			checkChar(Character.toString(c));

			System.out.println("==I=N=P=U=T============");
			System.out.println(c);
			System.out.println("===Q=U=E=U=E===========");
			System.out.println(queue.toString());
			System.out.println("====S=T=A=C=K==========");
			System.out.println(stack.toString());
		}
	}

	/**
	 * Check current read element of the input char array. There are 6
	 * possibilities: addition, subtraction, multiplication, division, power and
	 * brackets. Then decide what to do with each element - splits processing path
	 * depending on current read element.
	 * 
	 * @param s
	 *            - String - current read element.
	 */
	private void checkChar(String s) {

		// When it is any digit, just add to queue
		if (s.matches("\\d")) {
			queue.add(s);

		} else if (s.matches("[(]")) {
			stack.add(s);

		} else if (s.matches("[)]")) {
			emptyBracket();

		} else if (s.matches("[+-]")) {
			if (stack.empty()) {
				stack.add(s);

			} else {
				plusMinus(s);
			}

		} else if (s.matches("[*/]")) {
			if (stack.empty()) {
				stack.add(s);

			} else {
				multiDiv(s);
			}
		} else if (s.matches("[\\^]")) {
			power(s);
		}
	}

	private void plusMinus(String s) {

		if (stack.peek().matches("[*/+-\\^]")) {
			lowerPriority(s);

		} else {
			stack.add(s);
		}
	}

	private void multiDiv(String s) {

		if (stack.peek().matches("[*/\\^]")) {
			lowerPriority(s);

		} else if (stack.peek().matches("[+-]")) {
			stack.add(s);

		} else {
			stack.add(s);

		}
	}

	private void power(String s) {

		if (stack.peek().matches("[\\^]")) {
			lowerPriority(s);

		} else if (stack.peek().matches("[*/+-]")) {
			stack.add(s);
		} else {
			stack.add(s);
		}
	}

	/**
	 * When closing bracket appears ")", move all elements from stack to the queue
	 * until opening bracket "(" appear on the top of the stack.
	 */
	private void emptyBracket() {

		while (!stack.peek().matches("[(]")) {
			queue.add(stack.pop());
		}
		// Remove opening bracket from the stack.
		stack.pop();
	}

	/**
	 * When current read element has lower or the same level of priority, remove it
	 * from stack and move to the queue.
	 * 
	 * @param s
	 *            - String - current read element of the input.
	 */
	private void lowerPriority(String s) {
		queue.add(stack.pop());
		stack.add(s);
	}
}
