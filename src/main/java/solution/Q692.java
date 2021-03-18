package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Problem(
        title = "Top K Frequent Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/top-k-frequent-words/"
)
public class Q692 {
    public List<String> topKFrequent(String[] words, int k) {
        return priorityQueueSolution(words, k);
    }

    private List<String> priorityQueueSolution(String[] words, int k) {
        List<String> ans = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for(String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> pQueue = new PriorityQueue<>((a, b) -> {
            if(map.get(a) == map.get(b))
                return b.compareTo(a);
            return map.get(a) - map.get(b);
        });

        for(String key : map.keySet()) {
            pQueue.offer(key);
            if(pQueue.size() > k)
                pQueue.poll();
        }

        while(!pQueue.isEmpty())
            ans.add(0, pQueue.poll());

        return ans;
    }
}