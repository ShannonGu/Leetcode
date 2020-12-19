/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */
class Solution {
    //https://www.youtube.com/watch?v=KB9IcSCDQ9k
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        //小trick，对奇偶情况统一处理
        int k = (n1 + n2 + 1) / 2;
        int l = 0, r = n1;
        while (l < r) {
            //在两个数组中找两个分割点，使得两个数组的左边部分元素个数和等于右边部分元素个数和
            //表示nums1数组左边部分有partition1个，注意不是下标;
            int partition1 = l + (r - l) / 2;
            //则nums2左边部分有partition2个
            int partition2 = k - partition1;

            //中位数只可能在nums1[partition1 - 1], nums1[partition1]
            //            nums2[partition2 - 1], nums2[partition2]中
            //当出现nums1[partiton1 - 1] <= nums2[partition2] && nums2[partition2 - 1] <= nums1[partition1]时

            //表示左半部分取的太少了
            if (nums1[partition1] < nums2[partition2 - 1])
                l = partition1 + 1;
            else
                r = partition1;
        }

        int partition1 = l, partition2 = k - l;
        
        //nums1数组左边部分最大数
        //partition1 == 0则要确保两个数组左边部分最大数为maxLeft2
        //故maxLeft1=Integer.MIN_VALUE;
        //partition2 == 0时同理
        int maxLeft1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
        
        //nums1数组右边部分最小数
        //partition1 == n1则要确保两个数组右边部分的最小数为minRight2;
        //故minRight1=Integer.MAX_VALUE
        //partition2 == n2时同理
        int minRight1 = partition1 == n1 ? Integer.MAX_VALUE : nums1[partition1];

        int maxLeft2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
        int minRight2 = partition2 == n2 ? Integer.MAX_VALUE : nums2[partition2];

        //总共奇数个数
        if ((n1 + n2) % 2 == 1)
            return Math.max(maxLeft1, maxLeft2) * 1.0;
        else {
            //偶数个
            int maxLeft = Math.max(maxLeft1, maxLeft2);
            int minRight = Math.min(minRigh1, minRight2);
            return (maxLeft + minRight) * 0.5;   
        }
    }


    //https://www.youtube.com/watch?v=LPFhl65R7ww
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int x = nums1.length, y = nums2.length;
    //     if(x > y)
    //         return findMedianSortedArrays(nums2, nums1);
    //     int l = 0, r = x;
    //     int k = (x + y + 1) / 2;

    //     //注意'='
    //     while (l <= r) {
    //         //在两个数组中找两个分割点，使得两个数组的左边部分元素个数和等于右边部分元素个数和
    //         //表示nums1数组左边部分有partitionX个，注意不是下标;
    //         int partitionX = l + (r - l) / 2;

    //         //表示nums2数组左边部分有partitionY个
    //         int partitionY = k - partitionX;
            
    //         //partitionX == 0则要确保两个数组左边部分最大数为maxLeftY
    //         //故maxLeftX=Integer.MIN_VALUE;
    //         //partitionY == 0时同理
    //         //nums1数组左边部分最大数
    //         int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
    //         //partitionX == x则要确保两个数组右边部分的最小数为minRightY;
    //         //故minRightX=Integer.MAX_VALUE
    //         //partitionY == x时同理
    //         //nums1数组右边部分最小数
    //         int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

    //         //nums2数组左边部分最大数
    //         int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
    //         //nums2数组右边部分最小数
    //         int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];


    //         //满足划分条件,两个数组左边部分 <= 右边部分;
    //         if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
    //             //总个数为偶数
    //             if ((x + y) % 2 == 0) {
    //                 //取最中间两数的平均值;
    //                 return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) * 0.5;
    //             } else {
    //                 //总个数为奇数
    //                 //取最中间数;
    //                 return Math.max(maxLeftX, maxLeftY) * 1.0;
    //             }
    //         } else if (maxLeftX > minRightY) {
    //             //nums1数组左边部分取多了
    //             //数量
    //             r = partitionX - 1;
    //         } else {
    //             //nums1数组左边部分取少了
    //             l = partitionX + 1;
    //         }
    //     }
    //     //数组不满足题目要求;
    //     throw new IllegalArgumentException();
    // }
}
