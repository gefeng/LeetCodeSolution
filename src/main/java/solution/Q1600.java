package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Throne Inheritance",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/throne-inheritance/"
)
public class Q1600 {
    private class Person {
        String name;
        boolean alive;
        List<Person> children;
        Person(String name) {
            this.name = name;
            this.alive = true;
            this.children = new ArrayList<>();
        }
    }

    Map<String, Person> map;
    Person king;
    public Q1600(String kingName) {
        king = new Person(kingName);

        map = new HashMap<>();
        map.put(kingName, king);
    }

    /**
     * Time:  O(1)
     * */
    public void birth(String parentName, String childName) {
        Person p = new Person(childName);

        map.get(parentName).children.add(p);
        map.put(childName, p);
    }

    /**
     * Time:  O(1)
     * */
    public void death(String name) {
        map.get(name).alive = false;
    }

    /**
     * Preorder traversal (we can cut off died subtree)
     * Time:  O(N)
     * */
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();

        Successor(king, order);

        return order;
    }

    private boolean Successor(Person x, List<String> order) {
        if(x.alive) {
            order.add(x.name);
        }

        boolean alive = x.alive;
        for(Person c : x.children) {
            alive = alive | Successor(c, order);
        }

        // clean up all died subtree
        if(!alive) {
            for(Person c : x.children) {
                map.remove(c.name);
            }
            x.children.clear();
        }

        return alive;
    }
}
