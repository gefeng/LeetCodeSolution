package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Population Year",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-population-year/"
)
public class Q1854 {
    /*
    * Sweep line
    * */
    private static final int START_YEAR = 1950;
    private static final int END_YEAR = 2050;
    public int maximumPopulation(int[][] logs) {
        int[] pop = new int[END_YEAR - START_YEAR + 1];
        for(int[] log : logs) {
            pop[log[0] - START_YEAR]++;
            pop[log[1] - START_YEAR]--;
        }

        int countPop = 0;
        int maxPop = 0;
        int maxPopYear = START_YEAR;
        for(int i = 0; i < pop.length; i++) {
            countPop += pop[i];
            if(countPop > maxPop) {
                maxPop = countPop;
                maxPopYear = i + START_YEAR;
            }
        }

        return maxPopYear;
    }
}
