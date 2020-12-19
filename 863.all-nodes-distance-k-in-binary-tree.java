import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/*
 * @lc app=leetcode id=863 lang=java
 *
 * [863] All Nodes Distance K in Binary Tree
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<TreeNode, List<TreeNode>> m;
    private Set<TreeNode> visited;//判断当前节点是否已经遍历过了
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        m = new HashMap<>();
        visited = new HashSet<>();
        visited.add(target);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        helper(root, null);
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            //距离缩短为0
            //表示要找的节点此时已经都在队列中了
            if (K == 0) {
                int len = q.size();
                //依次出队存入结果
                for (int i = 0; i < len; ++i) {
                    TreeNode tmp = q.poll();
                    res.add(tmp.val);
                }
                //完成，退出循环
                break;
            }

            //否则，还未到达距离为k的节点
            int len = q.size();
            //对当前队列中的每个点遍历其邻接点
            //并对没有之前没有遍历过的将其存入队列中
            for (int i = 0; i < len; ++i) {
                TreeNode tmp = q.poll();
                //该节点存在邻接点
                if (m.containsKey(tmp)) {
                    for (TreeNode node : m.get(tmp)) {
                        //此前已经遍历过该node
                        if (visited.contains(node))
                            continue;
                        q.offer(node);
                        visited.add(node);
                    }
                }
            }
            //距离减1
            --K;
        }
        return res;
    }

    //先序递归建立每个节点的邻接表
    private void helper(TreeNode node, TreeNode pre) {
        //node表示当前节点，pre表示其父节点
        if(node == null)
            return;
        //该种情况已经存在
        if (m.containsKey(node))
            return;
        
        //父节点不为空
        if (pre != null) {
            //将子节点存入父节点的邻接表中
            if (!m.containsKey(pre))
                m.put(pre, new ArrayList<>());
            m.get(pre).add(node);

            if (!m.containsKey(node))
                m.put(node, new ArrayList<>());
            //将父节点存入子节点的邻接表中
            m.get(node).add(pre);
        }

        //递归遍历左子树
        helper(node.left, node);
        //遍历右子树
        helper(node.right, node);
    }
    
}

