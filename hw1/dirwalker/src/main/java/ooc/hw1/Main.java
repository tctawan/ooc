package ooc.hw1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        DirWalker walker = new DirWalker();
        File startDirectory = new File("/Users/tctawan/ooc/hw1/docs");

        try {
            walker.run(startDirectory);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
