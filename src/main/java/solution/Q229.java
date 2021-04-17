package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Majority Element II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/majority-element-ii/"
)
public class Q229 {
    /*
    * 投票算法
    * */
    public List<Integer> majorityElement(int[] nums) {
        Integer candidate1 = null;
        Integer candidate2 = null;
        int count1 = 0;
        int count2 = 0;

        for(int n : nums) {
            if(candidate1 != null && candidate1.equals(n)) {
                count1++;
            } else if(candidate2 != null && candidate2.equals(n)) {
                count2++;
            } else if(candidate1 == null || count1 == 0) {
                candidate1 = n;
                count1 = 1;
            } else if(candidate2 == null || count2 == 0) {
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for(int n : nums) {
            if(candidate1 != null && candidate1.equals(n))
                count1++;
            else if(candidate2 != null && candidate2.equals(n))
                count2++;
        }

        if(count1 > nums.length / 3)
            ans.add(candidate1);
        if(count2 > nums.length / 3)
            ans.add(candidate2);
        return ans;
    }
}
