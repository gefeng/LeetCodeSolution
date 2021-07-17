package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "H-Index II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/h-index-ii/"
)
public class Q275 {
    public int hIndex(int[] citations) {
        int n = citations.length;

        int lo = 0;
        int hi = n;
        int hIdx = 0;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(mid == 0) {       // h == 0
                if(citations[n - 1] == 0) {
                    return 0;
                } else {
                    lo = mid + 1;
                }
            } else if(mid == n) { // h == n
                if(citations[0] >= n) {
                    return n;
                } else {
                    hi = mid - 1;
                }
            } else {  // h == mid
                int c = citations[n - mid];
                int pc = citations[n - mid - 1];
                if(c >= mid) {
                    if(pc <= mid) {
                        hIdx = mid;
                    }
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return hIdx;
    }
}
