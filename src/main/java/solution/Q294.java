package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Flip Game II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/flip-game-ii/"
)
public class Q294 {
    /*
    * if opponent lose we win the same for the opponent
    * both players play optimally
    * */
    public boolean canWin(String currentState) {
        return dfs(currentState.toCharArray(), new HashMap<>());
        //return dfs(currentState.toCharArray(), 0, new HashMap<>(), new HashMap<>());
    }

    private boolean dfs(char[] s, Map<String, Boolean> dp) {
        String key = new String(s);

        Boolean val = dp.get(key);
        if(val != null) {
            return dp.get(key);
        }

        boolean win = false;
        for(int i = 1; i < s.length; i++) {
            if(s[i - 1] == '+' && s[i] == '+') {
                s[i - 1] = '-';
                s[i] = '-';
                boolean opWin = dfs(s, dp);
                s[i - 1] = '+';
                s[i] = '+';
                if(!opWin) {
                    win = true;
                    break;
                }
            }
        }

        dp.put(key, win);
        return win;
    }

    private boolean dfs(char[] s, int turn, Map<String, Boolean> dp1, Map<String, Boolean> dp2) {
        if(!canMove(s)) {
            return turn == 1;
        }

        String key = new String(s);

        if(turn == 0) {
            if(dp1.containsKey(key)) {
                return dp1.get(key);
            }

            for(int i = 1; i < s.length; i++) {
                if(s[i] == '+' && s[i - 1] == '+') {
                    s[i] = '-';
                    s[i - 1] = '-';
                    if(dfs(s, (turn + 1) % 2, dp1, dp2)) {
                        dp1.put(key, true);
                        s[i] = '+';
                        s[i - 1] = '+';
                        return true;
                    }
                    s[i] = '+';
                    s[i - 1] = '+';
                }
            }
            dp1.put(key, false);
            return false;
        } else {
            if(dp2.containsKey(key)) {
                return dp2.get(key);
            }

            for(int i = 1; i < s.length; i++) {
                if(s[i] == '+' && s[i - 1] == '+') {
                    s[i] = '-';
                    s[i - 1] = '-';
                    if(!dfs(s, (turn + 1) % 2, dp1, dp2)) {
                        dp2.put(key, false);
                        s[i] = '+';
                        s[i - 1] = '+';
                        return false;
                    }
                    s[i] = '+';
                    s[i - 1] = '+';
                }
            }
            dp2.put(key, true);
            return true;
        }
    }

    private boolean canMove(char[] s) {
        for(int i = 1; i < s.length; i++) {
            if(s[i] == '+' && s[i - 1] == '+') {
                return true;
            }
        }
        return false;
    }
}
