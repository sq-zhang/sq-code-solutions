package codeforces.round.round662.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static Map<Integer, Integer> maps = new HashMap<>();
    public static void main(String[] args) {
        int n = sc.nextInt();
        for (int i = 0;i < n;i++) {
            int m = sc.nextInt();
            maps.put(m, maps.getOrDefault(m, 0) + 1);
        }
        int four = 0, two = 0;
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            if (entry.getValue() >= 4) {
                four++;
                two += (entry.getValue() - 4) / 2;
            } else if (entry.getValue() >= 2) {
                two++;
            }
        }
        int q = sc.nextInt();
        for (int i = 0;i < q;i++) {
            String mark = sc.next();
            int m = sc.nextInt();
            int count = maps.getOrDefault(m, 0);
            if (mark.equals("+")) {
                maps.put(m, count + 1);
                if (count == 1) {
                    two++;
                } else if (count == 3) {
                    two--;
                    four++;
                } else if (count > 4 && count % 2 == 1) {
                    two++;
                }
            } else {
                maps.put(m, count - 1);
                if (count == 2) {
                    two--;
                } else if (count == 4) {
                    four--;
                    two++;
                } else if (count > 4 && count % 2 == 0) {
                    two--;
                }
            }
            boolean match = four >= 1 && ((four - 1) * 2 + two) >= 2;
            pw.println(match ? "YES" : "NO");
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