package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Difference",
        difficulty = QDifficulty.EASY,
        tag =  QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-the-difference/"
)
public class Q389 {
    // lookup table
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            count[cs - 'a']++;
            count[ct - 'a']--;
        }

        count[t.charAt(t.length() - 1) - 'a']--;

        char diff = 'a';
        for(int i = 0; i < 26; i++) {
            if(count[i] < 0) {
                diff = (char)(i + 'a');
                break;
            }
        }
        return diff;
    }

    // bit manipulation
    public char findTheDifferenceBitManipulation(String s, String t) {
        int xor = 0;
        for(int i = 0; i < s.length(); i++) {
            xor ^= s.charAt(i);
        }

        for(int i = 0; i < t.length(); i++) {
            xor ^= t.charAt(i);
        }

        return (char)xor;
    }
}
