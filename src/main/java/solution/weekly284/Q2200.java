package solution.weekly284;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find All K-Distant Indices in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/contest/weekly-contest-284/problems/find-all-k-distant-indices-in-an-array/"
)
public class Q2200 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();

        for(int i = 0, j = 0; i < n; ) {
            if(nums[i] == key) {
                while(j < n && j < i + k + 1) {
                    if(j > i && nums[j] == key) {
                        break;
                    }
                    if(Math.abs(i - j) <= k) {
                        ans.add(j);
                    }
                    j++;
                }
                i = j;
            } else {
                i++;
            }
        }

        return ans;
    }
}
