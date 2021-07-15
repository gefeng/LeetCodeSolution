package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Repeated DNA Sequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/repeated-dna-sequences/"
)
public class Q187 {
    public List<String> findRepeatedDnaSequences(String s) {
        return bitMaskSol(s);
    }

    private List<String> hashSetSol(String s) {
        int n = s.length();
        Set<String> ans = new HashSet<>();
        Set<String> seen = new HashSet<>();

        for(int l = 0, r = 0; r < n; r++) {
            if(r - l + 1 > 10) {
                l++;
            }
            if(r - l + 1 == 10) {
                String key = s.substring(l, r + 1);
                if(seen.contains(key)) {
                    ans.add(key);
                } else {
                    seen.add(key);
                }
            }
        }

        return new ArrayList<>(ans);
    }

    // A-00 C-01 G-10 T-11  2^20
    private List<String> bitMaskSol(String s) {
        int n = s.length();
        Map<Character, Integer> dna = new HashMap<>() {
            {
                put('A', 0);
                put('C', 1);
                put('G', 2);
                put('T', 3);
            }
        };
        Set<Integer> seen = new HashSet<>();
        Set<String> ans = new HashSet<>();

        int mask = 0;
        for(int l = 0, r = 0; r < n; r++) {
            mask = (mask << 2) | dna.get(s.charAt(r));

            if(r - l + 1 > 10) {
                //mask = mask ^ (dna.get(s.charAt(l++)) << 20);
                mask = mask & ~(3 << 2 * 10);
                l++;
            }

            if(r - l + 1 == 10) {
                if(seen.contains(mask)) {
                    ans.add(s.substring(l, r + 1));
                } else {
                    seen.add(mask);
                }
            }
        }

        return new ArrayList<>(ans);
    }

    private List<String> rollingHashSol(String s) {
        long base = 4;
        long mod = (long)1e9 + 7;
        int n = s.length();
        Set<String> ans = new HashSet<>();
        Set<Long> hs = new HashSet<>();

        long hash = 0;
        long d = 1;
        for(int l = 0, r = 0; r < n; r++) {
            hash = ((hash * base) % mod + s.charAt(r) - 'A') % mod;

            if(r - l + 1 > 10) {
                hash = (mod + hash - d * (s.charAt(l++) - 'A') % mod) % mod;
            } else {
                d = d * base % mod;
            }

            if(r - l + 1 == 10) {
                if(hs.contains(hash)) {
                    ans.add(s.substring(l, r + 1));
                } else {
                    hs.add(hash);
                }
            }
        }

        return new ArrayList<>(ans);
    }
}
