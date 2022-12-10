package AdventOfCode2022;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Day9Test {

	// members
	Day9 kata = new Day9();
	String inputString;
	List<Map<String, Object>> instructions;
	
	@BeforeEach
	void init() {
		inputString = 
				  "R 4\n"
				+ "U 4\n"
				+ "L 3\n"
				+ "D 1\n"
				+ "R 4\n"
				+ "D 1\n"
				+ "L 5\n"
				+ "R 2"
				;
		
		//instructions = kata.parseInput(inputString);
		
		
	}

	@Test
	public void testCalculate() {
		assertEquals(8, kata.calculate(inputString));
		
	}

	@Test
	public void testUpdateTPosition() {
		Map<String, Integer> h = new HashMap<>();
		Map<String, Integer> t = new HashMap<>();
		h.put("x", 3);
		h.put("y", 1);
		t.put("x", 1);
		t.put("y", 1);
		kata.updateTPosition(h, t, 2);
		assertEquals(2, t.get("x"));
		assertEquals(1, t.get("y"));
		
		h.put("x", 1);
		h.put("y", 1);
		t.put("x", 1);
		t.put("y", 3);
		kata.updateTPosition(h, t, 2);
		assertEquals(1, t.get("x"));
		assertEquals(2, t.get("y"));
		
		h.put("x", 2);
		h.put("y", 3);
		t.put("x", 1);
		t.put("y", 1);
		kata.updateTPosition(h, t, 2);
		assertEquals(2, t.get("x"));
		assertEquals(2, t.get("y"));
		
	}

	@Test
	public void testWithinRange() {
		assertTrue(kata.withinRange(1, 0, 2));
		assertFalse(kata.withinRange(-2, 0, 0));
	}

	@Test
	public void testIncrementMapIntVal() {
		Map<String, Integer> map = new HashMap<>();
		String key = "a";
		map.put(key, 0);
		kata.incrementMapIntVal(map, key, 1);
		assertEquals(1, map.get(key));
		kata.incrementMapIntVal(map, key, -2);
		assertEquals(-1, map.get(key));
	}

	@Test
	public void testGenPositionMap() {
		Map<String, Integer> map = kata.genPositionMap(0,0);
		assertTrue(map.containsKey("x"));
		assertTrue(map.containsKey("y"));
		assertEquals(0, map.get("x"));
		assertEquals(0, map.get("y"));
	}

	@Test
	public void testGenZerosMatrix() {
		int[][] mat = kata.genZerosMatrix(2,2);
		
		for (int[] row: mat) {
			for (int i: row) {
				assertEquals(0, i);
			}
		}
	}

	@Test
	public void testParseInput() {
		List<Map<String, Object>> instructions = kata.parseInput(inputString);
		assertEquals(inputString.split("\n").length, instructions.size());
		assertTrue(instructions.get(0).containsKey("direction"));
		assertTrue(instructions.get(0).containsKey("step"));
		assertEquals("x", instructions.get(0).get("direction"));
		assertEquals(4, instructions.get(0).get("step"));
	}

	
	
}
