package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Student that Will Replace the Chalk",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/"
)
public class Q1894 {
    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;

        long sum = 0;
        for(int c : chalk) {
            sum += c;
        }

        long kk = k % sum;
        //k %= sum;
        //k = k % sum;

        for(int i = 0; i < n; i++) {
            kk -= chalk[i];
            if(kk < 0) {
                return i;
            }
        }

        return 0;
    }
}
