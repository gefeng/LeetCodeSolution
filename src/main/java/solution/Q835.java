package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Image Overlap",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/image-overlap/"
)
public class Q835 {
    public int largestOverlap(int[][] A, int[][] B) {
        int maxOverlap = 0;
        int n = A.length;
        List<int[]> posA = new ArrayList<>();
        List<int[]> posB = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(A[i][j] == 1)
                    posA.add(new int[] {i, j});
                if(B[i][j] == 1)
                    posB.add(new int[] {i, j});
            }
        }

        for(int[] pA : posA) {
            for(int[] pB : posB) {
                int offsetX = pA[0] - pB[0];
                int offsetY = pA[1] - pB[1];
                String key = offsetX + "-" + offsetY;
                map.put(key, map.getOrDefault(key, 0) + 1);
                maxOverlap = Math.max(maxOverlap, map.get(key));
            }
        }

        return maxOverlap;
    }
}
