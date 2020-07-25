package codeforces.round.round659.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), k = sc.nextInt(), l = sc.nextInt();
            int[] a = new int[n + 1];
            List<Integer> safe = new ArrayList<>();
            safe.add(0);
            for(int i = 1;i <= n;i++) {
                a[i] = sc.nextInt();
                if (a[i] + k <= l) {
                    safe.add(i);
                }
            }
            safe.add(n + 1);
            boolean ans = true;
            for (int i = 1;i < safe.size() && ans;i++) {
                int tide = k;
                boolean down = true;
                for(int j = safe.get(i - 1) + 1;j < safe.get(i);j++) {
                    tide += down ? -1 : 1;
                    if (down) {
                        tide -= Math.max(0, a[j] + tide - l);
                    }
                    if (tide < 0 || a[j] + tide > l) {
                        ans = false;
                        break;
                    }
                    if (tide == 0) {
                        down = false;
                    }
                }
            }
            pw.println(ans ? "Yes" : "No");
        }
        pw.flush();
    }

    static class FS {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception ignored) {}
            }
            return st.nextToken();
        }
        int[] nextArray(int n) {
            int[] a = new int[n];
            for(int i = 0;i < n;i++) {
                a[i] = nextInt();
            }
            return a;
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}