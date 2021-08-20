package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@Problem(
        title = "Minimum Genetic Mutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/minimum-genetic-mutation/"
)
public class Q433 {
    /**
     * Similar to "Open Lock"
     *
     * Time:  O(4 ^ 8) = O(1)
     * Space: O(4 ^ 8) = O(1)
     * */
    private static final char[] genChar = new char[] {'A', 'C', 'G', 'T'};
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        Set<String> validSet = new HashSet<>();

        for(String s : bank) {
            validSet.add(s);
        }

        queue.offer(start);
        seen.add(start);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                String g = queue.poll();

                if(g.equals(end)) {
                    return step;
                }

                char[] ga = g.toCharArray();

                for(int j = 0; j < 8; j++) {
                    char cur = ga[j];
                    for(int k = 0; k < 4; k++) {
                        char next = genChar[k];

                        if(cur == next) {
                            continue;
                        }

                        ga[j] = next;
                        String ng = new String(ga);

                        if(validSet.contains(ng) && !seen.contains(ng)) {
                            queue.offer(ng);
                            seen.add(ng);
                        }

                        ga[j] = cur;
                    }
                }
            }

            step++;
        }

        return -1;
    }
}
