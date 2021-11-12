package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Adding Two Negabinary Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/adding-two-negabinary-numbers/"
)
public class Q1073 {
    /**
     * Time:  O(max(M, N))
     * Space: O(max(M, N))
     * */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();
        int m = arr1.length;
        int n = arr2.length;
        int i = m - 1;
        int j = n - 1;
        int c = 0;
        while(i >= 0 || j >= 0 || c != 0) {
            int x = i < 0 ? 0 : arr1[i];
            int y = j < 0 ? 0 : arr2[j];
            int z = Math.abs(x + y + c) % 2;
            if(x + y + c > 1) {
                c = -1;
            } else if(x + y + c < 0) {
                c = 1;
            } else {
                c = 0;
            }
            i--;
            j--;
            res.add(z);
        }

        while(res.size() > 1 && res.get(res.size() - 1) == 0) {
            res.remove(res.size() - 1);
        }

        int len = res.size();
        int[] ans = new int[len];
        for(int k = len - 1; k >= 0; k--) {
            ans[k] = res.get(len - k - 1);
        }
        return ans;
    }
}
