package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Eliminate Maximum Number of Monsters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/eliminate-maximum-number-of-monsters/"
)
public class Q1921 {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arrive = new int[n];

        for(int i = 0; i < n; i++) {
            arrive[i] = (dist[i] + speed[i] - 1) / speed[i];
        }

        Arrays.sort(arrive);

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(arrive[i] == i) {
                return cnt;
            }
            cnt++;
        }

        return n;
    }
}
