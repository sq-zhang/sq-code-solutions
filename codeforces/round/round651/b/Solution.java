package codeforces.round.round651.b;

import java.io.BufferedReader;
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
            int n = sc.nextInt();
            List<Integer> odd = new ArrayList<>(),
                    even = new ArrayList<>();
            for(int i = 1;i <= 2 * n;i++) {
                int num = sc.nextInt();
                if (num % 2 == 0) {
                    even.add(i);
                } else {
                    odd.add(i);
                }
            }
            int count = 0;
            for(int i = 0;i < odd.size() - 1;i += 2) {
                if (count == n - 1) {
                    break;
                }
                pw.println(odd.get(i) + " " + odd.get(i + 1));
                count++;
            }
            for(int i = 0;i < even.size() - 1;i += 2) {
                if (count == n - 1) {
                    break;
                }
                pw.println(even.get(i) + " " + even.get(i + 1));
                count++;
            }
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}