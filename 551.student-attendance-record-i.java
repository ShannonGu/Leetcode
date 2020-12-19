/*
 * @lc app=leetcode id=551 lang=java
 *
 * [551] Student Attendance Record I
 */

// @lc code=start
class Solution {
    public boolean checkRecord(String s) {
        int cntA = 0, cntL = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                ++cntA;
                if (cntA > 1)
                    return false;
                cntL = 0;
            } else if (c == 'L') {
                ++cntL;
                if (cntL > 2)
                    return false;
            } else {
                cntL = 0;
            }
        }
        return true;
    }
}
// @lc code=end

