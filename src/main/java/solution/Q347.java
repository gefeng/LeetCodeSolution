package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


@Problem(
        title = "Top K Frequent Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/top-k-frequent-elements/"
)
public class Q347 {
    class Pair<K, V> {
        public K key;
        public V val;
        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Pair<Integer, Integer>> pQueue = new PriorityQueue<>((p1, p2) -> p1.val - p2.val);
        for(int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for(int key : map.keySet()) {
            pQueue.add(new Pair<>(key, map.get(key)));
            if(pQueue.size() > k)
                pQueue.poll();
        }

        int[] ans = new int[k];
        for(int i = 0; i < k; i++) {
            ans[i] = pQueue.poll().key;
        }
        return ans;
    }
}
