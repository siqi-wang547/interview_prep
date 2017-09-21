package facebook.medium;

public class LC91_DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0'? 0: 1;
        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i-1, i));
            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            if (oneDigit >= 1 && oneDigit <= 9) dp[i] += dp[i-1];
            if (twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i-2];
        }
        return dp[n];
    }

    public int numDecodes(String s) {
        if (s == null || s.length() == 0) return 0;
        int a = 1, b = s.charAt(0) == '0'? 0: 1;
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = Integer.parseInt(s.substring(i-1, i));
            int twoDigit = Integer.parseInt(s.substring(i-2, i));
            int tmp = a;
            a = b;
            if (oneDigit < 1 || oneDigit > 9) b = 0;
            if (twoDigit >= 10 && twoDigit <= 26) b += tmp;
        }
        return b;
    }
}

