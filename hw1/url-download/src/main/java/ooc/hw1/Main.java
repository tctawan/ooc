package ooc.hw1;

import java.io.IOException;


public class Main {

    public static void main(String[] args){
        UrlConnDownload download1 = new UrlConnDownload();
        FileUtilDownload download2 = new FileUtilDownload();
        HttpClientDownload download3 = new HttpClientDownload();

        try {
            download1.download("http://www.africau.edu/images/default/sample.pdf"
                    , "/Users/tctawan/Downloads/sample-1.pdf" );
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            download2.download("http://www.pdf995.com/samples/pdf.pdf"
                    ,"/Users/tctawan/Downloads/sample-2.pdf" );
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            download3.download("https://cs.muic.mahidol.ac.th/courses/ooc/docs/api/org/omg/CosNaming/NamingContextExtPackage/package-use.html#org.omg.CosNaming.NamingContextExtPackage"
                    ,"/Users/tctawan/Downloads/sample-3" );
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
