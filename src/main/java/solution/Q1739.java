package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Building Boxes",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/building-boxes/"
)
public class Q1739 {
    public int minimumBoxes(int n) {
        if(n < 4) {
            return n;
        }

        List<Integer> base = new ArrayList<>();
        for(int i = 1, j = 2; i <= n; i += j, j++) {
            base.add(i);
        }

        int lo = 0;
        int hi = n;
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(canStack(mid, n - mid, base)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean canStack(int b, int k, List<Integer> base) {
        if(k <= 0) {
            return true;
        }
        if(b < 3) {
            return false;
        }

        int idx = Collections.binarySearch(base, b);
        idx = idx < 0 ? -idx - 2 : idx;
        int curr = base.get(idx);
        int next = base.get(idx - 1);

        if(b == curr || b == curr + 1) {
            return canStack(next, k - next, base);
        } else {
            next = next + b - curr - 1;
            return canStack(next, k - next, base);
        }
    }
}
