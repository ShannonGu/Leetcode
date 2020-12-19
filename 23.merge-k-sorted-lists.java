/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //https://www.youtube.com/watch?v=XqA8bBoEdIY
//    // 使用最小堆
//    // 每次将所有list的头结点加入到最小堆中
//    // 取出最小的头结点,再将该节点的next节点继续加入堆中，进行迭代;
    // public ListNode mergeKLists(ListNode[] lists) {
    //     ListNode dummy = new ListNode(0);
    //     ListNode tail = dummy;
    //     PriorityQueue<ListNode> q = new PriorityQueue<>(
    //             new Comparator<ListNode>() {
    //                 public int compare(ListNode a, ListNode b) {
    //                     return a.val - b.val;
    //             }
    //         }
    //     );
    //      //先将所有list的头结点加入到最小堆中
    //     for (ListNode list : lists) {
    //         if (list != null)
    //             q.offer(list);
    //     }
    //     //每次取出最小的头结点;
    //     while (!q.isEmpty()) {
    //         tail.next = q.poll();
    //         tail = tail.next;
    //         if (tail.next != null)
    //             q.offer(tail.next);
    //     }
    //     return dummy.next;
    // }
    

    //divide and conquer
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }
    
    private ListNode merge(ListNode[] lists, int l, int r) {
        if(l > r)
            return null;
        if(l == r)
            return lists[l];
        if(l + 1 == r)
            return mergeTwoLists(lists[l], lists[r]);
        int mid = l + ((r - l) >> 1);
        ListNode l1 = merge(lists, l, mid);
        ListNode l2 = merge(lists, mid + 1, r);
        return mergeTwoLists(l1, l2);
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode tmp = l1;
                l1 = l2;
                l2 = tmp;
            }
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}

