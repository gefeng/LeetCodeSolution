package solution.biweekly82;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "The Latest Time to Catch a Bus",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/biweekly-contest-82/problems/the-latest-time-to-catch-a-bus/"
)
public class Q2332 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int m = buses.length;
        int n = passengers.length;
        int ans = 0;

        Arrays.sort(buses);
        Arrays.sort(passengers);

        for(int i = 0, j = 0; i < m; i++) {
            int cnt = 0;

            while(j < n && cnt < capacity && passengers[j] <= buses[i]) {
                if(j == 0 || passengers[j] - passengers[j - 1] > 1) {
                    ans = passengers[j] - 1;
                }

                cnt += 1;

                if((j == n - 1 || passengers[j + 1] - passengers[j] > 1) && passengers[j] + 1 <= buses[i] && cnt != capacity) {
                    ans = passengers[j] + 1;
                }
                j++;
            }
            if(cnt < capacity && (j == 0 || passengers[j - 1] != buses[i])) {

                ans = Math.max(ans, buses[i]);
            }

        }

        return ans;
    }
}
