package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Groups of Special-Equivalent Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/groups-of-special-equivalent-strings/"
)
public class Q893 {
    public int numSpecialEquivGroups(String[] A) {
        Set<String> groups = new HashSet<>();
        for(String s : A) {
            int[] count = new int[52];
            for(int i = 0; i < s.length(); i++)
                count[s.charAt(i) - 'a' + 26 * (i % 2)]++;
            groups.add(Arrays.toString(count));
        }
        return groups.size();
    }
}
