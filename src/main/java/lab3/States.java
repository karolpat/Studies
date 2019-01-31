package lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class States {

	private String state;
	private String nextState;
	private int input;
	private int write;

	private static List<States> stateList;

	public States() {
	}

	private States(String state, String nextState, int input, int write) {
		setState(state);
		setInput(input);
		setWrite(write);
		setNextState(nextState);
	}

	private List<States> populateList() {

		stateList = new ArrayList<>();
		
								//current state, nextState, input, to write
		States q0in0 = new States("Q0", "Q1", 0, 1);
		States q0in1 = new States("Q0", "Q2", 1, 0);
		States q0inh = new States("Q0", "Q4", -1, -1);
		States q1in0 = new States("Q1", "Q3", 0, 1);
		States q1in1 = new States("Q1", "Q1", 1, 0);
		States q1inh = new States("Q1", "Q3", -1, 1);
		States q2in0 = new States("Q2", "Q1", 0, 0);
		States q2in1 = new States("Q2", "Q1", 1, 1);
		States q2inh = new States("Q2", "Q1", -1, 0);
		States q3in0 = new States("Q3", "Q3", 0, 0);
		States q3in1 = new States("Q3", "Q3", 1, 1);
		States q4in0 = new States("Q4", "Q4", -1, -1);
		States q4in1 = new States("Q4", "Q4", -1, -1);
		States q4inh = new States("Q4", "Q4", -1, -1);

		stateList.addAll(Arrays.asList(q0in0, q0in1, q0inh, q1in0, q1in1, q1inh, q2in0, q2in1, q2inh, q3in0, q3in1, q4in0,q4in1,q4inh));
		return stateList;

	}

	/**
	 * Search for state through list of states depending on current state and input
	 * @param currentState - current state of machine
	 * @param input - "1" or "0"
	 * @return instance of State with given current state and input parameters
	 */
	public States getByCurrentStateAndInput(String currentState, int input){
		
		populateList();
		
		for (States s : stateList) {
			if (s.getState().equals(currentState) && s.getInput() == input) {
				return s;
			}
		}
		return null;

	}

	@Override
	public String toString() {
		return "[State: " + this.getState() + ", next state: " + this.getNextState() + ", input: " + this.getInput()
				+ ", write: " + this.getWrite() + "]";
	}

	private String getState() {
		return this.state;
	}

	public String getNextState() {
		return this.nextState;
	}

	private int getInput() {
		return this.input;
	}

	public int getWrite() {
		return this.write;
	}

	private void setState(String state) {
		this.state = state;
	}

	private void setNextState(String nextState) {
		this.nextState = nextState;
	}

	private void setInput(int input) {
		this.input = input;
	}

	private void setWrite(int write) {
		this.write = write;
	}

}
