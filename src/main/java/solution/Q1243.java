package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Array Transformation",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/array-transformation/"
)
public class Q1243 {
    /**
     * Time:  O(N * max(diff))
     * Space: O(N)
     * */
    public List<Integer> transformArray(int[] arr) {
        int[] pre = arr;
        int[] cur = arr;
        int n = arr.length;

        while(true) {
            cur = new int[n];
            cur[0] = pre[0];
            cur[n - 1] = pre[n - 1];
            boolean end = true;
            for(int i = 1; i < n - 1; i++) {
                if(pre[i] > pre[i - 1] && pre[i] > pre[i + 1]) {
                    cur[i] = pre[i] - 1;
                    end = false;
                } else if(pre[i] < pre[i - 1] && pre[i] < pre[i + 1]) {
                    cur[i] = pre[i] + 1;
                    end = false;
                } else {
                    cur[i] = pre[i];
                }
            }

            pre = cur;

            if(end) {
                break;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ans.add(pre[i]);
        }

        return ans;
    }
}
