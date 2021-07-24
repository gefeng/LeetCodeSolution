package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Create Sorted Array through Instructions",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/create-sorted-array-through-instructions/"
)
public class Q1649 {
    /**
     * Time:  O(NlogN)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int createSortedArray(int[] instructions) {
        return mergeSortSol(instructions);
    }

    private class BIT {
        int[] b = new int[100002];

        void add(int i) {
            for(; i < 100002; i += i & (-i)) {
                b[i]++;
            }
        }

        int query(int i) {
            int sum = 0;
            for(; i > 0; i -= i & (-i)) {
                sum += b[i];
            }
            return sum;
        }

        int queryRange(int i, int j) {
            return query(j) - query(i);
        }
    }

    private int binaryIndexTreeSol(int[] instructions) {
        int ans = 0;
        int max = 0;
        BIT bit = new BIT();

        for(int ins : instructions) {

            int l = bit.query(ins - 1);
            int r = ins >= max ? 0 : bit.queryRange(ins, max);
            ans = (ans + Math.min(l, r)) % MOD;

            bit.add(ins);
            max = Math.max(max, ins);
        }

        return ans;
    }

    private int mergeSortSol(int[] instructions) {
        int n = instructions.length;
        int[][] nums = new int[n][3];

        for(int i = 0; i < n; i++) {
            nums[i][0] = instructions[i];
        }

        mergeSort(nums, 0, n - 1);

        int ans = 0;
        for(int[] num : nums) {
            ans = (ans + Math.min(num[1], num[2])) % MOD;
        }

        return ans;
    }

    private void mergeSort(int[][] nums, int start, int end) {
        if(start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[][] nums, int start, int mid, int end) {
        int[][] m = new int[end - start + 1][3];

        int i = start;
        int j = mid + 1;
        int k = 0;
        int cntDup = 0;

        while(i <= mid || j <= end) {
            if(i > mid) {
                m[k] = new int[] { nums[j][0], nums[j][1] + mid - start + 1, nums[j][2] };

                if(cntDup != 0) {
                    m[k][1] -= cntDup;

                    if(j + 1 <= end && nums[j + 1][0] != nums[j][0]) {
                        cntDup = 0;
                    }
                }

                j++;
            } else if(j > end) {
                m[k] = new int[] { nums[i][0], nums[i][1], nums[i][2] };
                i++;
            } else {
                if(nums[i][0] <= nums[j][0]) {
                    if(nums[i][0] == nums[j][0]) {
                        cntDup++;
                    }
                    m[k] = new int[] { nums[i][0], nums[i][1], nums[i][2] };
                    i++;
                } else {
                    m[k] = new int[] { nums[j][0], nums[j][1] + i - start, nums[j][2] + mid - i + 1 };
                    if(cntDup != 0) {
                        m[k][1] -= cntDup;

                        if(j + 1 <= end && nums[j + 1][0] != nums[j][0]) {
                            cntDup = 0;
                        }
                    }

                    j++;
                }
            }

            k++;
        }

        for(i = 0; i < m.length; i++) {
            nums[start + i][0] = m[i][0];
            nums[start + i][1] = m[i][1];
            nums[start + i][2] = m[i][2];
        }
    }
}
