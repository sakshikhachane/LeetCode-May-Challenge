/*
Given an arbitrary ransom note string and another string containing letters from all the magazines,
write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
*/


class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
         HashMap<Character,Integer> h=new HashMap<Character,Integer>();
        
        for(int i=0;i<ransomNote.length();i++)
        {
            if(!h.containsKey(ransomNote.charAt(i)))
            {
                h.put(ransomNote.charAt(i),1);
            }
            else
            {
                int cnt=(int)h.get(ransomNote.charAt(i));
                h.put(ransomNote.charAt(i),cnt+1);       
            }
        }

        for(int i=0;i<magazine.length();i++)
        {
            if(h.containsKey(magazine.charAt(i)))
            {
                int cnt=(int)h.get(magazine.charAt(i));
                if(cnt>0)
                {
                	h.put(magazine.charAt(i),cnt-1);
                }
                
            }
        }
        
        for(int i=0;i<ransomNote.length();)
        {
            if(h.containsKey(ransomNote.charAt(i)))
            {
                int cnt=(int)h.get(ransomNote.charAt(i));
                if(cnt==0)
                {
                    i++;
                }
                else
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}
