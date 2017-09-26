package backtrack;

import java.util.*;

/**
	LC131 Palindrome Partitioning
	Tags: String, Backtracking
	Date: Mar. 19, 2017
**/

/**
	Basic idea is to use DFS backtracking to traverse all possible solutions, DP is also used to record if a substring
	is a valid palindrome given start and end index.
	Note: every time you reach a successful search you go down in the search tree, the integer k you are going to give
	shoud start from the next index of the end index of your last valid substring. (Line32, i+1)
	Time Complexity: O(2^n) according to https://discuss.leetcode.com/topic/1524/shouldn-t-we-use-dp-in-addition-to-dfs
**/

public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] palin = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++)
                palin[i][j] = isPalindrome(s, i, j);
        }
        List<String> list = new ArrayList<>();
        getResult(res, list, s, palin, 0);
        return res;
    }
    
    private void getResult(List<List<String>> res, List<String> list, String s, boolean[][] palin, int k) {
        if (k == s.length()) res.add(new ArrayList(list));
        for (int i = k; i < s.length(); i++) {
            if (palin[k][i]) {
                list.add(s.substring(k, i+1));
                getResult(res, list, s, palin, i+1); // ****
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            //System.out.println("i: " + i + ", j: " + j);
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    
}