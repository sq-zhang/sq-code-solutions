package leetcode.weekly195.c;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author sqzhang
 * @date 2020/6/24
 */
class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        TreeSet<Integer> sunny = new TreeSet<>();
        Map<Integer, Integer> rainMap = new HashMap<>();
        for(int i = 0;i < n;i++) {
            if (rains[i] == 0) {
                sunny.add(i);
            } else {
                Integer lastRain = rainMap.get(rains[i]);
                if (lastRain != null) {
                    Integer lastSun = sunny.higher(lastRain);
                    if (lastSun == null) {
                        return new int[0];
                    }
                    res[lastSun] = rains[i];
                    sunny.remove(lastSun);
                }
                rainMap.put(rains[i], i);
                res[i] = -1;
            }
        }
        return res;
    }
}