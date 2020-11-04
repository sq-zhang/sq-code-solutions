package atcoder.ABC181.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        String s = sc.next();
        boolean result = false;
        int n = s.length();
        List<int[]> two = new ArrayList<>();
        List<int[]> three = new ArrayList<>();
        int candidates = 16;
        while (candidates < 1000) {
            int x = candidates;
            int[] countsCandidate = new int[10];
            while (x > 0) {
                countsCandidate[x % 10]++;
                x /= 10;
            }
            if (candidates < 100) {
                two.add(countsCandidate);
            } else {
                three.add(countsCandidate);
            }
            candidates += 8;
        }
        int[] counts = new int[10];
        for (char c : s.toCharArray()) {
            counts[c - '0']++;
        }
        if (n == 1) {
            result = "8".equals(s);
        } else if (n == 2) {
            result = valid(counts, two);
        } else if (n >= 3) {
            result = valid(counts, three);
        }
        pw.println(result ? "Yes" : "No");
        pw.flush();
    }

    static boolean valid(int[] counts, List<int[]> numList) {
        for (int[] candidate : numList) {
            if (validOne(counts, candidate)) {
                return true;
            }
        }
        return false;
    }

    static boolean validOne(int[] counts, int[] candidate) {
        for (int i = 0;i <= 9;i++) {
            if (counts[i] < candidate[i]) {
                return false;
            }
        }
        return true;
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