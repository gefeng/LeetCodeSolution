package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Design Search Autocomplete System",
        difficulty = QDifficulty.HARD,
        tag = QTag.TRIE,
        url = "https://leetcode.com/problems/design-search-autocomplete-system/"
)
public class Q642 {
    class Sentence {
        String data = "";
        int times = 0;
        public Sentence(String data, int times) { this.data = data; this.times = times; }
    }
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        List<Sentence> hotSentences = new ArrayList<>();
        Sentence sentence = null;
    }

    TrieNode root;
    TrieNode curr;
    List<Character> inputStream;

    public Q642(String[] sentences, int[] times) {
        buildDictionary(sentences, times);
        inputStream = new ArrayList<>();
    }

    public List<String> input(char c) {
        List<String> hot = new ArrayList<>();
        if(c != '#') {
            if(curr.children.containsKey(c))
                hot = getHotSentences(curr.children.get(c));
            else
                curr.children.put(c, new TrieNode());
            curr = curr.children.get(c);
            inputStream.add(c);
        }
        else {
            // create new sentence or update the existing one's count
            Sentence s;
            if(curr.sentence != null) {
                s = curr.sentence;
                s.times++;
            }
            else {
                StringBuilder sb = new StringBuilder();
                inputStream.forEach(ch -> sb.append(ch));
                s = new Sentence(sb.toString(), 1);
                curr.sentence = s;
            }

            // update the whole branch
            curr = root;
            for(char ch : inputStream) {
                updateHotSentences(curr.children.get(ch), s);
                curr = curr.children.get(ch);
            }
            inputStream.clear();
            curr = root;
        }
        return hot;
    }

    public void buildDictionary(String[] sentences, int[] times) {
        root = new TrieNode();
        for(int i = 0; i < sentences.length; i++) {
            curr = root;
            Sentence s = new Sentence(sentences[i], times[i]);
            for(int j = 0; j < s.data.length(); j++) {
                char c = s.data.charAt(j);
                if(!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode());
                updateHotSentences(curr.children.get(c), s);
                curr = curr.children.get(c);
            }
            curr.sentence = s;
        }
        curr = root;
    }

    public void updateHotSentences(TrieNode node, Sentence s) {
        List<Sentence> hot = node.hotSentences;

        if(hot.size() == 0)
            hot.add(s);
        else {
            int index = hot.indexOf(s);
            if(index != -1)
                hot.get(index).times = s.times;
            else
                hot.add(s);
        }

        hot.sort((a, b) -> {
           if(a.times == b.times)
               return a.data.compareTo(b.data);
           else
               return b.times - a.times;
        });

        if(hot.size() > 3)
            hot.remove(hot.size() - 1);
    }

    public List<String> getHotSentences(TrieNode node) {
        List<String> hot = new ArrayList<>();
        for(Sentence s : node.hotSentences)
            hot.add(s.data);
        return hot;
    }
}
