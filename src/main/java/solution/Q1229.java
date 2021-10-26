package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "Meeting Scheduler",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/meeting-scheduler/"
)
public class Q1229 {
    /**
     * Time:  O(M * logM + N * logN)
     * Space: O(logM + logN)
     * */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int m = slots1.length;
        int n = slots2.length;
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));

        int i = 0;
        int j = 0;
        while(i < m && j < n) {
            int[] s1 = slots1[i];
            int[] s2 = slots2[j];
            if(s2[0] >= s1[1]) {
                i++;
            } else if(s1[0] >= s2[1]) {
                j++;
            } else {
                int l = Math.max(s1[0], s2[0]);
                int r = Math.min(s1[1], s2[1]);
                if(l + duration <= r) {
                    return Arrays.asList(l, l + duration);
                }

                if(s1[1] == s2[1]) {
                    i++;
                    j++;
                } else if(s1[1] > s2[1]) {
                    j++;
                } else {
                    i++;
                }
            }
        }

        return new ArrayList<>();
    }
}
