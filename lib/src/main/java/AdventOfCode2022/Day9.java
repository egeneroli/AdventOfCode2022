/**
 * 
 */
package AdventOfCode2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Day9 {
	
	public int calculate2(String input, int size) {
		// parse input string
		List<Map<String, Object>> instructions = parseInput(input);
		
		int n = (int) Math.pow(instructions.size(), 2);
		int[][] matrixT = genZerosMatrix(n, n);
		
		// mark initial position
		int m = n/2 + 1;
		//int m = 0;
		matrixT[m][m]++;
		
		List<Map<String, Integer>> posMapList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			posMapList.add(genPositionMap(m, m));
		}
		
		// iterate through instructions
		for (Map<String, Object> instruction: instructions) {
			//System.out.println("instructions: " + instruction);
			String direction = (String) instruction.get("direction");
			int step = (int) instruction.get("step");
			
			for (int i = 0; i < Math.abs(step); i++) {
				// increment/decrement H position
				//h.put(direction, h.get(direction) + step>0? 1: -1);
				incrementMapIntVal(posMapList.get(0), direction, step>0? 1: -1);
				//System.out.println("h: " + h);
				
				// mark position of H in H matrix
				//matrixH[h.get("y")][h.get("x")]++;
				
				// determine & update position of T
				//updateTPosition(h, t, 2);
				for (int j = 0; j < i + 1 && j + 1 < posMapList.size(); j++) {
					updateTPosition(posMapList.get(j), posMapList.get(j+1), 2);
				}
				//System.out.println("t: " + t);
				
				// mark position of T in T matrix
				matrixT[posMapList.get(size-1).get("y")][posMapList.get(size-1).get("x")]++;

			}
			//System.out.println();
		}
		
		//printMatrix(matrixT);
				
		// count number of positions visited in T matrix
		int result = Arrays.stream(matrixT).mapToInt(row -> (int) Arrays.stream(row).filter(i -> i > 0).count()).sum();
		//System.out.println("result: " + result);
		return result;
	}

	public int calculate(String input) {
		// parse input string
		List<Map<String, Object>> instructions = parseInput(input);
		
		// create necessary data structures
		int n = instructions.size();
		//int[][] matrixH = genZerosMatrix(n, n);
		int[][] matrixT = genZerosMatrix(n, n);
		
		// mark initial position
		int m = n/2 + 1;
		//int m = 0;
		//matrixH[m][m]++;
		matrixT[m][m]++;
		
		Map<String, Integer> h = genPositionMap(m, m);
		Map<String, Integer> t = genPositionMap(m, m);
		
		// iterate through instructions
		for (Map<String, Object> instruction: instructions) {
			//System.out.println("instructions: " + instruction);
			String direction = (String) instruction.get("direction");
			int step = (int) instruction.get("step");
			
			for (int i = 0; i < Math.abs(step); i++) {
				// increment/decrement H position
				//h.put(direction, h.get(direction) + step>0? 1: -1);
				incrementMapIntVal(h, direction, step>0? 1: -1);
				//System.out.println("h: " + h);
				
				// mark position of H in H matrix
				//matrixH[h.get("y")][h.get("x")]++;
				
				// determine & update position of T
				updateTPosition(h, t, 2);
				//System.out.println("t: " + t);
				
				// mark position of T in T matrix
				matrixT[t.get("y")][t.get("x")]++;

			}
			System.out.println();
		}
		
		//printMatrix(matrixT);
				
		// count number of positions visited in T matrix
		int result = Arrays.stream(matrixT).mapToInt(row -> (int) Arrays.stream(row).filter(i -> i > 0).count()).sum();
		//System.out.println("result: " + result);
		return result;
	}
	
	public void printMatrix(int[][] mat) {
		//for (int[] row: matrixH) {
		for (int i = mat.length-1; i >= 0; i--) {
			int[] row = mat[i];
			for (int x: row) {
				System.out.print(x);
			}
			System.out.print("\n");
		}
	}
	
	public void updateTPosition(Map<String, Integer> posH, Map<String, Integer> posT, int range) {
		int xH = posH.get("x");
		int yH = posH.get("y");
		int xT = posT.get("x");
		int yT = posT.get("y");
		
		// if within range, do nothing
		//System.out.println("1 - " + posT);
		if (withinRange(xH, xT, range) && withinRange(yH, yT, range)) return;
		//System.out.println("2 - " + posT);
		// if h in same row or column, move one step toward h
		//same row
		if (yH == yT) {
			incrementMapIntVal(posT, "x", (xH > xT)? 1: -1);
			//System.out.println("3a - " + posT);
		//same col
		} else if (xH == xT) {
			incrementMapIntVal(posT, "y", (yH > yT)? 1: -1);
			//System.out.println("3b - " + posT);
		// if not touching and not in same row or column, move diagonally toward h
		} else {
			incrementMapIntVal(posT, "x", (xH > xT)? 1: -1);
			incrementMapIntVal(posT, "y", (yH > yT)? 1: -1);
			//System.out.println("3c - " + posT);
		}
	}
	
	public boolean withinRange(int val1, int val2, int range) {
		return Math.abs(val1 - val2) < range;
	}
	
	public void incrementMapIntVal(Map<String, Integer> map, String key, int increment) {
		map.put(key, map.get(key) + increment);
	}
	
	public Map<String, Integer> genPositionMap(int x, int y) {
		Map<String, Integer> map = new HashMap<>();
		map.put("x", x);
		map.put("y", y);
		return map;
	}
	
	public int[][] genZerosMatrix(int nRows, int nCols) {
		int[][] matrix = new int[nRows][nCols];
		for (int[] row: matrix) {
			for (int val: row) {
				val = 0;
			}
		}
		return matrix;
	}
	
	public List<Map<String, Object>> parseInput(String input) {
		List<Map<String, Object>> instructions = new ArrayList<>();
		
		for (String lineString: input.split("\n")) {
			HashMap<String, Object> lineMap = new HashMap<>();
			String[] lineArray = lineString.split(" ");
			String direction = lineArray[0];
			int step = Integer.parseInt(lineArray[1]);
			
			if (direction.equals("L") || direction.equals("D")) step *= -1;
			
			if (direction.equals("R") || direction.equals("L")) direction = "x";
			if (direction.equals("U") || direction.equals("D")) direction = "y";
			
			lineMap.put("direction", direction);
			lineMap.put("step", step);

			instructions.add(lineMap);
		}
		
		return instructions;
	}
		
	public void run() {
		//var result = calculate(inputTest);
		//var result = calculate(inputTest);
		//var result = calculate2(inputTest, 10);
		var result = calculate2(inputReal, 10);
		System.out.println("result: " + result);
	}
	
	public static void main(String[] args) {
		Day9 kata = new Day9();
		kata.run();
	}
	
	public Day9() {
		readInput();
	}
	
	String inputTest;
	String inputReal;
	
	public void readInput() {
		inputTest = 
				    "R 5\n"
				  + "U 8\n"
				  + "L 8\n"
				  + "D 3\n"
				  + "R 17\n"
				  + "D 10\n"
				  + "L 25\n"
				  + "U 20"
				;
		
		inputReal = 
				  "R 1\n"
				+ "U 2\n"
				+ "R 1\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "D 2\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "L 2\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "R 2\n"
				+ "L 1\n"
				+ "R 1\n"
				+ "U 2\n"
				+ "D 1\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "U 1\n"
				+ "D 1\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "D 2\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "R 2\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "R 1\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "L 2\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "D 1\n"
				+ "U 2\n"
				+ "R 1\n"
				+ "D 1\n"
				+ "R 1\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "D 2\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "L 1\n"
				+ "R 1\n"
				+ "U 1\n"
				+ "R 1\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "R 1\n"
				+ "U 2\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "R 1\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "U 1\n"
				+ "D 1\n"
				+ "R 3\n"
				+ "L 3\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "D 1\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "L 2\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "R 2\n"
				+ "D 3\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "R 1\n"
				+ "L 1\n"
				+ "D 3\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "R 3\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "D 3\n"
				+ "L 2\n"
				+ "D 2\n"
				+ "R 2\n"
				+ "L 3\n"
				+ "D 1\n"
				+ "U 3\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "L 3\n"
				+ "U 2\n"
				+ "R 3\n"
				+ "U 3\n"
				+ "L 2\n"
				+ "U 3\n"
				+ "R 1\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "R 1\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "D 3\n"
				+ "R 2\n"
				+ "D 3\n"
				+ "L 3\n"
				+ "R 3\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "D 3\n"
				+ "L 3\n"
				+ "R 3\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "R 1\n"
				+ "L 2\n"
				+ "R 3\n"
				+ "D 1\n"
				+ "U 2\n"
				+ "R 3\n"
				+ "L 3\n"
				+ "R 3\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "R 3\n"
				+ "U 3\n"
				+ "R 4\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "L 4\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "R 3\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "L 4\n"
				+ "D 2\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "R 4\n"
				+ "D 1\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "U 1\n"
				+ "R 1\n"
				+ "D 4\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "L 1\n"
				+ "R 1\n"
				+ "L 1\n"
				+ "R 3\n"
				+ "L 1\n"
				+ "R 1\n"
				+ "D 1\n"
				+ "R 3\n"
				+ "D 3\n"
				+ "R 2\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "L 4\n"
				+ "D 3\n"
				+ "R 1\n"
				+ "L 4\n"
				+ "D 2\n"
				+ "R 1\n"
				+ "U 3\n"
				+ "L 1\n"
				+ "R 4\n"
				+ "L 3\n"
				+ "U 4\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "R 4\n"
				+ "L 1\n"
				+ "R 1\n"
				+ "U 3\n"
				+ "D 3\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "L 4\n"
				+ "R 4\n"
				+ "U 2\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "L 4\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "U 4\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "R 3\n"
				+ "U 4\n"
				+ "R 2\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "R 4\n"
				+ "L 2\n"
				+ "R 1\n"
				+ "L 2\n"
				+ "D 3\n"
				+ "U 4\n"
				+ "L 3\n"
				+ "U 4\n"
				+ "L 4\n"
				+ "U 4\n"
				+ "D 3\n"
				+ "U 3\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "U 1\n"
				+ "D 1\n"
				+ "L 4\n"
				+ "D 4\n"
				+ "L 2\n"
				+ "U 4\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "D 4\n"
				+ "R 1\n"
				+ "L 4\n"
				+ "U 5\n"
				+ "R 5\n"
				+ "U 3\n"
				+ "D 1\n"
				+ "L 4\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "U 2\n"
				+ "D 4\n"
				+ "R 5\n"
				+ "U 5\n"
				+ "D 5\n"
				+ "L 4\n"
				+ "R 4\n"
				+ "L 5\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "U 4\n"
				+ "L 1\n"
				+ "R 5\n"
				+ "L 5\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "R 4\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "L 3\n"
				+ "D 4\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "L 3\n"
				+ "U 4\n"
				+ "D 1\n"
				+ "L 5\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "D 4\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "R 1\n"
				+ "D 4\n"
				+ "L 5\n"
				+ "R 3\n"
				+ "L 3\n"
				+ "R 5\n"
				+ "L 4\n"
				+ "R 5\n"
				+ "D 4\n"
				+ "R 5\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "D 4\n"
				+ "R 4\n"
				+ "U 2\n"
				+ "L 5\n"
				+ "R 3\n"
				+ "D 5\n"
				+ "U 4\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "L 2\n"
				+ "R 5\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "U 5\n"
				+ "L 5\n"
				+ "D 5\n"
				+ "U 2\n"
				+ "R 5\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "R 1\n"
				+ "L 1\n"
				+ "R 3\n"
				+ "U 2\n"
				+ "D 4\n"
				+ "U 1\n"
				+ "R 3\n"
				+ "U 5\n"
				+ "D 5\n"
				+ "L 3\n"
				+ "U 1\n"
				+ "D 1\n"
				+ "L 4\n"
				+ "R 4\n"
				+ "L 4\n"
				+ "R 1\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "R 2\n"
				+ "U 2\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "L 5\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "U 3\n"
				+ "D 1\n"
				+ "R 4\n"
				+ "D 5\n"
				+ "L 1\n"
				+ "D 1\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "L 6\n"
				+ "D 2\n"
				+ "R 6\n"
				+ "D 3\n"
				+ "U 4\n"
				+ "R 3\n"
				+ "U 5\n"
				+ "D 4\n"
				+ "U 5\n"
				+ "D 1\n"
				+ "L 3\n"
				+ "U 5\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "R 4\n"
				+ "U 3\n"
				+ "L 6\n"
				+ "D 2\n"
				+ "R 4\n"
				+ "D 2\n"
				+ "L 2\n"
				+ "U 6\n"
				+ "R 4\n"
				+ "U 1\n"
				+ "R 4\n"
				+ "U 6\n"
				+ "L 6\n"
				+ "U 4\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "U 4\n"
				+ "L 3\n"
				+ "R 4\n"
				+ "D 4\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "D 1\n"
				+ "U 3\n"
				+ "L 5\n"
				+ "R 3\n"
				+ "L 4\n"
				+ "D 5\n"
				+ "R 1\n"
				+ "U 4\n"
				+ "L 2\n"
				+ "U 4\n"
				+ "R 2\n"
				+ "L 5\n"
				+ "R 6\n"
				+ "D 1\n"
				+ "U 1\n"
				+ "R 6\n"
				+ "D 6\n"
				+ "U 6\n"
				+ "R 6\n"
				+ "U 6\n"
				+ "R 2\n"
				+ "U 6\n"
				+ "R 4\n"
				+ "D 4\n"
				+ "U 5\n"
				+ "D 5\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "R 6\n"
				+ "D 5\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "L 6\n"
				+ "D 5\n"
				+ "L 5\n"
				+ "D 6\n"
				+ "U 5\n"
				+ "D 1\n"
				+ "U 5\n"
				+ "L 1\n"
				+ "R 5\n"
				+ "L 5\n"
				+ "U 5\n"
				+ "R 3\n"
				+ "U 2\n"
				+ "D 6\n"
				+ "L 5\n"
				+ "U 5\n"
				+ "D 5\n"
				+ "R 3\n"
				+ "D 1\n"
				+ "U 6\n"
				+ "R 6\n"
				+ "D 5\n"
				+ "U 5\n"
				+ "R 1\n"
				+ "D 5\n"
				+ "L 3\n"
				+ "R 4\n"
				+ "U 5\n"
				+ "R 5\n"
				+ "U 3\n"
				+ "D 3\n"
				+ "L 5\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "U 2\n"
				+ "L 5\n"
				+ "R 6\n"
				+ "L 1\n"
				+ "D 3\n"
				+ "U 5\n"
				+ "R 5\n"
				+ "L 5\n"
				+ "U 2\n"
				+ "R 4\n"
				+ "D 6\n"
				+ "L 3\n"
				+ "R 2\n"
				+ "U 6\n"
				+ "D 3\n"
				+ "R 6\n"
				+ "D 7\n"
				+ "L 3\n"
				+ "U 2\n"
				+ "L 3\n"
				+ "D 4\n"
				+ "L 2\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "L 4\n"
				+ "D 5\n"
				+ "R 2\n"
				+ "L 3\n"
				+ "D 2\n"
				+ "R 6\n"
				+ "U 6\n"
				+ "D 1\n"
				+ "R 5\n"
				+ "U 1\n"
				+ "R 5\n"
				+ "U 7\n"
				+ "L 6\n"
				+ "U 6\n"
				+ "L 4\n"
				+ "D 7\n"
				+ "U 4\n"
				+ "R 2\n"
				+ "L 7\n"
				+ "U 7\n"
				+ "L 5\n"
				+ "D 4\n"
				+ "U 4\n"
				+ "R 6\n"
				+ "U 4\n"
				+ "R 3\n"
				+ "U 3\n"
				+ "R 4\n"
				+ "U 2\n"
				+ "R 2\n"
				+ "D 4\n"
				+ "U 2\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "L 5\n"
				+ "D 6\n"
				+ "R 6\n"
				+ "U 7\n"
				+ "L 2\n"
				+ "D 4\n"
				+ "L 4\n"
				+ "D 7\n"
				+ "U 7\n"
				+ "D 5\n"
				+ "L 3\n"
				+ "U 5\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "L 6\n"
				+ "R 1\n"
				+ "D 1\n"
				+ "R 5\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "D 5\n"
				+ "U 3\n"
				+ "L 5\n"
				+ "R 7\n"
				+ "U 2\n"
				+ "R 6\n"
				+ "U 5\n"
				+ "D 3\n"
				+ "R 3\n"
				+ "U 4\n"
				+ "D 6\n"
				+ "U 2\n"
				+ "L 4\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "L 6\n"
				+ "U 6\n"
				+ "R 2\n"
				+ "U 6\n"
				+ "D 3\n"
				+ "L 4\n"
				+ "R 1\n"
				+ "L 7\n"
				+ "D 5\n"
				+ "U 3\n"
				+ "D 5\n"
				+ "R 7\n"
				+ "U 1\n"
				+ "L 1\n"
				+ "U 3\n"
				+ "R 5\n"
				+ "D 6\n"
				+ "R 3\n"
				+ "U 7\n"
				+ "L 3\n"
				+ "R 1\n"
				+ "L 7\n"
				+ "U 3\n"
				+ "D 6\n"
				+ "R 4\n"
				+ "L 2\n"
				+ "D 4\n"
				+ "R 6\n"
				+ "L 4\n"
				+ "D 8\n"
				+ "R 3\n"
				+ "D 5\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "L 4\n"
				+ "U 3\n"
				+ "R 1\n"
				+ "U 1\n"
				+ "D 3\n"
				+ "L 1\n"
				+ "R 6\n"
				+ "U 5\n"
				+ "D 6\n"
				+ "U 3\n"
				+ "D 3\n"
				+ "L 7\n"
				+ "U 3\n"
				+ "D 6\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "D 7\n"
				+ "L 7\n"
				+ "D 6\n"
				+ "L 2\n"
				+ "R 3\n"
				+ "L 1\n"
				+ "R 2\n"
				+ "U 3\n"
				+ "L 6\n"
				+ "D 3\n"
				+ "U 5\n"
				+ "L 3\n"
				+ "U 4\n"
				+ "R 8\n"
				+ "L 5\n"
				+ "U 5\n"
				+ "R 3\n"
				+ "D 4\n"
				+ "R 1\n"
				+ "L 7\n"
				+ "U 4\n"
				+ "R 4\n"
				+ "D 8\n"
				+ "U 8\n"
				+ "D 4\n"
				+ "L 8\n"
				+ "U 3\n"
				+ "L 3\n"
				+ "R 4\n"
				+ "L 1\n"
				+ "U 3\n"
				+ "D 4\n"
				+ "R 5\n"
				+ "L 4\n"
				+ "R 3\n"
				+ "L 7\n"
				+ "R 1\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "L 8\n"
				+ "U 5\n"
				+ "R 2\n"
				+ "L 7\n"
				+ "U 1\n"
				+ "L 8\n"
				+ "R 4\n"
				+ "U 3\n"
				+ "R 7\n"
				+ "U 6\n"
				+ "L 3\n"
				+ "U 6\n"
				+ "L 3\n"
				+ "U 3\n"
				+ "D 8\n"
				+ "L 5\n"
				+ "D 7\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "R 4\n"
				+ "L 4\n"
				+ "R 1\n"
				+ "D 8\n"
				+ "R 8\n"
				+ "L 4\n"
				+ "R 5\n"
				+ "L 2\n"
				+ "R 8\n"
				+ "L 7\n"
				+ "D 4\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "D 1\n"
				+ "L 2\n"
				+ "R 8\n"
				+ "U 2\n"
				+ "L 8\n"
				+ "D 5\n"
				+ "L 4\n"
				+ "R 6\n"
				+ "U 1\n"
				+ "D 2\n"
				+ "L 5\n"
				+ "R 7\n"
				+ "L 6\n"
				+ "R 1\n"
				+ "L 2\n"
				+ "R 3\n"
				+ "L 2\n"
				+ "D 2\n"
				+ "L 3\n"
				+ "R 4\n"
				+ "U 7\n"
				+ "R 6\n"
				+ "D 3\n"
				+ "L 4\n"
				+ "D 6\n"
				+ "R 3\n"
				+ "D 6\n"
				+ "R 8\n"
				+ "U 1\n"
				+ "R 6\n"
				+ "D 1\n"
				+ "U 7\n"
				+ "D 9\n"
				+ "R 2\n"
				+ "D 1\n"
				+ "U 6\n"
				+ "R 3\n"
				+ "D 5\n"
				+ "U 5\n"
				+ "L 6\n"
				+ "U 3\n"
				+ "D 7\n"
				+ "L 2\n"
				+ "D 6\n"
				+ "L 5\n"
				+ "U 4\n"
				+ "L 2\n"
				+ "D 5\n"
				+ "L 3\n"
				+ "D 4\n"
				+ "U 7\n"
				+ "L 4\n"
				+ "D 9\n"
				+ "R 3\n"
				+ "L 5\n"
				+ "R 5\n"
				+ "L 5\n"
				+ "U 1\n"
				+ "R 8\n"
				+ "L 6\n"
				+ "U 2\n"
				+ "D 2\n"
				+ "R 3\n"
				+ "L 5\n"
				+ "D 7\n"
				+ "L 4\n"
				+ "U 4\n"
				+ "D 9\n"
				+ "L 8\n"
				+ "R 1\n"
				+ "L 5\n"
				+ "R 8\n"
				+ "D 9\n"
				+ "R 6\n"
				+ "L 6\n"
				+ "U 6\n"
				+ "R 3\n"
				+ "U 5\n"
				+ "R 8\n"
				+ "U 4\n"
				+ "L 8\n"
				+ "U 3\n"
				+ "R 2\n"
				+ "L 7\n"
				+ "R 9\n"
				+ "U 6\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "D 9\n"
				+ "U 6\n"
				+ "R 5\n"
				+ "D 8\n"
				+ "R 1\n"
				+ "D 1\n"
				+ "L 4\n"
				+ "U 2\n"
				+ "D 9\n"
				+ "L 9\n"
				+ "D 5\n"
				+ "U 9\n"
				+ "R 2\n"
				+ "D 6\n"
				+ "R 9\n"
				+ "L 1\n"
				+ "R 2\n"
				+ "U 1\n"
				+ "L 7\n"
				+ "R 4\n"
				+ "U 4\n"
				+ "L 2\n"
				+ "U 2\n"
				+ "L 5\n"
				+ "D 5\n"
				+ "L 6\n"
				+ "D 2\n"
				+ "R 8\n"
				+ "L 4\n"
				+ "D 9\n"
				+ "R 8\n"
				+ "L 2\n"
				+ "D 4\n"
				+ "R 2\n"
				+ "L 6\n"
				+ "R 10\n"
				+ "U 7\n"
				+ "L 4\n"
				+ "U 9\n"
				+ "R 8\n"
				+ "D 9\n"
				+ "U 10\n"
				+ "R 2\n"
				+ "L 4\n"
				+ "U 7\n"
				+ "D 8\n"
				+ "R 8\n"
				+ "D 5\n"
				+ "L 5\n"
				+ "D 5\n"
				+ "L 2\n"
				+ "D 1\n"
				+ "U 5\n"
				+ "D 6\n"
				+ "R 7\n"
				+ "L 10\n"
				+ "U 9\n"
				+ "R 3\n"
				+ "D 9\n"
				+ "U 2\n"
				+ "L 8\n"
				+ "U 3\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "D 5\n"
				+ "R 2\n"
				+ "U 5\n"
				+ "R 4\n"
				+ "L 8\n"
				+ "U 10\n"
				+ "L 7\n"
				+ "D 4\n"
				+ "L 1\n"
				+ "U 7\n"
				+ "L 10\n"
				+ "R 1\n"
				+ "L 6\n"
				+ "D 10\n"
				+ "R 2\n"
				+ "U 3\n"
				+ "D 1\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "U 6\n"
				+ "L 1\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "D 6\n"
				+ "L 4\n"
				+ "D 3\n"
				+ "R 4\n"
				+ "L 3\n"
				+ "D 10\n"
				+ "U 2\n"
				+ "R 8\n"
				+ "U 5\n"
				+ "L 5\n"
				+ "R 8\n"
				+ "D 6\n"
				+ "L 8\n"
				+ "U 8\n"
				+ "R 9\n"
				+ "U 3\n"
				+ "L 9\n"
				+ "U 2\n"
				+ "R 5\n"
				+ "U 1\n"
				+ "R 1\n"
				+ "D 2\n"
				+ "L 3\n"
				+ "R 9\n"
				+ "D 10\n"
				+ "L 2\n"
				+ "R 9\n"
				+ "D 4\n"
				+ "L 7\n"
				+ "U 7\n"
				+ "R 2\n"
				+ "D 10\n"
				+ "U 5\n"
				+ "R 9\n"
				+ "L 6\n"
				+ "D 4\n"
				+ "U 10\n"
				+ "R 5\n"
				+ "D 2\n"
				+ "R 7\n"
				+ "U 10\n"
				+ "L 3\n"
				+ "D 9\n"
				+ "L 3\n"
				+ "R 6\n"
				+ "L 8\n"
				+ "U 5\n"
				+ "R 10\n"
				+ "L 9\n"
				+ "U 4\n"
				+ "R 8\n"
				+ "D 2\n"
				+ "R 4\n"
				+ "U 10\n"
				+ "L 1\n"
				+ "U 9\n"
				+ "L 9\n"
				+ "R 3\n"
				+ "D 4\n"
				+ "L 7\n"
				+ "R 6\n"
				+ "D 8\n"
				+ "L 5\n"
				+ "R 9\n"
				+ "L 8\n"
				+ "R 7\n"
				+ "D 10\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "U 6\n"
				+ "L 5\n"
				+ "R 5\n"
				+ "L 9\n"
				+ "R 2\n"
				+ "D 2\n"
				+ "U 5\n"
				+ "R 8\n"
				+ "D 11\n"
				+ "U 10\n"
				+ "L 3\n"
				+ "D 6\n"
				+ "U 11\n"
				+ "R 11\n"
				+ "U 4\n"
				+ "D 7\n"
				+ "L 8\n"
				+ "R 10\n"
				+ "U 10\n"
				+ "L 5\n"
				+ "R 7\n"
				+ "L 4\n"
				+ "U 2\n"
				+ "L 5\n"
				+ "U 3\n"
				+ "D 6\n"
				+ "U 11\n"
				+ "D 6\n"
				+ "L 1\n"
				+ "D 10\n"
				+ "L 6\n"
				+ "D 9\n"
				+ "R 3\n"
				+ "D 5\n"
				+ "R 6\n"
				+ "U 5\n"
				+ "L 7\n"
				+ "R 9\n"
				+ "D 5\n"
				+ "R 11\n"
				+ "D 10\n"
				+ "U 1\n"
				+ "L 2\n"
				+ "R 8\n"
				+ "L 6\n"
				+ "D 5\n"
				+ "U 10\n"
				+ "L 10\n"
				+ "R 5\n"
				+ "U 2\n"
				+ "D 3\n"
				+ "L 2\n"
				+ "R 6\n"
				+ "D 6\n"
				+ "L 1\n"
				+ "U 5\n"
				+ "R 8\n"
				+ "D 7\n"
				+ "R 10\n"
				+ "L 4\n"
				+ "D 2\n"
				+ "R 10\n"
				+ "U 8\n"
				+ "L 10\n"
				+ "R 7\n"
				+ "U 8\n"
				+ "D 7\n"
				+ "U 3\n"
				+ "R 8\n"
				+ "D 10\n"
				+ "U 11\n"
				+ "D 11\n"
				+ "L 4\n"
				+ "U 5\n"
				+ "R 10\n"
				+ "U 2\n"
				+ "L 11\n"
				+ "U 10\n"
				+ "R 10\n"
				+ "U 1\n"
				+ "D 6\n"
				+ "U 1\n"
				+ "L 3\n"
				+ "R 11\n"
				+ "L 2\n"
				+ "U 3\n"
				+ "D 1\n"
				+ "U 11\n"
				+ "L 2\n"
				+ "D 4\n"
				+ "R 9\n"
				+ "L 2\n"
				+ "D 7\n"
				+ "U 1\n"
				+ "R 2\n"
				+ "L 8\n"
				+ "R 6\n"
				+ "D 4\n"
				+ "R 1\n"
				+ "L 6\n"
				+ "D 4\n"
				+ "U 8\n"
				+ "D 8\n"
				+ "R 5\n"
				+ "U 11\n"
				+ "R 6\n"
				+ "U 11\n"
				+ "R 11\n"
				+ "U 7\n"
				+ "R 9\n"
				+ "D 4\n"
				+ "R 1\n"
				+ "D 9\n"
				+ "R 3\n"
				+ "L 9\n"
				+ "U 4\n"
				+ "R 6\n"
				+ "D 5\n"
				+ "L 3\n"
				+ "R 11\n"
				+ "U 11\n"
				+ "L 6\n"
				+ "R 11\n"
				+ "L 7\n"
				+ "D 1\n"
				+ "R 10\n"
				+ "D 2\n"
				+ "U 8\n"
				+ "R 4\n"
				+ "L 6\n"
				+ "R 4\n"
				+ "U 4\n"
				+ "D 12\n"
				+ "R 6\n"
				+ "L 12\n"
				+ "R 9\n"
				+ "L 8\n"
				+ "R 2\n"
				+ "L 12\n"
				+ "U 2\n"
				+ "L 9\n"
				+ "D 2\n"
				+ "U 8\n"
				+ "D 9\n"
				+ "R 12\n"
				+ "U 6\n"
				+ "L 2\n"
				+ "U 11\n"
				+ "R 5\n"
				+ "D 7\n"
				+ "U 11\n"
				+ "D 1\n"
				+ "R 6\n"
				+ "U 3\n"
				+ "D 9\n"
				+ "L 2\n"
				+ "R 1\n"
				+ "D 6\n"
				+ "R 12\n"
				+ "D 5\n"
				+ "L 11\n"
				+ "R 11\n"
				+ "L 3\n"
				+ "R 11\n"
				+ "L 1\n"
				+ "D 11\n"
				+ "U 10\n"
				+ "L 2\n"
				+ "R 2\n"
				+ "U 8\n"
				+ "D 3\n"
				+ "U 3\n"
				+ "L 4\n"
				+ "D 8\n"
				+ "L 2\n"
				+ "D 9\n"
				+ "R 10\n"
				+ "D 9\n"
				+ "U 11\n"
				+ "L 3\n"
				+ "D 4\n"
				+ "L 5\n"
				+ "U 4\n"
				+ "R 5\n"
				+ "D 12\n"
				+ "U 2\n"
				+ "L 11\n"
				+ "R 1\n"
				+ "L 6\n"
				+ "D 11\n"
				+ "R 9\n"
				+ "U 10\n"
				+ "D 2\n"
				+ "L 12\n"
				+ "D 7\n"
				+ "U 9\n"
				+ "D 12\n"
				+ "U 5\n"
				+ "L 4\n"
				+ "D 7\n"
				+ "L 11\n"
				+ "D 7\n"
				+ "L 2\n"
				+ "D 10\n"
				+ "R 7\n"
				+ "D 1\n"
				+ "R 9\n"
				+ "U 4\n"
				+ "D 12\n"
				+ "U 4\n"
				+ "R 3\n"
				+ "D 1\n"
				+ "L 6\n"
				+ "D 3\n"
				+ "R 9\n"
				+ "L 12\n"
				+ "D 10\n"
				+ "U 2\n"
				+ "L 12\n"
				+ "D 6\n"
				+ "L 2\n"
				+ "D 5\n"
				+ "L 1\n"
				+ "U 4\n"
				+ "L 5\n"
				+ "U 5\n"
				+ "R 7\n"
				+ "U 9\n"
				+ "R 4\n"
				+ "D 9\n"
				+ "L 13\n"
				+ "U 2\n"
				+ "L 8\n"
				+ "U 1\n"
				+ "R 10\n"
				+ "L 5\n"
				+ "U 2\n"
				+ "L 7\n"
				+ "U 6\n"
				+ "L 10\n"
				+ "U 11\n"
				+ "R 2\n"
				+ "U 6\n"
				+ "R 13\n"
				+ "D 4\n"
				+ "U 3\n"
				+ "R 11\n"
				+ "D 2\n"
				+ "U 7\n"
				+ "D 4\n"
				+ "R 13\n"
				+ "L 7\n"
				+ "R 11\n"
				+ "L 8\n"
				+ "U 4\n"
				+ "R 13\n"
				+ "D 9\n"
				+ "U 11\n"
				+ "L 4\n"
				+ "D 10\n"
				+ "U 8\n"
				+ "D 8\n"
				+ "R 6\n"
				+ "U 7\n"
				+ "L 8\n"
				+ "R 2\n"
				+ "U 7\n"
				+ "L 12\n"
				+ "R 2\n"
				+ "D 8\n"
				+ "L 7\n"
				+ "U 6\n"
				+ "D 6\n"
				+ "L 9\n"
				+ "R 8\n"
				+ "L 11\n"
				+ "U 6\n"
				+ "D 3\n"
				+ "U 2\n"
				+ "L 2\n"
				+ "R 1\n"
				+ "U 7\n"
				+ "L 6\n"
				+ "U 5\n"
				+ "D 6\n"
				+ "U 11\n"
				+ "D 11\n"
				+ "U 8\n"
				+ "R 12\n"
				+ "L 7\n"
				+ "R 3\n"
				+ "D 8\n"
				+ "L 11\n"
				+ "U 6\n"
				+ "D 10\n"
				+ "L 2\n"
				+ "D 6\n"
				+ "L 5\n"
				+ "D 10\n"
				+ "U 12\n"
				+ "D 6\n"
				+ "R 10\n"
				+ "D 4\n"
				+ "R 13\n"
				+ "U 2\n"
				+ "D 3\n"
				+ "L 9\n"
				+ "U 12\n"
				+ "L 10\n"
				+ "D 11\n"
				+ "R 7\n"
				+ "D 5\n"
				+ "L 5\n"
				+ "D 2\n"
				+ "L 11\n"
				+ "U 6\n"
				+ "L 1\n"
				+ "U 11\n"
				+ "D 5\n"
				+ "R 8\n"
				+ "U 1\n"
				+ "D 11\n"
				+ "U 8\n"
				+ "L 6\n"
				+ "D 6\n"
				+ "U 12\n"
				+ "L 7\n"
				+ "R 14\n"
				+ "D 8\n"
				+ "R 11\n"
				+ "D 12\n"
				+ "R 7\n"
				+ "U 6\n"
				+ "D 12\n"
				+ "R 6\n"
				+ "D 6\n"
				+ "L 6\n"
				+ "U 14\n"
				+ "D 2\n"
				+ "U 11\n"
				+ "D 11\n"
				+ "L 14\n"
				+ "U 8\n"
				+ "L 12\n"
				+ "D 12\n"
				+ "U 9\n"
				+ "R 7\n"
				+ "D 2\n"
				+ "U 10\n"
				+ "D 8\n"
				+ "R 8\n"
				+ "D 8\n"
				+ "L 10\n"
				+ "D 11\n"
				+ "L 4\n"
				+ "D 11\n"
				+ "U 4\n"
				+ "D 6\n"
				+ "U 5\n"
				+ "D 8\n"
				+ "R 14\n"
				+ "D 5\n"
				+ "R 1\n"
				+ "L 8\n"
				+ "R 8\n"
				+ "L 2\n"
				+ "R 11\n"
				+ "D 9\n"
				+ "L 3\n"
				+ "R 7\n"
				+ "L 5\n"
				+ "U 14\n"
				+ "L 1\n"
				+ "R 8\n"
				+ "L 12\n"
				+ "R 5\n"
				+ "D 11\n"
				+ "U 6\n"
				+ "R 1\n"
				+ "U 10\n"
				+ "R 11\n"
				+ "U 11\n"
				+ "L 9\n"
				+ "R 9\n"
				+ "L 8\n"
				+ "R 1\n"
				+ "D 3\n"
				+ "R 11\n"
				+ "U 12\n"
				+ "D 14\n"
				+ "L 1\n"
				+ "U 4\n"
				+ "D 2\n"
				+ "L 11\n"
				+ "R 4\n"
				+ "U 14\n"
				+ "R 1\n"
				+ "L 3\n"
				+ "R 13\n"
				+ "L 13\n"
				+ "D 8\n"
				+ "L 14\n"
				+ "U 12\n"
				+ "R 13\n"
				+ "D 9\n"
				+ "R 8\n"
				+ "D 12\n"
				+ "U 14\n"
				+ "D 2\n"
				+ "L 10\n"
				+ "R 1\n"
				+ "D 12\n"
				+ "R 13\n"
				+ "L 1\n"
				+ "U 5\n"
				+ "L 12\n"
				+ "R 6\n"
				+ "U 14\n"
				+ "R 6\n"
				+ "L 13\n"
				+ "U 11\n"
				+ "R 2\n"
				+ "D 6\n"
				+ "R 5\n"
				+ "U 12\n"
				+ "R 1\n"
				+ "D 11\n"
				+ "L 4\n"
				+ "U 6\n"
				+ "R 4\n"
				+ "U 5\n"
				+ "L 12\n"
				+ "D 3\n"
				+ "R 4\n"
				+ "D 1\n"
				+ "L 15\n"
				+ "R 15\n"
				+ "U 13\n"
				+ "R 10\n"
				+ "U 14\n"
				+ "R 15\n"
				+ "D 4\n"
				+ "R 8\n"
				+ "U 13\n"
				+ "L 4\n"
				+ "R 5\n"
				+ "D 7\n"
				+ "R 5\n"
				+ "L 2\n"
				+ "D 7\n"
				+ "L 4\n"
				+ "R 7\n"
				+ "D 6\n"
				+ "R 3\n"
				+ "D 12\n"
				+ "U 9\n"
				+ "L 14\n"
				+ "R 7\n"
				+ "L 12\n"
				+ "R 7\n"
				+ "D 15\n"
				+ "R 4\n"
				+ "L 1\n"
				+ "D 11\n"
				+ "R 10\n"
				+ "D 8\n"
				+ "L 9\n"
				+ "D 10\n"
				+ "L 4\n"
				+ "R 10\n"
				+ "U 8\n"
				+ "D 2\n"
				+ "L 8\n"
				+ "D 7\n"
				+ "U 11\n"
				+ "L 9\n"
				+ "U 15\n"
				+ "L 7\n"
				+ "R 6\n"
				+ "L 14\n"
				+ "D 2\n"
				+ "L 15\n"
				+ "D 6\n"
				+ "U 2\n"
				+ "R 9\n"
				+ "D 6\n"
				+ "U 7\n"
				+ "R 7\n"
				+ "U 15\n"
				+ "D 13\n"
				+ "L 6\n"
				+ "D 9\n"
				+ "R 12\n"
				+ "U 2\n"
				+ "R 8\n"
				+ "L 14\n"
				+ "R 11\n"
				+ "D 7\n"
				+ "U 1\n"
				+ "R 11\n"
				+ "D 12\n"
				+ "L 15\n"
				+ "D 9\n"
				+ "U 7\n"
				+ "L 3\n"
				+ "U 12\n"
				+ "L 3\n"
				+ "D 9\n"
				+ "R 12\n"
				+ "U 2\n"
				+ "D 8\n"
				+ "L 1\n"
				+ "U 15\n"
				+ "R 15\n"
				+ "U 15\n"
				+ "L 10\n"
				+ "U 2\n"
				+ "D 4\n"
				+ "R 8\n"
				+ "U 4\n"
				+ "D 8\n"
				+ "L 7\n"
				+ "R 11\n"
				+ "U 15\n"
				+ "D 9\n"
				+ "R 10\n"
				+ "D 8\n"
				+ "R 6\n"
				+ "D 6\n"
				+ "U 3\n"
				+ "L 13\n"
				+ "D 10\n"
				+ "L 11\n"
				+ "D 7\n"
				+ "U 5\n"
				+ "D 8\n"
				+ "U 7\n"
				+ "R 10\n"
				+ "U 1\n"
				+ "L 5\n"
				+ "D 13\n"
				+ "L 13\n"
				+ "U 11\n"
				+ "R 14\n"
				+ "D 7\n"
				+ "L 4\n"
				+ "R 8\n"
				+ "D 12\n"
				+ "U 14\n"
				+ "D 7\n"
				+ "L 5\n"
				+ "D 14\n"
				+ "U 2\n"
				+ "L 16\n"
				+ "R 9\n"
				+ "U 13\n"
				+ "R 14\n"
				+ "L 16\n"
				+ "U 16\n"
				+ "L 9\n"
				+ "U 13\n"
				+ "R 5\n"
				+ "U 15\n"
				+ "R 6\n"
				+ "D 7\n"
				+ "R 15\n"
				+ "L 15\n"
				+ "U 3\n"
				+ "L 5\n"
				+ "R 3\n"
				+ "U 1\n"
				+ "R 14\n"
				+ "U 5\n"
				+ "L 6\n"
				+ "U 13\n"
				+ "D 6\n"
				+ "U 1\n"
				+ "R 10\n"
				+ "D 11\n"
				+ "L 16\n"
				+ "U 10\n"
				+ "R 16\n"
				+ "D 15\n"
				+ "L 7\n"
				+ "R 15\n"
				+ "U 15\n"
				+ "R 5\n"
				+ "D 9\n"
				+ "U 6\n"
				+ "D 7\n"
				+ "L 15\n"
				+ "R 9\n"
				+ "L 10\n"
				+ "U 13\n"
				+ "R 7\n"
				+ "D 15\n"
				+ "U 14\n"
				+ "D 12\n"
				+ "L 2\n"
				+ "U 11\n"
				+ "D 14\n"
				+ "R 4\n"
				+ "L 1\n"
				+ "U 1\n"
				+ "R 13\n"
				+ "D 12\n"
				+ "R 14\n"
				+ "L 4\n"
				+ "R 11\n"
				+ "U 14\n"
				+ "L 2\n"
				+ "D 6\n"
				+ "R 15\n"
				+ "U 12\n"
				+ "R 15\n"
				+ "U 9\n"
				+ "D 11\n"
				+ "R 4\n"
				+ "L 15\n"
				+ "U 12\n"
				+ "D 14\n"
				+ "L 11\n"
				+ "U 10\n"
				+ "L 15\n"
				+ "U 9\n"
				+ "D 7\n"
				+ "R 12\n"
				+ "L 14\n"
				+ "U 15\n"
				+ "L 6\n"
				+ "U 2\n"
				+ "R 11\n"
				+ "L 12\n"
				+ "R 5\n"
				+ "U 8\n"
				+ "D 7\n"
				+ "U 6\n"
				+ "R 2\n"
				+ "U 6\n"
				+ "R 16\n"
				+ "L 2\n"
				+ "R 11\n"
				+ "L 7\n"
				+ "D 16\n"
				+ "R 7\n"
				+ "L 4\n"
				+ "R 13\n"
				+ "U 12\n"
				+ "D 1\n"
				+ "R 2\n"
				+ "D 4\n"
				+ "L 10\n"
				+ "D 12\n"
				+ "R 3\n"
				+ "D 14\n"
				+ "R 17\n"
				+ "L 4\n"
				+ "D 3\n"
				+ "L 2\n"
				+ "R 13\n"
				+ "L 15\n"
				+ "R 13\n"
				+ "U 9\n"
				+ "D 7\n"
				+ "L 13\n"
				+ "U 14\n"
				+ "D 6\n"
				+ "L 6\n"
				+ "D 6\n"
				+ "R 3\n"
				+ "U 6\n"
				+ "R 8\n"
				+ "U 14\n"
				+ "R 13\n"
				+ "L 15\n"
				+ "D 2\n"
				+ "U 3\n"
				+ "D 10\n"
				+ "R 16\n"
				+ "L 10\n"
				+ "D 8\n"
				+ "R 10\n"
				+ "D 9\n"
				+ "U 8\n"
				+ "R 1\n"
				+ "L 4\n"
				+ "U 14\n"
				+ "R 7\n"
				+ "L 16\n"
				+ "R 8\n"
				+ "D 4\n"
				+ "R 9\n"
				+ "U 12\n"
				+ "D 8\n"
				+ "U 7\n"
				+ "R 9\n"
				+ "L 16\n"
				+ "D 15\n"
				+ "U 11\n"
				+ "D 15\n"
				+ "U 2\n"
				+ "R 9\n"
				+ "U 10\n"
				+ "L 14\n"
				+ "D 14\n"
				+ "R 4\n"
				+ "U 12\n"
				+ "L 1\n"
				+ "U 8\n"
				+ "R 3\n"
				+ "U 13\n"
				+ "D 17\n"
				+ "U 17\n"
				+ "D 6\n"
				+ "U 17\n"
				+ "L 11\n"
				+ "D 16\n"
				+ "L 10\n"
				+ "R 10\n"
				+ "L 6\n"
				+ "U 16\n"
				+ "L 7\n"
				+ "D 8\n"
				+ "R 16\n"
				+ "U 7\n"
				+ "D 9\n"
				+ "L 2\n"
				+ "U 5\n"
				+ "L 4\n"
				+ "U 10\n"
				+ "D 1\n"
				+ "L 15\n"
				+ "U 5\n"
				+ "L 14\n"
				+ "R 7\n"
				+ "U 15\n"
				+ "L 14\n"
				+ "U 14\n"
				+ "L 7\n"
				+ "D 3\n"
				+ "L 4\n"
				+ "U 1\n"
				+ "R 11\n"
				+ "D 12\n"
				+ "R 9\n"
				+ "U 16\n"
				+ "L 3\n"
				+ "D 3\n"
				+ "L 4\n"
				+ "U 5\n"
				+ "D 16\n"
				+ "U 1\n"
				+ "D 15\n"
				+ "R 1\n"
				+ "L 16\n"
				+ "U 7\n"
				+ "L 2\n"
				+ "U 12\n"
				+ "L 13\n"
				+ "U 16\n"
				+ "D 9\n"
				+ "R 4\n"
				+ "D 15\n"
				+ "R 7\n"
				+ "U 14\n"
				+ "L 2\n"
				+ "U 9\n"
				+ "R 2\n"
				+ "L 13\n"
				+ "U 16\n"
				+ "R 4\n"
				+ "D 17\n"
				+ "U 10\n"
				+ "D 10\n"
				+ "U 10\n"
				+ "D 18\n"
				+ "R 10\n"
				+ "U 7\n"
				+ "L 6\n"
				+ "U 9\n"
				+ "D 9\n"
				+ "L 5\n"
				+ "U 9\n"
				+ "R 9\n"
				+ "L 15\n"
				+ "U 9\n"
				+ "D 10\n"
				+ "U 10\n"
				+ "R 10\n"
				+ "D 2\n"
				+ "U 15\n"
				+ "D 3\n"
				+ "R 18\n"
				+ "U 13\n"
				+ "R 1\n"
				+ "D 17\n"
				+ "L 16\n"
				+ "D 10\n"
				+ "L 1\n"
				+ "R 7\n"
				+ "U 1\n"
				+ "D 12\n"
				+ "L 3\n"
				+ "U 12\n"
				+ "L 6\n"
				+ "R 18\n"
				+ "D 13\n"
				+ "R 18\n"
				+ "U 17\n"
				+ "D 7\n"
				+ "L 11\n"
				+ "D 14\n"
				+ "L 2\n"
				+ "U 18\n"
				+ "D 8\n"
				+ "L 6\n"
				+ "D 16\n"
				+ "U 4\n"
				+ "R 14\n"
				+ "U 3\n"
				+ "D 11\n"
				+ "R 12\n"
				+ "U 12\n"
				+ "R 5\n"
				+ "D 16\n"
				+ "R 17\n"
				+ "U 3\n"
				+ "D 15\n"
				+ "U 11\n"
				+ "L 17\n"
				+ "U 15\n"
				+ "L 17\n"
				+ "U 12\n"
				+ "D 15\n"
				+ "L 14\n"
				+ "D 14\n"
				+ "U 13\n"
				+ "L 16\n"
				+ "R 5\n"
				+ "U 16\n"
				+ "D 6\n"
				+ "U 13\n"
				+ "L 13\n"
				+ "D 4\n"
				+ "R 6\n"
				+ "D 6\n"
				+ "L 18\n"
				+ "U 18\n"
				+ "L 15\n"
				+ "R 6\n"
				+ "L 6\n"
				+ "R 6\n"
				+ "U 13\n"
				+ "L 8\n"
				+ "U 13\n"
				+ "R 13\n"
				+ "U 13\n"
				+ "L 15\n"
				+ "R 6\n"
				+ "U 8\n"
				+ "R 10\n"
				+ "U 11\n"
				+ "L 18\n"
				+ "U 3\n"
				+ "L 2\n"
				+ "D 8\n"
				+ "L 5\n"
				+ "U 11\n"
				+ "L 5\n"
				+ "U 8\n"
				+ "R 13\n"
				+ "U 12\n"
				+ "R 9\n"
				+ "U 18\n"
				+ "D 11\n"
				+ "L 12\n"
				+ "D 13\n"
				+ "R 18\n"
				+ "U 2\n"
				+ "L 9\n"
				+ "D 14\n"
				+ "R 2\n"
				+ "L 17\n"
				+ "U 3\n"
				+ "L 9\n"
				+ "U 10\n"
				+ "R 9\n"
				+ "L 13\n"
				+ "R 15\n"
				+ "D 14\n"
				+ "U 2\n"
				+ "R 4\n"
				+ "L 5\n"
				+ "U 12\n"
				+ "L 14\n"
				+ "U 15\n"
				+ "D 13\n"
				+ "R 10\n"
				+ "L 5\n"
				+ "U 15\n"
				+ "D 10\n"
				+ "R 7\n"
				+ "L 9\n"
				+ "R 15\n"
				+ "L 6\n"
				+ "U 9\n"
				+ "L 15\n"
				+ "U 10\n"
				+ "R 14\n"
				+ "L 17\n"
				+ "R 16\n"
				+ "U 12\n"
				+ "D 15\n"
				+ "L 5\n"
				+ "U 17\n"
				+ "R 12\n"
				+ "D 17\n"
				+ "U 8\n"
				+ "R 10\n"
				+ "L 9\n"
				+ "R 6\n"
				+ "U 1\n"
				+ "D 3\n"
				+ "L 3\n"
				+ "D 7\n"
				+ "U 6\n"
				+ "L 9\n"
				+ "U 19\n"
				+ "R 10\n"
				+ "U 14\n"
				+ "R 15\n"
				+ "U 4\n"
				+ "D 9\n"
				+ "R 17\n"
				+ "L 6\n"
				+ "U 9\n"
				+ "R 2\n"
				+ "U 9\n"
				+ "R 16\n"
				+ "L 13\n"
				+ "U 11\n"
				+ "R 15\n"
				+ "D 10\n"
				+ "L 5\n"
				+ "U 1\n"
				+ "R 4\n"
				+ "U 17\n"
				+ "L 1\n"
				+ "U 10\n"
				+ "L 3\n"
				+ "U 9\n"
				+ "L 17\n"
				+ "U 8\n"
				+ "L 2\n"
				+ "U 10\n"
				+ "D 12\n"
				+ "L 16\n"
				+ "R 18\n"
				+ "L 6\n"
				+ "D 11\n"
				+ "R 6\n"
				+ "U 7\n"
				+ "R 3\n"
				+ "L 15\n"
				+ "R 7\n"
				+ "U 14\n"
				+ "R 2\n"
				+ "U 7\n"
				+ "R 17\n"
				+ "L 7\n"
				+ "R 1\n"
				+ "D 11\n"
				+ "R 10\n"
				+ "L 17\n"
				+ "R 5\n"
				+ "U 10\n"
				+ "L 15\n"
				+ "U 7\n"
				+ "R 7\n"
				+ "L 17\n"
				+ "D 19\n"
				+ "U 17\n"
				+ "R 3\n"
				+ "D 6\n"
				+ "L 8\n"
				+ "R 8\n"
				+ "D 3\n"
				+ "U 7\n"
				+ "L 9\n"
				;
	}
}
