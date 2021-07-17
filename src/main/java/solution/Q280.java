package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Wiggle Sort",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/wiggle-sort/"
)
public class Q280 {
    public void wiggleSort(int[] nums) {
        inPlaceSwapSol(nums);
    }

    private void inPlaceSwapSol(int[] nums) {
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            if((i % 2 == 0 && nums[i] > nums[i - 1]) || (i % 2 != 0 && nums[i] < nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void cntingSortSol(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[10001];

        for(int num : nums) {
            cnt[num]++;
        }

        int j = 0;
        for(int i = 0; i < n; i += 2) {
            while(cnt[j] == 0) {
                j++;
            }

            nums[i] = j;
            cnt[j]--;
        }

        for(int i = 1; i < n; i += 2) {
            while(cnt[j] == 0) {
                j++;
            }

            nums[i] = j;
            cnt[j]--;
        }
    }
}
