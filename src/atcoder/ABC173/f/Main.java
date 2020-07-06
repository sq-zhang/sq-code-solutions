package atcoder.ABC173.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        long res = 0;
        for (int i = 1; i <= n; i++)
            res += (long) i * (n - i + 1);
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int u = Math.min(a, b);
            int v = Math.max(a, b);
            res -= (long) (u + 1) * (n - v);
        }
        pw.println(res);
        pw.flush();
    }

    private static void solveUnionSet() {
        int n= sc.nextInt();
        Node[] gr = new Node[n + 1];
        for(int i = 0;i < n;i++){
            gr[i + 1] = new Node();
        }
        for(int i = 0;i < n - 1;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(u > v){
                gr[u].ns.add(v);
                gr[v].ts.add(u);
            } else {
                gr[v].ns.add(u);
                gr[u].ts.add(v);
            }
        }
        long ans = 0;
        Set<Integer> up = new HashSet<>();
        int[] p = new int[n + 1];
        for(int i = 1;i <= n;i++){
            p[i] = i;
        }
        for(int i = 1;i <= n;i++){
            for(Integer j : gr[i].ns){
                int sp = find(p,j);
                up.remove(sp);
                p[sp] = i;
            }
            up.add(i);
            ans += up.size();
        }
        long temp = ans;
        for(int i = 1;i < n;i++){
            gr[i].ts.add(n + 1);
            int pr = i;
            int v = -1;
            for(Integer j : gr[i].ts){
                temp += (long)(j - pr) * v;
                v++;
                pr = j;
            }
            ans += temp;
        }
        pw.println(ans);
        pw.flush();
        pw.close();
    }

    static int find(int[] p,int i){
        if(p[i]==i){
            return i;
        }
        return p[i]=find(p,p[i]);
    }

    static class Node{
        Set<Integer> ns;
        TreeSet<Integer> ts;
        Node(){
            ns=new HashSet<>();
            ts=new TreeSet<>();
        }
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