/*
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
    //https://www.cnblogs.com/grandyang/p/4288151.html
    // public Node connect(Node root) {
    //     if(root == null)
    //         return root;
    //     if(root.left != null)
    //         root.left.next = root.right;
    //     if(root.right != null)
    //         root.right.next = root.next != null ? root.next.left : null;
    //     connect(root.left);
    //     connect(root.right);
    //     return root;
    // }


    public Node connect(Node root) {
        if(root == null)
            return root;
        Node start = root, cur = null;
        while (start.left != null) {
            cur = start;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
        return root;
    }
}