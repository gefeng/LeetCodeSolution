package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Valid Sudoku",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/valid-sudoku/"
)
public class Q36 {
    public boolean isValidSudoku3Map(char[][] board) {
        HashMap<Character, Integer>[] rowMap = new HashMap[9];
        HashMap<Character, Integer>[] colMap = new HashMap[9];
        HashMap<Character, Integer>[] boxMap = new HashMap[9];
        for(int i = 0; i < 9; i++) {
            rowMap[i] = new HashMap<>();
            colMap[i] = new HashMap<>();
            boxMap[i] = new HashMap<>();
        }

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char num = board[i][j];
                if(num == '.')
                    continue;
                int k = 3 * (i / 3) + (j / 3);
                int rCount = rowMap[i].getOrDefault(num, 0);
                int cCount = colMap[j].getOrDefault(num, 0);
                int bCount = boxMap[k].getOrDefault(num, 0);
                if(rCount > 0 || cCount > 0 || bCount > 0)
                    return false;
                rowMap[i].put(num, rCount + 1);
                colMap[j].put(num, cCount + 1);
                boxMap[k].put(num, bCount + 1);
            }
        }
        return true;
    }
    public boolean isValidSudoku(char [][] board) {
        HashMap<String, HashSet<Character>> map = new HashMap<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.')
                    continue;
                String rKey = "r" + i;
                String cKey = "c" + j;
                String bKey = "b" + (3 * (i / 3) + (j / 3));
                if(!map.containsKey(rKey))
                    map.put(rKey, new HashSet<>());
                if(!map.containsKey(cKey))
                    map.put(cKey, new HashSet<>());
                if(!map.containsKey(bKey))
                    map.put(bKey, new HashSet<>());

                if(!map.get(rKey).add(board[i][j]))
                    return false;
                if(!map.get(cKey).add(board[i][j]))
                    return false;
                if(!map.get(bKey).add(board[i][j]))
                    return false;
            }
        }
        return true;
    }
}
