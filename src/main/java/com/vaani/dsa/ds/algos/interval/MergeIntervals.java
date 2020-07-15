package com.vaani.dsa.ds.algos.interval;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.vaani.dsa.ds.utils.simple.IntervalUtils.convertIntervalsToMatrix;
import static com.vaani.dsa.ds.utils.simple.IntervalUtils.convertMatrixToIntervals;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Given a collection of intervals, mergeIterative all overlapping intervals.
 * <p>
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */


public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = convertMatrixToIntervals(intervals);
        List<Interval> mergedIntervals = merge(intervalList);
        return convertIntervalsToMatrix(mergedIntervals);
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return result;
        Comparator<Interval> comparator = (i1, i2) -> {
            if (i1.start < i2.start) {
                return -1;
            } else if (i1.start > i2.start) {
                return 1;
            } else {//if starts are equal
                return Integer.compare(i1.end, i2.end);
            }
        };

        intervals.sort(comparator);

        for (Interval cur : intervals) {
            if (result.isEmpty()) {
                result.add(cur);
            } else {
                Interval last = result.get(result.size() - 1);
                if (last.end >= cur.start) {
                    last.end = Math.max(last.end, cur.end);
                } else {
                    result.add(cur);
                }
            }
        }
        return result;
    }
}
