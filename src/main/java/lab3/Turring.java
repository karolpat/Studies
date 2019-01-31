package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Turring {

	private final static String STATE_Q1 = "Q1";
	private final static String STATE_Q2 = "Q2";
	private final static int HASH = -1;
	private final static int INVALID_WRITE = -1;
	private final static int LIST_BEGINNING = 0;

	private static String currentState = "Q0";
	States state = new States();
	List<String> stateList = new ArrayList<>();

	/**
	 * Invoke method for receiving input String and parsing it to List of integers
	 * 
	 * @return list of integers.
	 */
	private List<Integer> getIntList() {

		return stringToIntList(receiveString());
	}

	/**
	 * Ask user of binary number and check whether it is binary and at least 2
	 * characters long
	 * 
	 * @return String provided by the user
	 */
	private String receiveString() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Give me some binary number.");

		String input = sc.nextLine();

		// Input string must contains "1" or "0" only and has length of 2 at least
		if (input.matches("[01]{1,}")) {
			sc.close();
			return input;
		} else {
			System.out.println("Your input is invalid.");
			return receiveString();
		}
	}

	/**
	 * Splits String given at the beginning to list of integers
	 * 
	 * @param input
	 *            - String provided by the user
	 * @return list of integers
	 */
	private List<Integer> stringToIntList(String input) {
		List<Integer> inputList = new ArrayList<>();
		Pattern pattern = Pattern.compile("");

		inputList = pattern.splitAsStream(input).map(Integer::valueOf).collect(Collectors.toList());

		return inputList;
	}

	/**
	 * Invokes method of receiving input list of integers. Reads list of integers
	 * backwards, populates array of position indicator.
	 */
	public void doTurring() {

		List<Integer> inputList = getIntList();

		// Add Q0 state
		stateList.add(currentState);

		// Display info about the current state and the tape content
		// new char[0] - array for indicator position
		display(inputList, new char[0]);

		// Read input backwards
		for (int i = inputList.size() - 1; i > -1; i--) {

			char[] ind = new char[i + 1];

			// Populate indicator array
			for (int j = 0; j < ind.length; j++) {
				if (j == ind.length - 1) {
					ind[j] = '^';
				} else {
					ind[j] = ' ';
				}
			}
			
			state = state.getByCurrentStateAndInput(currentState, inputList.get(i));
			inputList = changeStateAndWrite(state, inputList, i);
			display(inputList, ind);
		}

		// If whole list has been read and still remains in Q1 state, then 1 have to be
		// written at the end of the String
		if (currentState.equals(STATE_Q2)) {

			state = state.getByCurrentStateAndInput(STATE_Q2, HASH);		
			inputList = changeStateAndWrite(state, inputList, HASH);
			display(inputList, new char[0]);
		}

		// If whole list has been read and still remains in Q1 state, 	sthen 1 have to be
		// written at the end of the String
		if (currentState.equals(STATE_Q1)) {

			state = state.getByCurrentStateAndInput(STATE_Q1, HASH);
			inputList = changeStateAndWrite(state, inputList, HASH);
			display(inputList, new char[0]);
		}

		System.out.println("End of job.");
		System.out.println("Final state: " + currentState);
		System.out.println("Went through states: " + stateList.toString());
	}

	/**
	 * Depending on index of the list and current state, write operation is
	 * performed. When current state is Q1 and full list has been read, "1" is added
	 * at the beginning of the list.
	 * 
	 * @param state
	 *            - instance of States class, contains currentState, nextState,
	 *            input, write
	 * @param inputList
	 *            - list of integers given at the beginning
	 * @param i
	 *            - index of the list
	 * @return list of integers, the same as on input, but with performed read/write
	 *         operations
	 */
	private List<Integer> changeStateAndWrite(States state, List<Integer> inputList, int i) {

		if (i > HASH) {

			// if (state.getWrite() != INVALID_WRITE) {
			inputList.remove(i);
			// }
			inputList.add(i, state.getWrite());
		} else {
			inputList.add(LIST_BEGINNING, state.getWrite());
		}

		currentState = state.getNextState();
		stateList.add(currentState);

		return inputList;
	}

	/**
	 * Display statistic info: current state and content of the tape
	 * 
	 * @param inputList
	 *            - list of integer given at the beginning
	 * @param indicator
	 *            - char array with indicator "^" on proper position
	 */
	private static void display(List<Integer> inputList, char[] indicator) {

		for (int z : inputList) {
			System.out.print(z + " ");
		}
		System.out.println();
		if (indicator.length > 0) {
			for (char c : indicator) {
				System.out.print(c + " ");
			}
		}
		System.out.println("\nCurrent state: " + currentState);
		System.out.println("<==========================================>");
	}

}
