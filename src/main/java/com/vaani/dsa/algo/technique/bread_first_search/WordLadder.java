package com.vaani.dsa.algo.technique.bread_first_search;

import java.util.*;
/* https://leetcode.com/problems/word-ladder/
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
*/

public class WordLadder {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        LinkedList<Count> queue = new LinkedList<Count>();
        queue.add(new Count(start, 1));
        visited.put(start, true);

        while (!queue.isEmpty()) {
            Count c = queue.poll();
            // for each character in the string, start new branches
            for (int i = 0; i < start.length(); i++) {
                StringBuilder sb = new StringBuilder(c.string);
                char sc = c.string.charAt(i);
                // for each different character as new node
                for (char cc = 'a'; cc <= 'z'; cc++) {
                    if (cc == sc) continue;
                    sb.setCharAt(i, cc);
                    String tmp = sb.toString();
                    // if we haven't visited this node and is in our dictionary
                    // we visit this node
                    if (visited.get(tmp) == null && dict.contains(tmp)) {
                        if (tmp.equals(end)) return c.count + 1;
                        visited.put(tmp, true);
                        queue.add(new Count(tmp, c.count + 1));
                    }
                }
            }
        }
        return 0;

    }

    class Count {
        //string
        String string;
        //the counts from start string to current string
        int count;

        public Count(String string, int count) {
            this.string = string;
            this.count = count;
        }
    }

    static class WordNode {
        String word;
        int numSteps;

        public WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        return ladderLength2(beginWord, endWord, new HashSet<>(wordList));
    }

    // cleaner with the external data structure
    public int ladderLength2(String beginWord, String endWord, Set<String> wordDict) {
        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        // No need to add the endwowrd if not part of dictionary as per new format
//        wordDict.add(endWord);

        while (!queue.isEmpty()) {
            WordNode curr = queue.poll();
            String currWord = curr.word;

            if (currWord.equals(endWord)) {
                return curr.numSteps;
            }

            char[] currArr = currWord.toCharArray();
            for (int i = 0; i < currArr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = currArr[i];
                    if (currArr[i] != c) {
                        currArr[i] = c;
                    }

                    String newWord = new String(currArr);
                    if (wordDict.contains(newWord)) {
                        queue.add(new WordNode(newWord, curr.numSteps + 1));
                        wordDict.remove(newWord);
                    }

                    currArr[i] = temp;// fix the string again
                }
            }
        }

        return 0;
    }

    // Without external Class WordNode, but less clean
    public int ladderLength3(String beginWord, String endWord, Set<String> wordDict) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String currWord = queue.poll();
                char[] currArr = currWord.toCharArray();
                for (int j = 0; j < currArr.length; j++) {
                    char temp = currArr[j];
                    for (char c = 'a'; c <= 'z'; c++) {

                        if (currArr[j] != c) {
                            currArr[j] = c;
                        }

                        String newWord = new String(currArr);

                        if (wordDict.contains(newWord)) {

                            // if new word is end word, just return now istead of adding to queue
                            // also end word should be part of dict, which we checked
                            if (newWord.equals(endWord)) {
                                return level + 1;
                            }
                            queue.add(newWord);
                            // remove to avoid duplicates
                            wordDict.remove(newWord);
                        }

                    }
                    currArr[j] = temp;// fix the string again
                }
            }

            level++;


        }

        return 0;
    }
}
