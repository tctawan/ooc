package ooc.hw1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpClientDownload {

    final int BUFFER_SIZE = 4096;

    public void download(String fileURL, String savePath ) throws IOException {
        System.out.println("HttpClientDownload: File downloading...");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(fileURL);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        InputStream inputStream = entity.getContent();
        FileOutputStream outputStream = new FileOutputStream(savePath);

        int readBytes;
        byte[] buffer = new byte[BUFFER_SIZE] ;
        while((readBytes = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, readBytes);
        }

        inputStream.close();
        outputStream.close();
        client.close();

        System.out.println("HttpClientDownload: File downloaded.");

    }
}
