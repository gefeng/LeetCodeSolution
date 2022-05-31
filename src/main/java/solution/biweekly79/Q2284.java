package solution.biweekly79;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sender With Largest Word Count",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/biweekly-contest-79/problems/sender-with-largest-word-count/"
)
public class Q2284 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        int n = messages.length;
        String ans = "";
        int max = 0;
        for(int i = 0; i < n; i++) {
            String m = messages[i];
            String s = senders[i];

            int tot = m.split("\\s").length;
            map.put(s, map.getOrDefault(s, 0) + tot);
        }

        for(String k : map.keySet()) {
            int tot = map.get(k);
            if(tot > max) {
                max = tot;
                ans = k;
            } else if(tot == max && k.compareTo(ans) > 0) {
                ans = k;
            }
        }

        return ans;
    }
}
