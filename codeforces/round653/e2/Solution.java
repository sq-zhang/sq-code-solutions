package codeforces.round653.e2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    static PriorityQueue<Book> pqA, pqB;
    static long total = 0;
    static int countA = 0, countB = 0;
    static int booksUsed = 0;

    static PriorityQueue<Book> cheapestA, cheapestB, cheapestBoth, cheapestNone, worstBoth, worstA, worstB, worstNone;

    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        Book[] books = new Book[n], unsorted = new Book[n];
        for (int i = 0; i < n; i++) {
            unsorted[i] = books[i] = new Book(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        pqA = new PriorityQueue<>(Comparator.reverseOrder());
        pqB = new PriorityQueue<>(Comparator.reverseOrder());

        cheapestA = new PriorityQueue<>();
        cheapestB = new PriorityQueue<>();
        cheapestBoth = new PriorityQueue<>();
        cheapestNone = new PriorityQueue<>();

        worstBoth = new PriorityQueue<>(Comparator.reverseOrder());
        worstA = new PriorityQueue<>(Comparator.reverseOrder());
        worstB = new PriorityQueue<>(Comparator.reverseOrder());
        worstNone = new PriorityQueue<>(Comparator.reverseOrder());

        Arrays.sort(books);
        for (Book book : books) {
            if (book.likeA && book.likeB) {
                cheapestBoth.add(book);
                if (countA < k || countB < k) {
                    add(book);
                    if (countA > k && !pqA.isEmpty()) {
                        Book toRemove = pqA.remove();
                        remove(toRemove);
                    }
                    if (countB > k && !pqB.isEmpty()) {
                        Book toRemove = pqB.remove();
                        remove(toRemove);
                    }
                } else if (!pqA.isEmpty() && !pqB.isEmpty()) {
                    int bonus = pqA.peek().t + pqB.peek().t;
                    if (bonus > book.t) {
                        Book removed1 = pqA.remove(), removed2 = pqB.remove();
                        add(book);
                        remove(removed1);
                        remove(removed2);
                    }
                }
            } else if (book.likeA) {
                cheapestA.add(book);
                if (countA <k) {
                    add(book);
                }
            } else if (book.likeB) {
                cheapestB.add(book);
                if (countB <k) {
                    add(book);
                }
            } else {
                cheapestNone.add(book);
            }
        }

        while (booksUsed > m) {
            Book toAdd = getCheapestUnused(cheapestBoth);
            Book worstAUsed = getWorstUsed(worstA);
            Book worstBUsed = getWorstUsed(worstB);
            if (toAdd == null || worstAUsed == null || worstBUsed == null) {
                pw.println(-1);
                pw.flush();
                return;
            }
            remove(worstAUsed);
            remove(worstBUsed);
            add(toAdd);
        }

        while (booksUsed < m) {
            Book cheapestAUnused = getCheapestUnused(cheapestA);;
            Book cheapestBUnused = getCheapestUnused(cheapestB);
            Book cheapestBothUnused = getCheapestUnused(cheapestBoth);
            Book cheapestNoneUnused = getCheapestUnused(cheapestNone);

            Book bestToAdd = minCost(cheapestAUnused, cheapestBUnused, cheapestBothUnused, cheapestNoneUnused);
            Book worstBothUsed = getWorstUsed(worstBoth);
            if (worstBothUsed == null || cheapestAUnused == null || cheapestBUnused == null) {
                if (bestToAdd == null) {
                    pw.println(-1);
                    pw.flush();
                    return;
                }
                add(bestToAdd);
            } else {
                long costBestToAdd = bestToAdd.t;
                long costSwap = cheapestAUnused.t + cheapestBUnused.t - worstBothUsed.t;
                if (costBestToAdd < costSwap) {
                    add(bestToAdd);
                } else {
                    remove(worstBothUsed);
                    add(cheapestAUnused);
                    add(cheapestBUnused);
                }
            }
        }

        if (countA <k || countB <k) {
            pw.println(-1);
            pw.close();
            return;
        } else {
            pw.println(total);
        }

        for (int i = 0; i < n; i++) {
            if (unsorted[i].used) {
                pw.print(i + 1 + " ");
            }
        }
        pw.println();
        pw.close();
    }

    static void add(Book b) {
        b.used = true;
        total += b.t;
        booksUsed++;
        if (b.likeA && b.likeB) {
            countA++;
            countB++;
            worstBoth.add(b);
        } else if(b.likeA) {
            pqA.add(b);
            worstA.add(b);
            countA++;
        } else if (b.likeB) {
            pqB.add(b);
            worstB.add(b);
            countB++;
        } else {
            worstNone.add(b);
        }
    }

    static void remove(Book book) {
        book.used = false;
        total -= book.t;
        booksUsed--;

        if (book.likeA && book.likeB) {
            countA--;
            countB--;
            cheapestBoth.add(book);
        } else if(book.likeA) {
            countA--;
            cheapestA.add(book);
        } else if (book.likeB) {
            countB--;
            cheapestB.add(book);
        } else {
            cheapestNone.add(book);
        }
    }

    static Book minCost(Book a, Book b, Book c, Book d) {
        Book res = better(a, b);
        res = better(res, c);
        res = better(res, d);
        return res;
    }

    static Book better(Book a, Book b) {
        if (a == null) return b;
        if (b == null) return a;
        return a.t <= b.t ? a : b;
    }

    static Book getCheapestUnused(PriorityQueue<Book> pq) {
        while (!pq.isEmpty()) {
            Book next = pq.peek();
            if (next.used) pq.remove();
            else return next;
        }
        return null;
    }

    static Book getWorstUsed(PriorityQueue<Book> pq) {
        while (!pq.isEmpty()) {
            Book next = pq.peek();
            if (!next.used) pq.remove();
            else return next;
        }
        return null;
    }

    static class Book implements Comparable<Book> {
        int t;
        boolean likeA, likeB;
        boolean used;
        public Book(int t, int likeA, int likeB) {
            this.t = t;
            this.likeA = likeA ==1;
            this.likeB = likeB ==1;
        }

        public int compareTo(Book o) {
            return Integer.compare(t, o.t);
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