/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
class Solution {
    Set<String> set = new HashSet<>();
    Lock lock = new ReentrantLock();
    String hostName;
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        hostName = getHost(startUrl);

        dfs(startUrl, htmlParser);
        return new ArrayList<>(set);
    }

    void dfs(String startUrl, HtmlParser htmlParser)   {
        if (!getHost(startUrl).equals(hostName))
            return;
        lock.lock();
        try {
            if (set.contains(startUrl))
                return;
            set.add(startUrl);
        }
        finally {
            lock.unlock();
        }

        List<String> urlsToCrawl = htmlParser.getUrls(startUrl);

        List<Thread> threadToWait = new ArrayList<>();
        for (String url : urlsToCrawl)  {
            if (!getHost(url).equals(hostName))
                continue;
            lock.lock();
            try {
                if (set.contains(url))
                    continue;
            }
            finally {
                lock.unlock();
            }
            Thread t = new Thread(() -> {
                dfs(url, htmlParser);
            });
            t.start();
            threadToWait.add(t);
        }


        for (Thread t : threadToWait)
            try {
                t.join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            

        return;
    }

    String getHost(String url)  {
        return url.substring(url.indexOf("://") + 3).split("/")[0];
    }
}