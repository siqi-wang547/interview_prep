package facebook.hard;

import java.util.*;

//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//Note: The input string may contain letters other than the parentheses ( and ).
//example: "()())()" -> ["()()()", "(())()"]
//         "(a)())()" -> ["(a)()()", "(a())()"]
//         ")(" -> [""]

/**
 * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
 The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

 To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

 After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information: the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
 For this, we keep tracking the last removal position and only remove ‘)’ after that.

 Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
 The answer is: do the same from right to left.
 However a cleverer idea is: reverse the string and reuse the code!
 */
public class LC301_RmInvalidParentheses {

    /**
     * DFS solution, remove the first redundant ')' and then reverse
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(ans, s, 0, 0, new char[] {'(', ')'});
        return ans;
    }

    private void remove(List<String> ans, String s, int last_i, int last_j, char[] par) {
        System.out.printf("s = %s, lasti = %d, lastj = %d\n", s, last_i, last_j);
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    remove(ans, s.substring(0, j) + s.substring(j + 1), i, j, par);
                }
            }
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') remove(ans, reversed, 0, 0, new char[]{')', '('});
        else {
            System.out.println("find solution" + reversed);
            ans.add(reversed);
        }
    }

    public static void main(String[] args) {
        String test = "()())()";
        LC301_RmInvalidParentheses sol = new LC301_RmInvalidParentheses();
        sol.removeInvalidParentheses(test);
        /*
        s = ()())(), lasti = 0, lastj = 0
        s = (())(), lasti = 4, lastj = 1
        s = )())((, lasti = 0, lastj = 0
        find solution(())()
        s = ()()(), lasti = 4, lastj = 3
        s = )()()(, lasti = 0, lastj = 0
        find solution()()()
         */
    }
}
