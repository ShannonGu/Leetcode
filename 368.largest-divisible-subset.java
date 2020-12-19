/*
 * @lc app=leetcode id=368 lang=java
 *
 * [368] Largest Divisible Subset
 */
class Solution {
    // https://blog.csdn.net/xindoo/article/details/53046600
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 考虑一个数学性质,整除的传递性;若a%b==0,b%c==0,则a%c==0;
        // 即c是b的因子,b是a的因子,那么c肯定是a的因子;
        // 那么就是要找出最长的整除链(a->b->c->d)
        // 这个整除链就满足两两整除的条件;
        List<Integer> res = new ArrayList<Integer>();
        int len = nums.length;
        if (len < 2) {
            if (len == 0)
                return res;
            res.add(nums[0]);
            return res;
        }

        // factorcount[i]表示以nums[i]结尾的整除链的长度;
        // prefactors[i]表示以nums[i]结尾的整除链前面一个数的下标;
        int[] factorcount = new int[len];
        int[] prefactors = new int[len];

        // mxLen表示最长整除链的长度，mxIdx表示其起始数的下标;
        int mxLen = 0, mxIdx = 0;

        // 先将数组排序;
        Arrays.sort(nums);

        // 从后向前将每个数作为整除链的末尾数;
        for (int i = len - 1; i >= 0; --i) {
            // 从末尾数开始向后遍历，更新以该数为末尾的最长整除链的长度;
            for (int j = i; j < len; ++j) {
                // nums[j]%nums[i]==0说明nums[i]可以接在以nums[j]为末尾的整除链的后面;
                // 并且更新以nums[i]为末尾的整除链的长度;
                if (nums[j] % nums[i] == 0 && factorcount[i] < factorcount[j] + 1) {
                    factorcount[i] = factorcount[j] + 1;
                    // 记录整除链中末尾数字的上一个数的下标;
                    prefactors[i] = j;
                    if (factorcount[i] > mxLen) {
                        mxLen = factorcount[i];
                        //mxIdx为整除链末尾数字的下标;
                        mxIdx = i;
                    }
                }
            }
        }

        for (int i = 0; i < mxLen; ++i) {
            //由于prefactors数组的存在，可以往前查找整除链的每一个数字;
            res.add(nums[mxIdx]);
            mxIdx = prefactors[mxIdx];
        }
        return res;
    }
}
