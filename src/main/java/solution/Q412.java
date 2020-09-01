package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Problem(
        title = "Fizz Buzz",
        difficulty = QDifficulty.EASY,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/fizz-buzz/"
)
public class Q412 {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>() {{
            put(3, "Fizz");
            put(5, "Buzz");
        }};

        for(int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for(Integer key : map.keySet()) {
                if(i % key == 0) {
                    sb.append(map.get(key));
                }
            }
            if(sb.length() == 0)
                sb.append(i);
            ans.add(sb.toString());
        }
        return ans;
    }
}
