package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Find Positive Integer Solution for a Given Equation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/"
)
public class Q1237 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    private interface CustomFunction {
        int f(int x, int y);
    }
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int x = 1; x <= 1000; x++) {
            int lo = 1;
            int hi = 1000;
            int y = 0;
            while(lo <= hi) {
                int mid = lo + hi >> 1;
                int res = customfunction.f(x, mid);
                if(res == z) {
                    y = mid;
                    break;
                } else if(res > z) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if(y != 0) {
                ans.add(Arrays.asList(x, y));
            }
        }

        return ans;
    }
}
