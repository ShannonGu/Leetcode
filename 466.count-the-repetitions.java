/*
 * @lc app=leetcode id=466 lang=java
 *
 * [466] Count The Repetitions
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/6149294.html
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        // repeatCnt[i]表示当遍历完第i个s1出现了几个s2, 这里i从1开始计数
        int[] repeatCnt = new int[n1 + 1];
        // nextIdx[i]表示遍历完第i个s1时,s2的下一个字符是第几个, 这里i从1开始计数
        int[] nextIdx = new int[n1 + 1];

        int j = 0, cnt = 0;
        // 遍历每一个s1
        for (int k = 1; k <= n1; ++k) {
            for (int i = 0; i < s1.length(); ++i) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    ++j;
                    if (j == s2.length()) {
                        j = 0;
                        ++cnt;
                    }
                }
            }
            // 遍历完第k个s1出现了cnt个s2
            repeatCnt[k] = cnt;
            // 下一个s1中s2的第j个字符第一个出现
            nextIdx[k] = j;

            // 寻找s2出现的第一个pattern, 之前的不是pattern
            for (int st = 0; st < k; ++st) {
                // 第一次重复
                if (nextIdx[st] == j) {
                    // 一个pattern占用s1的个数
                    int interval = k - st;
                    // 从st开始, n1个st中总共有几个interval个连续的s1, 也即pattern的个数
                    int repeat = (n1 - st) / interval;
                    // 从st开始出现的pattern中的s2总个数, 其中repeatCnt[k] - repeatCnt[st]表示一个pattern表示有几个s2
                    int s2Cnt = (repeatCnt[k] - repeatCnt[st]) * repeat;
                    // 最后还剩的非pattern的字符串中还有几个s2
                    int remainCnt = repeatCnt[st + (n1 - st) % interval];
                    return (s2Cnt + remainCnt) / n2;
                }
            }
        }

        // 如果pattern未层出现, 直接用n1个s1中出现的s2个数计算S2的个数
        return repeatCnt[n1] / n2;
    }

}
