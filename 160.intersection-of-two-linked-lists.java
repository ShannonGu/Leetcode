/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */
public class Solution {
    // https://www.cnblogs.com/grandyang/p/4128461.html
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode l1 = headA, l2 = headB;
        // 假设A的头节点到相交点的距离为a，B的头节点到相交点的距离为b
        // 相交点到终点的距离为c
        // 当一个链表遍历到结尾空节点时，转向到另一个链表的头结点
        // 按照此种方式，如果两个链表长度不同，最终会在a+b+c次前进后同时叨叨
        while (l1 != l2) {
            l1 = l1 != null ? l1.next : headB;
            l2 = l2 != null ? l2.next : headA;
        }
        return l1;
    }
}
