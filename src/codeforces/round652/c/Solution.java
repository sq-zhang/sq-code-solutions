package codeforces.round652.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);


    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), k = sc.nextInt();
            int[] nums = new int[n], friends = new int[k];
            Map<Integer, Integer> numsCount = new HashMap<>();
            for(int i = 0;i < n;i++) {
                nums[i] = sc.nextInt();
                numsCount.put(nums[i], numsCount.getOrDefault(nums[i], 0) + 1);
            }
            List<Integer> numsList = new ArrayList<>(numsCount.keySet());
            numsList.sort(Collections.reverseOrder());
            int res = 0;
            for(int i = 0;i < k;i++) {
                friends[i] = sc.nextInt();
                int max = Integer.MAX_VALUE, min = Integer.MAX_VALUE;
                for(int j = 0;j < numsList.size();j++) {
                    if (friends[i] == 0) {
                        break;
                    }
                    int num = numsList.get(j);
                    int c = numsCount.get(num);
                    if (c > 0) {
                        if (j == numsList.size() - 1 && c > friends[i]) {
                            numsCount.put(num, c - friends[i]);
                            friends[i] = 0;
                        } else {
                            numsCount.put(num, c - 1);
                            friends[i]--;
                        }
                        max = Math.max(max, num);
                        min = Math.min(min, num);
                    }
                }
                res += max + min;
            }
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}