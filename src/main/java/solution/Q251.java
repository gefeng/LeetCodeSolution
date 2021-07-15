package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Problem(
        title = "Flatten 2D Vector",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/flatten-2d-vector/"
)
public class Q251 {
    List<Integer> vec;
    Iterator<Integer> it;
    public Q251(int[][] vec) {
        this.vec = new ArrayList<>();
        for(int i = 0; i < vec.length; i++) {
            for(int j = 0; j < vec[i].length; j++) {
                this.vec.add(vec[i][j]);
            }
        }

        this.it = this.vec.iterator();
    }

    public int next() {
        return it.next();
    }

    public boolean hasNext() {
        return it.hasNext();
    }
}
