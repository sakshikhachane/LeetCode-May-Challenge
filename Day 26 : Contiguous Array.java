/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Note: The length of the given binary array will not exceed 50,000.
*/

/*
Approach #1 Brute Force [Time Limit Exceeded]
Algorithm:
The brute force approach is really simple. We consider every possible subarray within the given array and count
the number of zeros and ones in each subarray. Then, we find out the maximum size subarray with equal no. of zeros and ones out of them
*/


public class Solution {

    public int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }
}

/*
Approach #2 Using Extra Array [Accepted]
Algorithm:
In this approach, we make use of a countcount variable, which is used to store the relative number of ones and
zeros encountered so far while traversing the array. The countcount variable is incremented by one for every \text{1}1
encountered and the same is decremented by one for every \text{0}0 encountered.
We start traversing the array from the beginning. If at any moment, the countcount becomes zero,
it implies that we've encountered equal number of zeros and ones from the beginning till the current index of the array(ii).
Not only this, another point to be noted is that if we encounter the same countcount twice while traversing the array,
it means that the number of zeros and ones are equal between the indices corresponding to the equal countcount values.
The following figure illustrates the observation for the sequence [0 0 1 0 0 0 1 1]:



public class Solution {

    public int findMaxLength(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }
}

*/



/*
Approach #3 Using HashMap [Accepted]
Algorithm

This approach relies on the same premise as the previous approach. But, we need not use an array of size \text{2n+1}2n+1,
since it isn't necessary that we'll encounter all the countcount values possible.
Thus, we make use of a HashMap mapmap to store the entries in the form of (index, count)(index,count). 
We make an entry for a countcount in the mapmap whenever the countcount is encountered first,
and later on use the correspoding index to find the length of the largest subarray with equal no. of zeros and
ones when the same countcount is encountered again.

public class Solution {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}



*/
