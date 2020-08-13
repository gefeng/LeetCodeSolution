package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Replace Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/replace-words/"
)
public class Q648 {
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = buildTrie(dict);
        StringBuilder sBuilder = new StringBuilder();
        for(String word : sentence.split("\\s+")) {
            if(sBuilder.length() > 0)
                sBuilder.append(" ");
            TrieNode curr = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!curr.children.containsKey(c) || curr.word != null) {
                    break;
                }
                curr = curr.children.get(c);
            }
            sBuilder.append(curr.word == null ? word : curr.word);
        }

        return sBuilder.toString();
    }

    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode();
        TrieNode curr;
        for(String s : dict) {
            curr = root;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.word = s;
        }

        return root;
    }
}
