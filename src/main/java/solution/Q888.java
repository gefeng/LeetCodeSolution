package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Fair Candy Swap",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/fair-candy-swap/"
)
public class Q888 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sum1 = 0;
        int sum2 = 0;
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int x : aliceSizes) {
            sum1 += x;
            set1.add(x);
        }
        for(int x : bobSizes) {
            sum2 += x;
            set2.add(x);
        }

        int[] ans = new int[2];
        if(sum1 > sum2) {
            for(int x : set1) {
                if(set2.contains(x - (sum1 - sum2) / 2)) {
                    ans[0] = x;
                    ans[1] = x - (sum1 - sum2) / 2;
                    break;
                }
            }
        } else {
            for(int x : set2) {
                if(set1.contains(x - (sum2 - sum1) / 2)) {
                    ans[0] = x - (sum2 - sum1) / 2;
                    ans[1] = x;
                    break;
                }
            }
        }

        return ans;
    }
}
