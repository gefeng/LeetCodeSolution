package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Making File Names Unique",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/making-file-names-unique/"
)
public class Q1487 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        String[] res = new String[n];
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String s = names[i];

            if(map.containsKey(s)) {
                int k = map.get(s);
                String ss = "";
                do {
                    k++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(s).append("(").append(k).append(")");
                    ss = sb.toString();
                } while(map.containsKey(ss));


                map.put(ss, 0);
                map.put(s, k);

                res[i] = ss;
            } else {
                map.put(s, 0);
                res[i] = s;
            }
        }

        return res;
    }
}
