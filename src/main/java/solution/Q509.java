package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Fibonacci Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY
)
public class Q509 {
    public int fibIterative(int N) {
        if(N == 0 || N == 1) {
            return N;
        }
        int p1 = 0;
        int p2 = 1;
        int retVal = 0;
        for(int i = 2; i <= N; ++i) {
            retVal = (p1 + p2);
            p1 = p2;
            p2 = retVal;
        }

        return retVal;
    }

    public int fibRecursive(int N) {
        if(N == 0 || N == 1) {
            return N;
        }

        return fibRecursive(N - 1) + fibRecursive(N - 2);
    }
}
