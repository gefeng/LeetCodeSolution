package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Palindrome Pair",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/palindrome-pairs/"
)
public class Q336 {
    TrieNode root = new TrieNode();
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        List<Integer> words = new ArrayList<>();
        int index = -1; // we can store the empty string index in root node if have one.
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        if(words.length == 0)
            return ans;

        buildTrie(words);

        for(int i = 0; i < words.length; i++) {
            String suffix = words[i];
            String prefix = new StringBuilder(suffix).reverse().toString();
            for(int j : getWordsWithPrefix(prefix)) {
                if(!words[j].equals(suffix) && isPalindrome(words[j] + suffix)) {
                    ans.add(Arrays.asList(j, i));
                }
            }
        }

        return ans;
    }

    private void buildTrie(String[] words) {
        for(int i = 0; i < words.length; i++) {
            TrieNode curr = root;
            curr.words.add(i);
            for(int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                curr = curr.children.get(c);
                curr.words.add(i);
            }
            curr.index = i;
        }
    }

    private List<Integer> getWordsWithPrefix(String prefix) {
        List<Integer> words = new ArrayList<>();
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(curr.index != -1)
                words.add(curr.index);
            if(!curr.children.containsKey(c))
                return words;
            curr = curr.children.get(c);
        }
        words.addAll(curr.words);
        return words;
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}
