package solution.weekly288;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Total Beauty of the Gardens",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-288/problems/maximum-total-beauty-of-the-gardens/"
)
public class Q2234 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        long ans = 0;
        long complete = 0;

        Arrays.sort(flowers);

        int cnt = 0;
        for(int i = flowers.length - 1; i >= 0; i--) {
            if(flowers[i] >= target) {
                complete += full;
                cnt++;
            } else {
                break;
            }
        }

        int n = flowers.length - cnt;
        long[] f = new long[n];
        long[] psum = new long[n + 1];

        for(int i = 0; i < n; i++) {
            f[i] = flowers[i];
        }

        for(int i = 1; i <= n; i++) {
            psum[i] = psum[i - 1] + f[i - 1];
        }

        //n - 0
        for(int i = 0; i <= n; i++) {
            long need = (long)target * i;
            long have = psum[n] - psum[n - i];

            if(need - have > newFlowers) {
                break;
            }

            long left = newFlowers - (need - have);

            int lo = 1;
            int hi = n - i;
            int p = 0;

            while(lo <= hi) {
                int mid = lo + hi >> 1;

                if(psum[mid] + left >= (long)mid * f[mid - 1]) {
                    lo = mid + 1;
                    p = mid;
                } else {
                    hi = mid - 1;
                }
            }

            //System.out.println(i + " " + p);
            if(p == 0) {
                ans = Math.max(ans, (long)i * full);
            } else {
                left = left - ((long)p * f[p - 1] - psum[p]);
                long max = Math.min(target - 1, left / p + f[p - 1]);
                ans = Math.max(ans, (long)i * full + max * partial);
            }
        }

        return ans + complete;
    }
}
