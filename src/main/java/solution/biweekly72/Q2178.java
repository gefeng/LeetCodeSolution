package solution.biweekly72;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Split of Positive Even Integers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-72/problems/maximum-split-of-positive-even-integers/"
)
public class Q2178 {
    /**
     * Time:  O(sqrt(N))
     * Space: O(sqrt(N))
     * */
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if(finalSum % 2 == 1) return ans;

        long sum = 0;
        for(long eve = 2; sum < finalSum; eve += 2) {
            ans.add(eve);
            sum += eve;
        }

        if(sum > finalSum) {
            int last = ans.size() - 1;
            sum -= ans.get(last);
            ans.remove(last);
            ans.set(last - 1, finalSum - sum + ans.get(last - 1));
        }

        return ans;
    }
}
