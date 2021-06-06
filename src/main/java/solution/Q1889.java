package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Space Wasted From Packaging",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/minimum-space-wasted-from-packaging/"
)
public class Q1889 {
    /*
    * 注意题目的constraints，这题有个constraint提示所有suppliers提供的总boxes数和packages数差不多
    * 通过binary search packages找到所有能装进当前box的packages可以减少时间复杂度
    * */
    private static final int MOD = (int)1e9 + 7;
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int m = packages.length;
        int n = boxes.length;

        Arrays.sort(packages);

        long totalSize = 0;
        for(int p : packages) {
            totalSize += p;
        }

        long minSpace = -1;
        for(int[] s : boxes) {
            Arrays.sort(s);   // logk

            if(packages[m - 1] > s[s.length - 1]) {
                continue;
            }

            long space = 0;
            int currIdx = 0;
            int prevIdx = -1;

            // O(N * (KlogM + KlogK))
            for(int box : s) {
                currIdx = binarySearch(packages, box);
                space += (currIdx - prevIdx) * (long)box;

                if(currIdx == m - 1) {
                    break;
                }

                prevIdx = currIdx;
            }

            minSpace = minSpace == -1 ? space : Math.min(minSpace, space);
        }

        return minSpace == -1 ? -1 : (int)((minSpace - totalSize) % MOD);
    }

    private int binarySearch(int[] packages, int target) {
        int lo = 0;
        int hi = packages.length - 1;
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(packages[mid] <= target) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
