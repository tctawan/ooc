import org.apache.commons.io.DirectoryWalker;

import java.io.*;
import java.util.*;

public class DirWalker extends DirectoryWalker {

    private int noOfFiles = 0;
    private int noOfDirs = 0;
    private HashMap<String,Integer> fileExts = new HashMap<String, Integer>();


    public DirWalker(){
        super();
    }

    public void run(File startDirectory) throws IOException {
        walk(startDirectory, new ArrayList());
    }

    protected boolean handleDirectory(File directory, int depth, Collection results){
        noOfDirs++;
        return true;
    }

    protected void handleFile(File file, int depth, Collection results){
        noOfFiles++;
        String name = file.getName();
        String extension = "";
        if(name.contains(".")){
            extension = name.substring(name.lastIndexOf("."));
        }
        int count = fileExts.containsKey(extension) ? fileExts.get(extension) : 0;
        fileExts.put(extension, count + 1);
    }

    public static void main(String[] args) throws IOException {
        DirWalker walker = new DirWalker();
        File startDirectory = new File("/Users/tctawan/ooc/hw1/docs");
        walker.run(startDirectory);
        System.out.println(walker.noOfFiles);
        System.out.println(walker.noOfDirs);
        System.out.println(walker.fileExts.size());
        System.out.println(walker.fileExts.keySet());
        System.out.println(walker.fileExts);

    }

}
