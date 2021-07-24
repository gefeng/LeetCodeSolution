package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Furthest Building You Can Reach",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/furthest-building-you-can-reach/"
)
public class Q1642 {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return onePassSol(heights, bricks, ladders);
    }

    /**
     * Time:  O(N * logN * logN)
     * Space: O(N)
     * */
    private int binarySearchSol(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        int lo = 0;
        int hi = n - 1;
        int ans = 0;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(canReach(heights, mid, bricks, ladders)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[] h, int f, int b, int l) {
        Queue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 1; i <= f; i++) {
            int diff = h[i] - h[i - 1];
            if(diff > 0) {
                minHeap.offer(diff);
            }
        }

        while(!minHeap.isEmpty()) {
            int top = minHeap.poll();
            if(top <= b) {
                b -= top;
            } else if(l > 0) {
                l--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * use ladder until no ladder left, try to replace ladder with bricks.
     * 
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private int onePassSol(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        Queue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 1; i < n; i++) {
            int diff = heights[i] - heights[i - 1];

            if(diff <= 0) {
                continue;
            }

            minHeap.offer(diff);
            if(minHeap.size() > ladders) {
                bricks -= minHeap.poll();
            }

            if(bricks < 0) {
                return i - 1;
            }
        }

        return n - 1;
    }
}
