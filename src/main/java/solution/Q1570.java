package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Dot Product of Two Sparse Vectors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/dot-product-of-two-sparse-vectors/"
)
public class Q1570 {
    /*
    * 这题我理解的是当两个vector都是sparse的是时候，hashmap的方案是可行的，
    * 因为不为0的element个数非常小，map不会特别大。
    * 但是如果有一个vector不是sparse的话，就会有performance issue，map
    * 会变得非常大，collision增多并且重复计算hashing
    * */
    List<int[]> nonzero;
    Q1570(int[] nums) {
        nonzero = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nonzero.add(new int[] { i, nums[i] });
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(Q1570 vec) {
        int prod = 0;
        int i = 0;
        int j = 0;
        int len1 = nonzero.size();
        int len2 = vec.nonzero.size();
        while(i < len1 && j < len2) {
            int[] x = nonzero.get(i);
            int[] y = vec.nonzero.get(j);
            if(x[0] == y[0]) {
                prod += x[1] * y[1];
                i++;
                j++;
            } else if(x[0] < y[0]) {
                i++;
            } else {
                j++;
            }
        }

        return prod;
    }
}
