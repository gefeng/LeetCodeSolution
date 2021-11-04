package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Invalid Transactions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/invalid-transactions/"
)
public class Q1169 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        int n = transactions.length;

        List<String[]> cand = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String[] t = transactions[i].split(",");
            cand.add(t);
        }

        for(int i = 0; i < n; i++) {
            String[] t1 = cand.get(i);

            int amount = Integer.parseInt(t1[2]);
            if(amount > 1000) {
                ans.add(String.join(",", t1));
                continue;
            }

            int time1 = Integer.parseInt(t1[1]);
            String name1 = t1[0];
            String city1 = t1[3];

            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }

                String[] t2 = cand.get(j);

                int time2 = Integer.parseInt(t2[1]);
                String name2 = t2[0];
                String city2 = t2[3];

                if(Math.abs(time1 - time2) <= 60 && !city1.equals(city2) && name1.equals(name2)) {
                    ans.add(String.join(",", t1));
                    break;
                }
            }
        }

        return ans;
    }
}
