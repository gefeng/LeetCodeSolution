package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Nested List Weight Sum II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/nested-list-weight-sum-ii/"
)
public class Q364 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private interface NestedInteger {
        boolean isInteger();
        List<NestedInteger> getList();
        Integer getInteger();
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = getDepth(nestedList);
        return getSum(nestedList, 1, maxDepth);
    }

    private int getDepth(List<NestedInteger> nestedList) {
        int max = 0;

        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                max = Math.max(max, 1);
            } else {
                max = Math.max(max, 1 + getDepth(ni.getList()));
            }
        }

        return max;
    }

    private int getSum(List<NestedInteger> nestedList, int curDepth, int maxDepth) {
        int sum = 0;
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                sum += (maxDepth - curDepth + 1) * ni.getInteger();
            } else {
                sum += getSum(ni.getList(), curDepth + 1, maxDepth);
            }
        }

        return  sum;
    }
}
