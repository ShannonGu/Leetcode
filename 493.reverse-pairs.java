/*
 * @lc app=leetcode id=493 lang=java
 *
 * [493] Reverse Pairs
 *
 * https://leetcode.com/problems/reverse-pairs/description/
 *
 * algorithms
 * Hard (23.12%)
 * Likes:    510
 * Dislikes: 79
 * Total Accepted:    24.6K
 * Total Submissions: 106.1K
 * Testcase Example:  '[1,3,2,3,1]'
 *
 * Given an array nums, we call (i, j) an important reverse pair if i < j and
 * nums[i] > 2*nums[j].
 * 
 * You need to return the number of important reverse pairs in the given
 * array.
 * 
 * Example1:
 * 
 * Input: [1,3,2,3,1]
 * Output: 2
 * 
 * 
 * Example2:
 * 
 * Input: [2,4,3,5,1]
 * Output: 3
 * 
 * 
 * Note:
 * 
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 32-bit integer.
 * 
 * 
 */
class Solution {
    //https://leetcode.com/problems/reverse-pairs/discuss/97268/General-principles-behind-problems-similar-to-%22Reverse-Pairs%22
    //利用树状数组
    class FenwickTree{
        int[] sums;
        public FenwickTree(int n){
            sums = new int[n+1];
        }
        private int lowBit(int x){
            return x & (-x);
        }
        
        public void update(int i, int delta){
            while(i < sums.length){
                sums[i] += delta;
                i += lowBit(i);
            }
        }
        
        public int query(int i){
            int sum = 0;
            while(i > 0){
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
    }
    

    public int reversePairs(int[] nums) {
        int n = nums.length;

        //set去重
        Set<Long> set = new TreeSet<>();
        for (int num : nums) {
            set.add((long)num);
            set.add((long) num * 2);
        }

        //记录每个新数组每个元素的相对大小
        Map<Long, Integer> ranks = new HashMap<>();
        Iterator it = set.iterator();
        int rank = 0;
        while (it.hasNext()) {
            ranks.put((long) it.next(), ++rank);
        }
        FenwickTree T = new FenwickTree(ranks.size());
        int res = 0;

        for (int i = n - 1; i >= 0; --i) {
            //从后往前,先取原数组每个元素的相对大小
            rank = ranks.get((long) nums[i]) - 1;
            //在BIT中查找比其小的元素
            res += T.query(rank);
            //由于需要原数组元素与原数组元素的两倍进行比较
            //所以原数组元素插入到BIT中以0作为其出现次数
            T.update(rank + 1, 0);

            //在将元素两倍后的值的相对大小插入到BIT中
            rank = ranks.get((long) 2 * (long) nums[i]);
            T.update(rank, 1);
        }
        return res;
    }
}

