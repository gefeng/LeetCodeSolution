package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "People Whose List of Favorite Companies Is Not a Subset of Another List",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/"
)
public class Q1452 {
    /**
     * Time:  O(N ^ 2 * M)
     * Space: O(N * M)
     * */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        List<Integer> ans = new ArrayList<>();
        List<Set<String>> fc = new ArrayList<>();

        for(List<String> l : favoriteCompanies) {
            fc.add(new HashSet<>(l));
        }

        for(int i = 0; i < n; i++) {
            boolean unique = true;

            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }

                boolean subset = true;
                List<String> me = favoriteCompanies.get(i);
                Set<String> other = fc.get(j);
                for(String s : me) {
                    if(!other.contains(s)) {
                        subset = false;
                        break;
                    }
                }
                if(subset) {
                    unique = false;
                    break;
                }
            }

            if(unique) {
                ans.add(i);
            }
        }

        return ans;
    }
}
