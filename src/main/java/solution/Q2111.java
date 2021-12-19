package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Minimum Operations to Make the Array K-Increasing",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/"
)
public class Q2111 {
    /**
     * Time:  (K * (N / K) * log(N / K))
     * Space: (N)
     * */
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        int ans = 0;

        for(int i = 0; i < k; i++) {
            List<Integer> l = new ArrayList<>();
            for(int j = i; j < n; j += k) {
                l.add(arr[j]);
            }

            int[] x = l.stream().mapToInt(a -> a.intValue()).toArray();
            int lis = lis(x);
            ans += x.length - lis;
        }

        return ans;
    }

    private int lis(int[] arr) {
        int n = arr.length;
        List<Integer> dp = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(dp.size() == 0 || arr[i] >= dp.get(dp.size() - 1)) {
                dp.add(arr[i]);
            } else {
                int idx = upperBound(dp, arr[i]);
                dp.set(idx, arr[i]);
            }
        }

        return dp.size();
    }

    private int upperBound(List<Integer> l, int t) {
        int ret = 0;
        int lo = 0;
        int hi = l.size() - 1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(t < l.get(mid)) {
                ret = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ret;
    }
}
