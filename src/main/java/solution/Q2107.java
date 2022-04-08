package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Unique Flavors After Sharing K Candies",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies/"
)
public class Q2107 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int shareCandies(int[] candies, int k) {
        int n = candies.length;
        int ans = 0;

        Map<Integer, Integer> fmap = new HashMap<>();

        for(int c : candies) {
            fmap.put(c, fmap.getOrDefault(c, 0) + 1);
        }

        for(int l = 0, r = 0; r < n; r++) {
            int c = candies[r];

            decrease(fmap, c);

            if(r - l + 1 > k) {
                fmap.put(candies[l], fmap.getOrDefault(candies[l++], 0) + 1);
            }

            if(r - l + 1 == k) {
                ans = Math.max(ans, fmap.size());
            }
        }

        return ans;
    }

    private void decrease(Map<Integer, Integer> map, int k) {
        int f = map.get(k);
        if(f == 1) {
            map.remove(k);
        } else {
            map.put(k, f - 1);
        }
    }
}
