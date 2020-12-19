/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    //https://www.cnblogs.com/grandyang/p/4290148.html
    public Node connect(Node root) {
        Node dummy = new Node(0, null, null, null);
        Node cur = dummy, head = root;
        while (head != null) {
            if (head.left != null) {
                cur.next = head.left;
                cur = cur.next;
            }
            if (head.right != null) {
                cur.next = head.right;
                cur = cur.next;
            }
            head = head.next;
            if (head == null) {
                cur = dummy;
                head = dummy.next;
                dummy.next = null;
            }
        }
        return root;
    }
}
// @lc code=end

