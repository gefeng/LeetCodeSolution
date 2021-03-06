package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;


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
    // O(nlogn)
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

    // O(N)
    private int[] quickSelectSolution(int[] nums, int k) {
        // build frequency map
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        // build array for quick select
        int[] unique = new int[map.size()];
        int i = 0;
        for(int key : map.keySet())
            unique[i++] = key;

        return quickSelect(unique, k, map, 0, unique.length - 1);
    }

    private int[] quickSelect(int[] nums, int k, HashMap<Integer, Integer> map, int start, int end) {
        int pivot = partition(nums, map, start, end);

        if(k - 1 == pivot)
            return Arrays.copyOfRange(nums, 0, k);
        else if(k - 1 < pivot)
            return quickSelect(nums, k, map, start, pivot - 1);
        else
            return quickSelect(nums, k, map, pivot + 1, end);
    }

    private int partition(int[] nums, HashMap<Integer, Integer> map, int start, int end) {
        Random rand = new Random();
        int randIdx = rand.nextInt(end - start + 1) + start;
        swap(nums, randIdx, end);

        int i = start;
        int j = start;
        for(; i < end; i++) {
            int freq1 = map.get(nums[end]);
            int freq2 = map.get(nums[i]);
            if(freq2 >= freq1) {
                swap(nums, i, j);
                j++;
            }
        }
        swap(nums, j, end);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
