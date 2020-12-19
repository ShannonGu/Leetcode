/*
 * @lc app=leetcode id=135 lang=java
 *
 * [135] Candy
 */
class Solution {
    // https://zxi.mytechroad.com/blog/greedy/leetcode-135-candy/
    public int candy(int[] ratings) {
        //先有较高评分值的孩子要比两边的孩子有更多的糖果
        //所以可以遍历两遍，先从左到右，在从右到左
        int n = ratings.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        //先从左到右遍历，如果右边孩子的评分比左边孩子高，则右边孩子的糖果比左边孩子多一个
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1])
                res[i] = res[i - 1] + 1;
        }

        //在从右到左遍历，如果左边孩子评分比右边孩子高，则左边孩子的糖果至少比右边孩子多一个
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1])
                res[i] = Math.max(res[i], res[i + 1] + 1);
        }
        int sum = 0;
        for(int e : res)
            sum += e;
        return sum;
    }
}
