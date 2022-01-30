package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Can Place Flowers",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/can-place-flowers/"
)
public class Q605 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int m = flowerbed.length;

        for(int i = 0; i < m; ) {
            if(flowerbed[i] == 1) {
                i++;
            } else {
                int j = i;
                while(j < m && flowerbed[j] == 0) {
                    j++;
                }
                if(i == 0 && j == m) {
                    n -= (j - i + 1) / 2;
                } else if(i == 0 || j == m) {
                    n -= (j - i) / 2;
                } else {
                    n -= (j - i - 1) / 2;
                }

                i = j;
            }
        }

        return n <= 0;
    }
}
