package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Town Judge",
        difficulty = QDifficulty.EASY,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/find-the-town-judge/"
)
public class Q997 {
    public int findJudge(int N, int[][] trust) {
        return oneArraySolution(N, trust);
    }

    private int twoArraySolution(int N, int[][] trust) {
        int[] indegree = new int[N + 1];
        int[] outdegree = new int[N + 1];

        for(int[] t : trust) {
            outdegree[t[0]]++;
            indegree[t[1]]++;
        }

        for(int i = 1; i <= N; i++) {
            if(outdegree[i] == 0 && indegree[i] == N - 1)
                return i;
        }
        return -1;
    }

    /*
        for each trust[i]
            score[trust[i][0]]--;
            score[trust[i][1]]++;
    */
    private int oneArraySolution(int N, int[][] trust) {
        int[] score = new int[N + 1];
        for(int[] t : trust) {
            score[t[0]]--;
            score[t[1]]++;
        }

        for(int i = 1; i <= N; i++) {
            if(score[i] == N - 1)
                return i;
        }

        return -1;
    }
}
