/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2

*/


class Solution {
    public int majorityElement(int[] nums) {
        HashMap <Integer,Integer> h=new HashMap<Integer,Integer>();
        
        for(int i=0;i<nums.length;i++)
        {
            if(!h.containsKey(nums[i]))
            {
                h.put(nums[i],1);
            }
            else
            {
                int count=(int)h.get(nums[i]);
                h.put(nums[i],count+1);
                
                if((count+1)>(nums.length/2))
                {
                    return nums[i];
                }
            }
        }
        return nums[0];
        
    }
}
