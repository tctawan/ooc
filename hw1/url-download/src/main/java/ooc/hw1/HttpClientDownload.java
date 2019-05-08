package ooc.hw1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;

public class HttpClientDownload {

    public void download(String fileURL, String savePath ) throws IOException {
        System.out.println("HttpClientDownload: File downloading...");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(fileURL);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        FileOutputStream outputStream = new FileOutputStream(savePath);

        entity.writeTo(outputStream);
        outputStream.close();
        client.close();

        System.out.println("HttpClientDownload: File downloaded.");
    }
}
