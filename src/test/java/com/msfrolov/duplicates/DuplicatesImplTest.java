package com.msfrolov.duplicates;

import org.junit.Test;

import java.io.File;

public class DuplicatesImplTest {

    @Test public void handle() throws Exception {
        Duplicates duplicates = new DuplicatesImpl();
        File sourceFile = new File("c:\\test\\source.txt");
        File outputFile = new File("c:\\test\\output.txt");
        duplicates.handle(sourceFile, outputFile);
    }

}
