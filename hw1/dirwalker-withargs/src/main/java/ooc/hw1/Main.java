package ooc.hw1;

import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {

            DirWalkerWithArgs walker = new DirWalkerWithArgs();

            walker.run(args);

    }
}
