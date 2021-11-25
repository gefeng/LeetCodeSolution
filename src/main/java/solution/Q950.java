package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@Problem(
        title = "Reveal Cards In Increasing Order",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/reveal-cards-in-increasing-order/"
)
public class Q950 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] ans = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();

        Arrays.sort(deck);

        for(int i = n - 1; i >= 0; i--) {

            if(i < n - 1) {
                deque.offerFirst(deque.pollLast());
            }

            deque.offerFirst(deck[i]);
        }

        for(int i = 0; i < n; i++) {
            ans[i] = deque.pollFirst();
        }

        return ans;
    }
}
