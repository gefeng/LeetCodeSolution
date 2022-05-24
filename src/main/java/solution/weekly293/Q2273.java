package solution.weekly293;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find Resultant Array After Removing Anagrams",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/contest/weekly-contest-293/problems/find-resultant-array-after-removing-anagrams/"
)
public class Q2273 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();

        Deque<String> stack = new ArrayDeque<>();

        for(String s : words) {
            if(!stack.isEmpty() && isOk(s, stack.peek())) {
                continue;
            } else {
                stack.push(s);
            }
        }

        ans = new ArrayList<>(stack);
        Collections.reverse(ans);
        return ans;
    }

    private boolean isOk(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if(m == n) {
            int[] cnt1 = new int[26];
            int[] cnt2 = new int[26];

            for(int i = 0; i < n; i++) {
                cnt1[s1.charAt(i) - 'a']++;
                cnt2[s2.charAt(i) - 'a']++;
            }

            for(int i = 0; i < 26; i++) {
                if(cnt1[i] != cnt2[i]) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
