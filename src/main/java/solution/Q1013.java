package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition Array Into Three Parts With Equal Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/"
)
public class Q1013 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canThreePartsEqualSum(int[] arr) {
        int n = arr.length;
        int sum = 0;

        for(int x : arr) {
            sum += x;
        }

        if(sum % 3 != 0) {
            return false;
        }

        int t = sum / 3;
        int cnt = 0;
        sum = 0;
        for(int i = 0; i < n; i++) {
            sum += arr[i];
            if(sum == t) {
                cnt++;
                sum = 0;
            }

            if(cnt == 2 && i < n - 1) {
                return true;
            }
        }

        return false;
    }
}
