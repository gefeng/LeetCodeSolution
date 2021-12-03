package solution;

public class Q914 {
    /**
     * Time:  O(N * log(max(deck)))
     * Space: O(max(deck));
     * */
    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[10001];
        int n = deck.length;

        for(int i = 0; i < n; i++) {
            cnt[deck[i]]++;
        }

        int g = 0;
        for(int i = 0; i < 10001; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            if(g != 0) {
                g = gcd(g, cnt[i]);
            } else {
                g = cnt[i];
            }
        }

        return g != 1;
    }

    private int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}
