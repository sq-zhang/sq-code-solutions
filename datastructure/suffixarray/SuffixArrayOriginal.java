package datastructure.suffixarray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * an O(n(log n)^2) implementation of suffix array
 * @author sqzhang
 * @year 2020
 */
public class SuffixArrayOriginal extends SuffixArray {

    public SuffixArrayOriginal(String text) {
        super(text);
    }

    @Override
    void buildSuffixArray() {
        sa = new int[n];
        int[] c = new int[n];
        int[][] a = new int[n][];
        for(int i = 0;i < n;i++) {
            a[i] = new int[]{text[i], i};
        }
        Arrays.sort(a, Comparator.comparingInt(o -> o[0]));

        for(int i = 0;i < n;i++) {
            sa[i] = a[i][1];
        }
        c[sa[0]] = 0;
        for(int i = 1;i < n;i++) {
            if (a[i][0] == a[i - 1][0]) {
                c[sa[i]] = c[sa[i - 1]];
            } else {
                c[sa[i]] = c[sa[i - 1]] + 1;
            }
        }

        int k = 0;
        while((1 << k) < n) {
            for(int i = 0;i < n;i++) {
                a[i] = new int[]{c[i], c[(i + ((1 << k))) % n], i};
            }
            Arrays.sort(a, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
            for(int i = 0;i < n;i++) {
                sa[i] = a[i][2];
            }
            c[sa[0]] = 0;
            for(int i = 1;i < n;i++) {
                if (a[i][0] == a[i - 1][0] && a[i][1] == a[i - 1][1]) {
                    c[sa[i]] = c[sa[i - 1]];
                } else {
                    c[sa[i]] = c[sa[i - 1]] + 1;
                }
            }
            k++;
        }
    }

    public static void main(String[] args) {
        SuffixArrayOriginal suffixArray = new SuffixArrayOriginal("ababba");
        System.out.println(suffixArray);
    }
}
