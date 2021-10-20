package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Number of Burgers with No Waste of Ingredients",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/"
)
public class Q1276 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ans = new ArrayList<>();

        int a = tomatoSlices;
        int b = cheeseSlices;
        if((a - 2 * b) % 2 != 0) {
            return ans;
        }

        int x = (a - 2 * b) / 2;
        int y = b - x;
        if(x < 0 || y < 0) {
            return ans;
        }

        ans.add(x);
        ans.add(y);

        return ans;
    }
}
