package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@Problem(
        title = "Describe the Painting",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.LINE_SWEEP,
        url = "https://leetcode.com/problems/describe-the-painting/"
)
public class Q1943 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<List<Long>> splitPainting(int[][] segments) {
        List<List<Long>> res = new ArrayList<>();
        TreeMap<Integer, Long> map = new TreeMap<>();

        for(int[] s : segments) {
            map.put(s[0], map.getOrDefault(s[0], 0L) + s[2]);
            map.put(s[1], map.getOrDefault(s[1], 0L) - s[2]);
        }

        int start = 0;
        long color = 0;
        for(int end : map.keySet()) {
            if(color != 0) {
                res.add(Arrays.asList((long)start, (long)end, color));
            }
            color += map.get(end);
            start = end;
        }

        return res;
    }
}
