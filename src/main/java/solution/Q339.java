package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Nested List Weight Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/nested-list-weight-sum/"
)
public class Q339 {
    interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
    public int depthSum(List<NestedInteger> nestedList) {
        //return dfsSum(nestedList, 1);
        return bfs(nestedList);
    }

    private int dfsSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                sum += (ni.getInteger() * depth);
            } else {
                sum += dfsSum(ni.getList(), depth + 1);
            }
        }

        return sum;
    }

    private int bfs(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new ArrayDeque<>();
        queue.addAll(nestedList);

        int sum = 0;
        int depth = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                if(curr.isInteger()) {
                    sum += curr.getInteger() * depth;
                } else {
                    queue.addAll(curr.getList());
                }
            }
            depth++;
        }

        return sum;
    }
}
