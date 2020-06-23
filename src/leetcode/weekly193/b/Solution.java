package leetcode.weekly193.b;

import com.sun.tools.javac.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @author sqzhang
 * @date 2020/6/14
 * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，
 * 请找出移除后数组中不同整数的最少数目。
 */
class Solution {
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> maps = new HashMap<>();
        for(int a : arr) {
            maps.put(a, maps.getOrDefault(a, 0) + 1);
        }

        List<Integer> values = new ArrayList<>();
        for(Integer key : maps.keySet()) {
            values.add(maps.get(key));
        }
        Collections.sort(values);
        int res = 0;
        for (int v : values) {
            if (k >= v) {
                k -= v;
                res++;
            } else {
                break;
            }
        }

        return values.size() - res;
    }

    public static void test(int[] nums, int k, int target) {
        Assert.check(findLeastNumOfUniqueInts(nums, k) == target);
    }

    public static void main(String[] args) {
        test(new int[]{5, 5, 4}, 1, 1);
        test(new int[]{4,3,1,1,3,3,2}, 3, 2);
    }
}
