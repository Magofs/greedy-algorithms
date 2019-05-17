package programmingWeek3;
import java.util.ArrayList;
/*
 * This is greedy algorithms
 */
public class ProgrammingWeek3 {
	ArrayList<Item> elements;
	ArrayList<Integer> revenues;
	ArrayList<Integer> clicks;
	ArrayList<Segments> points;
	ArrayList<Integer> digit;
	ArrayList<Integer> answer;
	
	public ProgrammingWeek3() {
		elements = new ArrayList<Item>();
		revenues = new ArrayList<>();
		clicks = new ArrayList<>();
		points = new ArrayList<>();
		answer = new ArrayList<>();
		digit = new ArrayList<>();
		String s = "97531";
		String a = "4";
		int b = 456;
		int c = 565;
		//System.out.println(String.valueOf(Math.abs((long)b)).charAt(0) < String.valueOf(Math.abs((long)c)).charAt(0));
		//System.out.println(s.substring(0, 3) + a + s.substring(3));
		}
	
/*Task: The goal in this problem is to find the minimum number of coins needed to change the input value
*(an integer) into coins with denominations 1, 5, and 10.
*Input Format: The input consists of a single integer.
*Constraints: 1 ≤ 𝑚 ≤ 10^3
*Output Format: Output the minimum number of coins with denominations 1, 5, 10 that changes.
*/
	public int moneyChange(int m) {
		// TODO Auto-generated method stub
		return getCount(m, 0);
	}
	
	private int getCount(int i, int count) {
		if (i > 0) {
			if (i % 10 == 0) {
				return getCount(i - 10, count + 1);
			}
			else if (i % 5 == 0) {
				return getCount(i - 5, count + 1);
			}
			else {
				return getCount(i - 1, count + 1);
			}
		}
		else return count;
	}
	
	/* Task: The goal of this code problem is to implement an algorithm for the fractional knapsack problem.
	 * Input Format: The first line of the input contains the number 𝑛 of items and the capacity 𝑊 of a knapsack.
	 * The next 𝑛 lines define the values and weights of the items. The 𝑖-th line contains integers v1 and vi—th
	 * value and the weight of i𝑖-th item, respectively.
	 * My greedy solution runs in log(3n)
	 */	
	
	/* 
	 * Driver code for running first problem
	 */
	public double runFirst(int items, int totalValue) {
		elements.add( new Item(60, 20));
		elements.add( new Item(100, 50));
		elements.add( new Item(120, 30));
		return knapsack(items, totalValue);
	}
	/*
	 *  Driver code for running second problem
	 */
	public double runSecond(int items, int totalValue) {
		elements.clear();
		elements.add( new Item(500, 30));
		return knapsack(items, totalValue);
	}
	public double knapsack(int items, int totalValue) {
		if (items == 0) return 0.0;
		return getValue(items, totalValue, 0.0, 0.0, 0.0);
	}
	
