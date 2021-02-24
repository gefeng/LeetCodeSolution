package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Accounts Merge",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/accounts-merge/"
)
public class Q721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> emailToName = new HashMap<>();
        HashMap<String, Integer> emailToId = new HashMap<>();
        int[] parent = new int[10 * 1000];
        for(int i = 0; i < parent.length; i++)
            parent[i] = i;

        int id = 0;
        for(List<String> account : accounts) {
            String name = account.get(0);
            for(int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if(!emailToId.containsKey(email))
                    emailToId.put(email, id++);
                if(!emailToName.containsKey(email))
                    emailToName.put(email, name);
                union(parent, emailToId.get(account.get(1)), emailToId.get(email));
            }
        }

        HashMap<Integer, List<String>> merged = new HashMap<>();
        for(String email : emailToId.keySet()) {
            int group = find(parent, emailToId.get(email));
            if(!merged.containsKey(group))
                merged.put(group, new LinkedList<>());
            merged.get(group).add(email);
        }

        for(Integer key : merged.keySet()) {
            List<String> emails = merged.get(key);
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));
        }

        return new ArrayList<>(merged.values());
    }

    private void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = parent[find(parent, j)];
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
