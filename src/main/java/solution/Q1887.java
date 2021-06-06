package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reduction Operations to Make the Array Elements Equal",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/"
)
public class Q1887 {
    public int reductionOperations(int[] nums) {
        int[] freq = new int[50001];

        for(int num : nums) {
            freq[num]++;
        }

        int cnt = 0;
        int prev = 0;
        for(int i = freq.length - 1; i >= 0; i--) {
            if(freq[i] == 0) {
                continue;
            }

            cnt += (freq[i] + prev);
            prev += freq[i];
        }

        return cnt - prev;
    }
}
