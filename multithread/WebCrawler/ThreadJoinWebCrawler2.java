package multithread.WebCrawler;

/**
 * @author sqzhang
 * @date 2020/6/13
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import multithread.WebCrawler.util.HtmlParser;
import multithread.WebCrawler.util.HtmlParserImpl;

class ThreadJoinWebCrawler2 {
    private final Map<String, Boolean> vis = new HashMap<>();
    private final List<String> urls = new ArrayList<>();
    private HtmlParser htmlparser;

    private List<String> getNextVertexes(String u) {
        return htmlparser.getUrls(u);
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.htmlparser = htmlParser;
        urls.clear();
        dfs(startUrl);
        return urls;
    }

    private boolean isSameHost(String u, String v) {
        return u.split("/")[2].equals(v.split("/")[2]);
    }

    private void dfs(String u) {
        synchronized (vis) {
            if( vis.containsKey(u)) {
                return;
            }
            vis.put(u, true);
        }
        synchronized (urls) {
            urls.add(u);
        }
        List<String> next = getNextVertexes(u);
        List<Thread> threads = new ArrayList<>();
        for(String v: next) {
            if(!isSameHost(u, v)) {
                continue;
            }
            Thread t = new Thread(()-> dfs(v));
            threads.add(t);
            t.start();
        }
        try {
            for(Thread t : threads) {
                t.join();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadJoinWebCrawler2 threadJoinWebCrawler = new ThreadJoinWebCrawler2();
        System.out.println(threadJoinWebCrawler.crawl(
            "http://news.yahoo.com/news/topics/",
            new HtmlParserImpl())
        );

        System.out.println(threadJoinWebCrawler.crawl(
            "http://news.google.com",
            new HtmlParserImpl())
        );
    }
}
