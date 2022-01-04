package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Partition Labels",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/partition-labels/"
)
public class Q763 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length();

        int[] last = new int[26];
        for(int i = 0; i < n; i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        for(int i = 0; i < n; i++) {
            int j = last[s.charAt(i) - 'a'];

            for(int k = i + 1; k <= j; k++) {
                if(last[s.charAt(k) - 'a'] > j) {
                    j = last[s.charAt(k) - 'a'];
                }
            }

            ans.add(j - i + 1);
            i = j;
        }

        return ans;
    }
}
