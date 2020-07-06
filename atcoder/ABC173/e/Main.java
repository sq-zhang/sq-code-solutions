package atcoder.ABC173.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static final int e97 = (int)1e9 + 7;

    public static void main(String[] args) {
        int n = sc.nextInt(), k = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0;i < n;i++) {
            a[i] = sc.nextLong();
        }
        Arrays.sort(a);

        if(n == k){
            long ans =1;
            for(long e: a){
                ans = (e * ans) % e97;
            }
            ans += e97;
            ans %= e97;
            System.out.println(ans);
            return;
        }

        int negCount = 0, zeroCount =0;
        for(int i = 0; i < n; i++){
            if(a[i]<0) negCount++;
            else if(a[i] == 0) zeroCount++;
            else break;
        }
        int posCount = n - negCount - zeroCount;

        if(zeroCount > (n - k)){
            System.out.println(0);
            return;
        }
        if((zeroCount == (n - k)) && (negCount % 2 == 1)){
            System.out.println(0);
            return;
        }

        if((posCount == 0) && (k % 2 == 1)){
            if(zeroCount > 0){
                System.out.println(0);
            } else {
                long ans =1;
                for(int i = n - 1; i >= n - k; i--){
                    ans = (a[i] * ans) % e97;
                }
                ans += e97;
                ans %= e97;
                System.out.println(ans);
            }
            return;
        }

        long ans = 1;

        List<Long> multiPair = new ArrayList<>();
        for(int i = 1; i < negCount; i += 2){
            multiPair.add(a[i - 1] * a[i]);
        }

        int lastPos = n;
        if(k % 2 == 0){
            lastPos -= 2;
        } else {
            ans = a[n-1];
            lastPos -= 3;
        }

        while(lastPos >= (zeroCount + negCount)){
            multiPair.add(a[lastPos] * a[lastPos + 1]);
            lastPos -= 2;
        }

        multiPair.sort(Comparator.reverseOrder());
        for(int i = 0; i < k / 2; i++){
            long addi = multiPair.get(i) % e97;
            ans *= addi;
            ans %= e97;
        }

        if(ans < 0) {
            ans += e97;
        }
        pw.println(ans);
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