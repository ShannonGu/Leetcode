/*
 * @lc app=leetcode id=328 lang=java
 *
 * [328] Odd Even Linked List
 */
class Solution {

    // https://www.cnblogs.com/grandyang/p/5138936.html
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // odd指向当前奇节点的末尾;
        ListNode odd = head, even = odd.next;
        while (even != null && even.next != null) {
            // tmp始终指向第一个偶节点;
            ListNode tmp = odd.next;
            odd.next = even.next;
            even.next = even.next.next;

            // 奇结点后面始终连着第一个偶节点
            odd.next.next = tmp;
            // odd始终指向当前最后一个奇节点
            odd = odd.next;
            even = even.next;
        }
        return head;
    }

}
