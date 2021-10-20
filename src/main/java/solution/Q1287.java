package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Element Appearing More Than 25% In Sorted Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/"
)
public class Q1287 {
    /**
     * Time:  O(N)
     * Space: O(max(arr))
     * */
    public int findSpecialInteger(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[] cnt = new int[100001];

        for(int x : arr) {
            cnt[x] += 1;
            if(cnt[x] > n / 4) {
                ans = x;
                break;
            }
        }

        return ans;
    }
}
