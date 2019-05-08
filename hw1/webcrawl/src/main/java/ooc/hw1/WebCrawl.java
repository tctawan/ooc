package ooc.hw1;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class WebCrawl {

    private long wordCount = 0;

    private void countWords(Document doc) {
        String texts = doc.text();
        String[] words = texts.split("\\W+");
        wordCount += words.length;
    }

    private String getFilePath(String webUrl, String saveDir) {
        String children = webUrl.replace("https://cs.muic.mahidol.ac.th/courses/ooc/", "");
        File file = new File(saveDir, children);
        file.getParentFile().mkdirs();
        if (file.isDirectory()) {
            return null;
        }
        return file.getPath();
    }

    private String getFileExt(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }

    private void download(byte[] content, String filePath) {
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean shouldDownload(String abs, String rel, HashSet<String> wasInQueue) {
        return abs.contains("https://cs.muic.mahidol.ac.th/courses/ooc/docs/")
                && !rel.contains(" ")
                && !rel.contains("#")
                && !rel.contains("?")
                && !wasInQueue.contains(abs);
    }

    private void handleLinks(Elements elements,String attrKey, HashSet<String> wasInQueue, LinkedList<String> queue){
        for (Element element : elements) {
            String rel = element.attr(attrKey);
            String abs = element.absUrl(attrKey);
            if (shouldDownload(abs, rel, wasInQueue)) {
                wasInQueue.add(abs);
                queue.add(abs);
            }
        }
    }

    //Given a web url, the function will returns an array of unvisited url in this page.
    private void updateQueue(Document doc, HashSet<String> wasInQueue, LinkedList<String> queue) {
        Elements hrefs = doc.getElementsByAttribute("href");
        Elements srcs = doc.getElementsByAttribute("src");

        handleLinks(hrefs,"href",wasInQueue,queue);
        handleLinks(srcs,"src",wasInQueue,queue);

    }

    private byte[] getContent(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() != 404){
            try {
                HttpEntity entity = response.getEntity();
                byte[] bytes = EntityUtils.toByteArray(entity);
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public long bfs(String startUrl, String saveDir){
        CloseableHttpClient client = HttpClients.createDefault();
        HashSet<String> wasInQueue = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(startUrl);
        wasInQueue.add(startUrl);
        while (!queue.isEmpty()) {
            String webUrl = queue.poll();
            HttpGet request = new HttpGet(webUrl);
            if (webUrl.contains("https://cs.muic.mahidol.ac.th/courses/ooc/docs/")) {
                String filePath = getFilePath(webUrl, saveDir);
                if (filePath != null) {
                    try {
                        HttpResponse response = client.execute(request);
                        byte[] content = getContent(response);
                        if (content != null) {
                            download(content, filePath);
                            if (getFileExt(filePath).equals("html")) {
                                Document doc = Jsoup.parse(new String(content), webUrl);
                                updateQueue(doc, wasInQueue, queue);
                                countWords(doc);
                            }
                        }
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    request.releaseConnection();
                }
            }
        }
        System.out.println("IM OUT!! PEACEEEE");
        return wordCount;
    }
}