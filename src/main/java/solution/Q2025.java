package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Number of Ways to Partition an Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/"
)
public class Q2025 {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        //int[] preSum = new int[n + 1];
        Map<Integer, List<Integer>> lMap = new HashMap<>();
        Map<Integer, List<Integer>> rMap = new HashMap<>();

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            //preSum[i + 1] = preSum[i] + nums[i];
            lMap.computeIfAbsent(sum, key -> new ArrayList<>()).add(i);
        }

        int temp = sum;
        for(int i = 0; i < n; i++) {
            rMap.computeIfAbsent(temp, key -> new ArrayList<>()).add(i);
            temp -= nums[i];
            if(i != n - 1 && temp * 2 == sum) {
                ans++;
            }
        }
        //System.out.println(ans + " " + sum);
        for(int i = 0; i < n; i++) {
            int diff = k - nums[i];
            int nSum = sum + diff;

            if(nSum % 2 != 0) {
                continue;
            }

            List<Integer> l1 = lMap.get(nSum / 2);
            List<Integer> l2 = rMap.get(nSum / 2);

            int cnt = 0;
            if(l1 != null) {
                int idx = Collections.binarySearch(l1, i);
                if(idx < 0) {
                    idx = -idx - 1;
                }
                idx--;
                if(idx >= 0) {
                    cnt += idx + 1;
                }
            }

            if(l2 != null) {
                int idx = Collections.binarySearch(l2, i);
                //System.out.println(i + " " + idx);
                if(idx < 0) {
                    idx = -idx - 1;
                } else {
                    idx++;
                }
                if(idx <= n) {
                    cnt += l2.size() - idx;
                }

            }
            //System.out.println(i + " " + cnt);
            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
