/*
 * @lc app=leetcode id=901 lang=java
 *
 * [901] Online Stock Span
 */
class StockSpanner {

    //https://zxi.mytechroad.com/blog/stack/leetcode-901-online-stock-span/
    public StockSpanner() {
        st = new Stack<>();
    }
    
    public int next(int price) {
        int span = 1;
        while (!st.isEmpty() && price >= (st.peek())[0]) {
            span += (st.peek())[1];
            st.pop();
        }
        
        st.push(new int[]{price, span});
        return span;
    }

    private Stack<int[]> st;
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

