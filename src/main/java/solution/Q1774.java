package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Closest Dessert Cost",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/closest-dessert-cost/"
)
public class Q1774 {
    /*
    * recursively found all possibilities.
    * */
    private int closest = Integer.MAX_VALUE;
    private int minDiff = Integer.MAX_VALUE;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        for(int i = 0; i < baseCosts.length; i++) {
            addToppings(toppingCosts, 0, baseCosts[i], target);
        }

        return closest;
    }

    private void addToppings(int[] toppingCosts, int idx, int cost, int target) {
        if(idx == toppingCosts.length) {
            int diff = Math.abs(target - cost);
            if(diff <= minDiff) {
                closest = diff == minDiff ? Math.min(closest, cost) : cost;
                minDiff = diff;
            }

            return;
        }

        for(int i = 0; i < 3; i++) {
            addToppings(toppingCosts, idx + 1, cost + toppingCosts[idx] * i, target);
        }
    }
}
