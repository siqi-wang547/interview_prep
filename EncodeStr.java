import java.util.*;

/**
 *
 */
public class EncodeStr {

    /**
     * LC394. Decode String
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        Stack<Integer> cntSt = new Stack<>();
        Stack<String> resSt = new Stack<>();
        int idx = 0;
        resSt.push("");
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                int start = idx;
                while (Character.isDigit(s.charAt(idx))) {
                    idx++;
                } // s.charAt(idx) is not a digit
                // System.out.printf("start=%d,idx=%d,substr=%s\n", start, idx, s.substring(start,idx));

                cntSt.push(Integer.parseInt(s.substring(start, idx)));
                idx--;
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int rep = cntSt.pop();
                String str = resSt.pop();
                // System.out.printf("repeat=%d, poppedStr=%s\n", rep, str);

                for (int i = 0; i < rep; i++) tmp.append(str);
                // System.out.println("after repeat str = " + tmp.toString());

                resSt.push(resSt.pop() + tmp.toString());
            } else if (c == '[') {
                resSt.push("");
            } else {
                resSt.push(resSt.pop() + c);
            }
            idx++;
        }

        return resSt.pop();
    }

    /**
     * LC471. Encode String with Shortest Length
     * @param s
     * @return
     */
    public static String encode(String s) {
        if (s == null || s.length() < 5) return s;
        int len = s.length();
        String[][] dp = new String[len][len]; // encoded s[i:j] (i, j inclusive)

        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i < s.length() - l; i++) {
                int j = i + l;
                String substr = s.substring(i, j + 1);
                dp[i][j] = substr;
                if (l >= 4) {
                    for (int k = i; k < j; k++) {
                        if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }

                    // find out if substr itself can be repetitive
                    for (int k = 0; k < substr.length(); k++) {
                        String repeatStr = substr.substring(0, k+1);
                        if(repeatStr != null
                                && substr.length() % repeatStr.length() == 0
                                && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length() / repeatStr.length() + "[" + dp[i][i+k] + "]";
                            if(ss.length() < dp[i][j].length()) {
                                dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        System.out.println("res = " + decodeString("2[ab3[cd]]4[xy]"));
        System.out.println("res = " + encode("aaaaaa"));
    }
}
