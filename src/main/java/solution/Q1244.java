package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Design A Leaderboard",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/design-a-leaderboard/"
)
public class Q1244 {
    Map<Integer, Integer> scoMap;
    TreeMap<Integer, Integer> cntMap;
    public Q1244() {
        scoMap = new HashMap<>();
        cntMap = new TreeMap<>(Comparator.reverseOrder());
    }

    /**
     * Time:  O(logN)
     * */
    public void addScore(int playerId, int score) {
        score = scoMap.getOrDefault(playerId, 0) + score;
        Integer pre = scoMap.put(playerId, score);
        if(pre != null) {
            int cnt = cntMap.get(pre);
            if(cnt == 1) {
                cntMap.remove(pre);
            } else {
                cntMap.put(pre, cnt - 1);
            }
        }


        cntMap.put(score, cntMap.getOrDefault(score, 0) + 1);
    }

    /**
     * Time:  O(K)
     * */
    public int top(int K) {
        int sum = 0;
        for(Map.Entry<Integer, Integer> e : cntMap.entrySet()) {
            int sco = e.getKey();
            int cnt = e.getValue();
            while(K != 0 && cnt != 0) {
                sum += sco;
                K--;
                cnt--;
            }
            if(K == 0) {
                break;
            }
        }
        return sum;
    }

    /**
     * Time:  O(logN)
     * */
    public void reset(int playerId) {
        int score = scoMap.get(playerId);
        scoMap.remove(playerId);

        int cnt = cntMap.get(score);
        if(cnt == 1) {
            cntMap.remove(score);
        } else {
            cntMap.put(score, cnt - 1);
        }
    }
}
