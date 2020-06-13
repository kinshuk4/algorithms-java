package com.vaani.dsa.ds.algos.tree.binary.traversal.view;

import java.util.*;

public class Temp {
    public static void main(String[] args) {
        List<Integer> cities = new ArrayList<>();
//        cities.add(1);
//        cities.add(3);
//        cities.add(6);
//        cities.add(2);
//        cities.add(4);

        cities.add(2);
        cities.add(6);
        cities.add(2);
        cities.add(3);
        cities.add(4);
        cities.add(8);
        System.out.println(countRoutes(5, cities));

    }

//    public static int countRoutes(int fuel, List<Integer> cities) {
//        // Write your code here
//        int count = 0;
//        Set<Set<Integer>> visited = new HashSet<>();
//        for (int i = 0; i < cities.size() - 1; i++) {
//            Set<Integer>
//            count += countRoutesUtil(fuel, cities, i, i + 1, visited);
//        }
//        return count - 1;
//    }
//
//    private static int countRoutesUtil(int fuelPrev, List<Integer> cities, int prevIndex, int currIndex, Set<Integer> visited) {
//        if (prevIndex == cities.size() - 1 || currIndex >= cities.size() || cities.get(prevIndex).equals(cities.get(currIndex)) || visited.contains(currIndex)) {
//            return 0;
//        } else {
//            int fuelUsed = Math.abs(cities.get(prevIndex) - cities.get(currIndex));
//            int fuelRemaining = fuelPrev - fuelUsed;
//            if (fuelRemaining < 0) {
//
//                int result = countRoutesUtil(fuelPrev, cities, prevIndex, currIndex + 1, visited);
//                return result;
//            } else {
//                visited.add(currIndex);
//                int result = 1 + countRoutesUtil(fuelRemaining, cities, currIndex, currIndex + 1, visited);
//                visited.remove(currIndex);
//                return result;
//            }
//        }
//    }

    public static int countRoutes(int fuel, List<Integer> cities) {
        // Write your code here
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        for (int i = 1; i < cities.size() ; i++) {
            int fuelUsed = Math.abs(cities.get(0) - cities.get(i));
            count += countRoutesUtil(fuel - fuelUsed, cities, 0, i , visited);
        }
        return count ;
    }

    private static int countRoutesUtil(int fuelPrev, List<Integer> cities, int prevIndex, int currIndex, Set<Integer> visited) {
        if (fuelPrev < 0 || prevIndex == cities.size() - 1 || currIndex >= cities.size() || cities.get(prevIndex) == cities.get(currIndex) || visited.contains(currIndex)) {
            return 0;
        } else {
            int fuelUsed = Math.abs(cities.get(prevIndex) - cities.get(currIndex));
            int fuelRemaining = fuelPrev - fuelUsed;
            if (fuelRemaining < 0) {
                visited.add(currIndex + 1);
                int result = countRoutesUtil(fuelRemaining, cities, prevIndex, currIndex + 1, visited);
                visited.remove(currIndex + 1);
                return result;
            } else {
                visited.add(currIndex );
                int result = 1 + countRoutesUtil(fuelRemaining, cities, currIndex, currIndex + 1, visited);
                visited.remove(currIndex);
                return result;
            }
        }
    }


//    public static int countRoutes(int fuel, List<Integer> cities) {
//        // Write your code here
//        int count =0;
//        for(int i=0;i<cities.size()-1;i++)
//            count+=generateRoutes(i , fuel,i+1, cities);
//        return count ;
//    }
//
//    private static int generateRoutes(int prevIndex, int remainingFuel ,int currentIndex , List<Integer> cities){
//        if(prevIndex==cities.size()-1 || currentIndex>=cities.size() || cities.get(prevIndex)==cities.get(currentIndex))
//            return 0;
//        else if (Math.abs(cities.get(prevIndex) - cities.get(currentIndex))> remainingFuel)
//            return generateRoutes(prevIndex , remainingFuel , currentIndex+1 , cities);
//        else
//            return   1+ generateRoutes(currentIndex , remainingFuel -Math.abs(cities.get(prevIndex) - cities.get(currentIndex)) ,currentIndex+1 ,  cities );
//    }


}
