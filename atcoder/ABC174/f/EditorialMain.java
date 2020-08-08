package atcoder.ABC174.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class EditorialMain {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] t;
    static int n, q;
    public static void main(String[] args) {
        n = sc.nextInt();
        q = sc.nextInt();
        int[] colors = new int[n];
        int[] lastPos = new int[n];
        Arrays.fill(lastPos, -1);
        int[] next = new int[n];
        Arrays.fill(next, -1);
        t = new int[n];
        for (int i = 0; i < n; i++) {
            colors[i] = sc.nextInt() - 1;
            if (lastPos[colors[i]] != -1) {
                next[lastPos[colors[i]]] = i;
            } else {
                add(i, 1);
            }
            lastPos[colors[i]] = i;
        }
        Query[] queries = new Query[q];
        for (int i = 0; i < q; i++) {
            queries[i] = new Query(sc.nextInt() - 1, sc.nextInt() - 1, i);
        }
        Arrays.sort(queries, Comparator.comparingInt(o -> o.from));
        int curFr = 0;
        int[] res = new int[q];
        for (Query query : queries) {
            while (curFr != query.from) {
                int ne = next[curFr];
                if (ne != -1) {
                    add(ne, 1);
                }
                add(curFr, -1);
                curFr++;
            }
            res[query.id] = get(query.to);
        }
        for (int x : res) {
            pw.println(x);
        }
        pw.flush();
    }

    static void add(int pos, int x) {
        for (; pos < n; pos |= pos + 1) {
            t[pos] += x;
        }
    }

    static int get(int pos) {
        int r = 0;
        for (; pos >= 0; pos = (pos & (pos + 1)) - 1) {
            r += t[pos];
        }
        return r;
    }

    static class Query {
        int from, to, id;
        public Query(int from, int to, int id) {
            this.from = from;
            this.to = to;
            this.id = id;
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