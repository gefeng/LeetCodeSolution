package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rabbits in Forest",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/rabbits-in-forest/"
)
public class Q781 {
    /**
     * Time:  O(N + max(answers))
     * Space: O(max(answers)
     * */
    public int numRabbits(int[] answers) {
        int n = answers.length;
        int[] cnt = new int[1001];
        int ans = 0;

        for(int i = 0; i < n; i++) {
            cnt[answers[i]]++;
        }

        for(int i = 0; i < cnt.length; i++) {
            ans += cnt[i] / (i + 1) * (i + 1);
            if(cnt[i] % (i + 1) > 0) {
                ans += (i + 1);
            }
        }

        return ans;
    }
}
