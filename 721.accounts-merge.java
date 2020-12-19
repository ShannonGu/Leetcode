import java.util.HashSet;
import java.util.Map;

/*
 * @lc app=leetcode id=721 lang=java
 *
 * [721] Accounts Merge
 */
class Solution {
    // 因为这里accounts长度最大为1000
    // 每个账户名包含的邮箱最多为10
    // 所以如果每个邮箱都是不同的，最多有1000 * 10 = 10000个邮箱，需要10000个id
    private int[] parents = new int[10001];

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (int i = 0; i <= 10000; ++i) {
            parents[i] = i;
        }

        // 建立每一个账户的邮箱与其账户名之间的映射
        Map<String, String> mailToName = new HashMap<>();
        // 建立每一个邮箱与其ID之间的映射
        Map<String, Integer> mailToID = new HashMap<>();

        int id = 0;
        for (List<String> account : accounts) {
            // 记录账户名
            String name = "";
            for (String mail : account) {
                // 如果此时name为"",说明当前遍历到的账户名
                if (name.equals("")) {
                    name = mail;
                    continue;
                }

                // 否则，将遍历到的邮箱与账户名建立映射
                mailToName.put(mail, name);

                // 如果当前邮箱还没有对应的ID
                if (!mailToID.containsKey(mail)) {
                    mailToID.put(mail, id++);
                }

                // 将每一个账户名下所有邮箱都归并同一个ID下
                unite(mailToID.get(account.get(1)), mailToID.get(mail));
            }
        }

        // 建立每个根ID与该ID下的所有邮箱之间的映射
        Map<Integer, List<String>> res = new HashMap<>();
        for (String mail : mailToName.keySet()) {
            // 找到根ID
            int index = find(mailToID.get(mail));
            // 将邮箱加入到该根ID的列表中
            res.computeIfAbsent(index, x -> new ArrayList<>()).add(mail);
        }

        // 将每个根ID的列表进行排序
        // 并加上账户名
        for (List<String> val : res.values()) {
            Collections.sort(val);
            val.add(0, mailToName.get(val.get(0)));
        }
        return new ArrayList<>(res.values());
    }

    private int find(int x) {
        while (parents[x] != x) {
            x = parents[x];
        }
        return x;
    }

    private void unite(int x, int y) {
        parents[find(y)] = find(x);
    }
}
