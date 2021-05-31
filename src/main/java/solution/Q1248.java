package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Nice Subarrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/count-number-of-nice-subarrays/"
)
public class Q1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        return onePass(nums, k);
    }

    private int twoPass(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    private int atMostK(int[] nums, int k) {
        int n = nums.length;
        int cntOdd = 0;
        int cnt = 0;
        int l = 0;
        int r = 0;
        int prefix = 0;
        while(r < n) {
            boolean isOdd = nums[r] % 2 != 0;

            if(isOdd) {
                cntOdd++;
            }

            // fix the window
            while(cntOdd > k) {
                if(nums[l++] % 2 != 0) {
                    cntOdd--;
                }
            }

            if(isOdd) {
                prefix = r - l + 1;
            }
            cnt += prefix;
            r++;
        }

        return cnt;
    }

    private int onePass(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        int cntOdd = 0;
        int l = 0;
        int r = 0;
        int prefix = 0;
        while(r < n) {
            boolean isOdd = nums[r++] % 2 != 0;

            if(isOdd) {
                cntOdd++;
            }

            while(cntOdd > k) {
                if(nums[l++] % 2 != 0) {
                    cntOdd--;
                }
            }

            if(cntOdd == k) {
                if(isOdd) {
                    prefix = 1;
                    while(nums[l] % 2 == 0) {
                        prefix++;
                        l++;
                    }
                }
                cnt += prefix;
            }
        }

        return cnt;
    }
}
