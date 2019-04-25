package ooc.hw1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.cli.*;

public class DirWalkerWithArgs extends DirectoryWalker {

        private int noOfFiles = 0;
        private int noOfDirs = 0;
        private HashMap<String,Integer> fileExts = new HashMap<String, Integer>();


        public DirWalkerWithArgs(){
            super();
        }

        private void walk(File startDirectory) throws IOException {
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

        private Options createOptions(){
            Options options = new Options();
            options.addOption("a","total-num-files", false
                    ,"The total number of files.");
            options.addOption("b","total-num-dirs", false
                    ,"The total number of directory.");
            options.addOption("c","total-unique-exts", false
                    ,"Total number of unique file extensions.");
            options.addOption("d","list-exts", false
                    ,"List all unique file extensions. Do not list duplicates.");
            options.addOption(null,"num-ext", true
                    ,"List total number of file for specified extension EXT.");
            options.addOption("f", true
                    ,"Path to the documentation folder. This is a required argument.");
            return options;
        }

        public void run(String[] args) throws ParseException {
            CommandLineParser parser = new DefaultParser();
            Options options = this.createOptions();
            CommandLine cmd = parser.parse(options , args);

            if(cmd.hasOption("f")){
                String filename = cmd.getOptionValue("f");
                File startDirectory = new File(filename);
                try {
                    this.walk(startDirectory);
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(cmd.hasOption("a")){
                    System.out.println("Total number of files: " + this.noOfFiles);
                }
                if(cmd.hasOption("b")){
                    System.out.println("Total number of dirs: " + this.noOfDirs);

                }
                if(cmd.hasOption("c")){
                    System.out.println("Total number of unique file extensions: " + this.fileExts.size());
                }
                if(cmd.hasOption("d")){
                    System.out.println("All unique file extensions: " + this.fileExts.keySet());

                }
                if(cmd.hasOption("num-ext")){
                    String ext = cmd.getOptionValue("num-ext");
                    System.out.println("Total number of file for " + ext + " : " + this.fileExts.get(ext)  );

                }
            }
        }

}
