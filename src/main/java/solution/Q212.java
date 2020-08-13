package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Word Search II",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/word-search-ii/"
)
public class Q212 {
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }
    int[][] directions = new int[][] { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };
    TrieNode root = new TrieNode();
    List<String> ans = new ArrayList<>();
    public List<String> findWords(char[][] board, String[] words) {
        if(board.length == 0)
            return ans;

        for(String s : words) {
            TrieNode curr = root;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.word = s;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                TrieNode curr = root;
                backTrack(board, i, j, curr);
            }
        }

        return ans;
    }

    private void backTrack(char[][] board, int row, int col, TrieNode node) {
        if(!node.children.containsKey(board[row][col]))
            return;

        char letter = board[row][col];
        node = node.children.get(letter);
        board[row][col] = '.';

        if(node.word != null) {
            ans.add(node.word);
            node.word = null;
        }

        for(int i = 0; i < 4; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if(newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && board[newRow][newCol] != '.')
                backTrack(board, newRow, newCol, node);
        }

        board[row][col] = letter;
    }

    private boolean dfs(char[][] board, int row, int col, String word, int index, Set<Integer> visited) {
        if(row < 0 || row == board.length ||
                col < 0 || col == board[0].length ||
                visited.contains(row * board[0].length + col) ||
                board[row][col] != word.charAt(index))
            return false;

        if(index == word.length() - 1)
            return true;

        visited.add(row * board[0].length + col);

        boolean found = dfs(board, row + 1, col, word, index + 1, visited) ||
                        dfs(board, row - 1, col, word, index + 1, visited) ||
                        dfs(board, row, col + 1, word, index + 1, visited) ||
                        dfs(board, row, col - 1, word, index + 1, visited);

        visited.remove(row * board[0].length + col);

        return found;
    }
}
