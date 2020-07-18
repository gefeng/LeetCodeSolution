package solution;

import annotations.Problem;
import data_structure.Pair;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.List;

@Problem(
        title = "Design HashMap",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/design-hashmap/"
)
public class Q706 {
    private int capacity = 997;
    List<Pair<Integer, Integer>>[] table;
    /** Initialize your data structure here. */
    public Q706() {
        table = new LinkedList[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        if(table[index] == null)
            table[index] = new LinkedList<>();
        for(Pair<Integer, Integer> p : table[index]) {
            if(p.key == key) {
                p.value = value;
                return;
            }
        }
        table[index].add(new Pair<Integer, Integer>(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = hash(key);
        if(table[index] == null)
            return -1;
        for(Pair<Integer, Integer> p : table[index]) {
            if(p.key == key)
                return p.value;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = hash(key);
        if(table[index] == null)
            return;
        table[index].removeIf(p -> p.key == key);
    }

    private int hash(int key) {
        return key % capacity;
    }
}
