package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Wiggle Sort II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/wiggle-sort-ii/"
)
public class Q324 {
    /**
     * The problem is tricky.
     * The general idea is to find middle point of the sorted array.
     * We can use counting sort due to the constraints.
     * After finding we do "wiggle" insertion. But somehow we cannot
     * do this from left to right,
     * test case [4,5,5,6] for example fails if we insert like
     * [4 _ 5 _] -> [4 5 5 6]
     * instead we insert
     * [_ 6 _ 5] -> [5 6 4 5]
     * the order of insertion of left or right part does matter
     * because it's likely l,r share a bucket.
     * for example,
     * cnt[5] = 10 and middle is within this bucket. we must take right
     * part first before taking left. Otherwise, we gonna take all 5s
     * regardless right part may have some 5s.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public void wiggleSort(int[] nums) {
        countingSortSol(nums);
    }

    private void countingSortSol(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[5001];

        for(int num : nums) {
            cnt[num]++;
        }

        int l = 0;
        int r = 5000;
        int mid = 0;
        for(int i = 1; i < 5001; i++) {
            mid += cnt[i];
            if(mid >= (n + 1) / 2) {
                l = i;
                break;
            }
        }

        for(int i = 0; i < n / 2; i++) {
            while(r > 0 && cnt[r] == 0) {
                r--;
            }
            nums[2 * i + 1] = r;
            cnt[r]--;
        }

        for(int i = 0; i < (n + 1) / 2; i++) {
            while(l < 5001 && cnt[l] == 0) {
                l--;
            }

            nums[2 * i] = l;
            cnt[l]--;
        }
    }
}
