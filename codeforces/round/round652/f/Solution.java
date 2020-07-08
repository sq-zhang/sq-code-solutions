package codeforces.round.round652.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        boolean[] win = new boolean[t];
        boolean[] lose = new boolean[t];
        for(int i = 0; i < t; i++){
            long s = sc.nextLong(), e = sc.nextLong();
            boolean w = forceWin(s, e);
            boolean l = forceLose(s, e);
            if(i == 0){
                win[0] = w;
                lose[0] = l;
            } else {
                if(win[i - 1]) {
                    if(!w)  win[i] = true;
                    if(!l)  lose[i] = true;
                }
                if(lose[i - 1]){
                    if(w)   win[i] = true;
                    if(l)   lose[i] = true;
                }
            }
        }
        pw.println((win[t - 1] ? 1 : 0) + " " + (lose[t - 1] ? 1 : 0));
        pw.flush();
    }

    static boolean forceWin(long s, long e){
        if(e % 2 == 1) {
            return s % 2 == 0;
        } else if(s > e / 2) {
            return s % 2 != 0;
        } else if(s > e / 4) {
            return true;
        } else {
            return forceWin(s, e / 4);
        }
    }

    static boolean forceLose(long s, long e){
        return s > e / 2 || forceWin(s,e / 2);
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