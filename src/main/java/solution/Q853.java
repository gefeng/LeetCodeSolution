package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

@Problem(
        title = "Car Fleet",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/car-fleet/"
)
public class Q853 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int carFleet(int target, int[] position, int[] speed) {
        int ans = 0;
        int n = position.length;
        int[][] cars = new int[n][2];

        for(int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, Comparator.comparingInt(a -> a[0]));

        Deque<int[]> deque = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!deque.isEmpty() && cars[i][1] < deque.peekLast()[1]) {
                if(canCatch(deque.peekLast(), cars[i], target)) {
                    deque.pollLast();
                } else {
                    break;
                }
            }

            deque.offerLast(cars[i]);
        }

        return deque.size();
    }

    private boolean canCatch(int[] c, int[] t, int target) {
        double time = ((double)t[0] - c[0]) / (c[1] - t[1]);

        return t[0] + t[1] * time <= target;
    }
}
