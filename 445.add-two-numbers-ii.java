/*
 * @lc app=leetcode id=445 lang=java
 *
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (50.09%)
 * Total Accepted:    90.9K
 * Total Submissions: 181.5K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 * 
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 * 
 * 
 * 
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
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
    //https://www.cnblogs.com/grandyang/p/6216480.html

    
    //用栈辅助;
    // public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //     Stack<ListNode>s1 = new Stack<ListNode>(), s2 = new Stack<ListNode>();
    //     while(l1 != null){
    //         s1.push(l1);
    //         l1 = l1.next;
    //     }
    //     while(l2 != null){
    //         s2.push(l2);
    //         l2 = l2.next;
    //     }
    //     ListNode dummy = new ListNode(-1);
    //     dummy.next = null;
    //     int carry = 0;
    //     while(!s1.empty() || !s2.empty() || carry != 0){
    //         int val = (s1.empty() ? 0 : s1.pop().val) + (s2.empty() ? 0 : s2.pop().val) + carry;
    //         carry = val / 10;
    //         val = val % 10;
    //         ListNode tmp = new ListNode(val);
    //         tmp.next = dummy.next;
    //         dummy.next = tmp;
    //     }
    //     return dummy.next;
    // }
    

    //递归解法;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        int len1 = getLength(l1), len2 = getLength(l2);
        ListNode head = new ListNode(1);
        head.next = len1 > len2 ? helper(l1, l2, len1 - len2) : helper(l2, l1, len2 - len1);
        if(head.next.val > 9){
            head.next.val %= 10;
            return head;
        }
        return head.next;
    }
    
    public int getLength(ListNode l){
        int cnt = 0;
        while(l != null){
            ++cnt;
            l = l.next;
        }
        return cnt;
    }

    public ListNode helper(ListNode l1, ListNode l2, int diff){
        if(l1 == null)
            return null;
        ListNode res = (diff == 0) ? (new ListNode(l1.val + l2.val)) : (new ListNode(l1.val));
        ListNode post = (diff == 0) ? helper(l1.next,l2.next, 0) : helper(l1.next, l2, diff - 1);
        if(post != null && post.val > 9){
            post.val %= 10;
            ++res.val;
        }
        res.next = post;
        return res;
    }
}

