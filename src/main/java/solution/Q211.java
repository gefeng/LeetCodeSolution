package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Add and Search Word - Data structure design",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/add-and-search-word-data-structure-design/"
)
public class Q211 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Q211() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode curr = root;
        return searchInTrie(word, curr, 0);
    }

    private boolean searchInTrie(String word, TrieNode node, int start) {
        for(int i = start; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c != '.') {
                if(node.children[c - 'a'] == null)
                    return false;
                else
                    node = node.children[c - 'a'];
            }
            else {
                for(int j = 0; j < 26; j++) {
                    if(node.children[j] != null && searchInTrie(word, node.children[j], i + 1))
                        return true;
                }
                return false;
            }
        }
        return node.isWord;
    }

    private boolean dfs(TrieNode node, String word, int index) {
        if(index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if(c != '.') {
            if(node.children[c - 'a'] == null)
                return false;
            else
                return dfs(node.children[c - 'a'], word, index + 1);
        }
        else {
            for(int i = 0; i < 26; i++) {
                if (node.children[i] != null && dfs(node.children[i], word, index + 1))
                    return true;
            }
            return false;
        }
    }
}
