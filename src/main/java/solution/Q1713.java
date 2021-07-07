package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimum Operations to Make a Subsequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-operations-to-make-a-subsequence/"
)
public class Q1713 {
    /*
        1. find longest common subsequence of target and arr
        2. O(mn) which is too slow
        3. it's mentioned target array only contains distinct elements. What does that mean?
        4. if it's the LCS, each element can be mapped to target and their positions in target should be in ascending order
        5. convert the LCS problem to LIS problem
        6. solve LIS by using patient sort in O(nlogn) time

        target = [6,4,8,1,3,2],
        arr = [4,7,6,2,3,8,6,1]
        map each element in arr to the index in target,
        arr = [1, -1, 0, 5, 4, 2, 0, 3]
    */
    public int minOperations(int[] target, int[] arr) {
        int m = target.length;
        int n = arr.length;

        Map<Integer, Integer> idxMap = new HashMap<>();
        for(int i = 0; i < m; i++) {
            idxMap.put(target[i], i);
        }

        for(int i = 0; i < n; i++) {
            Integer idx = idxMap.get(arr[i]);
            arr[i] = idx == null ? -1 : idx;
        }

        int lis = getLIS(arr);

        return m - lis;
    }

    private int getLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        int len = 0;

        for(int num : nums) {
            if(num == -1) {
                continue;
            }

            if(len == 0 || num > lis[len - 1]) {
                lis[len++] = num;
            } else {
                int idx = ceiling(lis, len, num);
                lis[idx] = num;
            }
        }

        return len;
    }

    private int ceiling(int[] nums, int n, int target) {
        int lo = 0;
        int hi = n - 1;
        int ceiling = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] >= target) {
                ceiling = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ceiling;
    }
}
