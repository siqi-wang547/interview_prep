package interviews;

public class Solution {
    static int[] mergeArrays(int[] a, int[] b) {
        int m = a.length;
        int[] res = new int[2 * m];
        int i = 0, j = 0, p = 0;
        while (i < m && j < m) {
            if (a[i] < b[j]) {
                res[p++] = a[i++];
            } else {
                res[p++] = b[j++];
            }
        }
        while (i < m) res[p++] = a[i++];
        while (j < m) res[p++] = b[j++];
        return res;
    }

    static int[] stringSimilarity(String[] inputs) {
        int T = Integer.parseInt(inputs[0]);
        int[] res = new int[T];
        for (int i = 1; i <= T; i++) {
            String input = inputs[i];
            res[i-1] = sum(helper(input)) + input.length();
        }
        return res;
    }

    private static int sum(int[] arr) {
        int res = 0;
        for (int i: arr) res += i;
        return res;
    }

    private static int[] helper(String s) {
        int[] res = new int[s.length()];
        int l = 0, r = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (i <= r) {
                res[i] = Math.min(r - i + 1, res[i - l]);
            }
            while (i + res[i] < s.length() && s.charAt(res[i]) == s.charAt(i + res[i])) {
                res[i]++;
            }
            if (r < i + res[i] - 1) {
                l = i;
                r = i + res[i] - 1;
            }
        }
        return res;
    }
}
