package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "How Many Apples Can You Put into the Basket",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket/"
)
public class Q1196 {
    public int maxNumberOfApples(int[] weight) {
        int ans = 0;

        Arrays.sort(weight);

        int cnt = 0;
        for(int w : weight) {
            if(cnt + w <= 5000) {
                ans += 1;
                cnt += w;
            } else {
                break;
            }
        }

        return ans;
    }
}
