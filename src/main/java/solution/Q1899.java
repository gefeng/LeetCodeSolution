package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Merge Triplets to Form Target Triplet",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/merge-triplets-to-form-target-triplet/"
)
public class Q1899 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        Set<int[]> impossible = new HashSet<>();
        for(int[] t : triplets) {
            if(t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                impossible.add(t);
            }
        }

        boolean[] seen = new boolean[3];
        for(int[] t : triplets) {
            if(impossible.contains(t)) {
                continue;
            }

            for(int i = 0; i < 3; i++) {
                seen[i] = seen[i] ? seen[i] : t[i] == target[i];
            }
        }

        return seen[0] && seen[1] && seen[2];
    }
}
