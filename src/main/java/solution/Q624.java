package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Maximum Distance in Arrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-distance-in-arrays/"
)
public class Q624 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxDistance(List<List<Integer>> arrays) {
        int n = arrays.size();
        int res = 0;
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);

        for(int i = 1; i < n; i++) {
            List<Integer> a = arrays.get(i);
            int first = a.get(0);
            int last = a.get(a.size() - 1);

            res = Math.max(res, Math.abs(last - min));
            res = Math.max(res, Math.abs(max - first));

            min = Math.min(min, first);
            max = Math.max(max, last);
        }

        return res;
    }
}
