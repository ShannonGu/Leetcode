import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4781203.html
    public int hIndex(int[] citations) {
        //先从大到小排序
        //然后从前往后遍历,知道某篇论文的索引大于其被引次数
        citations = IntStream.of(citations)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        // List<Integer> res = Arrays.stream(citations).boxed().collect(Collectors.toList());
        // Collections.sort(res, Collections.reverseOrder());
        // citations = res.stream().mapToInt(Integer::intValue).toArray();

        for (int i = 0; i < citations.length; ++i) {
            if (i >= citations[i])
                return i;
        }

        //否则返回整个数组大小
        return citations.length;
    }
}

