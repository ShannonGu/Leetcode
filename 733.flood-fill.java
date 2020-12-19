/*
 * @lc app=leetcode id=733 lang=java
 *
 * [733] Flood Fill
 */
class Solution {
    private int[][] dires = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private int m, n;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor)
            return image;
        m = image.length;
        n = image[0].length;
        helper(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }
    
    private void helper(int[][] image, int i, int j, int newColor, int color) {
        if(i < 0 || i == m || j < 0 || j == n || image[i][j] != color)
            return;
        image[i][j] = newColor;
        for (int[] dire : dires) {
            int x = i + dire[0], y = j + dire[1];
            helper(image, x, y, newColor, color);
        }
    }
}

