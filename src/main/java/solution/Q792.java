package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Number of Matching Subsequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/number-of-matching-subsequences/"
)
public class Q792 {
    public int numMatchingSubseq(String s, String[] words) {
        return bucketSolution(s, words);
        //return binarySearchSolution(s, words);
    }

    private int bucketSolution(String s, String[] words) {
        int m = s.length(), n = words.length, cnt = 0;
        Queue<int[]>[] buckets = new Queue[26];

        for(int i = 0; i < 26; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        for(int i = 0; i < n; i++) {
            buckets[words[i].charAt(0) - 'a'].offer(new int[] {i, 0});
        }

        for(int i = 0; i < m; i++) {
            Queue<int[]> b = buckets[s.charAt(i) - 'a'];
            int size = b.size();

            for(int j = 0; j < size; j++) {
                int[] item = b.poll();
                if(item[1] + 1 == words[item[0]].length()) {
                    cnt++;
                } else {
                    buckets[words[item[0]].charAt(++item[1]) - 'a'].offer(item);
                }
            }
        }

        return cnt;
    }

    private int binarySearchSolution(String s, String[] words) {
        int cnt = 0, n = s.length();
        List<Integer>[] map = new List[26];

        for(int i = 0; i < 26; i++) {
            map[i] = new ArrayList<>();
        }

        // Collect indices and build lookup table
        for(int i = 0; i < n; i++) {
            map[s.charAt(i) - 'a'].add(i);
        }

        for(String word : words) {
            if(isSubseq(map, word)) {
                cnt++;
            }
        }

        return cnt;
    }

    private boolean isSubseq(List<Integer>[] map, String word) {
        int n = word.length();
        int k = -1;

        for(int i = 0; i < n; i++) {
            List<Integer> l = map[word.charAt(i) - 'a'];

            if(l.size() == 0) {
                return false;
            }

            k = ceiling(l, k);
            if(k == -1) {
                return false;
            }
        }

        return true;
    }

    private int ceiling(List<Integer> l, int k) {
        int lo = 0, hi = l.size() - 1;
        int ck = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int i = l.get(mid);
            if(i > k) {
                ck = i;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ck;
    }
}
