package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Number of Valid Move Combinations On Chessboard",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/number-of-valid-move-combinations-on-chessboard/"
)
public class Q2056 {
    /**
     * Time:  O(32 * 16 * 16 * 16)
     * Space: O(32 * 16 * 16 * 16)
     * */
    private static final int[][][] DIRECTIONS = new int[][][] {
            {{0, 1}, {0, -1}, {1, 0}, {-1, 0}},
            {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}},
            {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}}
    };
    private int ans = 1;

    public int countCombinations(String[] pieces, int[][] positions) {
        int n = pieces.length;

        List<int[]>[] pos = new List[n];
        for(int i = 0; i < n; i++) {
            pos[i] = new ArrayList<>();
            switch(pieces[i]) {
                case "rook":
                    gen(positions[i], DIRECTIONS[0], pos[i]);
                    break;
                case "queen":
                    gen(positions[i], DIRECTIONS[1], pos[i]);
                    break;
                case "bishop":
                    gen(positions[i], DIRECTIONS[2], pos[i]);
                    break;
            }

            ans *= pos[i].size();
        }

        prune(pos, n, 0, new ArrayList<>());

        return ans;
    }

    private void prune(List<int[]>[] pos, int n, int cur, List<int[]> cand) {
        if(cur == n) {
            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {
                    int[] p1 = cand.get(i);
                    int[] p2 = cand.get(j);
                    int dx1 = p1[2] - p1[0] == 0 ? 0 : (p1[2] - p1[0]) / Math.abs(p1[2] - p1[0]);
                    int dy1 = p1[3] - p1[1] == 0 ? 0 : (p1[3] - p1[1]) / Math.abs(p1[3] - p1[1]);
                    int dx2 = p2[2] - p2[0] == 0 ? 0 : (p2[2] - p2[0]) / Math.abs(p2[2] - p2[0]);
                    int dy2 = p2[3] - p2[1] == 0 ? 0 : (p2[3] - p2[1]) / Math.abs(p2[3] - p2[1]);
                    int d1 = Math.max(Math.abs(p1[0] - p1[2]), Math.abs(p1[1] - p1[3]));
                    int d2 = Math.max(Math.abs(p2[0] - p2[2]), Math.abs(p2[1] - p2[3]));

                    int t1 = 0, t2 = 0;

                    while(t1 <= d1 || t2 <= d2) {
                        int nx1 = t1 > d1 ? p1[2] : p1[0] + dx1 * t1;
                        int ny1 = t1 > d1 ? p1[3] : p1[1] + dy1 * t1;
                        int nx2 = t2 > d2 ? p2[2] : p2[0] + dx2 * t2;
                        int ny2 = t2 > d2 ? p2[3] : p2[1] + dy2 * t2;
                        if(nx1 == nx2 && ny1 == ny2) {
                            ans--;
                            return;
                        }
                        t1++;
                        t2++;
                    }
                }
            }
            return;
        }

        for(int[] p : pos[cur]) {
            cand.add(p);
            prune(pos, n, cur + 1, cand);
            cand.remove(cand.size() - 1);
        }
    }

    private void gen(int[] init, int[][] dir, List<int[]> all) {
        int r = init[0];
        int c = init[1];
        all.add(new int[] {r, c, r, c});
        for(int[] d : dir) {
            for(int t = 1; t <= 8; t++) {
                int nr = r + d[0] * t;
                int nc = c + d[1] * t;
                if(nr >= 1 && nc >= 1 && nr <= 8 && nc <= 8) {
                    all.add(new int[] {r, c, nr, nc});
                } else {
                    break;
                }
            }
        }
    }
}
