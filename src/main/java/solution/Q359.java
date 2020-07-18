package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Logger Rate Limiter",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/logger-rate-limiter/"
)
public class Q359 {
    HashMap<String, Integer> map;
    /** Initialize your data structure here. */
    public Q359() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer prev = map.get(message);
        if(prev == null) {
            map.put(message, timestamp);
            return true;
        }
        else {
            if(timestamp - prev >= 10) {
                map.put(message, timestamp);
                return true;
            }
        }
        return false;
    }
}
