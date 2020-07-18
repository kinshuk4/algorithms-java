package com.vaani.dsa.algo.ds.interval;
import com.vaani.dsa.ds.core.visual.Interval;

import java.util.*;

public class CoverIntervals {
    private static List<Integer> optimalPoints(List<Interval> intervals) {

        /* A safe way to cover a segment by a point: choose the right endpoint. If
         segments are sorted by minimun right endpoint first, then it means that
         if another segment started before the endpoint it will end after it or
         in the same point. The right endpoint will cover all other segments
         that started before or segments that start in the same endpoint
         */


        // Sort segments by minimun right endpoint
        intervals.sort(Comparator.comparingInt(a -> a.end));

        // Store optimal points used
        ArrayList<Integer> optimalPoints = new ArrayList<>();

        int optimalPoint = intervals.get(0).end;
        optimalPoints.add(optimalPoint);

        // Iterate through each segment end point and discard all the segments
        // that the endpoit covers
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);

            // if the point is not in the segment
            if (optimalPoint < interval.start || optimalPoint > interval.end) {
                optimalPoint = interval.end;
                optimalPoints.add(optimalPoint);
            }

        }

        return optimalPoints;

    }

}
