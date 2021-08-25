package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest Uncommon Subsequence II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/longest-uncommon-subsequence-ii/"
)
public class Q522 {
    public int findLUSlength(String[] strs) {
        return bruteForceSol(strs);
    }

    /**
     * Generate all subsequence and count the frequency of each subsequence.
     * The answer should be the longest subsequence whose frequency is 1.
     *
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N * N)
     * */
    private int bruteForceSol(String[] strs) {
        int res = -1;
        Map<String, Integer> freqMap = new HashMap<>();

        for(String s : strs) {
            int n = s.length();
            for(int i = 1; i < (1 << n); i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if((i & (1 << j)) != 0) {
                        sb.append(s.charAt(j));
                    }
                }
                String ss = sb.toString();
                freqMap.put(ss, freqMap.getOrDefault(ss, 0) + 1);
            }
        }

        for(Map.Entry<String, Integer> e : freqMap.entrySet()) {
            if(e.getValue() == 1) {
                res = Math.max(res, e.getKey().length());
            }
        }

        return res;
    }

    /**
     * Realize that if a uncommon subsequence exists, it must be one of the given strings.
     * More general is a shorter substring is a uncommon one, original string must be a
     * uncommon one.
     *
     * Time:  O(L * N ^ 2)
     * Space: O(1)
     * */
    private int smartSol(String[] strs) {
        int n = strs.length;
        int res = -1;

        for(int i = 0; i < n; i++) {
            boolean isUncommon = true;
            for(int j = 0; j < n; j++) {
                if(i != j) {
                    if(isSubsequence(strs[i], strs[j])) {
                        isUncommon = false;
                        break;
                    }
                }
            }

            if(isUncommon) {
                res = Math.max(res, strs[i].length());
            }
        }

        return res;
    }

    private boolean isSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int i = 0;
        int j = 0;
        while(i < m && j < n) {
            char c = s1.charAt(i);
            while(j < n && c != s2.charAt(j)) {
                j++;
            }
            if(j == n) {
                return false;
            }
            i++;
            j++;
        }

        return i == m;
    }
}
