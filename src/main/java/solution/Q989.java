package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Add to Array-Form of Integer",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/add-to-array-form-of-integer/"
)
public class Q989 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int n = num.length;
        int c = 0;
        int i = n - 1;
        while(i >= 0 || k != 0) {
            int x = i < 0 ? 0 : num[i];
            int y = k == 0 ? 0 : k % 10;

            ans.add((x + y + c) % 10);
            c = (x + y + c) / 10;
            i--;
            k /= 10;
        }

        if(c != 0) {
            ans.add(c);
        }

        Collections.reverse(ans);

        return ans;
    }
}
