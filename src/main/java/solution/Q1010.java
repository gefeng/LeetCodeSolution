package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Pairs of Songs With Total Durations Divisible by 60",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/"
)
public class Q1010 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    /*
    * two sum hidden
    * tricky is map all the number to 0-59;
    * exception will be 60, 120, 180... mod 60 would be 0 and 60 - 0 is out of range so mod again
    * */
    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        int total = 0;
        for(int t : time) {
            t = t % 60;
            total += count[(60 - t) % 60];
            count[t]++;
        }

        return total;
    }
}
