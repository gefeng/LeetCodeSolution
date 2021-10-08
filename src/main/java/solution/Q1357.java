package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Apply Discount Every n Orders",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/apply-discount-every-n-orders/"
)
public class Q1357 {
    /**
     * Time:  O(1)
     * Space: O(N)
     * */
    private int n;
    private int d;
    private int[] prices;
    private int cnt;
    public Q1357(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.d = discount;
        this.cnt = 0;

        this.prices = new int[201];
        for(int i = 0; i < products.length; i++) {
            this.prices[products[i]] = prices[i];
        }
    }

    public double getBill(int[] product, int[] amount) {
        double bill = 0;

        cnt++;
        for(int i = 0; i < product.length; i++) {
            bill += (prices[product[i]] * amount[i]);
        }

        if(cnt == n) {
            bill = bill * ((double)(100 - d) / 100);
            cnt = 0;
        }

        return bill;
    }
}
