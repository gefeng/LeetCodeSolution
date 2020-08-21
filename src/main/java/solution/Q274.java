package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "H-Index",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/h-index/"
)
public class Q274 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counter = new int[n + 1];
        for(int i = 0; i < n; i++) {
            counter[Math.min(citations[i], n)]++;
        }

        int hIndex = n;
        int papers = 0;
        for(; hIndex >= 0; hIndex--) {
            papers += counter[hIndex];
            if(hIndex <= papers)
                break;
        }
        return hIndex;
    }
}
