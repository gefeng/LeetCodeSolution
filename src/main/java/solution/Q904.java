package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Fruit Into Baskets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/fruit-into-baskets/"
)
public class Q904 {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> countMap = new HashMap<>();

        int l = 0;
        int r = 0;
        int maxLen = 0;
        while(r < tree.length) {
            countMap.put(tree[r], countMap.getOrDefault(tree[r], 0) + 1);
            while(countMap.size() > 2) {
                int cnt = countMap.get(tree[l]);
                if(cnt == 1) {
                    countMap.remove(tree[l]);
                } else {
                    countMap.put(tree[l], cnt - 1);
                }
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        return maxLen;
    }
}
