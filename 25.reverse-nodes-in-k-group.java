/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
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
    //https://www.cnblogs.com/grandyang/p/4441324.html
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1)
            return head;
        ListNode dummy = new ListNode(-1), pre = dummy, cur = head;
        dummy.next = head;
        for (int i = 1; cur != null; ++i) {
            //判断当前这一段的个数是不是k个
            if (i % k == 0) {
                //如果是，则将这一段翻转
                pre = reverse(pre, cur.next);
                //最后一个节点为下一段的首节点的前一个节点
                cur = pre.next;
            } else {
                //否则，继续遍历
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //pre和nxt分别指向每一段的首节点的前一个节点，尾节点的后一个节点
    //然后对该段链表进行逆置
    private ListNode reverse(ListNode pre, ListNode nxt) {
        ListNode last = pre.next, cur = last.next;
        while (cur != nxt) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
}

