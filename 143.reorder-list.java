import java.util.Stack;

/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
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
    //https://www.cnblogs.com/grandyang/p/4254860.html
    public void reorderList(ListNode head) {
        //先使用快慢指针找到链表中点，从中间断开，形成两个独立的链表
        //再将第二个链表翻转
        //将第二个链表的元素间隔地插入第一个链表中
        if(head == null || head.next == null || head.next.next == null)
            return;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        //从中间断开
        slow.next = null;
        //翻转第二个链表
        ListNode last = mid, pre = null;
        while (last != null) {
            ListNode nxt = last.next;
            last.next = pre;
            pre = last;
            last = nxt;
        }
        //间隔地插入第一个链表中
        ListNode tmp = head;
        while (tmp != null && pre != null) {
            ListNode nxt = tmp.next;
            tmp.next = pre;
            pre = pre.next;
            //没插入一个就和第二个链表断开
            tmp.next.next = nxt;
            tmp = nxt;
        }
    }

    // //用栈代替翻转链表
    // public void reorderList(ListNode head) {
    //     if(head == null || head.next == null || head.next.next == null)
    //         return;
    //     Stack<ListNode> st = new Stack<>();
    //     ListNode cur = head;
    //     while (cur != null) {
    //         st.push(cur);
    //         cur = cur.next;
    //     }
    //     //先求出链表长度
    //     //链表的后半部分是要间隔地插入到前半部分的
    //     int cnt = (st.size() - 1) / 2;
    //     cur = head;
    //     while (cnt-- > 0) {
    //         ListNode t = st.pop();
    //         ListNode nxt = cur.next;
    //         cur.next = t;
    //         t.next = nxt;
    //         cur = nxt;
    //     }
    //     //这里st.peek()是链表的中间节点,由于其还里连着后半部分首节点
    //     //因此若将其next指针置为空，则会导致循环链表
    //     st.peek().next = null;
    // }
}

