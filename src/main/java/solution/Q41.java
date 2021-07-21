package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "First Missing Positive",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/first-missing-positive/"
)
public class Q41 {
    /**
     * Key observation,
     * remove negative, 0 and NUMBERS LARGER THAN N
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int firstMissingPositive(int[] nums) {
        return constantSpaceSol(nums);
    }

    private int constantSpaceSol(int[] nums) {
        int n = nums.length;

        boolean seenOne = false;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                seenOne = true;
            }
            if(nums[i] < 1 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        if(!seenOne) {
            return 1;
        }

        for(int i = 0; i < n; i++) {
            int pos = Math.abs(nums[i]) - 1;

            if(nums[pos] > 0) {
                nums[pos] *= -1;
            }
        }

        int ans = n + 1;
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                ans = i + 1;
                break;
            }
        }

        return ans;
    }

    private int setSol(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int ans = -1;

        for(int num : nums) {
            if(num > 0) {
                seen.add(num);
            }
        }

        if(seen.isEmpty() || !seen.contains(1)) {
            return 1;
        }

        for(int num : seen) {
            if(num != 1 && !seen.contains(num - 1)) {
                ans = ans == -1 ? num - 1 : Math.min(ans, num - 1);
            }
            if(num != Integer.MAX_VALUE && !seen.contains(num + 1)) {
                ans = ans == -1 ? num + 1 : Math.min(ans, num + 1);
            }
        }

        return ans;
    }
}
