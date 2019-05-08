package ooc.hw1;


public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        WebCrawl wc = new WebCrawl();
        long wordCount= wc.bfs("https://cs.muic.mahidol.ac.th/courses/ooc/docs/index.html", "/Users/tctawan/Downloads/");
        System.out.println(wordCount);
        System.out.println((System.currentTimeMillis() - start)/1000);
    }
}
