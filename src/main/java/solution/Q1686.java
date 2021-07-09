package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Stone Game VI",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/stone-game-vi/"
)
public class Q1686 {
    /*
    * 这道题提供一个蛮典型的思路，当input constraints大于1e5的时候，dp的状态至少是2维以上，
    * 就要放弃dp解，因为肯定TLE。这些题目多数有greedy解。
    * */
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0] + b[1], a[0] + a[1]));

        int score1 = 0;
        int score2 = 0;
        for(int i = 0; i < n; i++) {
            score1 += aliceValues[i];
            score2 += bobValues[i];
            maxHeap.offer(new int[] {aliceValues[i], bobValues[i]});
        }

        int turn = 0;
        while(!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            if(turn == 0) {
                score2 -= top[1];
                turn = 1;
            } else {
                score1 -= top[0];
                turn = 0;
            }
        }

        return score1 == score2 ? 0 : (score1 > score2 ? 1 : -1);
    }
}
