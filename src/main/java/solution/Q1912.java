package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Design Movie Rental System",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-movie-rental-system/"
)
public class Q1912 {
    private Map<Integer, Map<Integer, Integer>> where;
    private Map<Integer, Map<Integer, Set<Integer>>> cheap;
    private Map<Integer, Map<Integer, Set<Integer>>> rents;
    public Q1912(int n, int[][] entries) {
        where = new HashMap<>();
        cheap = new HashMap<>();
        rents = new TreeMap<>();

        for(int[] e : entries) {
            int s = e[0], m = e[1], p = e[2];

            // init where [movie, [shop, price]]
            Map<Integer, Integer> x = where.computeIfAbsent(m, k -> new HashMap<>());
            x.put(s, p);

            // init cheap [movie, [price, [shop1, shop2,...]]]
            add(cheap, m, p, s);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> top = new ArrayList<>();

        Map<Integer, Set<Integer>> x = cheap.get(movie);
        if(x == null) {
            return top;
        }

        for(int p : x.keySet()) {
            for(int s : x.get(p)) {
                top.add(s);
                if(top.size() == 5) {
                    return top;
                }
            }
        }

        return top;
    }

    public void rent(int shop, int movie) {
        // update where
        int price = where.get(movie).get(shop);

        // update cheap
        remove(cheap, movie, price, shop);

        // update rents
        add(rents, price, shop, movie);
    }

    public void drop(int shop, int movie) {
        // update where
        int price = where.get(movie).get(shop);

        // update cheap
        add(cheap, movie, price, shop);

        // update rents
        remove(rents, price, shop, movie);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> top = new ArrayList<>();

        for(int p : rents.keySet()) {
            Map<Integer, Set<Integer>> x = rents.get(p);
            for(int s : x.keySet()) {
                Set<Integer> y = x.get(s);
                for(int m : y) {
                    top.add(Arrays.asList(s, m));
                    if(top.size() == 5) {
                        return top;
                    }
                }
            }
        }

        return top;
    }

    private void add(Map<Integer, Map<Integer, Set<Integer>>> map, int x, int y, int z) {
        Map<Integer, Set<Integer>> e1 = map.computeIfAbsent(x, k -> new TreeMap<>());
        Set<Integer> e2 = e1.computeIfAbsent(y, k -> new TreeSet<>());

        e2.add(z);
    }

    private void remove(Map<Integer, Map<Integer, Set<Integer>>> map, int x, int y, int z) {
        Map<Integer, Set<Integer>> e1 = map.get(x);
        Set<Integer> e2 = e1.get(y);

        e2.remove(z);
        if(e2.size() == 0) {
            e1.remove(y);
        }
        if(e1.size() == 0) {
            map.remove(x);
        }
    }
}
