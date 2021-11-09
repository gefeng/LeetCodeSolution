package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Path In Zigzag Labelled Binary Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/"
)
public class Q1104 {
    /**
     * Time:  O(logN * logN)
     * Space: O(logN)
     * */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> ans = new ArrayList<>();

        while(label != 0) {
            ans.add(label);
            label = label % 2 == 0 ? label / 2 : (label - 1) / 2;
        }

        int n = ans.size();
        for(int i = 1; i < n; i += 2) {
            int lv = n - i;
            int tot = 1;
            for(int j = 1; j < lv; j++) {
                tot *= 2;
            }

            int d = ans.get(i) - tot;
            ans.set(i, tot + tot - 1 - d);
        }

        for(int l = 0, r = n - 1; l < r; l++, r--) {
            int temp = ans.get(l);
            ans.set(l, ans.get(r));
            ans.set(r, temp);
        }

        return ans;
    }
}
