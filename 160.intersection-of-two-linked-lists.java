/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    //https://www.cnblogs.com/grandyang/p/4128461.html
    // public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //     if(headA == null || headB == null)
    //         return null;
    //     if(headA == headB)
    //         return headA;
    //     int lenA = Len(headA), lenB = Len(headB);

    //     int cnt = 0;
    //     if (lenA < lenB) {
    //         ListNode tmp = headA;
    //         headA = headB;
    //         headB = tmp;
    //         cnt = lenB - lenA;
    //     }
    //     else
    //         cnt = lenA - lenB;

    //     ListNode p = headA;
    //     while (cnt-- > 0 && p != null) {
    //         p = p.next;
    //     }

    //     headA = p;
    //     while (headA != null && headB != null) {
    //         if (headA == headB)
    //             return headA;
    //         headA = headA.next;
    //         headB = headB.next;
    //     }
    //     return null;
    // }

    // public int Len(ListNode a) {
    //     int cnt = 0;
    //     while (a != null) {
    //         ++cnt;
    //         a = a.next;
    //     }
    //     return cnt;
    // }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode a = headA, b = headB;
        //当一个链表遍历到结尾空节点时，转向到另一个链表的头结点
        while (a != b) {
            a = (a != null) ? a.next : headB;
            b = (b != null) ? b.next : headA;
        }
        return a;
    }
}

