package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Minimum Incompatibility",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-incompatibility/"
)
public class Q1681 {
    /*
       state:
           dp[i] means mininum sum by picking numbers as mask i
       transition:
           dp[i] if cnt_bits(i) <= n / k then nums[l_most_bit] - nums[r_most_bit]
                 else min(dp[i - tb] + dp[tb]) where tb is the submask of i and cnt_bits(tb) == n / k
   */
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int size = n / k;
        int[] dp = new int[1 << n];

        if(!isValid(nums, k)) {
            return -1;
        }

        Arrays.sort(nums);

        Set<Integer> subsets = new HashSet<>();
        for(int i = 0; i < (1<<n); i++) {
            int numBits = cntBits(i);
            if(numBits != size) {
                continue;
            }
            subsets.add(i);

            if(isValidMask(i, nums)) {
                int score = nums[getMSB(i)] - nums[getLSB(i)];
                subsets.add(i);
                dp[i] = score;
            }
        }

        for(int i = 1; i < (1 << n); i++) {
            int numBits = cntBits(i);

            if(numBits % size != 0 || numBits == size) {
                continue;
            }


            for(int tb = i; tb > 0; tb = (tb - 1) & i) {
                if(!subsets.contains(tb) || dp[tb] == 0 || dp[i - tb] == 0) {
                    continue;
                }

                dp[i] = dp[i] == 0 ? dp[i - tb] + dp[tb] : Math.min(dp[i], dp[i - tb] + dp[tb]);
            }
        }

        return dp[(1 << n) - 1];
    }

    private boolean isValid(int[] nums, int k) {
        int[] cnt = new int[17];
        for(int num : nums) {
            if(++cnt[num] > k) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidMask(int mask, int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < 16; i++) {
            if(((1 << i) & mask) != 0) {
                if(seen.contains(nums[i])) {
                    return false;
                }
                seen.add(nums[i]);
            }
        }
        return true;
    }

    private int cntBits(int num) {
        int cnt = 0;
        while(num != 0) {
            cnt += num % 2;
            num /= 2;
        }
        return cnt;
    }

    private int getMSB(int num) {
        int i = -1;
        while(num != 0) {
            num >>= 1;
            i++;
        }
        return i;
    }

    private int getLSB(int num) {
        for(int i = 0; i < 16; i++) {
            if((num & (1 << i)) != 0) {
                return i;
            }
        }
        return -1;
    }
}
