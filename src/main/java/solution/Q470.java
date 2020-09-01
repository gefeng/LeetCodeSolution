package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Implement Rand10() Using Rand7()",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.REJECTION_SAMPLING,
        url = "https://leetcode.com/problems/implement-rand10-using-rand7/"
)
public class Q470 {
    public int rand10() {
        int idx = 0;
        do {
            int row = rand7();
            int col = rand7();
            idx = col + (row - 1) * 7;
        } while(idx > 40);

        return (idx - 1) % 10 + 1;
    }

    // dummy function
    private int rand7() {
        return 0;
    }
}
