package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Permutations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/permutations/"
)
public class Q46 {
    List<List<Integer>> ans = new ArrayList<>();
    //Set<Integer> set = new LinkedHashSet<>();
    List<Integer> perm = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backTrack(nums);
        return ans;
    }

    private void backTrack(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(!perm.contains(nums[i])) {
                perm.add(nums[i]);
                if(perm.size() == nums.length)
                    ans.add(new ArrayList<>(perm));
                else
                    backTrack(nums);
                perm.remove(perm.size() - 1);
            }
        }
    }
}
