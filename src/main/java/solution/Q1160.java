package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Words That Can Be Formed by Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/"
)
public class Q1160 {
    /**
     * Time:  O(N * L)
     * Space: O(1)
     * */
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        int[] lookup = new int[26];
        for(int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            lookup[c - 'a']++;
        }

        for(String word : words) {
            int[] count = new int[26];
            boolean isGood = true;
            int len = word.length();
            for(int i = 0; i < len; i++) {
                char c = word.charAt(i);
                count[c - 'a']++;
                if(lookup[c - 'a'] == 0 || lookup[c - 'a'] < count[c - 'a']) {
                    isGood = false;
                    break;
                }
            }
            if(isGood)
                ans += len;
        }
        return ans;
    }
}
