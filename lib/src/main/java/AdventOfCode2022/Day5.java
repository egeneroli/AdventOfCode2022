/**
 * 
 */
package AdventOfCode2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/****************************************************************************
* <b>Title</b>: Day3.java
* <b>Project</b>: lib
* <b>Package</b>: AdventOfCode2022
* <b>Description: </b> FILL IN DESCRIPTION HERE
* 
* @author egeneroli
* @version 1.0
* @since Dec 4, 2022
* @updates:
****************************************************************************/
public class Day5 {
	
	public String calculate2(String initialStateString, String instructionsString) {
		// parse initial state string
		List<Stack<Character>> stackList = parseInitialState(initialStateString);
		
		// parse instructions
		List<Map<String, Integer>> instructionsList = parseInstructions(instructionsString);
		
		// iterate through instructions, make necessary changes to stacks
		for (Map<String, Integer> instructionMap: instructionsList) {
			
			// iterate through number of moves
			Stack<Character> tempStack = new Stack<>();
			
			for (int i = 0; i < instructionMap.get("move"); i++) {
				char c = stackList.get(instructionMap.get("from")-1).pop();
				tempStack.push(c);
				//stackList.get(instructionMap.get("to")-1).push(c);
			}
			
			for (int i = 0; i < instructionMap.get("move"); i++) {
				char c = tempStack.pop();
				stackList.get(instructionMap.get("to")-1).push(c);
			}
		}
		
		String resultString = stackList.stream().map(stack -> stack.peek()+"").collect(Collectors.joining(""));
		
		return resultString;
	}
	
	public String calculate(String initialStateString, String instructionsString) {
		// parse initial state string
		List<Stack<Character>> stackList = parseInitialState(initialStateString);
		
		// parse instructions
		List<Map<String, Integer>> instructionsList = parseInstructions(instructionsString);
		
		// iterate through instructions, make necessary changes to stacks
		for (Map<String, Integer> instructionMap: instructionsList) {
			
			// iterate through number of moves
			for (int i = 0; i < instructionMap.get("move"); i++) {
				char c = stackList.get(instructionMap.get("from")-1).pop();
				stackList.get(instructionMap.get("to")-1).push(c);
			}
		}
		
		String resultString = stackList.stream().map(stack -> stack.peek()+"").collect(Collectors.joining(""));
		
		return resultString;
	}
	
	public List<Map<String, Integer>> parseInstructions(String instructionsString) {
		List<Map<String, Integer>> instructionsList = new ArrayList<>();
		for (String line: instructionsString.split("\n")) {
			String[] lineArray = line.split(" ");
			Map<String, Integer> lineMap = new HashMap<>();
			for (int i = 0; i < lineArray.length; i+=2) {
				lineMap.put(lineArray[i], Integer.parseInt(lineArray[i+1]));
			}
			
			instructionsList.add(lineMap);
		}
		
		return instructionsList;
	}
	
	public List<Stack<Character>> parseInitialState(String initialStateString) {
		int numberOfStacks = Character.getNumericValue(initialStateString.charAt(initialStateString.length()-1));
		
		String[] stringArray = initialStateString.split("\n");
		//int numberOfStacks = (stringArray[stringArray.length].length() + 1) / 3;
		
		//Stack<Character>[] stackArray = new Stack[numberOfStacks];
		List<Stack<Character>> stackList = new ArrayList<>();
		for (int i = 0; i < numberOfStacks; i++) {
			//stackArray[i] = new Stack<Character>();
			stackList.add(new Stack<Character>());
		}
		
		// iterate through levels, starting with last (base)
		for (int i = stringArray.length - 2; i >= 0; i--) {
			String level = stringArray[i];
			
			// iterate through "containers" and add each character to respective stack
			for (int j = 0; j < numberOfStacks; j++) {
				int index = 4 * j + 1;
				char c = level.charAt(index);
				if (c != ' ') {
					//stackArray[j].push(c);
					stackList.get(j).push(c);
				}
				
			}
		}
		
		return stackList;
	}
	
