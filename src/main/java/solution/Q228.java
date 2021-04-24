package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Summary Ranges",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/summary-ranges/"
)
public class Q228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();
        if(nums.length == 0)
            return ranges;

        int l = 0;
        int r = 1;
        while(r <= nums.length) {
            if(r == nums.length || nums[r - 1] + 1 != nums[r]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[l]);
                if(l != r - 1) {
                    sb.append("->").append(nums[r - 1]);
                }
                ranges.add(sb.toString());
                l = r;
            }
            r++;
        }

        return ranges;
    }
}
