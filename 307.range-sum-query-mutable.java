/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 */



// https://zxi.mytechroad.com/blog/sp/fenwick-tree-binary-indexed-tree-sp3/
// 构建数状数组Binary-Indexed-tree(FenwickTree)
class NumArray {

    class FenwickTree {
        //用于存放BIT中每个节点的值
        //数组从下标1开始存放
        //sums[i]表示前i个元素的和;
        private int[] sums;

        //构造函数
        public FenwickTree(int n) {
            sums = new int[n + 1];
        }

        //取得x二进制最低位的1,所代表的数
        //lowBit(3) = lowBit(5) = 1 =  2 ^ 0
        //lowBit(4) = 4 = 2 ^ 2, lowBit(6) = 2 = 2 ^ 1;
        private int lowBit(int x) {
            return x & (-x);
        }

        //当数组中某个数更新时，要更新BIT中相应的受影响的节点;
        //时间复杂度O(logn)
        //delta为数组变化前后同位置的两个数的差
        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowBit(i);
            }
        }

        //查找前i个元素的和;
        //时间复杂度O(logn)
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
    }
    

    FenwickTree T;
    int[] nums_;
    public NumArray(int[] nums) {
        int n = nums.length;
        nums_ = new int[n];
        T = new FenwickTree(n);
        for (int i = 0; i < n; ++i) {
            nums_[i] = nums[i];
            //注意这里是i+1,因为sums数组是从下标1开始存放的;
            T.update(i + 1, nums_[i]);
        }
    }
    
    public void update(int i, int val) {
        T.update(i + 1, val - nums_[i]);
        nums_[i] = val;
    }
    
    public int sumRange(int i, int j) {
        //注意这里为j+1
        //原因同上
        return T.query(j + 1) - T.query(i);
    }
}

//https://www.jianshu.com/p/91f2c503e62f
//数组模拟Segment Tree
// class NumArray {

//     //构建segment tree类
//     class SegmentTree {
//         int[] seg;

//         public SegmentTree(int n) {
//             seg = new int[3 * n];
//         }

//         public void build(int[] nums, int start, int end, int idx) {
//             if (start == end)
//                 seg[idx] = nums[start];
//             else {
//                 int mid = start + ((end - start) >> 1);

//                 build(nums, start, mid, 2 * idx + 1);

//                 build(nums, mid + 1, end, 2 * idx + 2);

//                 seg[idx] = seg[2 * idx + 1] + seg[2 * idx + 2];
//             }
//         }

//         public void update(int start, int end, int pos, int val, int idx) {
//             //pos为要更新的值在区间中的位置
//             //val为要更新的值
//             if (start == end) {
//                 seg[idx] = val;
//                 return;
//             }

//             int mid = start + ((end - start) >> 1);
//             //确定pos在的范围
//             if (pos <= mid) {
//                 update(start, mid, pos, val, 2 * idx + 1);
//             } else {
//                 update(mid + 1, end, pos, val, 2 * idx + 2);
//             }
//             seg[idx] = seg[2 * idx + 1] + seg[2 * idx + 2];
//         }

//         public int sumRange(int start, int end, int i, int j, int idx) {
//             if (start > j || end < i)
//                 return 0;

//             //[start, end]完全在[i,j]内部
//             if (i <= start && j >= end)
//                 return seg[idx];

//             //[i, j]与[start, end]部分相交，或者[i, j]完全在[start, end]内部;
//             int mid = start + ((end - start) >> 1);
//             //求[start, mid]与[i, j]相交部分之和加上[mid + 1, end]与[i, j]相交部分之和
//             return sumRange(start, mid, i, j, 2 * idx + 1) + sumRange(mid + 1, end, i, j, 2 * idx + 2);
//         }
//     }
    
//     SegmentTree T;
//     int len;

//     public NumArray(int[] nums) {
//         len = nums.length;
//         //注意判断len，防止溢出
//         if (len > 0) {
//             T = new SegmentTree(len);
//             T.build(nums, 0, len - 1, 0);
//         }
//     }

//     public void update(int i, int val) {
//         T.update(0, len - 1, i, val, 0);
//     }

//     public int sumRange(int i, int j) {
//         return T.sumRange(0, len - 1, i, j, 0);
//     }
// }






//https://www.jianshu.com/p/91f2c503e62f
//二叉树
// class NumArray {

//     class segTreeNode {
//         public int start, end;
//         public int sum;
//         public segTreeNode left, right;

//         public segTreeNode(int start, int end, int sum) {
//             this.start = start;
//             this.end = end;
//             this.sum = sum;
//             this.left = this.right = null;
//         }
//     }

//     class segTree {
//         segTreeNode rootNode;

//         public segTree(int start, int end, int sum) {
//             rootNode = new segTreeNode(start, end, sum);
//         }

//         public segTreeNode build(int start, int end, int[] nums) {
//             if (start > end)
//                 return null;
//             segTreeNode root = new segTreeNode(start, end, 0);

//             if (start != end) {
//                 int mid = start + ((end - start) >> 1);
//                 root.left = build(start, mid, nums);
//                 root.right = build(mid + 1, end, nums);
//                 root.sum = root.left.sum + root.right.sum;
//             } else {
//                 root.sum = nums[start];
//             }
//             return root;
//         }

//         public int sumRange(segTreeNode root, int start, int end) {
//             if (start == root.start && end == root.end)
//                 return root.sum;
//             int mid = root.start + ((root.end - root.start) >> 1);
//             int leftSum = 0, rightSum = 0;
//             if (start <= mid) {
//                 if (mid < end) {
//                     leftSum = sumRange(root.left, start, mid);
//                 } else {
//                     leftSum = sumRange(root.left, start, end);
//                 }
//             }

//             if (mid < end) {
//                 if (start <= mid) {
//                     rightSum = sumRange(root.right, mid + 1, end);
//                 } else {
//                     rightSum = sumRange(root.right, start, end);
//                 }
//             }
//             return leftSum + rightSum;
//         }

//         public void update(segTreeNode root, int index, int val) {
//             if (root.start == index && root.end == index) {
//                 root.sum = val;
//                 return;
//             }

//             int mid = root.start + ((root.end - root.start) >> 1);
//             if (root.start <= index && index <= mid) {
//                 update(root.left, index, val);
//             }

//             if (mid < index && index <= root.end) {
//                 update(root.right, index, val);
//             }
//             root.sum = root.left.sum + root.right.sum;
//         }
//     }

//     segTree T;

//     public NumArray(int[] nums) {
//         T = new segTree(0, nums.length - 1, 0);
//         T.rootNode = T.build(0, nums.length - 1, nums);
//     }

//     public void update(int i, int val) {
//         T.update(T.rootNode, i, val);
//     }

//     public int sumRange(int i, int j) {
//         return T.sumRange(T.rootNode, i, j);
//     }
// }
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
