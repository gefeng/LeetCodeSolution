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
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for(int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        int left = 0;
        int right = 0;
        for(int i = 0; i < S.length(); i++) {
            right = Math.max(right, last[S.charAt(i) - 'a']);
            if(i == right) {
                ans.add(right - left + 1);
                left = i + 1;
            }
        }

        return ans;
    }
}
