package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Minimum Index Sum of Two Lists",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/minimum-index-sum-of-two-lists/"
)
public class Q599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        List<String> rest = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list1.length; i++)
            map.put(list1[i], i);

        for(int i = 0; i < list2.length; i++) {
            Integer index = map.get(list2[i]);
            if(index != null) {
                if(index + i < min) {
                    min = index + i;
                    rest.clear();
                    rest.add(list2[i]);
                }
                else if(index + i == min)
                    rest.add(list2[i]);
            }
        }

        return rest.toArray(new String[rest.size()]);
    }
}
