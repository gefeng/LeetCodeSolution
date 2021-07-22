package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition Array into Disjoint Intervals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/partition-array-into-disjoint-intervals/"
)
public class Q915 {
    public int partitionDisjoint(int[] nums) {
        return onePass(nums);
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int twoPass(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int rmin = Integer.MAX_VALUE;
        int[] lmax = new int[n];

        lmax[0] = nums[0];
        for(int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], nums[i]);
        }

        for(int i = n - 1; i >= 0; i--) {
            if(rmin >= lmax[i]) {
                ans = i + 1;
            }

            rmin = Math.min(rmin, nums[i]);
        }

        return ans;
    }

    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private int onePass(int[] nums) {
        int n = nums.length;
        int partion = 0;
        int leftMax = nums[0];
        int globalMax = leftMax;

        for(int i = 0; i < n; i++) {
            if(nums[i] < leftMax) {
                partion = i;
                leftMax = globalMax;
            }
            globalMax = Math.max(globalMax, nums[i]);
        }

        return partion + 1;

    }
}
