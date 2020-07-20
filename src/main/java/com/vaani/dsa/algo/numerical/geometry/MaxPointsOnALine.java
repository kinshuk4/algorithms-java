package com.vaani.dsa.algo.numerical.geometry;

import com.vaani.dsa.ds.core.visual.Point;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * <p>
 */
public class MaxPointsOnALine {
    int max = 0;

    public static void main(String[] args) {
        Point[] points = {
                new Point(1, 1),
                new Point(1, 1),
                new Point(1, 1),
        };
        MaxPointsOnALine test = new MaxPointsOnALine();
        System.out.println(test.maxPoints(points));
    }

    public int maxPoints(Point[] points) {
        if (points.length <= 2) return points.length;

        Map<Double, Integer> map = new HashMap<>();
        int vertical = 1;
        int horizontal = 1;
        int dup = 0;

        for (int i = 0; i < points.length; i++) {
            Point base = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                Point point = points[j];
                if (base.x == point.x && base.y == point.y) {
                    dup++;
                } else if (base.x == point.x) {
                    vertical++;
                } else if (base.y == point.y) {
                    horizontal++;
                } else {
                    double slope = 10000 * (base.y - point.y) / (base.x - point.x);
                    if (!map.containsKey(slope)) {
                        map.put(slope, 1);
                    }
                    map.put(slope, map.get(slope) + 1);
                }
            }
            max = findMax(map, vertical, horizontal, dup);
            vertical = 1;
            horizontal = 1;
            dup = 0;
            map.clear();
        }
        return max;
    }

    private int findMax(Map<Double, Integer> map, int vertical, int horizonal, int dup) {
        if (map.isEmpty()) {
            max = Math.max(Math.max(vertical, horizonal), dup + 1);
            return max;
        }

        for (int num : map.values()) {
            max = Math.max(max, num + dup);
        }
        max = Math.max(Math.max(vertical, horizonal), max);
        return max;
    }

    public int maxPoints2Setter(int[][] points) {
        Point[] arr = new Point[points.length];
        int i = 0;
        for (int[] point : points) {
            arr[i++] = new Point(point[0], point[1]);
        }
        return maxPoints2(arr);
    }

    public int maxPoints2(Point[] points) {
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            Map<BigDecimal, Integer> map = new HashMap<>();
            int dups = 0, verticals = 0;

            for (int j = i + 1; j < points.length; j++) {
                Point p1 = points[i], p2 = points[j];

                if (p1.x == p2.x && p1.y == p2.y) {
                    dups++;
                } else if (p1.x == p2.x) {
                    verticals++;
                } else if (p1.y == p2.y) {// no need to handle it separately, as x1-x2 is not 0, but doing it for clarity
                    map.put(BigDecimal.ZERO, map.getOrDefault(BigDecimal.ZERO, 0) + 1);
                } else {
//                    double k = (double) (p2.y - p1.y) / (p2.x - p1.x);
                    // normal double will not work for test case:[[0,0],[94911151,94911150],[94911152,94911151]]
                    BigDecimal k = BigDecimal.valueOf(p2.y - p1.y).divide(BigDecimal.valueOf(p2.x - p1.x), new MathContext(20));
                    map.put(k, map.getOrDefault(k, 0) + 1);
                }
            }

            int localMax = 0;
            for (Map.Entry<BigDecimal, Integer> entry : map.entrySet()) {
                localMax = Math.max(localMax, entry.getValue());
            }
            localMax = localMax + dups + 1;// note we are adding dups as well

            localMax = Math.max(localMax, verticals + dups + 1);
            result = Math.max(localMax, result);
        }
        return result;

    }

    //https://www.youtube.com/watch?v=KqZV0XfKQks
    public int maxPoints3Memo(Point[] points) {
        return -1;

    }
}
