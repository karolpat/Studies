package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class Main {

	final static int MAX_VALUE = 5;
	final static int ACCEPTANCE = 3;
	final static int RETURN = 6;

	States state = new States();
	Map<Integer, Integer> stateMap = state.getStates();

	List<Integer> road = new ArrayList<Integer>();
	int[] availableCoins = { 1, 2, 5 };
	int sum = 0;
	int currentState = 0;

	public static void main(String[] args) {

		Main main = new Main();
		main.getTea();
	}

	private void getTea() {

		int currentCoin;
		Scanner sc = new Scanner(System.in);
		System.out.println("Tea costs 5zl‚!!!");

		while (true) {

			if(communicate()) break;

			try {
				currentCoin = sc.nextInt();
				if (!checkCoin(currentCoin)) {
					System.out.println("It is not a valid coin!!!!!!!");
					continue;
				}

				sum += currentCoin;
				if (sum > MAX_VALUE)
					sum = -1;
				checkState();
				road.add(currentState);

			} catch (Exception e) {
				System.out.println("It is not a valid coin!!!!!!");
				e.printStackTrace();
				break;

			}
		}
	}

	private boolean checkCoin(int coin) {
		return ArrayUtils.contains(availableCoins, coin);
	}

	private void checkState() {

		for (Map.Entry<Integer, Integer> entry : stateMap.entrySet()) {
			if (sum == entry.getValue()) {
				currentState = entry.getKey();
			}
		}
	}

	private boolean communicate() {

		if (currentState == ACCEPTANCE) {
			System.out.println("OK. Here's your tea.");
			System.out.println("Went trough states:");
			for (Integer i : road) {
				System.out.print("-> q" + i );
			}
			clear();
			return true;
			
//			System.out.println("\n<=======N=E=W==O=R=D=E=R=======>");
			
		} else if (currentState == RETURN) {
			System.out.println("Error. Returning money.");
			System.out.println("Went trough states.");
			for (Integer i : road) {
				System.out.print("-> q" + i );
			}
			clear();
			return true;
//			System.out.println("\n<=======N=E=W==O=R=D=E=R=======>");
		}

		System.out.println("Current state: q" + currentState);
		System.out.println("Sum: " + sum);
		System.out.println("Insert coin");
		return false;
	}
	

	private void clear() {
		sum = 0;
		currentState = 0;
		road.clear();
	}

}
