package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

@Problem(
        title = "K-Similar Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/k-similar-strings/"
)
public class Q854 {
    /**
     * I think the time complexity of this problem is exponential even apply BFS to find the shortest "path"
     *
     * Time:  O(3 ^ N * N)
     * Space: O(3 ^ N)
     * */
    class Solution {
        public int kSimilarity(String s1, String s2) {
            int n = s1.length();
            int ans = 0;
            char[] a = s1.toCharArray();
            char[] b = s2.toCharArray();
            Queue<char[]> q = new ArrayDeque<>();

            q.offer(a);

            while(!q.isEmpty()) {
                int sz = q.size();
                for(int i = 0; i < sz; i++) {
                    char[] x = q.poll();

                    if(Arrays.equals(x, b)) {
                        return ans;
                    }

                    int d = 0;
                    for(int j = 0; j < n; j++) {
                        if(x[j] != b[j]) {
                            d = j;
                            break;
                        }
                    }

                    for(int j = d + 1; j < n; j++) {
                        if(x[j] == b[d]) {
                            char[] nx = Arrays.copyOf(x, n);
                            nx[j] = nx[d];
                            nx[d] = b[d];
                            q.offer(nx);
                        }
                    }
                }
                ans++;
            }

            return -1;
        }
    }
}
