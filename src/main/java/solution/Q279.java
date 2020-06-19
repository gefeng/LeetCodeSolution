package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;

@Problem(
        title = "Perfect Squares",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/perfect-squares/"
)
public class Q279 {
    HashSet<Integer> sqrNums = new HashSet<>();

    private boolean isDividedBy(int n, int count) {
        if(count == 1)
            return sqrNums.contains(n);

        for(Integer num : sqrNums) {
            if(isDividedBy(n - num, count - 1))
                return true;
        }
        return false;
    }

    /**Greedy+DFS**/
    public int numSquares(int n) {
        for(int i = 1; i * i <= n; i++)
           sqrNums.add(i * i);

        if(sqrNums.size() == 0)
            return 0;

        int count = 1;
        for(; count <= n; count++) {
            if(isDividedBy(n, count))
                return count;
        }
        return count;
    }

    /**Greedy+BFS**/
    public int numSquaresBFS(int n) {
        ArrayList<Integer> sqrs = new ArrayList<>();
        for(int i = 1; i * i <= n; i++)
            sqrs.add(i * i);

        if(sqrs.size() == 0)
            return 0;

        int level = 1;
        HashSet<Integer> queue = new HashSet<>();
        queue.add(n);
        while(!queue.isEmpty()) {
            HashSet<Integer> nextQueue = new HashSet<>();

            for(int remain : queue) {
                for(int sqr : sqrs) {
                    if(remain == sqr)
                        return level;
                    if(remain > sqr)
                        nextQueue.add(remain - sqr);
                    else
                        break;
                }
            }

            queue = nextQueue;
            level++;
        }
        return level;
    }
}
