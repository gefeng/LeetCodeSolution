package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Shopping Offers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/shopping-offers/"
)
public class Q638 {
    int ans = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int m = special.size();

        for(int i = 0; i < n; i++) {
            List<Integer> l = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                l.add(j == i ? 1 : 0);
            }
            l.add(price.get(i));
            special.add(l);
        }

        dfs(special, 0, needs.stream().mapToInt(a -> a.intValue()).toArray(), new int[n], 0);

        return ans;
    }

    private void dfs(List<List<Integer>> special, int idx, int[] needs, int[] cur, int paid) {
        if(Arrays.equals(needs, cur)) {
            ans = Math.min(ans, paid);
            return;
        }

        if(idx == special.size()) return;

        int n = needs.length;
        dfs(special, idx + 1, needs, Arrays.copyOf(cur, n), paid);

        List<Integer> offer = special.get(idx);
        int[] nxt = Arrays.copyOf(cur, n);
        while(true) {
            boolean isOk = true;
            for(int i = 0; i < n; i++) {
                nxt[i] += offer.get(i);
                if(nxt[i] > needs[i]) {
                    isOk = false;
                    break;
                }
            }

            if(isOk) {
                paid += offer.get(n);
                dfs(special, idx + 1, needs, Arrays.copyOf(nxt, n), paid);
            } else {
                break;
            }
        }
    }
}
