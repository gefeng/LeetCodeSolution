package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Boats to Save People",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/boats-to-save-people/"
)
public class Q881 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        int ans = 0;

        Arrays.sort(people);

        for(int l = 0, r = n - 1; l <= r; ) {
            int x = people[l];
            int y = people[r];
            if(x + y <= limit) {
                ans += 1;
                l++;
                r--;
            } else {
                ans += 1;
                r--;
            }
        }

        return ans;
    }
}
