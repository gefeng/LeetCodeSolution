package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Problem(
        title = "Find the Shortest Superstring",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-the-shortest-superstring/"
)
public class Q943 {
    /*
        variant travelling salesman problem

    */
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][] graph = new int[n][n];

        // build adjacency matrix
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                graph[i][j] = getDistance(words[i], words[j]);
            }
        }

        /*
            now lets solve the TSP :)

            state:
                dp[mask][j] means minimum cost travelling from node 0 through nodes in mask to j
            transition:
                dp[mask][j] = min(dp[mask - k][k] + cost[k][j])

            we need to memorize j if the path has the lowest cost, therefore we can rebuild
            the optimal path later.
        */
        int[][] dp = new int[(1 << n) - 1][n];
        int[][] path = new int[(1 << n) - 1][n];

        for(int i = 0; i < n; i++) {
            dp[0][i] = words[i].length();
        }

        for(int i = 1; i < (1 << n) - 1; i++) {

            Arrays.fill(dp[i], Integer.MAX_VALUE); // init with +infi

            for(int j = 0; j < n; j++) {
                if(((1 << j) & i) != 0) {  // if next node j is in visited nodes
                    continue;
                }

                int minCost = Integer.MAX_VALUE;
                int minParent = 0;
                for(int k = 0; k < n; k++) {
                    if(((1 << k) & i) == 0) {
                        continue;
                    }

                    if(dp[i ^ (1 << k)][k] + graph[k][j] < minCost) {
                        minCost = dp[i ^ (1 << k)][k] + graph[k][j];
                        minParent = k;
                    }
                }
                dp[i][j] = minCost;
                path[i][j] = minParent;
            }
        }

        /*
            find optimal path (minimum cost) visited all nodes
            i.e. dp[1110][0]

            // 10000  1<<n
            // 01111  (1<<n)-1
            // 01110  (1<<n)-2
            // 1111 ^ 0001  1110
        */
        int minCost = Integer.MAX_VALUE;
        int optState = 0;
        int optLast = 0;
        for(int i = 0; i < n; i++) {
            int state = ((1 << i) ^ ((1 << n) - 1));
            if(dp[state][i] < minCost) {
                optState = state;
                optLast = i;
                minCost = dp[state][i];
            }
        }
        System.out.println(minCost);
        // build the optimal path
        //  110  0  0
        //  100  1  1
        //  000
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(optLast);
        int currState = optState;
        int currNode = optLast;
        while(currState > 0) {
            int prev = path[currState][currNode];

            stack.push(prev);

            currState = currState - (1 << prev);
            currNode = prev;
        }

        // build the string
        StringBuilder sb = new StringBuilder();
        int prev = stack.pop();
        sb.append(words[prev]);
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            int len = words[curr].length();
            // atgcatc
            System.out.println(prev + " " + curr + " " + graph[prev][curr]);
            sb.append(words[curr].substring(len - graph[prev][curr], len));
            prev = curr;
        }

        return sb.toString();
    }

    /*
        the distance from s1 to s2 is defined as,
        the length of substring in s2 after overlapping.
        i.e. s1 = abc, s2 = bce  distance = 1

        s1 = ctaagt   s2 = ttca


        len of prefix = i

        m - i
    */
    private int getDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        for(int i = n - 1; i > 0; i--) {
            if(i < m) {
                String prefix = s2.substring(0, i);
                String suffix = s1.substring(m - i, m);
                if(prefix.equals(suffix)) {
                    return n - i;
                }
            }
        }
        return n;
    }
}
