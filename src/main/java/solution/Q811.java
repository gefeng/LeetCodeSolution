package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Subdomain Visit Count",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/subdomain-visit-count/"
)
public class Q811 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for(String cpd : cpdomains) {
            String[] arr = cpd.split(" ");
            int cnt = Integer.parseInt(arr[0]);
            String[] dom = arr[1].split("\\.");
            String cur = "";
            for(int i = dom.length - 1; i >= 0; i--) {
                if(!cur.isEmpty()) cur = dom[i] + "." + cur;
                else cur = dom[i];

                map.put(cur, map.getOrDefault(cur, 0) + cnt);
            }
        }


        for(String k : map.keySet()) {
            int f = map.get(k);
            ans.add(Integer.toString(f) + " " + k);
        }

        return ans;
    }
}
