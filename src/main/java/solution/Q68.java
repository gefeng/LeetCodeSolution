package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Text Justification",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/text-justification/"
)
public class Q68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();

        List<String> line = new ArrayList<>();
        line.add(words[0]);
        int currLen = words[0].length();
        int numChars = currLen;
        for(int i = 1; i < words.length; i++) {
            String word = words[i];
            currLen += (word.length() + 1);
            numChars += word.length();

            if(currLen > maxWidth) {
                ans.add(makeLine(line, numChars - word.length(), maxWidth));
                line.clear();
                currLen = word.length();
                numChars = currLen;
            }

            line.add(word);
        }

        ans.add(makeLastLine(line, maxWidth));

        return ans;
    }

    private String makeLine(List<String> words, int numChars, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int numWords = words.size();
        int numSpace = maxWidth - numChars;
        int spaceLen = numWords == 1 ? numSpace : numSpace / (numWords - 1);
        int spaceLeft = numWords == 1 ?  0 : numSpace % (numWords - 1);

        sb.append(words.get(0));
        for(int i = 1; i < numWords; i++) {
            for(int j = 0; j < spaceLen; j++) {
                sb.append(' ');
            }
            if(spaceLeft != 0) {
                sb.append(' ');
                spaceLeft--;
            }

            sb.append(words.get(i));
        }

        for(int i = sb.length(); i < maxWidth; i++) {
            sb.append(' ');
        }

        return sb.toString();
    }

    private String makeLastLine(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int numWords = words.size();

        sb.append(words.get(0));
        for(int i = 1; i < numWords; i++) {
            sb.append(' ');
            sb.append(words.get(i));
        }

        for(int i = sb.length(); i < maxWidth; i++) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
