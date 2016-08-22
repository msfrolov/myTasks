package com.msfrolov.subsequence;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsequenceTest {

    @Test public void subsequenceTest() {
        List<String> xI = new ArrayList<>(Arrays.asList("maxim".split("")));
        List<String> yI = new ArrayList<>(Arrays.asList("qmqaqxytjtjtykyliol;l;pxqio;poipoqq".split("")));
        Subsequence subsequence = new SubsequenceImpl();
        boolean b = subsequence.find(xI, yI);
        System.out.println("answer = " + b);
    }
}
