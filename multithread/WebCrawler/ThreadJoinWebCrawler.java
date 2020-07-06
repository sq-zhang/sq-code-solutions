package multithread.WebCrawler;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
import java.net.URI;
import java.util.*;
import multithread.WebCrawler.util.HtmlParser;
import multithread.WebCrawler.util.HtmlParserImpl;

class ThreadJoinWebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) throws InterruptedException {
        String host = URI.create(startUrl).getHost();
        Crawler crawler = new Crawler(startUrl, host, htmlParser);
        Crawler.res = new ArrayList<>();
        crawler.start();
        crawler.join();
        return Crawler.res;
    }


    static class Crawler extends Thread {
        private String startUrl;
        private String hostname;
        private HtmlParser htmlParser;

        public static volatile List<String> res = new ArrayList<>();

        public Crawler(String startUrl, String hostname, HtmlParser htmlParser){
            this.startUrl = startUrl;
            this.hostname = hostname;
            this.htmlParser = htmlParser;
        }

        @Override
        public void run(){
            String host = URI.create(startUrl).getHost();

            if(!host.equals(hostname) || res.contains(startUrl)){
                return;
            }

            res.add(startUrl);

            List<Thread> threads = new ArrayList<>();
            for(String url: htmlParser.getUrls(startUrl)){
                Crawler crawler = new Crawler(url, hostname, htmlParser);
                crawler.start();
                threads.add(crawler);
            }

            for(Thread t: threads){
                joinThread(t);
            }
        }

        public void joinThread(Thread thread){
            try {
                thread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadJoinWebCrawler threadJoinWebCrawler = new ThreadJoinWebCrawler();
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
