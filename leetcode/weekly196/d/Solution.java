package leetcode.weekly196.d;

/**
 * @author sqzhang
 * @year 2020
 */
public class Solution {
    public String minInteger(String num, int k) {
        char[] c = num.toCharArray();
        int n = c.length;
        for(int i = 0;i < n && k > 0;++i){
            int cur = i;
            for(int j = i + 1;j < n && j - i <= k;++j){
                if(c[j] < c[cur]){
                    cur = j;
                }
            }
            for(int j = cur;j > i && k > 0;--j){
                k--;
                char t = c[j];
                c[j] = c[j - 1];
                c[j - 1] = t;
            }
        }
        return new String(c);
    }
}
