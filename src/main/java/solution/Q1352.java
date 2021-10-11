package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Product of the Last K Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/product-of-the-last-k-numbers/"
)
public class Q1352 {
    List<Integer> prod;
    public Q1352() {
        prod = new ArrayList<>();
        prod.add(1);
    }

    public void add(int num) {
        if(num == 0) {
            prod = new ArrayList<>();
            prod.add(1);
        } else {
            prod.add(prod.get(prod.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        if(k > prod.size() - 1) {
            return 0;
        }

        return prod.get(prod.size() - 1) / prod.get(prod.size() - 1 - k);
    }
}
