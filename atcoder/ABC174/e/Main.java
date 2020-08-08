package atcoder.ABC174.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), k = sc.nextInt();
        int[] a = new int[n];
        int max = 0;
        for (int i = 0;i < n;i++) {
            a[i] = sc.nextInt();
            max = Math.max(max, a[i]);
        }
        if(k == 0){
            pw.println(max);
            pw.flush();
            return;
        }

        int l = 1, r = max, ans = max;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(isOk(a, mid, k)){
                ans = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        pw.println(ans);
        pw.flush();
    }

    public static boolean isOk(int[] a, int target, int k) {
        int count = 0;
        for (int num : a) {
            if (num > target) {
                int needs = num / target;
                if (num % target == 0) {
                    needs--;
                }
                count = count + needs;
                if (count > k) {
                    return false;
                }
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