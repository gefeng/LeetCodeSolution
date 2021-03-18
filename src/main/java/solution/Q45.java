package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Jump Game II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/jump-game-ii/"
)
public class Q45 {
    public int jump(int[] nums) {
        return greedySolution(nums);
    }

    /*
        start at point i, we can jump to nums[i]
        iterate through i -> i + nums[i] and stores the next farthest jump we can get
        once hit i + nums[i] expand the range to the farthest position we found means another jump

        [2,2,1,1,0]
    */
    private int linearTimeSolution(int[] nums) {
        int steps = 0;
        int currEnd = 0;
        int nextEnd = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            nextEnd = Math.max(nextEnd, i + nums[i]);
            if(i == currEnd) {
                steps++;
                currEnd = nextEnd;
            }
        }
        return steps;
    }

    // O(n^2)
    private int greedySolution(int[] nums) {
        int steps = 0;
        int i = nums.length - 1;
        while(i > 0) {
            int minStart = -1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] >= i - j)
                    minStart = j;
            }
            if(minStart == -1)
                break;
            i = minStart;
            steps++;
        }
        return steps;
    }

    // O(n^2)
    private int bfsSolution(int[] nums) {
        int minSteps = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();

        queue.offer(0);
        seen.add(0);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int pos = queue.poll();
                if(pos == nums.length - 1)
                    return minSteps;
                for(int j = nums[pos]; j >= 1; j--) {
                    int nextPos = pos + j;
                    if(nextPos < nums.length && !seen.contains(nextPos)) {
                        queue.offer(nextPos);
                        seen.add(nextPos);
                    }
                }
            }
            minSteps++;
        }

        return minSteps;
    }

    private int dfsWithMemo(int[] nums, int currPos, Integer[] memo) {
        if(currPos == nums.length - 1)
            return 0;

        if(memo[currPos] != null)
            return memo[currPos];

        int minSteps = -1;
        int maxSteps = Math.min(nums[currPos], nums.length - 1 - currPos);
        for(int i = 1; i <= maxSteps; i++) {
            int steps = dfsWithMemo(nums, currPos + i, memo);
            if(steps != -1)
                minSteps = minSteps == -1 ? steps + 1 : Math.min(minSteps, steps + 1);
        }
        memo[currPos] = minSteps;
        return minSteps;
    }
}
