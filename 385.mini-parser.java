/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    //https://www.cnblogs.com/grandyang/p/5771434.html
    public NestedInteger deserialize(String s) {
        if(s.isEmpty())
            return new NestedInteger();
        if(s.charAt(0) != '[')
            return new NestedInteger(Integer.valueOf(s));
        Stack<NestedInteger> st = new Stack<>();
        int start = 1;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '[') {
                st.push(new NestedInteger());
                start = i + 1;
            } else if (s.charAt(i) == ',' || s.charAt(i) == ']') {
                if (i > start) {
                    st.peek().add(new NestedInteger(Integer.valueOf(s.substring(start, i))));
                }
                start = i + 1;
                if (s.charAt(i) == ']') {
                    //合并成一个总的nested
                    if (st.size() > 1) {
                        NestedInteger t = st.pop();
                        st.peek().add(t);
                    }
                }
            }
        }
        return st.peek();
    }
}