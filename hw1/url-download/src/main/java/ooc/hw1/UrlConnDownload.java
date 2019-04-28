package ooc.hw1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnDownload {

    final int BUFFER_SIZE = 4096;

    public void download(String fileURL, String savePath) throws IOException {
        System.out.println("UrlConnDownload : File downloading...");
        URL url = new URL(fileURL);
        URLConnection conn = url.openConnection();

        InputStream inputStream = conn.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(savePath);

        int byteRead;
        byte[] buffer = new byte[BUFFER_SIZE];
        while(( byteRead = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, byteRead);
        }
        inputStream.close();
        outputStream.close();
        System.out.println("UrlConnDownload: File downloaded.");
    }
}
