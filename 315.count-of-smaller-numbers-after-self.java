import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

//https://www.youtube.com/watch?v=2SVLYsq5W8M

// BIT解法
class Solution {
    //首先构建树状数组;
    class FenwickTree {
        private int[] sums;

        private int lowBit(int x) {
            return x & (-x);
        }

        public FenwickTree(int n) {
            sums = new int[n + 1];
        }

        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowBit(i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        //先将数组内元素插入到TreeSet中，自动排序;
        Set<Integer> nums_ = new TreeSet();
        for (int num : nums) {
            nums_.add(num);
        }

        //然后将数组内的元素与其相对大小映射起来;
        //从1开始
        Map<Integer, Integer> ranks = new HashMap();
        int rank = 0;
        Iterator it = nums_.iterator();
        //利用TreeSet自动的排序性质;
        //比如[1,6,2,5], 插入到TreeSet中变成[1, 2, 5, 6];
        //原数组的元素相应rank为[1, 4, 2, 3]; 
        while (it.hasNext()) {
            ranks.put((Integer)it.next(), ++rank);
        }

        List<Integer> res = new ArrayList();
        //以ranks的大小为参数传入
        //可以模拟出一个大小为ranks.size() + 1, freq数组, 初始值均为0;
        //FenwickTree构建出一个大小ranks.size() + 1的sums数组,
        //sums[i]用来求freq[0, i)的数之和(注意这里sums是从下标1开始存储的);
        //当得到一个数的rank时, 就在freq[rank]上加1;
        //表示当前这个数在原数组nums中已经出现的次数,然后根据sums,
        //求得在原数组中在对应rank = i的数右边的比该数小的数有多少个;
        FenwickTree T = new FenwickTree(ranks.size());

        //然后利用FenwickTree在更新元素的情况可以快速求累加和的性质;
        for (int i = nums.length - 1; i >= 0; --i) {
            //从数组最后一个元素开始
            //先找到其rank, 注意这里减1;
            //因为FenwickTree中sums数组是是从下标1开始存储的
            rank = ranks.get(nums[i]) - 1;

            //查询sums[rank],这里rank是nums[i]所对应rank减1后得到的;
            //因为要求的是比自己小的，所以不应包括本身
            res.add(T.query(rank));

            //然后在freq数组中该数对应的rank加上1;
            //注意这里update后一个参数是delta,
            //delta=1,表示该数出现1次，freq相应位置的值加1;
            T.update(rank + 1, 1);
        }
        Collections.reverse(res);
        return res;
    }
}



//BST解法
// class Solution {
//     class BSTNode {
//         //当前节点的值,即数组中的数;
//         int val;
//         //该值出现的次数;
//         int cnt;
//         //表示左子树中比当前节点小的节点个数
//         int left_cnt;

//         BSTNode left;
//         BSTNode right;

//         BSTNode(int val) {
//             this.val = val;
//             this.cnt = 1;
//             this.left_cnt = 0;
//             left = null;
//             right = null;
//         }

//         //当需要得出比当前数小的个数时;
//         int lessOrEqual() {
//             return cnt + left_cnt;
//         }
//     }

//     private int insert(BSTNode root, int val) {
//         if (root.val == val) {
//             ++root.cnt;
//             return root.left_cnt;
//         } else if (val < root.val) {
//             ++root.left_cnt;
//             if (root.left == null) {
//                 root.left = new BSTNode(val);
//                 return 0;
//             }
//             return insert(root.left, val);
//         }
//         else {
//             if (root.right == null) {
//                 root.right = new BSTNode(val);
//                 return root.lessOrEqual();
//             }
//             return root.lessOrEqual() + insert(root.right, val);
//         }
//     }
    

//     public List<Integer> countSmaller(int[] nums) {
//         if(nums.length == 0)
//             return new ArrayList<Integer>();
//         List<Integer> res = new ArrayList<>();
//         int n = nums.length;
//         BSTNode root = new BSTNode(nums[n - 1]);
//         res.add(0);
//         for (int i = n - 2; i >= 0; --i) {
//             res.add(insert(root, nums[i]));
//         }
//         Collections.reverse(res);
//         return res;
//     }
// }
