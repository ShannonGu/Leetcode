/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * algorithms
 * Medium (27.15%)
 * Total Accepted:    247.3K
 * Total Submissions: 910.6K
 * Testcase Example:  '{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}'
 *
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input:
 * 
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * 
 * Explanation:
 * Node 1's value is 1, both of its next and random pointer points to Node 2.
 * Node 2's value is 2, its next pointer points to null and its random pointer
 * points to itself.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You must return the copy of the given head as a reference to the cloned
 * list.
 * 
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return head;
        copyNodes(head);
        connectRandom(head);
        return splitList(head);

    }
    
    public void copyNodes(Node head){
        Node p = head;
        while(p != null){
            Node cpNode = new Node();
            cpNode.val = p.val;
            cpNode.next = p.next;
            p.next = cpNode;
            cpNode.random = null;

            p = cpNode.next;
        }
    }

    public void connectRandom(Node head) {
        Node p = head;
        while (p != null) {
            Node cpNode = p.next;
            if (p.random != null) {
                cpNode.random = p.random.next;
            }
            p = cpNode.next;
        }
    }
    
    public Node splitList(Node head){
        Node oriNode = head;
        Node cpHead = null, cpNode = null;

        //寻找复制后的链表的头结点;
        if(oriNode != null){
            cpHead = cpNode = oriNode.next;
            oriNode.next = cpHead.next;
            oriNode = oriNode.next;
        }

        while(oriNode != null){
            cpNode.next = oriNode.next;
            cpNode = cpNode.next;
            oriNode.next = cpNode.next;
            oriNode = oriNode.next;
        }
        return cpHead;
    }
}

