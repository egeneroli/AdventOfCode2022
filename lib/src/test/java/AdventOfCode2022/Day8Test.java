package AdventOfCode2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day8Test {

	// members
	Day8 day8 = new Day8();
	String inputString;
	int[][] inputMatrix;
	
	@BeforeEach
	void init() {
		inputString = 
				  "30373\n"
				+ "25512\n"
				+ "65332\n"
				+ "33549\n"
				+ "35390"
				;
		
		inputMatrix = day8.parseInput(inputString);
		
	}
	
	@Test
	public void testParseInput() {
		int[][] matrix = day8.parseInput(inputString);
		assertEquals(5, matrix[1][1]);
		assertEquals(4, matrix[3][3]);
		assertEquals(5, matrix[4][1]);
		assertEquals(3, matrix[2][3]);
	}

	@Test
	public void testCountVisibleLeft() {
		assertEquals(1, day8.countVisibleLeft(inputMatrix, 1, 2));
		assertEquals(2, day8.countVisibleLeft(inputMatrix, 3, 2));
	}

	@Test
	public void testCountVisibleRight() {
		assertEquals(2, day8.countVisibleRight(inputMatrix, 1, 2));
	}

	@Test
	public void testCountVisibleUp() {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCountVisibleDown() {
		throw new RuntimeException("not yet implemented");
	}
	
	
}
