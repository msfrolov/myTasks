package com.msfrolov.duplicates;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

public class DuplicatesImpl implements Duplicates {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DuplicatesImpl.class);

    /**
     * processing of the source file and obtain the final form of ordered non-recurring strings
     *
     * @param sourceFile
     * @param outputFile
     */
    @Override public boolean handle(File sourceFile, File outputFile) {
        Map<String, Integer> map = new TreeMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
                FileWriter writer = new FileWriter(outputFile, true)) {
            if (!sourceFile.exists())
                throw new FileNotFoundException();
            if (!outputFile.exists())
                outputFile.createNewFile();
            while ((line = reader.readLine()) != null) {
                if (!map.containsKey(line)) {
                    map.put(line, 1);
                } else {
                    map.replace(line, map.get(line) + 1);
                }
            }
            for (Map.Entry<String, Integer> lines : map.entrySet()) {
                writer.write("\r\n" + lines.getKey() + "[" + lines.getValue() + "]");
            }
            return true;
        } catch (FileNotFoundException ex) {
            log.info("file not found source file - \"{}\" output file - \"{}\"", sourceFile.getAbsolutePath(),
                    outputFile.getAbsolutePath());
            return false;
        } catch (Exception ex) {
            log.info("files processing ended in failure");
            return false;
        }
    }
}
