package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "High Five",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/high-five/"
)
public class Q1086 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[][] highFive(int[][] items) {
        List<Integer>[] map = new List[1001];

        for(int i = 0; i < 1001; i++) {
            map[i] = new ArrayList<>();
        }

        for(int[] item : items) {
            map[item[0]].add(item[1]);
        }

        int tot = 0;
        for(int i = 0; i < 1001; i++) {
            if(!map[i].isEmpty()) {
                tot += 1;
                Collections.sort(map[i], Comparator.reverseOrder());
            }
        }

        int[][] ans = new int[tot][2];
        int p = 0;
        for(int i = 0; i < 1001; i++) {
            if(!map[i].isEmpty()) {
                int sum = 0;
                for(int j = 0; j < 5; j++) {
                    sum += map[i].get(j);
                }

                ans[p++] = new int[] {i, sum / 5};
            }
        }

        return ans;
    }
}
