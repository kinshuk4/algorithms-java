package com.vaani.dsa.ds.utils.simple;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalUtils {
    public static List<Interval> convertMatrixToIntervals(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int[] i : intervals) {
            intervalList.add(new Interval(i[0], i[1]));
        }
        return intervalList;
    }

    public static int[][] convertIntervalsToMatrix(List<Interval> intervals) {
        int[][] intervalMatrix = new int[intervals.size()][];

        for (int i = 0; i < intervals.size(); i++) {
            intervalMatrix[i] = new int[]{intervals.get(i).start, intervals.get(i).end};
        }
        return intervalMatrix;
    }


}
