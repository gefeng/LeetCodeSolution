package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Find Duplicate File in System",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-duplicate-file-in-system/"
)
public class Q609 {
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> ans = new ArrayList<>();

        Map<String, List<String>> fileToPath = new HashMap<>();

        for(String path : paths) {
            String[] files = path.split("\\s");
            String dir = files[0];
            for(int i = 1; i < files.length; i++) {
                String file = files[i];

                int open = file.indexOf("(");
                String fileName = file.substring(0, open);
                String content = file.substring(open + 1, file.length() - 1);

                StringBuilder sb = new StringBuilder(dir).append("/").append(fileName);
                fileToPath.computeIfAbsent(content, k -> new ArrayList<>()).add(sb.toString());
            }
        }

        for(String key : fileToPath.keySet()) {
            List<String> files = fileToPath.get(key);
            if(files.size() > 1) {
                ans.add(files);
            }
        }

        return ans;
    }
}
