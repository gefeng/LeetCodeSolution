package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Distribute Candies",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/distribute-candies/"
)
public class Q575 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        int half = n / 2;
        int res = 0;
        int[] cnt = new int[200001];

        for(int i = 0; i < n; i++) {
            cnt[candyType[i] + 100000]++;
        }

        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] != 0) {
                res++;
            }
            if(res == half) {
                break;
            }
        }

        return res;
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int hashsetSol(int[] candyType) {
        Set<Integer> set = new HashSet<>();

        for(int type : candyType) {
            set.add(type);
        }

        return Math.min(set.size(), candyType.length / 2);
    }
}
