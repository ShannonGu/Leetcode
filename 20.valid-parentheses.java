/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */
class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> m = new HashMap<>();
        m.put(')', '(');
        m.put(']', '[');
        m.put('}', '{');
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                st.push(c);
            else {
                if (st.isEmpty())
                    return false;
                else {
                    char t = st.pop();
                    if (t != m.get(c))
                        return false;
                }
            }
        }
        return st.isEmpty();
    }
}