	private double getValue(int items, double totalWeight, double amount, double weight, double sum) {
		if (items < 1 || totalWeight <= 0.0) return roundAvoid(sum, 4);
		else {
			weight = bestItem(elements);
			amount = value(elements);
			remove(weight);
			if (weight > totalWeight) {
				double fraction = min(weight, totalWeight);
				amount = amount * fraction;
			}
			totalWeight = totalWeight - weight;
			sum = sum + amount;
			return getValue(items - 1, totalWeight, 0.0, 0.0, sum);				
		}
	}
//Helper code for delivering #.#### double
	private double roundAvoid(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
// Helper code for delivering best value
	private double value(ArrayList<Item> elements) {
		// TODO Auto-generated method stub
		double maxValuePerWeight = elements.get(0).value/elements.get(0).weight;
		int best = elements.get(0).value;
		for (int i = 1; i < elements.size(); i++) {
			if (elements.get(i).value/elements.get(i).weight > maxValuePerWeight) {
				maxValuePerWeight = elements.get(i).value/elements.get(i).weight;
				best = elements.get(i).value;
			}			
		}		
		return best;
	}
// Helper code for delivering best weight
	private void remove(double d) {
		// TODO Auto-generated method stub
		for (int i = 0; i < elements.size(); i++) {
			if ((double)elements.get(i).weight == d) {
				elements.remove(i);
			}
		}
	}
//Helper code for delivering fraction of best
	private double min(double w, double totalWeight) {
		// TODO Auto-generated method stub
		return totalWeight/w;
	}

	private int bestItem(ArrayList<Item> elements) {
		// TODO Auto-generated method stub
		double maxValuePerWeight = elements.get(0).value/elements.get(0).weight;
		int best = elements.get(0).weight;
		for (int i = 1; i < elements.size(); i++) {
			if (elements.get(i).value/elements.get(i).weight > maxValuePerWeight) {
				maxValuePerWeight = elements.get(i).value/elements.get(i).weight;
				best = elements.get(i).weight;
			}
		}
		return best;
	}
	/*
	 * Driver code for running first problem
	 */
	public int runFirstMaxAdReveneue(int n) {
		revenues.add(23);
		clicks.add(39);
		return getMaxAdRevenue(n, revenues, clicks);
	}
	/*
	 * Driver code for running second problem
	 */
	public int runSecondMaxAdReveneue(int n) {
		revenues.clear(); clicks.clear();
		revenues.add(1); revenues.add(3); revenues.add(-5);
		clicks.add(-2); clicks.add(4); clicks.add(1);
		return getMaxAdRevenue(n, revenues, clicks);
	}
	/* Task: Given two sequences a1, a2, . . . , b1 (b(i) is the profit per click of the i-th ad) and a1, b2, . . . , C1 (Ci is
	* the average number of clicks per day of the i-th slot), we need to partition them into 𝑛 pairs (bi, ci𝑗 ) such that the sum of their products is maximized.
	* Input Format: The first line contains an integer n, the second one contains a sequence of integers a1, a2, . . . , an, the third one contains a sequence of integers c1, c2, . . . , cn.
	* The next n lines define the values and weights of the items. The 𝑖i-th line contains integers 𝑣w1 and 𝑤w2—the
	* value and the weight of n𝑖-th item, respectively.
	* Constraints: 1 ≤ 𝑛 ≤ 10^3; −10^5 ≤ 𝑎𝑖, 𝑏𝑖 ≤ 10^5 for all 1 ≤ 𝑖 ≤ 𝑛.
	*Solution: My solution works in O(4n) for more than one items in list, this can easily be improved by sorting the lists before the calculus so it works in O(n), but this was not an explicit 
	* part of the task
	*/
	private int getMaxAdRevenue(int n, ArrayList<Integer> revenues, ArrayList<Integer> clicks) {
		// TODO Auto-generated method stub
		return getSum(n, revenues,  clicks, 0);
	}
		private int getSum(int n, ArrayList<Integer> revenues, ArrayList<Integer> clicks, int sum) {
		// TODO Auto-generated method stub
			if (n < 1 || revenues.isEmpty() || clicks.isEmpty()) return sum;
			else if (revenues.size() == 1 && clicks.size() == 1) {
				return sum + (revenues.get(0) * clicks.get(0));
			}
			else {
				int maxRevenue = getMax(revenues);
				int maxClicks = getMax(clicks);
				sum += maxRevenue * maxClicks;
				removeFromList(maxRevenue, revenues);
				removeFromList(maxClicks, clicks);
				return getSum(n - 1, revenues, clicks, sum);
			}
	}

	private void removeFromList(int max, ArrayList<Integer> minList) {
		// TODO Auto-generated method stub
		if (minList.size() == 1) minList.remove(0);
		else {
			for (int i = 0; i < minList.size(); i++) {
				if (minList.get(i) == max) {
					minList.remove(i);
				}
			}
		}
	}

	private int getMax(ArrayList<Integer> list) {
		// TODO Auto-generated method stub
		int max = list.get(0);
		if (list.size() == 1) return max;
		else {
			for (int i = 1; i < list.size(); i++) {
				if (list.get(i) > max)
						max = list.get(i);
			}
			return max;
		}		
	}
	/*
	 * Task: find number of segments you need to visit a point which are present in every set
	 * My solution works in O(n) given that the sets are pre-sorted.
	 */
	public String runFirstSegment(int n) {
			points.add(new Segments(1, 3)); 
			points.add(new Segments(2, 5));
			points.add(new Segments(3, 6));			
			return getSegment(n);
	}
	public String runSecondSegment(int n) {
		points.clear();
		points.add(new Segments(1, 3)); 
		points.add(new Segments(2, 5));
		points.add(new Segments(5, 6));
		points.add(new Segments(4, 7));
		return getSegment(n);
}
	
	private String getSegment(int n) {
		// TODO Auto-generated method stub
		ArrayList<Integer> numberOfSegments = new ArrayList<>();
		ArrayList<Integer> numberOfPoints = new ArrayList<>();
		int i = 0;
		int start, end;
		while (i < n) {
			start =  points.get(i).from;			
			end = points.get(i).to;
			numberOfSegments.add(start);
			numberOfPoints.add(end);
			i++;			
			while  (i < n && points.get(i).from <= end)  {
				i++;
			}
		}
		return numberOfSegments.size() + "\n" + getPoints(numberOfPoints);
	}

	private String getPoints(ArrayList<Integer> numberOfPoints) {
		// TODO Auto-generated method stub
		String s = "";		
		for (int i = 0; i < numberOfPoints.size(); i++) {
			s += numberOfPoints.get(i) + " ";
		}
		return s;
	}
	/*
	 * Task: Find distinctive pairs that sums up to n
	 * Solution: start with number = 1 and subtract from n, increment with number += 1 and subtract from n.
	 * This iteration continues until n is less than 2 * number, then you know that it is impossible to find another 
	 * distinctive pair, the remainder n is therefore the last number that can be put in the list 
	 * My greedy solution works in O(n/2)
	 */
	public String getDistinctiveSummation(int n) {
		ArrayList<Integer> sums = new ArrayList<>();
		int number = 1;
		while (n > 0) {
			if (n <= 2 * number) {
				sums.add(n);
				n -= n;
			}
			else {
				sums.add(number);
				n -= number;
				number++;
			}
		}		
		return sums.size() + "\n" + getSums(sums);
	}

	private String getSums(ArrayList<Integer> sums) {
		// TODO Auto-generated method stub
		String s = "";
		for (int i = 0; i < sums.size(); i++) {
			s += sums.get(i) + " ";
		}
		return s;
	}
	/*
	 *  Task: compose the largest number of a set integers 
	 *  Solution: figure out what the first integer is in every number ("541" + "7" < "7" + "541")
	 *  A borderline case is when both first numbers are equal let's say 96 and 94, you should, 
	 *  therefore, check that the next number is larger or smaller until there are no more numbers to check
	 *  Remember always iterate over the smallest number to insure that the smallest digit newer go out of bounds
	 *  my solution works in n iterations + n iterations over larger numbers. That is O(n(exp 2))
	 */
	public String runFirstLargestNumber(int digits) {
		digit.add(21); digit.add(2);
		return largestNumber(digits);
	}
	public String runSecondLargestNumber(int digits) {
		answer.clear();
		digit.add(9); digit.add(4); digit.add(6); digit.add(1); digit.add(9);
		return largestNumber(digits);
	}
	public String runThirdLargestNumber(int digits) {
		answer.clear();
		digit.add(23); digit.add(39); digit.add(92); 
		return largestNumber(digits);
	}
	public String largestNumber(int digits) {
		int i = 0;
		
		while (!digit.isEmpty()) {
			int maxDigit = String.valueOf(Math.abs((long)digit.get(i))).charAt(0);
			int max = digit.get(i);
			int index = 0;
			for (int j = i + 1; j < digit.size(); j++) {
				if (String.valueOf(Math.abs((long)digit.get(j))).charAt(0) >= maxDigit && String.valueOf(Math.abs((long)digit.get(j))).length() < 2) {
					maxDigit = String.valueOf(Math.abs((long)digit.get(j))).charAt(0);
	        	 	max = digit.get(j);
	        	 	index = j;
				}
				else if (String.valueOf(Math.abs((long)digit.get(j))).charAt(0) >= maxDigit && 
						String.valueOf(Math.abs((long)digit.get(i))).length() > 1 && 
						String.valueOf(Math.abs((long)digit.get(j))).length() > 1) {
						int lengthi = String.valueOf(Math.abs((long)digit.get(i))).length();
						int lengthj = String.valueOf(Math.abs((long)digit.get(j))).length();
						if (lengthi > lengthj && String.valueOf(Math.abs((long)digit.get(j))).substring(0, lengthj).equals(String.valueOf(Math.abs((long)digit.get(i))).substring(0, lengthj))) {
							maxDigit = String.valueOf(Math.abs((long)digit.get(j))).charAt(0);
							max = digit.get(j);
							index = j;
						}
						if (lengthj > lengthi && String.valueOf(Math.abs((long)digit.get(j))).substring(0, lengthi).equals(String.valueOf(Math.abs((long)digit.get(i))).substring(0, lengthi))) {
							maxDigit = String.valueOf(Math.abs((long)digit.get(i))).charAt(0);
							max = digit.get(i);
							index = i;
						}
							
						if (lengthi == lengthj || lengthi < lengthj) {
								for (int z = 0, k = 0; z < lengthi; z++, k++) {
									if (String.valueOf(Math.abs((long)digit.get(j))).charAt(z) > String.valueOf(Math.abs((long)digit.get(i))).charAt(k)) {
										maxDigit = String.valueOf(Math.abs((long)digit.get(j))).charAt(0);
										max = digit.get(j);
										index = j;
									}
								}
							}
						else if (lengthj < lengthi) {
							for (int z = 0, k = 0; k < lengthj; z++, k++) {
								if (String.valueOf(Math.abs((long)digit.get(j))).charAt(z) > String.valueOf(Math.abs((long)digit.get(i))).charAt(k)) {
									maxDigit = String.valueOf(Math.abs((long)digit.get(j))).charAt(0);
									max = digit.get(j);
									index = j;
								}
							}
						}
				}
					
			}
			answer.add(max);
			digit.remove(index);
		}
		return getAnswer(answer);
	}

	private String getAnswer(ArrayList<Integer> answer) {
		// TODO Auto-generated method stub
		String s = "";
		for (int i = 0; i < answer.size(); i++)
			s += "" + answer.get(i);
		return s;
	}

	
}
