package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Array of Double Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/array-of-doubled-pairs/"
)
public class Q954 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canReorderDoubled(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[200001];

        for(int num : arr) {
            cnt[num + 100000]++;
        }

        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] == 0 || i % 2 == 1) {
                continue;
            }

            int j = (i - 100000) / 2 + 100000;
            if(j < 0) {
                continue;
            }

            int min = Math.min(cnt[i], cnt[j]);
            cnt[i] = Math.max(0, cnt[i] - min); // [0,0]
            cnt[j] = Math.max(0, cnt[j] - min);
        }

        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
