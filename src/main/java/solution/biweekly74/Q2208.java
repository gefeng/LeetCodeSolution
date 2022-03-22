package solution.biweekly74;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Minimum Operations to Halve Array Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/contest/biweekly-contest-74/problems/minimum-operations-to-halve-array-sum/"
)
public class Q2208 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int halveArray(int[] nums) {
        int ans = 0;
        double sum = 0;

        Queue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int x : nums) {
            sum += x;
            pq.offer((double)x);
        }

        double cur = sum;
        while(true) {
            double max = pq.poll();

            cur -= max / 2;
            max = max / 2;
            ans++;

            pq.offer(max);

            if(cur <= sum / 2) {
                break;
            }
        }

        return ans;
    }
}
