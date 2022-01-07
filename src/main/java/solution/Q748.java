package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Completing Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/shortest-completing-word/"
)
public class Q748 {
    /**
     * Time:  O(M + N * L)
     * Space: O(1)
     * */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String ans = "";
        int[] cnt = new int[26];
        for(int i = 0; i < licensePlate.length(); i++) {
            if(licensePlate.charAt(i) == ' ' || Character.isDigit(licensePlate.charAt(i))) continue;
            cnt[Character.toLowerCase(licensePlate.charAt(i)) - 'a']++;
        }

        for(String w : words) {
            int len = w.length();
            int[] x = new int[26];
            boolean isOk = true;
            for(int i = 0; i < len; i++) {
                x[w.charAt(i) - 'a']++;
            }

            for(int i = 0; i < 26; i++) {
                if(cnt[i] > 0 && x[i] < cnt[i]) {
                    isOk = false;
                    break;
                }
            }

            if(isOk) {
                if(ans.isEmpty() || ans.length() > len) {
                    ans = w;
                }
            }
        }

        return ans;
    }
}
