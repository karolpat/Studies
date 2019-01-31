package lab1;

import java.util.HashMap;
import java.util.Map;

public class States {

	public States() {}

	private Map<Integer, Integer> populateMap() {

		Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
		temp.put(0, 1);
		temp.put(1, 1);
		temp.put(2, 2);
		temp.put(3, 5);
		temp.put(4, 3);
		temp.put(5, 4);
		temp.put(6, -1);
//		temp.put(7, 4);

		return temp;
	}

	public Map<Integer, Integer> getStates() {
		return populateMap();
	}
}
