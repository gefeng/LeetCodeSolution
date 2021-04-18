package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Capacity To Ship Packages Within D Days",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/"
)
public class Q1011 {
    /*
    * 和410是一样的，dp也可以解，时间复杂度比binary search高
    * */
    public int shipWithinDays(int[] weights, int D) {
        return binarySearchSolution(weights, D);
        //return recursive(weights, D, 0, 1, new Integer[D + 1][weights.length + 1]);
    }

    private int recursive(int[] weights, int D, int start, int day, Integer[][] memo) {
        if(start > weights.length - 1) {
            return 0;
        }

        if(memo[day][start] != null)
            return memo[day][start];

        int dayCapacity = 0;
        int minCapacity = Integer.MAX_VALUE;
        for(int end = start + 1; end <= weights.length; end++) {
            dayCapacity += weights[end - 1];
            if(day == D) {
                minCapacity = dayCapacity;
            } else {
                minCapacity = Math.min(minCapacity, Math.max(dayCapacity, recursive(weights, D, end, day + 1, memo)));
            }
        }

        return memo[day][start] = minCapacity;
    }

    private int binarySearchSolution(int[] weights, int D) {
        int lo = 0;
        int hi = 0;
        for(int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }

        int mid = 0;
        int minCapacity = 0;

        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;

            if(canSplit(weights, D, mid)) {
                minCapacity = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return minCapacity;
    }

    private boolean canSplit(int[] weights, int D, int capacity) {
        int count = 0;
        int sum = 0;
        for(int end = 1; end <= weights.length; end++) {
            if(sum + weights[end - 1] > capacity) {
                sum = weights[end - 1];
                count++;
            } else {
                sum += weights[end - 1];
            }
        }

        return count < D;
    }
}
