package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Friends Of Appropriate Ages",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/friends-of-appropriate-ages/"
)
public class Q825 {
    public int numFriendRequests(int[] ages) {
        return countingSolution(ages);
    }

    /*
        int[] count = new int[121];

    */
    private int countingSolution(int[] ages) {
        int[] countAge = new int[121];
        for(int age : ages) {
            countAge[age]++;
        }

        int total = 0;
        for(int i = 1; i < countAge.length; i++) {
            if(countAge[i] == 0)
                continue;

            for(int j = 1; j <= i; j++) {
                if(countAge[j] != 0 && canRequest(i, j)) {
                    if(i == j) {
                        total += (countAge[i] * (countAge[i] - 1));
                    } else {
                        total += (countAge[j] * countAge[i]);
                    }
                }
            }
        }
        return total;
    }

    private boolean canRequest(int ageX, int ageY) {
        return ageY > ageX / 2 + 7;
    }

    private int binarySearchSolution(int[] ages) {
        Arrays.sort(ages);

        Map<Integer, Integer> lastIdx = new HashMap<>();

        for(int i = 0; i < ages.length; i++) {
            lastIdx.put(ages[i], i);
        }

        int count = 0;
        for(int i = 0; i < ages.length; i++) {
            int hi = lastIdx.get(ages[i]);
            int lo = binarySearch(ages, 0, hi, ages[i] / 2 + 7);
            if(lo != -1)
                count += (hi - lo);
        }

        return count;
    }

    // search 1st element greater than target
    private int binarySearch(int[] nums, int start, int end, int target) {
        int lo = start;
        int hi = end;
        int mid = 0;
        int idx = -1;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(nums[mid] > target) {
                idx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return idx;
    }
}
