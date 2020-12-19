/*
 * @lc app=leetcode id=328 lang=java
 *
 * [328] Odd Even Linked List
 *
 * https://leetcode.com/problems/odd-even-linked-list/description/
 *
 * algorithms
 * Medium (49.15%)
 * Total Accepted:    147.3K
 * Total Submissions: 299.6K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * Given a singly linked list, group all odd nodes together followed by the
 * even nodes. Please note here we are talking about the node number and not
 * the value in the nodes.
 * 
 * You should try to do it in place. The program should run in O(1) space
 * complexity and O(nodes) time complexity.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * 
 * 
 * Note:
 * 
 * 
 * The relative order inside both the even and odd groups should remain as it
 * was in the input.
 * The first node is considered odd, the second node even and so on ...
 * 
 * 
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

    //https://www.cnblogs.com/grandyang/p/5138936.html

    // public ListNode oddEvenList(ListNode head) {
    //     if(head == null || head.next == null)
    //         return head;
    //     ListNode odd = head, even = head.next;
    //     ListNode oddNode = odd, evenNode = even;
    //     ListNode tmp = even.next;
    //     while(tmp != null){
    //         oddNode.next = tmp;
    //         oddNode = tmp;
    //         evenNode.next = tmp.next;
    //         evenNode = tmp.next;
    //         if(tmp.next == null)
    //             break;
    //         else
    //             tmp = tmp.next.next;
    //     }
    //     oddNode.next = even;
    //     if(evenNode != null)
    //         evenNode.next = null;
    //     return odd;
    // }


    public ListNode oddEvenList(ListNode head){
        if(head == null || head.next == null)
            return head;
        //odd指向当前奇结点的末尾;
        ListNode odd = head, even = head.next;
        while(even != null && even.next != null){
            //tmp始终指向原来的第一个偶结点;
            ListNode tmp = odd.next;
            odd.next = even.next;
            even.next = even.next.next;
            
            //奇结点后面始终连着第一个偶结点
            odd.next.next = tmp;
            odd = odd.next;
            even = even.next;
        }
        return head;
    }
}

