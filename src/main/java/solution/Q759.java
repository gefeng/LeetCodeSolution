package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "Employee Free Time",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/employee-free-time/"
)
public class Q759 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int n = schedule.size();
        List<Interval> l = new ArrayList<>();

        for(List<Interval> e : schedule) {
            for(Interval in : e) {
                l.add(in);
            }
        }

        Collections.sort(l, Comparator.comparingInt(a -> a.start));

        List<Interval> m = new ArrayList<>();
        for(Interval in : l) {
            if(!m.isEmpty() && in.start <= m.get(m.size() - 1).end) {
                m.get(m.size() - 1).end = Math.max(m.get(m.size() - 1).end, in.end);
            } else {
                m.add(in);
            }
        }

        List<Interval> free = new ArrayList<>();
        for(int i = 1; i < m.size(); i++) {
            if(m.get(i).start - m.get(i - 1).end > 0) {
                free.add(new Interval(m.get(i - 1).end, m.get(i).start));
            }
        }

        return free;
    }
    private class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
}
