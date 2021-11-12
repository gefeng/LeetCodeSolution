package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find in Mountain Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-in-mountain-array/"
)
public class Q1095 {
    private interface MountainArray {
        int length();
        int get(int i);
    }
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = 0;
        int n = mountainArr.length();

        int lo = 0;
        int hi = n - 1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            int x = mountainArr.get(mid);

            if(mid == 0 || x > mountainArr.get(mid - 1)) {
                peak = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        int l = -1;
        lo = 0;
        hi = peak;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            int x = mountainArr.get(mid);

            if(target == x) {
                l = mid;
                break;
            } else if(target < x) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if(l != -1) {
            return l;
        }

        int r = -1;
        lo = peak;
        hi = n - 1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            int x = mountainArr.get(mid);

            if(target == x) {
                r = mid;
                break;
            } else if(target < x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return r;
    }
}
