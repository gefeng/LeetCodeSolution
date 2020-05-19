package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Repeated String Match",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q686 {
    public int repeatedStringMatch(String A, String B) {
        int minRepeatedTimes = 1;
        int maxRepeatedTimes = A.length() >= B.length() ? 2 : B.length() / A.length() + 2;
        StringBuilder repeatA = new StringBuilder();

        while(minRepeatedTimes <= maxRepeatedTimes) {
            if(repeatA.append(A).indexOf(B) != -1)
                return minRepeatedTimes;
            else
                minRepeatedTimes++;
        }
        return -1;
    }
}
