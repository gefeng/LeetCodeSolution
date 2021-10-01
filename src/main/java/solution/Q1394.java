package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Lucky Integer in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-lucky-integer-in-an-array/"
)
public class Q1394 {
    /**
     * Time:  O(N)
     * Space: O(max(arr))
     * */
    public int findLucky(int[] arr) {
        int ans = -1;
        int[] cnt = new int[501];
        for(int num : arr) {
            cnt[num]++;
        }

        for(int i = 500; i > 0; i--) {
            if(cnt[i] != 0 && cnt[i] == i) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
