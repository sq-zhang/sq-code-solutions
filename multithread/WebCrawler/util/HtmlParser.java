package multithread.WebCrawler.util;

import java.util.List;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public interface HtmlParser {
    // Return a list of all urls from a webpage of given url.
    // This is a blocking call, that means it will do HTTP request and return when this request is finished.
    public List<String> getUrls(String url);
}
