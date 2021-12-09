package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Koko Eating Bananas",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/koko-eating-bananas/"
)
public class Q875 {
    /**
     * Time:  O(N * log(max(piles)))
     * Space: O(1)
     * */
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = (int)1e9;
        int k = 0;

        while(lo <= hi) {
            int mid = lo + hi >> 1;

            boolean finish = true;
            int cnt = 0;
            for(int p : piles) {
                cnt += (p + mid - 1) / mid;
                if(cnt > h) {
                    finish = false;
                    break;
                }
            }

            if(finish) {
                k = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return k;
    }
}
