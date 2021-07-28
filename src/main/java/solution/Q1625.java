package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Lexicographically Smallest String After Applying Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/"
)
public class Q1625 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String findLexSmallestString(String s, int a, int b) {
        String res = s;
        int n = s.length();
        Queue<String> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();

        queue.offer(s);
        seen.add(s);

        while(!queue.isEmpty()) {
            String curr = queue.poll();

            if(curr.compareTo(res) < 0) {
                res = curr;
            }

            String rot = curr.substring(n - b, n) + curr.substring(0, n - b);
            if(!seen.contains(rot)) {
                queue.offer(rot);
                seen.add(rot);
            }

            char[] ca = curr.toCharArray();
            for(int i = 1; i < n; i += 2) {
                ca[i] = (char)((ca[i] - '0' + a) % 10 + '0');
            }
            String rep = new String(ca);
            if(!seen.contains(rep)) {
                queue.offer(rep);
                seen.add(rep);
            }
        }

        return res;
    }
}
