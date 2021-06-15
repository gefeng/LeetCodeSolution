package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Design Authentication Manager",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/design-authentication-manager/"
)
public class Q1797 {
    private class Session {
        String id;
        int expireAt;
        Session(String id, int expireAt) {
            this.id = id;
            this.expireAt = expireAt;
        }
    }
    private int timeToLive;
    private Map<String, Session> sMap;
    private Queue<Session> minHeap;
    public Q1797(int timeToLive) {
        this.timeToLive = timeToLive;
        sMap = new HashMap();
        minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.expireAt, b.expireAt));
    }

    public void generate(String tokenId, int currentTime) {
        cleanUp(currentTime);

        Session s = new Session(tokenId, currentTime + timeToLive);
        sMap.put(s.id, s);
        minHeap.offer(s);
    }

    public void renew(String tokenId, int currentTime) {
        cleanUp(currentTime);

        Session s = sMap.get(tokenId);
        if(s != null) {
            minHeap.remove(s);
            s.expireAt = currentTime + timeToLive;
            minHeap.offer(s);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        cleanUp(currentTime);

        return sMap.size();
    }

    private void cleanUp(int currentTime) {
        while(!minHeap.isEmpty() && minHeap.peek().expireAt <= currentTime) {
            sMap.remove(minHeap.poll().id);
        }
    }
}
