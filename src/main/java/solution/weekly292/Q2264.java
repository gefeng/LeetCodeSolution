package solution.weekly292;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest 3-Same-Digit Number in String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-292/problems/largest-3-same-digit-number-in-string/"
)
public class Q2264 {
    public String largestGoodInteger(String num) {
		String ans = "";
		int n = num.length();
		int best = -1;

		for(int i = 0; i < n; ) {
			int j = i;
			char c = num.charAt(j);
			while(i < n && c == num.charAt(i)) {
				i++;
			}

			if(i - j > 2) {
				best = Math.max(best, c - '0');
			}
		}

		if(best == 0) {
			ans = "000";
		} else if(best > 0) {
			ans = Integer.toString(best * 100 + best * 10 + best);
		}

		return ans;
	}
}
