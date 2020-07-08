package codeforces.round.round653.e1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), k = sc.nextInt();
        PriorityQueue<Integer> a = new PriorityQueue<>();
        PriorityQueue<Integer> b = new PriorityQueue<>();
        PriorityQueue<Integer> ab = new PriorityQueue<>();
        for(int i = 0;i < n;i++) {
            int t = sc.nextInt(), ai = sc.nextInt(), bi = sc.nextInt();
            if (ai == 1 && bi == 1) {
                ab.add(t);
            } else if (ai == 1) {
                a.add(t);
            } else if (bi == 1) {
                b.add(t);
            }
        }

        int countA = 0, countB = 0, res = 0;
        while(countA < k || countB < k) {
            if (!ab.isEmpty() && !a.isEmpty() && !b.isEmpty()) {
                if (ab.peek() <= a.peek() + b.peek()) {
                    res += ab.poll();
                } else {
                    res += a.poll() + b.poll();
                }
                countA++;
                countB++;
            } else if (!ab.isEmpty()) {
                res += ab.poll();
                countA++;
                countB++;
            } else if (!a.isEmpty() && countA < k) {
                res += a.poll();
                countA++;
            } else if (!b.isEmpty() && countB < k) {
                res += b.poll();
                countB++;
            } else {
                break;
            }
        }
        if (countA < k || countB < k) {
            pw.println(-1);
        } else {
            pw.println(res);
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