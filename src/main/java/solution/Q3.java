package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Longest Substring Without Repeating Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-substring-without-repeating-characters/"
)
public class Q3 {
    /*这种题要注意space complexity是O(min(n, k)) where
    * n = s.length, k = len(char set) (比如如果用lowercase letter，k = 26)
    * time complexity O(2n) typical sliding window*/
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int longest = 0;
        int len = s.length();
        while(left < len && right < len) {
            if(set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            else
            {
                set.add(s.charAt(right++));
                longest = Math.max(longest, right - left);
            }
        }
        return longest;
    }

    /*time complexity O(n)*/
    public int lengthOfLongestSubstringHashMap(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int longest = 0;
        for(; right < s.length(); right++) {
            char c = s.charAt(right);
            if(map.containsKey(c)) {
                int prevIdx = map.get(c);
                left = prevIdx >= left ? prevIdx + 1 : left;
            }
            longest = Math.max(longest, right - left + 1);
            map.put(c, right);
        }
        return longest;
    }
}
