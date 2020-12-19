import java.util.Arrays;

/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (k > arr.length)
            return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        int len = arr.length;
        if (x > arr[len - 1]) {
            int i = len - 1;
            while (k-- > 0)
                res.add(arr[i--]);
            return res;
        } else if (x < arr[0]) {
            int i = 0;
            while (k-- > 0)
                res.add(arr[i++]);
            return res;
        }

        // int l = 0, r = len - 1;
        // while (l < r) {
        //     int mid = l + r >> 1;
        //     if (arr[mid] >= x)
        //         r = mid;
        //     else
        //         l = mid + 1;
        // }
        // int i = l - 1, j = r;
        // if (arr[r] == x) {
        //     res.add(x);
        //     k--;
        //     j = r + 1;
        // }

        //通过自带二分查找函数寻找
        int idx = Arrays.binarySearch(arr, x);
        int i = 0, j = 0;
        //idx>=0说明x在arr[]中
        if (idx >= 0) {
            i = idx - 1;
            j = idx + 1;
            res.add(x);
            k--;
        } else {
            //否在不在arr[]中
            //idx = -(插入点) - 1, 插入点为第一个>待查找数的索引
            i = -idx - 2;
            j = -idx - 1;
        }

        while (k-- > 0) {
            if (i >= 0 && j < len) {
                if (x - arr[i] <= arr[j] - x) {
                    res.add(0, arr[i]);
                    --i;
                } else {
                    res.add(arr[j]);
                    ++j;
                }
            } else if (i >= 0) {
                res.add(0, arr[i]);
                --i;
            } else {
                res.add(arr[j]);
                ++j;
            }
        }
        return res;
    }
}

