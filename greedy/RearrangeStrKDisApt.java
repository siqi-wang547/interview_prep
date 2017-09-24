package greedy;

public class RearrangeStrKDisApt {

    /**
     * LC358. Rearrange String k Distance Apart
     * @param s input str for rearrangement
     * @param k min distance between same character in the rearranged str
     * @return rearranged str, empty str if no possible solution
     * @Solution https://discuss.leetcode.com/topic/49022/greedy-solution-beats-95
     */
    public String rearrangeString(String s, int k) {
        if (s == null || s.length() < 2) return s;
        // cnt keeps track of the remaining count of characters
        int[] cnt = new int[26];
        // keeps the leftmost position that a character can appear
        int[] valid = new int[26];

        for (char c: s.toCharArray()) cnt[c - 'a']++;
        char[] seq = new char[s.length()];

        for (int idx = 0; idx < s.length(); idx++) {
            int candidatePos = findValidMax(cnt, valid, idx);
            if (candidatePos == -1) return "";
            cnt[candidatePos]--;
            valid[candidatePos] = idx + k;
            seq[idx] = (char)(candidatePos + 'a');
        }
        return new String(seq);
    }

    private int findValidMax(int[] cnt, int[] valid, int idx) {
        int max = Integer.MIN_VALUE;
        int candidatePos = -1;
        for(int i = 0; i < cnt.length; i++){
            if(cnt[i] > 0 && cnt[i] > max && idx >= valid[i]) {
                max = cnt[i];
                candidatePos = i;
            }
        }
        return candidatePos;
    }
}
