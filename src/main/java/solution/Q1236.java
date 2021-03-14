package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Problem(
        title = "Web Crawler",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/web-crawler/"
)
public class Q1236 {
    private class HtmlParser {
        List<String> getUrls(String url) {
            return null;
        }
    }
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> urls = new ArrayList<>();
        String hostname = getHostName(startUrl);
        if(!isValidHostName(hostname))
            return urls;

        dfsCrawl(startUrl, htmlParser, hostname, new HashSet<>(), urls);
        return urls;
    }

    private String getHostName(String url) {
        StringBuilder sb = new StringBuilder();
        int idx = url.indexOf("//");
        idx += 2;
        for(; idx < url.length(); idx++) {
            char c = url.charAt(idx);
            if(c == '/')
                break;
            sb.append(c);
        }
        return sb.toString();
    }

    private boolean isValidHostName(String hostname) {
        int len = hostname.length();
        if(len < 1 || len > 63)
            return false;
        if(hostname.charAt(0) == '-' || hostname.charAt(len - 1) == '-')
            return false;
        for(int i = 0; i < len; i++) {
            char c = hostname.charAt(i);
            if(c != '-' && c != '.' && (c < 'a' || c > 'z') && (c < '0' || c > '9'))
                return false;
        }
        return true;
    }

    private boolean isUnderHostName(String hostname, String url) {
        return hostname.equals(getHostName(url));
    }

    private void dfsCrawl(String startUrl, HtmlParser htmlParser, String hostname, HashSet<String> visited, List<String> urls) {
        urls.add(startUrl);
        visited.add(startUrl);

        List<String> allUrls = htmlParser.getUrls(startUrl);

        for(String url : allUrls) {
            if(isUnderHostName(hostname, url) && !visited.contains(url))
                dfsCrawl(url, htmlParser, hostname, visited, urls);
        }
    }
}
