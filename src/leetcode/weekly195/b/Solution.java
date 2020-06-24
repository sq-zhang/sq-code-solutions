package leetcode.weekly195.b;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sqzhang
 * @date 2020/6/24
 */
class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> counts = new HashMap<>();
        String[] res = new String[names.length];
        for(int i = 0;i < names.length;i++) {
            int c = counts.getOrDefault(names[i], 0);
            if (c == 0) {
                res[i] = names[i];
            } else {
                while(counts.containsKey(names[i] + "(" + c + ")")) {
                    c++;
                }
                res[i] = names[i] + "(" + c + ")";
                counts.put(res[i], 1);
            }
            counts.put(names[i], c + 1);
        }
        return res;
    }
}