package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Remove Sub-Folders from the Filesystem",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/"
)
public class Q1233 {
    /**
     * Time:  O(N * logN + N * L * L)
     * Space: O(N * L)
     * */
    public List<String> removeSubfolders(String[] folder) {
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();

        Arrays.sort(folder, Comparator.comparingInt(a -> a.length()));

        for(String f : folder) {
            boolean isSub = false;
            int len = f.length();
            for(int i = 0; i < len; i++) {
                if(i == len - 1 || f.charAt(i + 1) == '/') {
                    if(set.contains(f.substring(0, i + 1))) {
                        isSub = true;
                        break;
                    }
                }
            }

            if(!isSub) {
                ans.add(f);
            }

            set.add(f);
        }

        return ans;
    }
}
