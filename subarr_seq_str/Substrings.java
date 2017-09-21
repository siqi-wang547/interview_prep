import java.utils.*;
improt java.io.*;

public class Substrings {

	/ **
	** LC_76. Minimum Window Substring: find the minimal substring of s that contains all chars in t
	** Solution: 用[start,end]记录一个window,从[0,0]开始,到[0,end]满足条件时,增加start,并更新minimal_length,重复,直到遍历结束,返回最小window
	** Tricks: 1. 用一个map判断字母出现次数,并依此判断substring是否满足条件(cnt == 0);
	**         2. start右移时,如果start处字母满足条件(在t中出现过),则更改substring满足条件的边界条件(cnt++);
	**         3. end右移,如果[start,new_end]满足条件,重复
	*/
	public String minWindow(String s, String t) {
		if (s.length() < t.length() || t.length == 0) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c: t.toCharArray()) 
        	map.put(c, map.getOrDefault(c, 0) + 1);
        int cnt = map.size();
        int start = 0, end = 0;
        int len = Integer.MAX_VALUE;

        while (end < s.length()) {
        	char ch = s.charAt(end);
        	if (map.containsKey(ch)) {
        		map.put(ch, map.get(ch) - 1);
				if (map.get(ch) == 0) cnt--;
			}
			end++;
			while (cnt == 0) {
				char tmp = s.charAt(start);
				if (map.containsKey(tmp)) {
					map.put(tmp, map.get(tmp) + 1);
					if (map.get(tmp) > 0) cnt++;
				}
				if (end - start < len) {
					len = end - start;
					head = start;
				}
				start++;
			}
        }

        return len == Integer.MAX_VALUE? "": s.substring(head, head + len);
    }

    / **
    ** LC_438. Find All Anagrams in a String
    ** Solution: 和上题一致,仅判断substring是否满足的条件不同,且需保存所有满足条件时的值
    */
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> res = new ArrayList<>();
        if (s.length() < p.length() || p.length() == 0) return res;
        Map<Character, Integer> map = new HashMap<>();
        for (char c: p.toCharArray()) 
        	map.put(c, map.getOrDefault(c, 0) + 1);
        int cnt = map.size(), len = p.length();
        int start = 0, end = 0;

        while (end < s.length()) {
        	char ch = s.charAt(end);
        	if (map.containsKey(ch)) {
        		map.put(ch, map.get(ch) - 1);
        		if (map.get(ch) == 0) cnt--;
        	}
        	end++;
        	while (cnt == 0) {
        		char tmp = s.charAt(start);
        		if (map.containsKey(tmp)) {
        			map.put(tmp, map.get(tmp) + 1);
        			if (map.get(tmp) > 0) cnt++;
        		}
        		if (end - start == len) res.add(start);
        		start++;
        	}
        }
        return res;
    }

    / **
    ** LC_3. Longest Substring Without Repeating Characters
    ** Solution: 仅有一个string,没有一个smaller string情况,end右移map更新,用cnt记录判断是否有重复字母,有重复则start右移
    ** Tricks: start右移时,仅当如果start处字母满足条件(map.get>1),则更改substring满足条件的边界条件(cnt--)
    */

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int start = 0, end = 0, len = 0, cnt = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            end++;
            if (map.get(ch) > 1) cnt++;
            while (cnt > 0) {
                char tmp = s.charAt(start);
                if (map.get(tmp) > 1) cnt--; // if条件不可去掉
                map.put(tmp, map.get(tmp) - 1);
                start++;
            }
            
            len = Math.max(len, end - start);
        }
        return len;
    }

    / **
     * LC424 Longest Repeating Character Replacement
     * Solution: We either grow the window by appending one char on the right, or shift the whole window to the right by one. And we only grow the window when the count of the new char exceeds the historical max count (from a previous window that covers a valid substring).
     * Tricks: Max is not the accurate return value, nor is max+k. Accurate return value should be the window size.
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int[] map = new int[26];
        int start = 0, end = 0, max = 0;
        while (end < s.length()) {
            max = Math.max(max, ++map[s.charAt(end)-'A']);
            if (max + k < end - start + 1) {
                map[s.charAt(start)-'A']--;
                start++;
            }
            end++;
        }
        return s.length() - start;
    }
}