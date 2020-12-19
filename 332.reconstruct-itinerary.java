import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * @lc app=leetcode id=332 lang=java
 *
 * [332] Reconstruct Itinerary
 */
class Solution {
    //使用hashmap映射每个起点与其邻接点，并用优先队列存入这些邻接点
    // Map<String, PriorityQueue<String>> targets = new HashMap<>();
    // List<String> route;

    // public List<String> findItinerary(List<List<String>> tickets) {
    //     //遍历所有起点
    //     for(List<String> ticket : tickets)
    //         targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
    //     List<String> route = new ArrayList<>();
    //     //使用栈存储路线
    //     Stack<String> st = new Stack<>();
    //     st.push("JFK");//起点
    //     while (!st.isEmpty()) {
    //         //栈顶是上一轮的终点，下一轮的起点
    //         String cur = st.peek();
    //         while (targets.containsKey(cur) && !targets.get(cur).isEmpty()) {
    //             String nxt = targets.get(cur).poll();
    //             st.push(nxt);
    //             cur = st.peek();
    //         }
    //         //逆序存入结果列表中
    //         route.add(0, st.pop());
    //     }
    //     return route;
    // }


    Map<String, PriorityQueue<String>> targets = new HashMap();
    List<String> route = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        }
        visit("JFK");
        return route;
    }
    
    private void visit(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty()) {
            String nxt = targets.get(airport).poll();
            visit(nxt);
        }
        route.add(0, airport);
    }

}

