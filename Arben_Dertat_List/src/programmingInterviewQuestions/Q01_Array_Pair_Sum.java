package programmingInterviewQuestions;

import java.util.*;

/*
http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/

Given an integer array, output all pairs that sum up to a specific value k.
*/
public class Q01_Array_Pair_Sum {

	public static void main(String[] args) {
		int[] array = {1,1,2,3,4,5,5,6,6};
		int k = 7;
		
		List<int[]> result = arrayPairSum(array, k);
		
		System.out.println("Result: ");
		for(int[] arr : result) {
			System.out.println("[" + arr[0] + ", " + arr[1] + "]");
		}

	}

	/*
	 * Time: O(N)
	 * Space:O(N)
	 */
	public static List<int[]> arrayPairSum(int[] array, int k) {
		List<int[]> result = new ArrayList<>();
		
		if(null==array || array.length<2)
			return result;
		
		Map<Integer, Integer> map = new HashMap<>();
		for(int i : array) {
			if(map.containsKey(i)) {
				int[] temp = new int[2];
				temp[0] = i;
				temp[1] = map.get(i);
				result.add(temp);

				map.remove(i);//prevent duplicates, if required
			}else {
				map.put(k-i, i);
			}
		}
		//System.out.println(map.entrySet());
		
		return result;
	}

}
