package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Design Excel Sum Formula",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/design-excel-sum-formula/"
)
public class Q631 {
    private int h;
    private int w;
    private int[][] mat;
    private List<Rect>[][] form;

    public Q631(int height, char width) {
        this.h = height;
        this.w = width - 'A' + 1;
        this.mat = new int[h][w];
        this.form = new List[h][w];

        for(int r = 0; r < h; r++) {
            for(int c = 0; c < w; c++) {
                form[r][c] = new ArrayList<>();
            }
        }
    }

    public void set(int row, char column, int val) {
        int r = row - 1;
        int c = column - 'A';

        mat[r][c] = val;

        form[r][c] = new ArrayList<>();
    }

    public int get(int row, char column) {
        int r = row - 1;
        int c = column - 'A';

        return get(r, c);
    }

    private int get(int r, int c) {
        if(form[r][c].isEmpty()) {
            return mat[r][c];
        }

        int sum = 0;
        for(Rect rect : form[r][c]) {
            for(int i = rect.tl[0]; i <= rect.br[0]; i++) {
                for(int j = rect.tl[1]; j <= rect.br[1]; j++) {
                    sum += get(i, j);
                }
            }
        }

        return sum;
    }

    public int sum(int row, char column, String[] numbers) {
        int r = row - 1;
        int c = column - 'A';
        mat[r][c] = 0;

        form[r][c] = new ArrayList<>();
        for(String s : numbers) {
            int del = s.indexOf(":");

            if(del < 0) {
                int[] rc = toCoord(s);
                form[r][c].add(new Rect(rc, rc));
            } else {
                int[] tl = toCoord(s.substring(0, del));
                int[] br = toCoord(s.substring(del + 1));
                form[r][c].add(new Rect(tl, br));
            }
        }

        return get(r, c);
    }

    private int[] toCoord(String s) {
        int[] res = new int[2];
        res[1] = s.charAt(0) - 'A';
        res[0] = Integer.parseInt(s.substring(1)) - 1;
        return res;
    }

    private class Rect {
        int[] tl;
        int[] br;
        Rect(int[] tl, int[] br) {
            this.tl = Arrays.copyOf(tl, 2);
            this.br = Arrays.copyOf(br, 2);
        }
        boolean isCell() {
            return tl[0] == br[0] && tl[1] == br[1];
        }
    }
}
