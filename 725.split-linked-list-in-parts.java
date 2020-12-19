/*
 * @lc app=leetcode id=725 lang=java
 *
 * [725] Split Linked List in Parts
 *
 * https://leetcode.com/problems/split-linked-list-in-parts/description/
 *
 * algorithms
 * Medium (48.95%)
 * Total Accepted:    27.3K
 * Total Submissions: 55.9K
 * Testcase Example:  '[1,2,3,4]\n5'
 *
 * Given a (singly) linked list with head node root, write a function to split
 * the linked list into k consecutive linked list "parts".
 * 
 * The length of each part should be as equal as possible: no two parts should
 * have a size differing by more than 1.  This may lead to some parts being
 * null.
 * 
 * The parts should be in order of occurrence in the input list, and parts
 * occurring earlier should always have a size greater than or equal parts
 * occurring later.
 * 
 * Return a List of ListNode's representing the linked list parts that are
 * formed.
 * 
 * 
 * Examples
 * 1->2->3->4, k = 5 // 5 equal parts
 * [ [1], 
 * [2],
 * [3],
 * [4],
 * null ]
 * 
 * Example 1:
 * 
 * Input: 
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2,
 * \root.next.next.val = 3, and root.next.next.next = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a
 * ListNode is [].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most
 * 1, and earlier parts are a larger size than the later parts.
 * 
 * 
 * 
 * Note:
 * The length of root will be in the range [0, 1000].
 * Each value of a node in the input will be an integer in the range [0, 999].
 * k will be an integer in the range [1, 50].
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
    //https://www.cnblogs.com/grandyang/p/4250107.html
    public ListNode[] splitListToParts(ListNode root, int k) {
        if(k == 0)
            return new ListNode[0];
        int cnt = 0;
        ListNode node = root;
        //先计算出总个数
        while (node != null) {
            ++cnt;
            node = node.next;
        }
        //再计算出每个分段至少有avg个，
        //而余数remainder表示前remainder个分段中还要加上一个
        int avg = cnt / k, remainder = cnt % k;
        ListNode[] res = new ListNode[k];
        int i = 0;
        while (i < k && root != null) {
            res[i] = root;
            //注意这里j从1开始
            //因为每次需要停在当前分段的最后一个节点，
            //然后新建临时节点tmp用于指向下一个分段的首节点;
            int j = 1;
            //当前分段处于前remainder个分段，需要加上额外的一个节点
            if (i < remainder) {
                while (j < avg + 1) {
                    root = root.next;
                    ++j;
                }
            } else {
                while (j < avg) {
                    root = root.next;
                    ++j;
                }
            }
            
            if (root != null) {
                ListNode tmp = root.next;
                root.next = null;
                root = tmp;
            }
            ++i;
        }
        return res;
    }
}

