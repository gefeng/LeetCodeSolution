package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Problem(
        title = "Keys and Rooms",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/keys-and-rooms/"
)
public class Q841 {
    private void dfs(List<List<Integer>> rooms, Set<Integer> visited, int curr) {
        if(visited.contains(curr))
            return;

        visited.add(curr);

        for(int room : rooms.get(curr)) {
            dfs(rooms, visited, room);
        }
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            if(!visited.contains(curr)) {
                visited.add(curr);
                for(int room : rooms.get(curr))
                    stack.push(room);
            }
        }
        return visited.size() == rooms.size();
    }
}
