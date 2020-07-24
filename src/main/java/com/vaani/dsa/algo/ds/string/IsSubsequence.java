package com.vaani.dsa.algo.ds.string;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 */
public class IsSubsequence {
	static class UsingIterative {
		public boolean isSubsequence(String s, String t) {
			int cur = -1;
			String test = "";
			for (int i = 0; i < s.length(); i++) {
				if (cur < t.length())
					test = t.substring(cur + 1);
				else
					return false;
				// not found char in rest of t
				if (test.indexOf(s.charAt(i)) == -1) {
					return false;
					// recode index in t, don't forget the increament of cur since test is residue of t.
				} else {
					cur += test.indexOf(s.charAt(i)) + 1;
				}
			}
			return true;
		}

		public boolean isSubsequence2(String s, String t) {
			if(s == null || "".equals(s)){
				return true;
			}
			int idx = 0;
			for (int i = 0; i < t.length(); i++) {
				if(t.charAt(i) == s.charAt(i)){
					idx++;
				}
				if(idx >= s.length()){
					return true;
				}
			}
			return false;
		}

	}

	// submitted
	public static boolean isSubsequence(String s, String t) {
		return helper(s, t, s.length(), t.length());
	}

	// m is length of str1 and n is length of str2
	private static boolean helper(String str1, String str2, int m, int n) {
		// Base Cases
		if (m == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}

		// If last characters of two strings are matching
		if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
			return helper(str1, str2, m - 1, n - 1);
		}

		// If last characters are not matching
		return helper(str1, str2, m, n - 1);
	}
}
