package com.vaani.dsa.algo.technique.depth_first_search;

import java.util.Arrays;

/**
 * Flood Fill: https://leetcode.com/problems/flood-fill/
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * <p>
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * <p>
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 * <p>
 * At the end, return the modified image.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * <p>
 * Note:
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class FloodFill {
    public static void main(String[] args) {
        //
    }

    static class UsingDFS1 {
        final int[] R = {1, -1, 0, 0};
        final int[] C = {0, 0, -1, 1};

        boolean[][] visited;

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            visited = new boolean[image.length][image[0].length];
//        int[][] copy = copyArray(image); - old method
            int[][] copy = Arrays.stream(image).map(int[]::clone).toArray(int[][]::new);
            dfs(copy, sr, sc, image[sr][sc], newColor);
            return copy;
        }


        private void dfs(int[][] image, int r, int c, int c1, int c2) {
            visited[r][c] = true;
            image[r][c] = c2;
            for (int i = 0; i < 4; i++) {
                int newR = r + R[i];
                int newC = c + C[i];
                if (newR >= 0 && newC >= 0 && newR < image.length && newC < image[0].length
                        && image[newR][newC] == c1 && !visited[newR][newC]) {
                    dfs(image, newR, newC, c1, c2);
                }
            }
        }

        private int[][] copyArray(int[][] image) {
            int[][] copy = new int[image.length][image[0].length];
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    copy[i][j] = image[i][j];
                }
            }
            return copy;
        }
    }

    static class UsingDFS2 {
        // faster and better - submitted
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int color = image[sr][sc];
            if (color != newColor) {
                dfs(image, sr, sc, color, newColor);
            }
            return image;
        }

        public void dfs(int[][] image, int r, int c, int color, int newColor) {
            if (r < 0 || c < 0 || r >= image.length || c >= image[0].length)
                return;
            if (image[r][c] == color) {
                image[r][c] = newColor;
                dfs(image, r - 1, c, color, newColor);
                dfs(image, r, c - 1, color, newColor);
                dfs(image, r + 1, c, color, newColor);
                dfs(image, r, c + 1, color, newColor);
            }
        }
    }


}
