package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Word Squares",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/word-squares/"
)
public class Q425 {
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        List<Integer> words = new ArrayList<>();
    }
    TrieNode root = new TrieNode();
    List<List<String>> ans = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<>();
    public List<List<String>> wordSquares(String[] words) {
        //buildHashMap(words);
        buildTrie(words);
        backTrackWithTrie(words, new ArrayList<>());
        return ans;
    }

    private void buildHashMap(String[] words) {
        for(String word : words) {
            for(int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                if(!map.containsKey(prefix))
                    map.put(prefix, new ArrayList<>());
                map.get(prefix).add(word);
            }
        }
    }

    private void buildTrie(String[] words) {
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            curr.words.add(i);
            for(int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                curr = curr.children.get(c);
                curr.words.add(i);
            }
        }
    }

    private void backTrackWithHashMap(String[] words, List<String> square) {
        if(square.size() == words[0].length()) {
            ans.add(new ArrayList<>(square));
            return;
        }

        String prefix = getPrefix(square);
        if (map.containsKey(prefix)) {
            for(String word : map.get(prefix)) {
                square.add(word);
                backTrackWithHashMap(words, square);
                square.remove(square.size() - 1);
            }
        }
    }

    private void backTrackWithTrie(String[] words, List<String> square) {
        if(square.size() == words[0].length()) {
            ans.add(new ArrayList<>(square));
            return;
        }

        String prefix = getPrefix(square);
        for(int wordIndex : getWordsWithPrefix(prefix)) {
            square.add(words[wordIndex]);
            backTrackWithTrie(words, square);
            square.remove(square.size() - 1);
        }
    }

    private String getPrefix(List<String> square) {
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < square.size(); i++) {
            prefix.append(square.get(i).charAt(square.size()));
        }
        return prefix.toString();
    }

    private List<Integer> getWordsWithPrefix(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c))
                return new ArrayList<>();
            curr = curr.children.get(c);
        }
        return curr.words;
    }
}
