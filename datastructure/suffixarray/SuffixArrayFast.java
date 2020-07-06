package datastructure.suffixarray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * an O(nlogn) implementation of suffix array
 * @author sqzhang
 * @year 2020
 */
public class SuffixArrayFast extends SuffixArray {

    public SuffixArrayFast(String text) {
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
                sa[i] = (sa[i] - (1 << k) + n) % n;
            }

            sa = countSort(sa, c, n);

            int[] newC = new int[n];
            newC[sa[0]] = 0;
            for(int i = 1;i < n;i++) {
                int preL = c[sa[i - 1]], preR = c[(sa[i - 1] + (1 << k)) % n];
                int curL = c[sa[i]], curR = c[(sa[i] + (1 << k)) % n];
                if (preL == curL && preR == curR) {
                    newC[sa[i]] = newC[sa[i - 1]];
                } else {
                    newC[sa[i]] = newC[sa[i - 1]] + 1;
                }
            }
            c = newC;
            k++;
        }
    }

    private int[] countSort(int[] p, int[] c, int n) {
        int[] count = new int[n];
        for(int x : c) {
            count[x]++;
        }
        int[] pos = new int[n];
        for(int j = 1;j < n;j++) {
            pos[j] = pos[j - 1] + count[j - 1];
        }
        int[] res = new int[n];
        for(int x : p) {
            res[pos[c[x]]++] = x;
        }
        return res;
    }

    public static void main(String[] args) {
        SuffixArrayFast suffixArray = new SuffixArrayFast("ababba");
        System.out.println(suffixArray);
    }
}
