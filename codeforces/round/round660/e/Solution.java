package codeforces.round.round660.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    static Point[][] seg;
    static Point[] points;
    static int n;
    static TreeMap<Double, Point> left;
    static TreeMap<Double, Point> right;
    private static final double EPSILON = 1e-11;

    public static void main(String[] args) {
        n = sc.nextInt();
        seg = new Point[n][2];
        points = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            int l = sc.nextInt(), r = sc.nextInt(), y = sc.nextInt();
            seg[i][0] = Point.of(l, y);
            seg[i][1] = Point.of(r, y);
            points[i] = seg[i][0];
            points[i + n] = seg[i][1];
        }

        double[] alphas = new double[2 * n * (n - 1) / 2];
        int alphaSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (seg[i][0].y == seg[j][0].y)
                    continue;
                double badStart = alpha(seg[i][0], seg[j][1]);
                double badEnd = alpha(seg[i][1], seg[j][0]);
                if (badStart > badEnd) {
                    double t = badStart;
                    badStart = badEnd;
                    badEnd = t;
                }
                alphas[alphaSize++] = compress(badStart + EPSILON, 1);
                alphas[alphaSize++] = compress(badEnd - EPSILON, -1);
            }
        }

        Arrays.sort(alphas, 0, alphaSize);

        left = computeLeft();
        right = computeRight();

        if (alphaSize == 0) {
            pw.println(score(0));
            return;
        }

        int bad = 0;
        double answer = Double.POSITIVE_INFINITY;
        for (int i = 0; i < alphaSize; i++) {
            double alpha = alphas[i];
            int inc = bad(alphas[i]);
            if (bad == 0)
                answer = Math.min(answer, score(alpha));
            bad += inc;
            if (bad == 0)
                answer = Math.min(answer, score(alpha));
        }

        pw.println(String.format("%.15f", answer));
        pw.flush();
    }

    private static double compress(double v, int i) {
        if (i > 0) {
            v = Double.longBitsToDouble(Double.doubleToLongBits(v) | 1);
        } else {
            v = Double.longBitsToDouble(~(~Double.doubleToLongBits(v) | 1));
        }
        return v;
    }

    private static int bad(double v) {
        return (Double.doubleToLongBits(v) & 1) == 1 ? 1 : -1;
    }

    private static TreeMap<Double, Point> computeLeft() {
        TreeMap<Double, Point> map = new TreeMap<>();
        Arrays.sort(points, Comparator.comparingDouble((Point p) -> - p.y).thenComparingDouble(p -> p.x));

        double alpha = Double.NEGATIVE_INFINITY;
        Point last = points[0];

        map.put(alpha, last);
        while (true) {
            double nextAlpha = Double.POSITIVE_INFINITY;
            Point next = last;
            for (Point p : points) {
                if (p.equals(last))
                    continue;

                double a = alpha(last, p);
                if (lessEquals(a, nextAlpha) && strictLess(alpha, a) && strictLess(p.y, next.y)) {
                    nextAlpha = a;
                    next = p;
                }
            }
            if (next == last)
                break;
            alpha = nextAlpha;
            last = next;
            map.put(alpha, last);
        }

        return map;
    }

    private static TreeMap<Double, Point> computeRight() {
        TreeMap<Double, Point> map = new TreeMap<>();
        Arrays.sort(points, Comparator.comparingDouble((Point p) -> -p.y).thenComparingDouble(p -> -p.x));

        double alpha = Double.POSITIVE_INFINITY;
        Point last = points[0];

        map.put(alpha, last);
        while (true) {
            double nextAlpha = Double.NEGATIVE_INFINITY;
            Point next = last;
            for (Point p : points) {
                if (p.equals(last))
                    continue;

                double a = alpha(last, p);
                if (lessEquals(nextAlpha, a) && strictLess(a, alpha) && strictLess(p.y, next.y)) {
                    nextAlpha = a;
                    next = p;
                }
            }
            if (next == last)
                break;
            alpha = nextAlpha;
            last = next;
            map.put(alpha, last);
        }

        return map;
    }

    private static double project(Point p, double alpha) {
        return p.x + alpha * p.y;
    }

    private static double score(double alpha) {
        double min = project(left.floorEntry(alpha).getValue(), alpha);
        double max = project(right.ceilingEntry(alpha).getValue(), alpha);
        return max - min;
    }

    private static boolean lessEquals(double a, double b) {
        return a <= b + EPSILON;
    }

    private static boolean strictLess(double a, double b) {
        return a < b - EPSILON;
    }

    private static double alpha(Point a, Point b) {
        return (b.x - a.x) / (a.y - b.y);
    }

    static class Point implements Comparable<Point> {

        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static Point of(double x, double y) {
            return new Point(x, y);
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        public int hashCode() {
            return Objects.hash(x, y);
        }

        public int compareTo(Point o) {
            int ret = Double.compare(x, o.x);
            if (ret != 0)
                return ret;
            return Double.compare(y, o.y);
        }

        public String toString() {
            return String.format("(%f, %f)", x, y);
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
