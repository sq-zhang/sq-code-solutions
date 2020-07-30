package leetcode;

/**
 * @author sqzhang
 * @year 2020
 */
class Solution {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int n = s.length();
        int left = 0, right = 0;
        for(int i = n - 1;i >= 0;i--) {
            int l1 = expand(s, i, i);
            int l2 = expand(s, i, i + 1);
            int len = Math.max(l1, l2);
            System.out.println("l" + l1 + "," + l2);
            if (len > right - left) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
                System.out.println(left + "," + right);
            }
        }
        System.out.println(left + "," + right);
        return s.substring(left, right + 1);
    }
    static int expand(String s, int i, int j) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public static void main(String[] args) {
        longestPalindrome("babad");
    }
}
