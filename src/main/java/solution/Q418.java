package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sentence Screen Fitting",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/sentence-screen-fitting/"
)
public class Q418 {
    /**
     * Interesting problem, the solution is very elegant.
     * I can only come up with a brute force solution by testing
     * word one by one.
     * It turns out that we just need to know if current line is
     * started with sentence[i] then which sentence[j] the next line start
     * with. We calculate j for each i. Meanwhile if bypass the last
     * word, increase counter for current i since that's a whole sentence.
     *
     * Then we can just iterate over rows to accumulate result and jump
     * to next index as we pre-calculated.
     *
     * Time:  O(N * C + R)
     * Space: O(N)
     * */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int res = 0;
        int[] nextIdx = new int[n];
        int[] times = new int[n];

        for(int i = 0; i < n; i++) {
            int j = i;
            int c = 0;

            while(c + sentence[j].length() - 1 < cols) {
                c += sentence[j].length() + 1;

                if(j == n - 1) {
                    times[i]++;
                }

                j = (j + 1) % n;
            }

            nextIdx[i] = j;
        }

        for(int r = 0, i = 0; r < rows; r++) {
            res += times[i];
            i = nextIdx[i];
        }

        return res;
    }
}
