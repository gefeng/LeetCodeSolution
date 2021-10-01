package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Longest Happy String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/longest-happy-string/"
)
public class Q1405 {
    /**
     * Time:  O(max(a, b, c))
     * Space: O(a + b + c)
     * */
    public String longestDiverseString(int a, int b, int c) {
        int[] abc = new int[] {a, b, c};
        StringBuilder sb = new StringBuilder();
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[0], Comparator.reverseOrder()));

        for(int i = 0; i < 3; i++) {
            if(abc[i] != 0) {
                pq.offer(new int[] {abc[i], i});
            }
        }

        while(!pq.isEmpty()) {
            int[] one = pq.poll();
            int[] two = pq.isEmpty() ? null : pq.poll();

            if(two == null) {
                if(sb.length() == 0 || sb.charAt(sb.length() - 1) - 'a' != one[1]) {
                    for(int i = 0; i < Math.min(2, one[0]); i++) {
                        sb.append((char)(one[1] + 'a'));
                    }
                }
            } else {
                if(one[0] - two[0] <= 1) {
                    for(int i = 0; i < Math.min(2, one[0]); i++) {
                        sb.append((char)(one[1] + 'a'));
                    }
                    for(int i = 0; i < Math.min(2, two[0]); i++) {
                        sb.append((char)(two[1] + 'a'));
                    }
                    one[0] = Math.max(0, one[0] - 2);
                    two[0] = Math.max(0, two[0] - 2);
                    if(one[0] > 0) {
                        pq.offer(one);
                    }
                    if(two[0] > 0) {
                        pq.offer(two);
                    }
                } else {
                    for(int i = 0; i < Math.min(2, one[0]); i++) {
                        sb.append((char)(one[1] + 'a'));
                    }
                    sb.append((char)(two[1] + 'a'));

                    one[0] = Math.max(0, one[0] - 2);
                    two[0]--;
                    if(one[0] > 0) {
                        pq.offer(one);
                    }
                    if(two[0] > 0) {
                        pq.offer(two);
                    }
                }
            }
        }

        return sb.toString();
    }
}
