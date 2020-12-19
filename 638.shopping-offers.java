import java.util.List;

/*
 * @lc app=leetcode id=638 lang=java
 *
 * [638] Shopping Offers
 */
class Solution {
    // https://www.cnblogs.com/grandyang/p/7261663.html
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int res = 0, n = price.size();
        for (int i = 0; i < n; ++i) {
            res += price.get(i) * needs.get(i);
        }

        for (List<Integer> offer : special) {
            boolean isValid = true;
            for (int j = 0; j < n; ++j) {
                //不能某项item买多了
                if (needs.get(j) - offer.get(j) < 0)
                    isValid = false;
                needs.set(j, needs.get(j) - offer.get(j));
            }
            if (isValid) {
                res = Math.min(res, shoppingOffers(price, special, needs) + offer.get(offer.size() - 1));
            }
            for (int j = 0; j < n; ++j) {
                needs.set(j, needs.get(j) + offer.get(j));
            }
        }
        return res;
    }
}
