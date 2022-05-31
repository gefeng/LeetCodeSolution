package solution.biweekly79;

import java.util.*;
import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Booking Concert Tickets in Groups",
        difficulty = QDifficulty.HARD,
        tag = QTag.SEGMENT_TREE,
        url = "https://leetcode.com/contest/biweekly-contest-79/problems/booking-concert-tickets-in-groups/"
)
public class Q2286 {
    int m;
    int n;
    SegmentTreeRMQ rmq;
    SegmentTreeRSQ rsq;
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public Q2286(int n, int m) {
        this.m = n;
        this.n = m;

        int[] A = new int[this.m];

        this.rmq = new SegmentTreeRMQ(A);
        this.rsq = new SegmentTreeRSQ(A);
    }

    /**
     * Time: O(logN)
     * */
    public int[] gather(int k, int maxRow) {
        int row = rmq.firstle(0, n - k);
        if(row == -1 || row > maxRow) {
            return new int[0];
        }

        int taken = rmq.min(row, row + 1);
        rmq.update(row, taken + k);
        rsq.update(row, taken + k);

        return new int[] {row, taken};
    }

    /**
     * Reserved seats will not take back so scatter call has a amortized time complexity
     * O((N * logN) / Q)
     * */
    public boolean scatter(int k, int maxRow) {
        long tot = rsq.sum(0, maxRow + 1);
        boolean ok = (long)(maxRow + 1) * n - tot >= k;

        if(ok) {
            int row = 0;
            while(k > 0) {
                long left = n - rsq.sum(row, row + 1);
                long taken = Math.min(k, left);

                rsq.update(row, (int)(n - left + taken));
                rmq.update(row, (int)(n - left + taken));
                k -= taken;
                row++;
            }
        }

        return ok;
    }

    private class SegmentTreeRMQ {
        int[] t;  // tree array

        int M; // size of tree array
        int N; // size of original array
        int H; // offset to the origin array in tree array

        private static final int INF = Integer.MAX_VALUE;

        SegmentTreeRMQ(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;  // resize tree array to next_power_2(N) * 2
            H = M >> 1;

            t = new int[M];

            Arrays.fill(t, INF);
        }

        SegmentTreeRMQ(int[] A) {
            this(A.length);

            for(int i = 0; i < N; i++) {
                t[H + i] = A[i];
            }

            for(int i = H - 1; i > 0; i--) {
                propagate(i);
            }
        }

        private void propagate(int i) {
            t[i] = Math.min(t[i * 2], t[i * 2 + 1]);
        }

        void update(int i, int v) {
            t[H + i] = v;

            for(int j = H + i >> 1; j > 0; j >>= 1) {
                propagate(j);
            }
        }

        int min(int l, int r) {
            int min = INF;
            for(l += H, r += H; l < r; l >>= 1, r >>= 1) {
                if((l & 1) == 1) min = Math.min(min, t[l++]);
                if((r & 1) == 1) min = Math.min(min, t[--r]);
            }

            return min;
        }

        public int firstle(int l, int v) {
            if(l >= H)return -1;
            int cur = H+l;
            while(true){
                if(t[cur] <= v){
                    if(cur >= H)return cur-H;
                    cur = 2*cur;
                }else{
                    cur++;
                    if((cur&cur-1) == 0)return -1;
                    if((cur&1)==0)cur>>>=1;
                }
            }
        }

        public int lastle(int l, int v) {
            if(l < 0)return -1;
            int cur = H+l;
            while(true){
                if(t[cur] <= v){
                    if(cur >= H)return cur-H;
                    cur = 2*cur + 1;
                }else{
                    if((cur&cur-1) == 0)return -1;
                    cur--;
                    if((cur&1)==1)cur>>>=1;
                }
            }
        }
    }

    private class SegmentTreeRSQ {
        long[] t;
        int N, M, H;
        SegmentTreeRSQ(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >> 1;
            t = new long[M];
        }

        SegmentTreeRSQ(int[] A) {
            this(A.length);
            for(int i = 0; i < N; i++) {
                t[i + H] = A[i];
            }
            for(int i = H - 1; i > 0; i--) {
                propagate(i);
            }
        }

        void propagate(int i) {
            t[i] = t[i * 2] + t[i * 2 + 1];
        }

        void update(int i, int v) {
            t[i + H] = v;
            for(int j = i + H >> 1; j > 0; j >>= 1) {
                propagate(j);
            }
        }

        long sum(int l, int r) {
            long sum = 0;
            for(l += H, r += H; l < r; l >>= 1, r >>= 1) {
                if((l & 1) == 1) sum += t[l++];
                if((r & 1) == 1) sum += t[--r];
            }
            return sum;
        }
    }
}
