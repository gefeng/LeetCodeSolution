package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Short Encoding of Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/short-encoding-of-words/"
)
public class Q820 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minimumLengthEncoding(String[] words) {
        int n = words.length;

        Set<String> set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            set.add(words[i]);
        }

        for(int i = 0; i < n; i++) {
            String s = words[i];
            if(!set.contains(s)) continue;

            boolean isSuffix = false;
            for(String x : set) {
                if(!s.equals(x) && x.endsWith(s)) {
                    isSuffix = true;
                    break;
                }
            }

            if(isSuffix) set.remove(s);
        }

        int ans = 0;
        for(String s : set) {
            ans += s.length() + 1;
        }

        return ans;
    }
}
