package solution;

import annotations.Problem;
import data_structure.TreeNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Most Frequent Subtree Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/most-frequent-subtree-sum/"
)
public class Q508 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        postorder(root, freqMap);

        int max = 0;
        for(int freq : freqMap.values()) {
            max = Math.max(max, freq);
        }

        int cnt = 0;
        for(int freq : freqMap.values()) {
            cnt = freq == max ? cnt + 1 : cnt;
        }

        int[] res = new int[cnt];
        int i = 0;
        for(Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
            if(e.getValue() == max) {
                res[i++] = e.getKey();
            }
        }

        return res;
    }

    private int postorder(TreeNode root, Map<Integer, Integer> freqMap) {
        if(root == null) {
            return 0;
        }

        int sumL = postorder(root.left, freqMap);
        int sumR = postorder(root.right, freqMap);
        int sum = sumL + sumR + root.val;

        freqMap.put(sum, freqMap.getOrDefault(sum, 0) + 1);

        return sum;
    }
}
