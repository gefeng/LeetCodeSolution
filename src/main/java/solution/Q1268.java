package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Search Suggestions System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/search-suggestions-system/"
)
public class Q1268 {
    private class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        String word = null;
        List<String> suggest = new ArrayList<>();
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> suggestions = new ArrayList<>();
        Arrays.sort(products, (a, b) -> (a.compareTo(b)));
        TrieNode root = buildTrie(products);

        TrieNode curr = root;
        for(int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            if(curr != null && curr.children.containsKey(c)) {
                curr = curr.children.get(c);
                suggestions.add(curr.suggest);
            } else {
                curr = null;
                suggestions.add(new ArrayList<>());
            }
        }
        return suggestions;
    }

    private TrieNode buildTrie(String[] products) {
        TrieNode root = new TrieNode();

        for(String product : products) {
            TrieNode curr = root;
            for(int i = 0; i < product.length(); i++) {
                char c = product.charAt(i);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                curr = curr.children.get(c);
                if(curr.suggest.size() < 3)
                    curr.suggest.add(product);
            }
            curr.word = product;
        }

        return root;
    }
}
