package solution;

import annotations.Problem;
import dataStructure.ListNode;
import enums.QDifficulty;
import enums.QTag;

/*
   这题的思路可以分成两点：
   1. 判断有没有intersection，判断依据就是两个list的tail节点是否相同
   2. 找到intersection node，用的trick是当a指针遍历完listA之后跳到listB的head，反之亦然，
   这样第二次遍历的时候两个指针能同时到达intersection node.
 */
@Problem(
        title = "Intersection of Two Linked Lists",
        difficulty = QDifficulty.EASY,
        tag = QTag.LINKED_LIST
)
public class Q160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        ListNode nodeA = headA;
        ListNode nodeB = headB;
        ListNode tailA = null;
        ListNode tailB = null;

        while(nodeA != nodeB) {
            if(nodeA.next == null) {
                tailA = nodeA;
                if(tailB != null && tailA != tailB) {
                    return null;
                }
                nodeA = headB;
            } else
                nodeA = nodeA.next;

            if(nodeB.next == null) {
                tailB = nodeB;
                if(tailA != null && tailB != tailA) {
                    return null;
                }
                nodeB = headA;
            } else
                nodeB = nodeB.next;
        }

        return nodeA;
    }
}
