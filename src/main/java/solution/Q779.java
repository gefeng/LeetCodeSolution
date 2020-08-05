package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "K-th Symbol in Grammar",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/k-th-symbol-in-grammar/"
)
public class Q779 {
    public int kthGrammar(int N, int K) {
        if(N == 1)
            return 0;
        int ancestor = kthGrammar(N - 1, (K + 1) / 2);
        int first = ancestor == 0 ? 0 : 1;
        int second = ancestor == 0 ? 1 : 0;
        return K % 2 == 0 ? second : first;
    }
}
