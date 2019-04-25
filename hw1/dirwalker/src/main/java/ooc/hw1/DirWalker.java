package ooc.hw1;

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
        System.out.println("Total num of files: " + this.noOfFiles);
        System.out.println("Total num of dirs: " + this.noOfDirs);
        System.out.println("Total num of exts: " + this.fileExts.size());
        System.out.println("List of unique exts: " + this.fileExts.keySet());
        System.out.println("Total number of files for exts: " + this.fileExts);
    }

    protected boolean handleDirectory(File directory, int depth, Collection results){
        noOfDirs++;
        return true;
    }

    protected void handleFile(File file, int depth, Collection results){
        noOfFiles++;
        String name = file.getName();
        String ext = "";
        if(name.contains(".")){
            ext = name.substring(name.lastIndexOf("."));
        }
        int count = fileExts.containsKey(ext) ? fileExts.get(ext) : 0;
        fileExts.put(ext, count + 1);
    }
}
