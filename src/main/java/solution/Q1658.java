package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimum Operations to Reduce X to Zero",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/"
)
public class Q1658 {
    public int minOperations(int[] nums, int x) {
        return mapSol(nums, x);
    }

    /**
     * the array can be split into 3 partitions,
     * a | b | c
     * since a + c = x then b = sum - x
     * tranlate to find longest b which sums to sum - x
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private int mapSol(int[] nums, int x) {
        int min = Integer.MAX_VALUE;
        int n = nums.length;
        int target = -x;
        for(int num : nums) {
            target += num;
        }

        if(target == 0) {
            return n;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            Integer idx = map.get(sum - target);
            if(idx != null) {
                min = Math.min(min, n - i + idx);
            }

            map.put(sum, i);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int twoPointersSol(int[] nums, int x) {
        int n = nums.length;
        int[] psL = new int[n + 1];
        int[] psR = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            psL[i] = psL[i - 1] + nums[i - 1];
            psR[i] = psR[i - 1] + nums[n - i];
        }

        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = n;

        while(i < n + 1 && j >= 0 && i + j <= n) {
            int target = x - psL[i];

            if(psR[j] == target) {
                min = Math.min(min, i + j);
                j--;
                i++;
            } else if(psR[j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * Time:  O(NlogN)
     * Space: O(N)
     * */
    private int binarySearchSol(int[] nums, int x) {
        int n = nums.length;
        int[] psL = new int[n + 1];
        int[] psR = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            psL[i] = psL[i - 1] + nums[i - 1];
            psR[i] = psR[i - 1] + nums[n - i];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n + 1; i++) {
            int target = x - psL[i];
            if(target < 0) {
                break;
            }

            int idx = Arrays.binarySearch(psR, target);
            if(idx >= 0 && i + idx <= n) {
                min = Math.min(min, i + idx);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
