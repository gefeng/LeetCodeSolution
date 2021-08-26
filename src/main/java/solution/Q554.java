package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Brick Wall",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/brick-wall/"
)
public class Q554 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cntMap = new HashMap<>();

        for(List<Integer> row : wall) {
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                cntMap.put(sum, cntMap.getOrDefault(sum, 0) + 1);
            }
        }

        int max = 0;
        for(Map.Entry<Integer, Integer> e : cntMap.entrySet()) {
            max = Math.max(max, e.getValue());
        }

        return wall.size() - max;
    }
}
