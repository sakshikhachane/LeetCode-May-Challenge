/*
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 
Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer
*/


class Solution {
    public int findComplement(int num) {
        
        String num_binary= Integer.toBinaryString(num);
        StringBuffer num_complement=new StringBuffer();
        for(int i=0;i<num_binary.length();i++)
        {
            if(num_binary.charAt(i)=='0')
            {
                num_complement.append('1');
            }
            else
            {
                num_complement.append('0');
            }
        }
        
        int num_decimal=Integer.parseInt(num_complement.toString(),2);
        return num_decimal;
    }
}
