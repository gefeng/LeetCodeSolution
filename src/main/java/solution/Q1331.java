package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;

@Problem(
        title = "Rank Transform of an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/rank-transform-of-an-array/"
)
public class Q1331 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] arrayRankTransform(int[] arr) {
        int len = arr.length;
        int[] copy = Arrays.copyOfRange(arr, 0, len);
        Arrays.sort(copy);

        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for(int i = 0; i < len; i++) {
            if(!map.containsKey(copy[i]))
                map.put(copy[i], rank++);
        }

        int[] ranks = new int[len];
        for(int i = 0; i < len; i++)
            ranks[i] = map.get(arr[i]);
        return ranks;
    }
}
