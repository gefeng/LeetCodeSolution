package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ransom Note",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counts = new int[26];
        magazine.chars().forEach(c -> counts[c-'a']++);
        return ransomNote.chars().allMatch(c -> counts[c-'a']-- > 0);
    }
}
