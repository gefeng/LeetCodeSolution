package solution.weekly285;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Longest Substring of One Repeating Character",
        difficulty = QDifficulty.HARD,
        tag = QTag.ORDERED_MAP,
        url = "https://leetcode.com/contest/weekly-contest-285/problems/longest-substring-of-one-repeating-character/"
)
public class Q2213 {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int m = queryCharacters.length();
        char[] arr = s.toCharArray();
        int[] ans = new int[m];
        TreeMap<Integer, Integer> intervals = new TreeMap<>();
        TreeMap<Integer, Integer> cntLen = new TreeMap<>();

        for(int i = 0; i < n; ) {
            int j = i;
            while(j < n && arr[j] == arr[i]) {
                j++;
            }

            int len = j - i;
            intervals.put(i, j - 1);
            cntLen.put(len, cntLen.getOrDefault(len, 0) + 1);

            i = j;
        }

        for(int i = 0; i < m; i++) {
            char nc = queryCharacters.charAt(i);
            int idx = queryIndices[i];

            // a a [a] a;  a a [b] c c
            Integer fk = intervals.floorKey(idx);
            int l = fk;
            int r = intervals.get(fk);
            int oLen = r - l + 1;

            if(l == r) {
                if(idx > 0 && nc == arr[idx - 1]) {
                    Integer lk = intervals.lowerKey(idx);
                    int preL = lk, preR = intervals.get(lk);
                    int preLen = preR - preL + 1;
                    // merge to previous interval
                    decrease(cntLen, preLen);
                    increase(cntLen, preLen + 1);
                    intervals.put(lk, idx);
                    decrease(cntLen, 1);
                    intervals.remove(fk);
                } else {
                    // nothing happen
                    // a a [b] -> [d] c c
                }

                if(idx < n - 1 && nc == arr[idx + 1]) {
                    fk = intervals.floorKey(idx);
                    l = fk;
                    r = intervals.get(fk);
                    oLen = r - l + 1;
                    int nxtL = idx + 1;
                    int nxtR = intervals.get(nxtL);
                    int nxtLen = nxtR - nxtL + 1;

                    // merge to next interval
                    decrease(cntLen, oLen);
                    decrease(cntLen, nxtLen);
                    increase(cntLen, oLen + nxtLen);

                    intervals.remove(nxtL);
                    intervals.put(l, nxtR);
                } else {
                    // nothing happen
                }
            } else {
                // b b a a [a] a c c
                if(nc != arr[idx]) {
                    // update left
                    if(l != idx) {
                        intervals.put(fk, idx - 1);
                        increase(cntLen, idx - l);
                    }

                    // update right
                    if(r != idx) {
                        intervals.put(idx + 1, r);
                        increase(cntLen, r - idx);
                    }

                    // update middle
                    if(idx == r && idx + 1 < n && nc == arr[idx + 1]) {
                        int nxtL = idx + 1;
                        int nxtR = intervals.get(nxtL);
                        int nxtLen = nxtR - nxtL + 1;
                        intervals.remove(nxtL);
                        intervals.put(idx, nxtR);
                        decrease(cntLen, nxtLen);
                        increase(cntLen, nxtLen + 1);
                    } else if(idx == l && idx > 0 && nc == arr[idx - 1]) {
                        // b b [c] -> [b] c c
                        Integer lk = intervals.lowerKey(idx);
                        int preL = lk, preR = intervals.get(lk);
                        int preLen = preR - preL + 1;
                        intervals.put(lk, idx);
                        intervals.remove(fk);
                        decrease(cntLen, preLen);
                        increase(cntLen, preLen + 1);
                    } else {
                        intervals.put(idx, idx);
                        increase(cntLen, 1);
                    }

                    decrease(cntLen, oLen);
                }
            }
            ans[i] = cntLen.lastKey();
            arr[idx] = nc;
        }

        return ans;
    }

    private void increase(TreeMap<Integer, Integer> map, int len) {
        map.put(len, map.getOrDefault(len, 0) + 1);
    }

    private void decrease(TreeMap<Integer, Integer> map, int len) {
        int f = map.get(len);
        if(f == 1) {
            map.remove(len);
        } else {
            map.put(len, f - 1);
        }
    }
}
