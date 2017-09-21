package facebook.hard;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class LC273_IntegerToWords {
    private static final String[] LESSTWENTY = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"};
    private static final String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", " Thousand", " Million", " Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String s = "";
        int i = 0;
        while (num > 0) {
            int res = num % 1000;
            if (res > 0) {
                String tmp = s;
                s = toWords(res) + THOUSANDS[i];
                if (tmp.length() > 0) s = s + " " + tmp; // corner cases!
            }
            num /= 1000;
            i++;
        }
        return s;
    }

    public String toWords(int num) {
        if (num == 0) return "";
        if (num < 20) return LESSTWENTY[num - 1];
        if (num < 100) return (TENS[num / 10 - 2] + " " + toWords(num % 10)).trim();
        return (LESSTWENTY[num / 100 - 1] + " Hundred " + toWords(num % 100)).trim(); // trim() !!!
    }
}
