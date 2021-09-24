package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Destination City",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/destination-city/"
)
public class Q1436 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String destCity(List<List<String>> paths) {
        String ans = "";
        Set<String> from = new HashSet<>();

        for(List<String> l : paths) {
            from.add(l.get(0));
        }

        for(List<String> l : paths) {
            if(!from.contains(l.get(1))) {
                ans = l.get(1);
                break;
            }
        }

        return ans;
    }
}
