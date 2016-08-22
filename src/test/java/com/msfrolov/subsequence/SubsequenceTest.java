package com.msfrolov.subsequence;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SubsequenceTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SubsequenceTest.class);

    Subsequence subsequence = new SubsequenceImpl();

    @Test public void subsequenceTest1() {
        List<String> xI = new ArrayList<>(Arrays.asList("maxim".split("")));
        List<String> yI = new ArrayList<>(Arrays.asList("qmqaqxytjtjtykyliol;l;pxqio;poimpoqq".split("")));
        boolean b = subsequence.find(xI, yI);
        log.info("answer = {}", b);
        assertTrue(b);
    }

    @Test public void subsequenceTest2() {
        List<String> xI = new ArrayList<>(Arrays.asList("FrolOv".split("")));
        List<String> yI = new ArrayList<>(Arrays.asList("qmqaqxFytjtjtykryliol;l;poxqio;polimpoqqV".split("")));
        boolean b = subsequence.find(xI, yI);
        log.info("answer = {}", b);
        assertTrue(!b);
    }

    @Test public void subsequenceTest3() {
        List<String> xI = new ArrayList<>(Arrays.asList("JAVA".split("")));
        List<String> yI = new ArrayList<>(Arrays.asList("qmqaqxFJytjtjtyAkryliol;l;Vpoxqio;polimpoqqVa".split("")));
        boolean b = subsequence.find(xI, yI);
        log.info("answer = {}", b);
        assertTrue(!b);
    }

    @Test public void subsequenceTest4() {
        List<String> xI = new ArrayList<>(Arrays.asList("JAVA".split("")));
        List<String> yI = new ArrayList<>(Arrays.asList("qmqaqxFJytjtjtyAkryliol;l;Vpoxqio;VpoliAmpoqq".split("")));
        boolean b = subsequence.find(xI, yI);
        log.info("answer = {}", b);
        assertTrue(!b);
    }
}
