package facebook.easy;


public class LC125_ValidPalindrome {

    /**
     * Valid palindrome that only considers alphanumeric values and ignores cases
     * Two pointer using regex
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

    public boolean isPalin(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char left = s.charAt(i), right = s.charAt(j);
            if (!Character.isLetterOrDigit(left)) i++;
            else if (!Character.isLetterOrDigit(right)) j--;
            else {
                if (left != right) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}
