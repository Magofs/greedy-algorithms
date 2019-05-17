package programmingWeek3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Tests are performed to see that algorithms is correct.
 * No tests are performed based on wrong input values and so forth
 */

class TestWeek3 {
	
	@Test
	@DisplayName("Test money change")
	public void kMoneyChangeTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals(2, week3.moneyChange(2));
		assertEquals(6, week3.moneyChange(28));			
	}
	
	@Test
	@DisplayName("Test knapsack")
	public void knapsackTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals(180.0000, week3.runFirst(3, 50));
		assertEquals(166.6667, week3.runSecond(1, 10));			
	}
	
	@Test
	@DisplayName("Test max ad revenue")
	public void maxAdRevenueTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals(897, week3.runFirstMaxAdReveneue(1));
		assertEquals(23, week3.runSecondMaxAdReveneue(3));			
	}
	
	@Test
	@DisplayName("Test collect signatures")
	public void collectSignatursTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals("1\n3 ",  week3.runFirstSegment(3));
		assertEquals("2\n3 6 ", week3.runSecondSegment(4));			
	}
	
	@Test
	@DisplayName("Distinctive pairs summation")
	public void distinctiveSummationTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals("3\n1 2 3 ",  week3.getDistinctiveSummation(6));
		assertEquals("3\n1 2 5 ",  week3.getDistinctiveSummation(8));
		assertEquals("1\n2 ",  week3.getDistinctiveSummation(2));		
	}
	
	@Test
	@DisplayName("get largest number of various integers")
	public void largestNumberTest() {
		ProgrammingWeek3 week3 = new ProgrammingWeek3();
		assertEquals("221",  week3.runFirstLargestNumber(2));
		assertEquals("99641",  week3.runSecondLargestNumber(5));
		assertEquals("923923",  week3.runThirdLargestNumber(3));
	}
	
	

}
