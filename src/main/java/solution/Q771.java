package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Jewels and Stones",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/jewels-and-stones/"
)
public class Q771 {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for(int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        for(int i = 0; i < S.length(); i++) {
            if(set.contains(S.charAt(i)))
                count++;
        }
        return count;
    }
}
