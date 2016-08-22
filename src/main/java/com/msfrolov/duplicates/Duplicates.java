package com.msfrolov.duplicates;

import java.io.File;

public interface Duplicates {

    /**
     * processing of the source file and obtain the final form of ordered non-recurring strings
     *
     * @param sourceFile
     * @param outputFile
     */
    boolean process(File sourceFile, File outputFile);
}
