package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Missing Ranges",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/missing-ranges/"
)
public class Q163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        if(nums.length == 0) {
            ans.add(formatRange(lower, upper));
            return ans;
        }

        for(int i = 0; i <= nums.length; i++) {
            if(i == 0) {
                if(nums[i] != lower) {
                    ans.add(formatRange(lower, nums[i] - 1));
                }
            } else if(i == nums.length) {
                if(nums[i - 1] != upper) {
                    ans.add(formatRange(nums[i - 1] + 1, upper));
                }
            } else {
                if(nums[i] - nums[i - 1] > 1) {
                    ans.add(formatRange(nums[i - 1] + 1, nums[i] - 1));
                }
            }
        }

        return ans;
    }

    private String formatRange(int lower, int upper) {
        if(lower == upper) {
            return Integer.toString(lower);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lower).append("->").append(upper);
        return sb.toString();
    }
}
