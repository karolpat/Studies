package lab2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Main {
	public static void main(String[] args) {
		
		Main main = new Main();
		
		main.readStrings();
	}

	private int currentState0;
	private int currentStateX;
	private Set<Integer> currentStatesList = new HashSet<>();
	private List<Set<Integer>> listOfLists = new ArrayList<>();
	private int[] alphabet = { 0, 1, 2, 3, 4 };

	public List<String> readFile() {
		Path path = Paths.get("C:\\dev\\Lab1\\src\\main\\resources\\input.txt");
		List<String> fileLines = new ArrayList<>();

		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(s -> fileLines.add(s));
			return fileLines;
		} catch (IOException ioe) {
			ioe.getMessage();
			return fileLines;
		}
	}

	public List<List<String>> splitLine() {

		List<String> fileLines = readFile();
		List<List<String>> splitted = new ArrayList<>();

		for (String line : fileLines) {
			splitted.add(Arrays.asList(line.split("#")));
		}

		return splitted;
	}

	public void readStrings() {
		List<List<String>> stringList = splitLine();

		for (List<String> ls : stringList) {
			for (String s : ls) {
				doAutomata(s);
			}

		}
	}

	public void doAutomata(String s) {

		Pattern pattern = Pattern.compile("");
		List<Integer> intList = new ArrayList<>();
		if (checkLength(s)) {
			intList = stringToIntList(s, pattern);
			changeState(intList, s);

		}

	}

	private boolean checkLength(String s) {

		if (s.length() < 2) {
			System.out.println("Input file contains too short String: " + s);
			return false;
		}
		return true;
	}

	private List<Integer> stringToIntList(String s, Pattern pattern) {
		return pattern.splitAsStream(s).map(Integer::valueOf).collect(Collectors.toList());
	}

	private void changeState(List<Integer> intList, String input) {

		currentStatesList.clear();
		System.out.println("Reading String: " + input);

		currentState0 = 0;
		currentStatesList.add(currentState0);

		for (int i : intList) {
			currentStatesList.add(getState(i));
			currentStatesList.add(currentStateX);
			if (!currentStatesList.contains(0))
				currentStatesList.add(0);
			System.out.println(currentStatesList.toString());
		}

		for (Set<Integer> l1 : listOfLists) {

			for (Integer i : l1) {
				System.out.print("q" + i + " ");
			}
			System.out.println();
		}

	}

	private int getState(int readInt) {

		if (!checkInput(readInt)) {
			System.out.println("Input out of range. Exit");
			System.exit(0);
		}

		System.out.println("Current read char: " + readInt);
		switch (currentState0) {
		case 0:
			if (readInt == 0) {
				currentState0 = 1;
			} else if (readInt == 1) {
				currentState0 = 2;
			} else if (readInt == 2) {
				currentState0 = 3;
			} else if (readInt == 3) {
				currentState0 = 4;
			} else {
				currentState0 = 5;
			}
			break;
		case 1:
			if (readInt == 0) {
				currentState0 = 6;
				currentStateX=0;
			} else {
				listOfLists.add(currentStatesList);
				currentStatesList.clear();
				currentStateX = 1;
				currentState0 = 0;

			}
			break;
		case 2:
			if (readInt == 1) {
				currentState0 = 6;
				currentStateX=0;
			} else {
				listOfLists.add(currentStatesList);
				currentStatesList.clear();
				currentStateX = 2;
				currentState0 = 0;

			}
			break;
		case 3:
			if (readInt == 2) {
				currentState0 = 6;
				currentStateX=0;
			} else {
				listOfLists.add(currentStatesList);
				currentStatesList.clear();
				currentStateX = 3;
				currentState0 = 0;

			}
			break;
		case 4:
			if (readInt == 3) {
				currentState0 = 6;
				currentStateX=0;
			} else {
				listOfLists.add(currentStatesList);
				currentStatesList.clear();
				currentStateX = 4;
				currentState0 = 0;

			}
			break;
		case 5:
			if (readInt == 4) {
				currentState0 = 6;
				currentStateX=0;
			} else {
				listOfLists.add(currentStatesList);
				currentStatesList.clear();
				currentStateX = 5;
				currentState0 = 0;

			}
		case 6:
			currentState0 = 6;
			currentStatesList.add(currentState0);
			break;
		}

		return currentState0;
	}

	private boolean checkInput(int input) {

		if (ArrayUtils.contains(alphabet, input))
			return true;

		return false;

	}

}
