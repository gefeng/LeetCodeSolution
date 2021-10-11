package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Tweet Counts Per Frequency",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/tweet-counts-per-frequency/"
)
public class Q1348 {
    /**
     * Time:  O()
     * Space: O(N)
     * */
    Map<String, TreeMap<Long, Integer>> map;
    Map<String, Integer> chunkSize;

    public Q1348() {
        map = new HashMap<>();
        chunkSize = new HashMap<>() {
            {
                put("minute", 60);
                put("hour", 3600);
                put("day", 86400);
            }
        };
    }

    /**
     * Time:  O(logN)
     * */
    public void recordTweet(String tweetName, int time) {
        TreeMap<Long, Integer> e = map.computeIfAbsent(tweetName, k -> new TreeMap<>());
        e.put((long)time, e.getOrDefault((long)time, 0) + 1);
    }

    /**
     * Time:  O(#chunks * N * logN)
     * */
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int chu = chunkSize.get(freq);
        TreeMap<Long, Integer> e = map.get(tweetName);

        List<Integer> res = new ArrayList<>();

        long st = startTime;
        while(true) {
            long et = Math.min(st + chu, (long)endTime + 1);

            int cnt = 0;
            for(int v : e.subMap(st, et).values()) {
                cnt += v;
            }
            res.add(cnt);

            st = et;
            if(st > endTime) {
                break;
            }
        }
        return res;
    }
}