	public void run() {
		String  initialStateString = 
				    "[P]     [L]         [T]            \n"
				  + "[L]     [M] [G]     [G]     [S]    \n"
				  + "[M]     [Q] [W]     [H] [R] [G]    \n"
				  + "[N]     [F] [M]     [D] [V] [R] [N]\n"
				  + "[W]     [G] [Q] [P] [J] [F] [M] [C]\n"
				  + "[V] [H] [B] [F] [H] [M] [B] [H] [B]\n"
				  + "[B] [Q] [D] [T] [T] [B] [N] [L] [D]\n"
				  + "[H] [M] [N] [Z] [M] [C] [M] [P] [P]\n"
				  + " 1   2   3   4   5   6   7   8   9"
				;
		
		String instructionsString = 
				  "move 8 from 3 to 2\n"
				  + "move 1 from 9 to 5\n"
				  + "move 5 from 4 to 7\n"
				  + "move 6 from 1 to 4\n"
				  + "move 8 from 6 to 8\n"
				  + "move 8 from 4 to 5\n"
				  + "move 4 from 9 to 5\n"
				  + "move 4 from 7 to 9\n"
				  + "move 7 from 7 to 2\n"
				  + "move 4 from 5 to 2\n"
				  + "move 11 from 8 to 3\n"
				  + "move 3 from 9 to 7\n"
				  + "move 11 from 2 to 8\n"
				  + "move 13 from 8 to 4\n"
				  + "move 11 from 5 to 6\n"
				  + "move 8 from 2 to 4\n"
				  + "move 1 from 5 to 4\n"
				  + "move 1 from 3 to 2\n"
				  + "move 2 from 2 to 1\n"
				  + "move 2 from 8 to 5\n"
				  + "move 3 from 7 to 5\n"
				  + "move 1 from 4 to 7\n"
				  + "move 9 from 6 to 7\n"
				  + "move 1 from 6 to 5\n"
				  + "move 1 from 1 to 4\n"
				  + "move 3 from 1 to 9\n"
				  + "move 15 from 4 to 3\n"
				  + "move 2 from 4 to 1\n"
				  + "move 1 from 1 to 9\n"
				  + "move 3 from 4 to 5\n"
				  + "move 1 from 4 to 1\n"
				  + "move 1 from 7 to 2\n"
				  + "move 1 from 6 to 3\n"
				  + "move 5 from 7 to 1\n"
				  + "move 19 from 3 to 9\n"
				  + "move 7 from 1 to 2\n"
				  + "move 24 from 9 to 7\n"
				  + "move 23 from 7 to 1\n"
				  + "move 1 from 4 to 6\n"
				  + "move 3 from 7 to 3\n"
				  + "move 1 from 6 to 1\n"
				  + "move 6 from 2 to 1\n"
				  + "move 21 from 1 to 9\n"
				  + "move 5 from 3 to 8\n"
				  + "move 2 from 2 to 5\n"
				  + "move 10 from 9 to 5\n"
				  + "move 1 from 2 to 1\n"
				  + "move 5 from 1 to 3\n"
				  + "move 6 from 3 to 4\n"
				  + "move 1 from 2 to 8\n"
				  + "move 3 from 5 to 2\n"
				  + "move 4 from 9 to 3\n"
				  + "move 13 from 5 to 9\n"
				  + "move 2 from 7 to 2\n"
				  + "move 3 from 4 to 7\n"
				  + "move 1 from 7 to 8\n"
				  + "move 5 from 1 to 3\n"
				  + "move 1 from 7 to 5\n"
				  + "move 1 from 8 to 1\n"
				  + "move 2 from 2 to 7\n"
				  + "move 19 from 9 to 2\n"
				  + "move 5 from 2 to 3\n"
				  + "move 7 from 5 to 9\n"
				  + "move 1 from 1 to 9\n"
				  + "move 5 from 9 to 2\n"
				  + "move 4 from 9 to 3\n"
				  + "move 20 from 3 to 9\n"
				  + "move 1 from 3 to 9\n"
				  + "move 3 from 7 to 3\n"
				  + "move 16 from 2 to 3\n"
				  + "move 12 from 3 to 4\n"
				  + "move 2 from 2 to 5\n"
				  + "move 1 from 2 to 4\n"
				  + "move 2 from 4 to 1\n"
				  + "move 4 from 8 to 1\n"
				  + "move 15 from 9 to 3\n"
				  + "move 2 from 5 to 3\n"
				  + "move 3 from 2 to 8\n"
				  + "move 5 from 8 to 5\n"
				  + "move 7 from 3 to 4\n"
				  + "move 2 from 9 to 6\n"
				  + "move 15 from 3 to 1\n"
				  + "move 3 from 1 to 8\n"
				  + "move 3 from 9 to 5\n"
				  + "move 9 from 4 to 1\n"
				  + "move 3 from 3 to 5\n"
				  + "move 2 from 6 to 5\n"
				  + "move 9 from 1 to 3\n"
				  + "move 1 from 9 to 4\n"
				  + "move 1 from 5 to 2\n"
				  + "move 3 from 8 to 5\n"
				  + "move 10 from 1 to 6\n"
				  + "move 12 from 4 to 8\n"
				  + "move 1 from 2 to 7\n"
				  + "move 2 from 5 to 6\n"
				  + "move 1 from 1 to 4\n"
				  + "move 7 from 3 to 6\n"
				  + "move 1 from 7 to 2\n"
				  + "move 2 from 4 to 9\n"
				  + "move 3 from 1 to 7\n"
				  + "move 1 from 9 to 8\n"
				  + "move 1 from 2 to 3\n"
				  + "move 3 from 1 to 7\n"
				  + "move 5 from 8 to 2\n"
				  + "move 5 from 7 to 1\n"
				  + "move 9 from 6 to 8\n"
				  + "move 6 from 6 to 9\n"
				  + "move 8 from 8 to 6\n"
				  + "move 1 from 7 to 4\n"
				  + "move 5 from 2 to 4\n"
				  + "move 7 from 5 to 1\n"
				  + "move 5 from 8 to 9\n"
				  + "move 11 from 6 to 7\n"
				  + "move 9 from 9 to 1\n"
				  + "move 2 from 7 to 5\n"
				  + "move 1 from 9 to 5\n"
				  + "move 1 from 3 to 6\n"
				  + "move 3 from 4 to 6\n"
				  + "move 1 from 8 to 2\n"
				  + "move 2 from 3 to 6\n"
				  + "move 6 from 5 to 2\n"
				  + "move 3 from 5 to 9\n"
				  + "move 3 from 2 to 1\n"
				  + "move 1 from 4 to 3\n"
				  + "move 3 from 2 to 7\n"
				  + "move 1 from 8 to 9\n"
				  + "move 1 from 2 to 8\n"
				  + "move 8 from 7 to 5\n"
				  + "move 1 from 7 to 8\n"
				  + "move 3 from 5 to 6\n"
				  + "move 5 from 5 to 2\n"
				  + "move 1 from 4 to 1\n"
				  + "move 1 from 3 to 2\n"
				  + "move 4 from 1 to 5\n"
				  + "move 4 from 2 to 6\n"
				  + "move 6 from 1 to 2\n"
				  + "move 5 from 9 to 3\n"
				  + "move 2 from 5 to 3\n"
				  + "move 3 from 3 to 6\n"
				  + "move 10 from 6 to 4\n"
				  + "move 4 from 8 to 5\n"
				  + "move 5 from 5 to 1\n"
				  + "move 21 from 1 to 7\n"
				  + "move 3 from 2 to 9\n"
				  + "move 1 from 5 to 2\n"
				  + "move 4 from 2 to 9\n"
				  + "move 8 from 4 to 8\n"
				  + "move 1 from 2 to 1\n"
				  + "move 7 from 8 to 2\n"
				  + "move 2 from 6 to 1\n"
				  + "move 2 from 1 to 5\n"
				  + "move 1 from 1 to 5\n"
				  + "move 4 from 3 to 7\n"
				  + "move 1 from 9 to 3\n"
				  + "move 4 from 6 to 3\n"
				  + "move 1 from 3 to 8\n"
				  + "move 1 from 3 to 4\n"
				  + "move 2 from 2 to 6\n"
				  + "move 2 from 9 to 7\n"
				  + "move 14 from 7 to 8\n"
				  + "move 10 from 8 to 7\n"
				  + "move 3 from 4 to 6\n"
				  + "move 5 from 2 to 3\n"
				  + "move 3 from 9 to 8\n"
				  + "move 3 from 3 to 4\n"
				  + "move 1 from 2 to 4\n"
				  + "move 1 from 9 to 4\n"
				  + "move 1 from 9 to 5\n"
				  + "move 1 from 5 to 2\n"
				  + "move 3 from 5 to 7\n"
				  + "move 1 from 4 to 6\n"
				  + "move 5 from 3 to 8\n"
				  + "move 1 from 6 to 8\n"
				  + "move 5 from 7 to 6\n"
				  + "move 14 from 8 to 5\n"
				  + "move 2 from 6 to 7\n"
				  + "move 18 from 7 to 2\n"
				  + "move 3 from 6 to 1\n"
				  + "move 5 from 5 to 4\n"
				  + "move 5 from 6 to 2\n"
				  + "move 7 from 2 to 1\n"
				  + "move 1 from 8 to 4\n"
				  + "move 1 from 5 to 1\n"
				  + "move 8 from 1 to 9\n"
				  + "move 10 from 4 to 3\n"
				  + "move 8 from 5 to 3\n"
				  + "move 1 from 4 to 3\n"
				  + "move 2 from 1 to 5\n"
				  + "move 1 from 5 to 3\n"
				  + "move 5 from 3 to 1\n"
				  + "move 1 from 1 to 3\n"
				  + "move 5 from 1 to 6\n"
				  + "move 13 from 3 to 1\n"
				  + "move 3 from 9 to 4\n"
				  + "move 2 from 9 to 6\n"
				  + "move 5 from 6 to 5\n"
				  + "move 6 from 5 to 1\n"
				  + "move 7 from 7 to 9\n"
				  + "move 7 from 9 to 6\n"
				  + "move 1 from 9 to 3\n"
				  + "move 1 from 7 to 9\n"
				  + "move 3 from 9 to 1\n"
				  + "move 12 from 2 to 7\n"
				  + "move 7 from 6 to 2\n"
				  + "move 22 from 1 to 7\n"
				  + "move 1 from 6 to 5\n"
				  + "move 4 from 7 to 6\n"
				  + "move 1 from 5 to 6\n"
				  + "move 2 from 4 to 1\n"
				  + "move 1 from 4 to 1\n"
				  + "move 23 from 7 to 9\n"
				  + "move 4 from 6 to 2\n"
				  + "move 4 from 7 to 3\n"
				  + "move 1 from 1 to 9\n"
				  + "move 6 from 2 to 1\n"
				  + "move 1 from 7 to 2\n"
				  + "move 7 from 2 to 8\n"
				  + "move 2 from 3 to 8\n"
				  + "move 3 from 1 to 9\n"
				  + "move 1 from 2 to 8\n"
				  + "move 5 from 8 to 3\n"
				  + "move 3 from 2 to 1\n"
				  + "move 2 from 7 to 8\n"
				  + "move 10 from 9 to 8\n"
				  + "move 4 from 1 to 3\n"
				  + "move 14 from 3 to 4\n"
				  + "move 7 from 4 to 5\n"
				  + "move 1 from 6 to 9\n"
				  + "move 5 from 5 to 8\n"
				  + "move 1 from 6 to 4\n"
				  + "move 6 from 9 to 4\n"
				  + "move 3 from 8 to 4\n"
				  + "move 1 from 5 to 1\n"
				  + "move 3 from 4 to 3\n"
				  + "move 9 from 4 to 3\n"
				  + "move 5 from 3 to 6\n"
				  + "move 5 from 1 to 5\n"
				  + "move 4 from 6 to 2\n"
				  + "move 8 from 9 to 2\n"
				  + "move 2 from 6 to 5\n"
				  + "move 3 from 4 to 7\n"
				  + "move 2 from 2 to 7\n"
				  + "move 2 from 5 to 4\n"
				  + "move 3 from 5 to 9\n"
				  + "move 3 from 4 to 2\n"
				  + "move 10 from 2 to 5\n"
				  + "move 1 from 9 to 8\n"
				  + "move 2 from 2 to 9\n"
				  + "move 3 from 7 to 2\n"
				  + "move 1 from 2 to 9\n"
				  + "move 13 from 5 to 1\n"
				  + "move 2 from 2 to 7\n"
				  + "move 8 from 9 to 2\n"
				  + "move 1 from 4 to 6\n"
				  + "move 1 from 9 to 5\n"
				  + "move 14 from 8 to 4\n"
				  + "move 7 from 4 to 5\n"
				  + "move 4 from 7 to 5\n"
				  + "move 2 from 3 to 8\n"
				  + "move 4 from 1 to 5\n"
				  + "move 2 from 5 to 4\n"
				  + "move 6 from 5 to 6\n"
				  + "move 7 from 2 to 5\n"
				  + "move 1 from 2 to 6\n"
				  + "move 1 from 5 to 2\n"
				  + "move 2 from 2 to 8\n"
				  + "move 2 from 1 to 3\n"
				  + "move 8 from 4 to 7\n"
				  + "move 1 from 4 to 3\n"
				  + "move 6 from 1 to 6\n"
				  + "move 7 from 3 to 9\n"
				  + "move 3 from 7 to 1\n"
				  + "move 2 from 8 to 7\n"
				  + "move 7 from 6 to 9\n"
				  + "move 2 from 3 to 6\n"
				  + "move 6 from 8 to 3\n"
				  + "move 9 from 5 to 3\n"
				  + "move 2 from 7 to 8\n"
				  + "move 2 from 6 to 4\n"
				  + "move 7 from 6 to 9\n"
				  + "move 5 from 3 to 8\n"
				  + "move 10 from 9 to 1\n"
				  + "move 11 from 1 to 8\n"
				  + "move 1 from 3 to 2\n"
				  + "move 4 from 5 to 6\n"
				  + "move 2 from 6 to 2\n"
				  + "move 2 from 7 to 9\n"
				  + "move 3 from 1 to 7\n"
				  + "move 6 from 3 to 9\n"
				  + "move 2 from 7 to 2\n"
				  + "move 2 from 6 to 9\n"
				  + "move 1 from 5 to 9\n"
				  + "move 11 from 9 to 8\n"
				  + "move 1 from 4 to 5\n"
				  + "move 6 from 9 to 8\n"
				  + "move 31 from 8 to 9\n"
				  + "move 1 from 3 to 6\n"
				  + "move 1 from 7 to 1\n"
				  + "move 1 from 4 to 3\n"
				  + "move 1 from 5 to 2\n"
				  + "move 1 from 1 to 8\n"
				  + "move 1 from 8 to 9\n"
				  + "move 1 from 7 to 3\n"
				  + "move 11 from 9 to 6\n"
				  + "move 2 from 3 to 1\n"
				  + "move 2 from 3 to 5\n"
				  + "move 1 from 5 to 4\n"
				  + "move 1 from 4 to 1\n"
				  + "move 6 from 8 to 3\n"
				  + "move 1 from 1 to 4\n"
				  + "move 1 from 4 to 6\n"
				  + "move 2 from 3 to 6\n"
				  + "move 17 from 9 to 2\n"
				  + "move 23 from 2 to 9\n"
				  + "move 14 from 9 to 4\n"
				  + "move 1 from 1 to 7\n"
				  + "move 1 from 5 to 6\n"
				  + "move 8 from 6 to 2\n"
				  + "move 1 from 3 to 2\n"
				  + "move 4 from 9 to 8\n"
				  + "move 5 from 4 to 7\n"
				  + "move 3 from 7 to 2\n"
				  + "move 1 from 1 to 2\n"
				  + "move 2 from 9 to 4\n"
				  + "move 3 from 6 to 9\n"
				  + "move 8 from 4 to 9\n"
				  + "move 2 from 4 to 2\n"
				  + "move 4 from 7 to 2\n"
				  + "move 1 from 7 to 9\n"
				  + "move 4 from 6 to 2\n"
				  + "move 16 from 2 to 1\n"
				  + "move 2 from 3 to 2\n"
				  + "move 18 from 9 to 8\n"
				  + "move 1 from 4 to 2\n"
				  + "move 1 from 6 to 8\n"
				  + "move 1 from 3 to 9\n"
				  + "move 3 from 9 to 5\n"
				  + "move 4 from 9 to 8\n"
				  + "move 6 from 2 to 8\n"
				  + "move 1 from 5 to 1\n"
				  + "move 4 from 2 to 8\n"
				  + "move 1 from 5 to 1\n"
				  + "move 17 from 1 to 4\n"
				  + "move 1 from 5 to 8\n"
				  + "move 10 from 4 to 3\n"
				  + "move 10 from 3 to 1\n"
				  + "move 4 from 4 to 9\n"
				  + "move 1 from 4 to 6\n"
				  + "move 1 from 4 to 8\n"
				  + "move 38 from 8 to 1\n"
				  + "move 27 from 1 to 5\n"
				  + "move 1 from 8 to 2\n"
				  + "move 1 from 6 to 3\n"
				  + "move 1 from 4 to 8\n"
				  + "move 1 from 8 to 4\n"
				  + "move 14 from 1 to 9\n"
				  + "move 1 from 3 to 1\n"
				  + "move 1 from 5 to 1\n"
				  + "move 1 from 2 to 5\n"
				  + "move 2 from 5 to 4\n"
				  + "move 17 from 5 to 8\n"
				  + "move 3 from 4 to 9\n"
				  + "move 2 from 9 to 1\n"
				  + "move 3 from 5 to 7\n"
				  + "move 3 from 7 to 4\n"
				  + "move 2 from 4 to 7\n"
				  + "move 12 from 1 to 4\n"
				  + "move 1 from 7 to 4\n"
				  + "move 1 from 7 to 6\n"
				  + "move 1 from 6 to 9\n"
				  + "move 11 from 4 to 3\n"
				  + "move 1 from 5 to 3\n"
				  + "move 11 from 3 to 9\n"
				  + "move 1 from 3 to 2\n"
				  + "move 3 from 5 to 4\n"
				  + "move 1 from 2 to 4\n"
				  + "move 1 from 5 to 8\n"
				  + "move 13 from 9 to 3\n"
				  + "move 16 from 9 to 1\n"
				  + "move 4 from 8 to 9\n"
				  + "move 2 from 1 to 4\n"
				  + "move 1 from 9 to 1\n"
				  + "move 1 from 9 to 7\n"
				  + "move 1 from 7 to 2\n"
				  + "move 6 from 8 to 3\n"
				  + "move 8 from 4 to 2\n"
				  + "move 4 from 9 to 6\n"
				  + "move 3 from 2 to 3\n"
				  + "move 3 from 6 to 1\n"
				  + "move 3 from 8 to 6\n"
				  + "move 1 from 6 to 8\n"
				  + "move 3 from 6 to 4\n"
				  + "move 11 from 3 to 5\n"
				  + "move 4 from 8 to 2\n"
				  + "move 6 from 3 to 5\n"
				  + "move 3 from 5 to 1\n"
				  + "move 2 from 8 to 3\n"
				  + "move 14 from 5 to 3\n"
				  + "move 4 from 3 to 4\n"
				  + "move 6 from 3 to 5\n"
				  + "move 3 from 2 to 9\n"
				  + "move 4 from 1 to 8\n"
				  + "move 3 from 9 to 6\n"
				  + "move 2 from 6 to 9\n"
				  + "move 6 from 4 to 3\n"
				  + "move 15 from 1 to 4\n"
				  + "move 1 from 6 to 7\n"
				  + "move 5 from 5 to 1\n"
				  + "move 11 from 3 to 1\n"
				  + "move 2 from 9 to 7\n"
				  + "move 1 from 5 to 6\n"
				  + "move 2 from 1 to 3\n"
				  + "move 7 from 2 to 6\n"
				  + "move 4 from 8 to 1\n"
				  + "move 8 from 4 to 2\n"
				  + "move 3 from 6 to 4\n"
				  + "move 5 from 1 to 4\n"
				  + "move 17 from 4 to 8\n"
				  + "move 3 from 3 to 7\n"
				  + "move 4 from 3 to 4\n"
				  + "move 4 from 4 to 2\n"
				  + "move 9 from 8 to 7\n"
				  + "move 1 from 3 to 8\n"
				  + "move 10 from 2 to 4\n"
				  + "move 1 from 6 to 2\n"
				  + "move 2 from 8 to 4\n"
				  + "move 2 from 6 to 9\n"
				  + "move 2 from 6 to 2\n"
				  + "move 1 from 2 to 3\n"
				  + "move 3 from 1 to 4\n"
				  + "move 1 from 3 to 2\n"
				  + "move 1 from 9 to 3\n"
				  + "move 1 from 9 to 7\n"
				  + "move 4 from 8 to 4\n"
				  + "move 10 from 4 to 8\n"
				  + "move 5 from 4 to 3\n"
				  + "move 1 from 2 to 8\n"
				  + "move 5 from 3 to 7\n"
				  + "move 3 from 7 to 8\n"
				  + "move 3 from 4 to 3\n"
				  + "move 8 from 7 to 2\n"
				  + "move 8 from 7 to 8\n"
				  + "move 1 from 3 to 2\n"
				  + "move 3 from 2 to 8\n"
				  + "move 9 from 2 to 5\n"
				  + "move 12 from 1 to 7\n"
				  + "move 21 from 8 to 3\n"
				  + "move 5 from 8 to 6\n"
				  + "move 8 from 7 to 5\n"
				  + "move 6 from 7 to 4\n"
				  + "move 12 from 5 to 7\n"
				  + "move 1 from 8 to 5\n"
				  + "move 2 from 4 to 2\n"
				  + "move 1 from 7 to 6\n"
				  + "move 14 from 3 to 8\n"
				  + "move 5 from 6 to 2\n"
				  + "move 7 from 2 to 6\n"
				  + "move 6 from 8 to 4\n"
				  + "move 11 from 7 to 4\n"
				  + "move 8 from 3 to 7\n"
				  + "move 4 from 5 to 7\n"
				  + "move 9 from 8 to 2\n"
				  + "move 6 from 4 to 1\n"
				  + "move 2 from 5 to 2\n"
				  + "move 1 from 7 to 2\n"
				  + "move 11 from 2 to 3\n"
				  + "move 1 from 2 to 1\n"
				  + "move 7 from 4 to 1\n"
				  + "move 5 from 6 to 8\n"
				  + "move 1 from 2 to 3\n"
				  + "move 2 from 8 to 7\n"
				  + "move 14 from 3 to 7\n"
				  + "move 15 from 7 to 6\n"
				  + "move 4 from 4 to 6\n"
				  + "move 2 from 8 to 3\n"
				  + "move 12 from 1 to 3\n"
				  + "move 1 from 8 to 2\n"
				  + "move 1 from 2 to 3\n"
				  + "move 1 from 3 to 9\n"
				  + "move 1 from 9 to 7\n"
				  + "move 1 from 1 to 4\n"
				  + "move 18 from 6 to 8\n"
				  + "move 3 from 3 to 2\n"
				  + "move 17 from 8 to 3\n"
				  + "move 3 from 7 to 6\n"
				  + "move 3 from 2 to 6\n"
				  + "move 25 from 3 to 7\n"
				  + "move 2 from 4 to 1\n"
				  + "move 9 from 6 to 5\n"
				  + "move 2 from 3 to 1\n"
				  + "move 1 from 3 to 9\n"
				  + "move 5 from 5 to 2\n"
				  + "move 1 from 8 to 3\n"
				  + "move 2 from 4 to 7\n"
				  + "move 1 from 9 to 4\n"
				  + "move 1 from 6 to 7\n"
				  + "move 2 from 5 to 2\n"
				  + "move 2 from 4 to 8\n"
				  + "move 2 from 5 to 8\n"
				  + "move 5 from 7 to 9\n"
				  + "move 27 from 7 to 5\n"
				  + "move 2 from 9 to 6"
				;
		
		var result = calculate2(initialStateString, instructionsString);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Day5 kata = new Day5();
		kata.run();
	}
	
	public Day5() {
		
	}
	
}
