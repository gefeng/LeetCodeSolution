package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Find the celebrity",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-celebrity/"
)
public class Q277 {
    private abstract class Relation {
        abstract boolean knows(int a, int b);
    }
    public class Solution extends Relation {
        @Override
        public boolean knows(int a, int b) {
            return false;
        }

        public int findCelebrity(int n) {
            return HashSetSolution(n);
        }

        private int HashSetSolution(int n) {
            HashSet<Integer> candidates = new HashSet<>();
            for(int i = 0; i < n; i++)
                candidates.add(i);

            for(int i = 0; i < n; i++) {
                if(isCelebrity(i, n, candidates))
                    return i;
            }
            return -1;
        }

        private boolean isCelebrity(int i, int n, HashSet<Integer> candidates) {
            if(!candidates.contains(i))
                return false;

            for(int j = 0; j < n; j++) {
                if(i == j)
                    continue;
                if(knows(i, j) || !knows(j, i)) {
                    candidates.remove(i);
                    return false;
                }
            }

            return true;
        }

        private int constantSpaceSolution(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++) {
                if(knows(candidate, i))
                    candidate = i;
            }

            if(isCelebrity(candidate, n))
                return candidate;
            return -1;
        }

        private boolean isCelebrity(int i, int n) {
            for(int j = 0; j < n; j++) {
                if(i == j)
                    continue;
                if(knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
