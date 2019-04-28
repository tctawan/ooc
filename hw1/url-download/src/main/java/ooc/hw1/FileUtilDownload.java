package ooc.hw1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtilDownload {

    public void download(String fileURL, String savePath) throws IOException {
        System.out.println("FileUtilDownload: Downloading...");
        URL url = new URL(fileURL);
        File dest = new File(savePath);

        FileUtils.copyURLToFile(url,dest);
        System.out.println("FileUtilDownload: Download done");
    }
}
