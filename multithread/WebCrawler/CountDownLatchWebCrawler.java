package multithread.WebCrawler;

/**
 * @author sqzhang
 * @date 2020/6/13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import multithread.WebCrawler.util.HtmlParser;
import multithread.WebCrawler.util.HtmlParserImpl;

class CountDownLatchWebCrawler {
    private Set<String> urlSet;
    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    private String hostName;
    private static final Integer MAX_ALIVE_THREAD_NUM = 128;

    class CrawlWorker implements Runnable {
        private String startUrl;
        private CountDownLatch countDownLatch;
        private HtmlParser htmlParser;

        CrawlWorker(String startUrl, CountDownLatch countDownLatch,HtmlParser htmlParser){
            this.startUrl = startUrl;
            this.countDownLatch = countDownLatch;
            this.htmlParser = htmlParser;
        }

        @Override
        public void run() {
            urlSet.add(startUrl);
            List<String> urlList = htmlParser.getUrls(startUrl);
            for(String url : urlList){
                if(urlSet.contains(url) || !getHost(url).equals(hostName)) {
                    continue;
                }
                queue.offer(url);
            }

            this.countDownLatch.countDown();
        }
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        urlSet = ConcurrentHashMap.newKeySet();
        hostName = getHost(startUrl);
        queue.offer(startUrl);
        while(!queue.isEmpty()){
            int curThreadNum = Math.min(MAX_ALIVE_THREAD_NUM, queue.size());
            CountDownLatch countDownLatch = new CountDownLatch(curThreadNum);
            for(int idx = 0; idx < curThreadNum ;idx++){
                String curUrl = queue.poll();
                CrawlWorker crawlWorker = new CrawlWorker(curUrl, countDownLatch, htmlParser);
                Thread thread = new Thread(crawlWorker);
                thread.start();
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(urlSet);
    }

    private static String getHost(String url){
        String host = url.substring(7);
        int idx = host.indexOf("/");
        return idx == -1 ? host : host.substring(0, idx);
    }

    public static void main(String[] args) {
        CountDownLatchWebCrawler threadJoinWebCrawler = new CountDownLatchWebCrawler();
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
