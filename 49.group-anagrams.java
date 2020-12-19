import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/4385822.html
    // public List<List<String>> groupAnagrams(String[] strs) {
    //     List<List<String>> res = new ArrayList<>();
    //     Map<String, List<String>> map = new HashMap<>();
    //     for (String str : strs) {
    //         char[] t = str.toCharArray();
    //         Arrays.sort(t);
    //         String key = String.valueOf(t);
    //         if(!map.containsKey(key))
    //             map.put(key, new ArrayList());
    //         map.get(key).add(str);
    //     }
    //     // Iterator it = map.entrySet().iterator();
    //     // while (it.hasNext()) {
    //     //     Map.Entry entry = (Map.Entry) it.next();
    //     //     res.add((List) entry.getValue());
    //     // }
    //     // return res;
    //     return new ArrayList(map.values());
    // }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] nums = new int[26];
        for (String str : strs) {
            Arrays.fill(nums, 0);
            for (char c : str.toCharArray())
                nums[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append("#");
                sb.append(num);
            }
            String key = sb.toString();
            if (!map.containsKey(key))
                map.put(key, new ArrayList());
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }
}

