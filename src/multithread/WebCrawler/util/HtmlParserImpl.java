package multithread.WebCrawler.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class HtmlParserImpl implements HtmlParser {

    private Map<String, ArrayList<String>> urlMaps;

    public HtmlParserImpl() {
        urlMaps = new HashMap<>();
        String[] urls = {
            "http://news.yahoo.com",
            "http://news.yahoo.com/news",
            "http://news.yahoo.com/news/topics/",
            "http://news.google.com",
            "http://news.yahoo.com/us"
        };
        int[][] edges = {{2, 0}, {2, 1}, {3, 2}, {3, 1}, {0, 4}};
        for(int[] edge : edges) {
            urlMaps.computeIfAbsent(urls[edge[0]],
                k -> new ArrayList<>()).add(urls[edge[1]]);
        }
    }

    @Override
    public List<String> getUrls(String url) {
        return urlMaps.getOrDefault(url, new ArrayList<>());
    }
}
