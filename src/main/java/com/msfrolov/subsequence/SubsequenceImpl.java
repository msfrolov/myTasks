package com.msfrolov.subsequence;

import org.slf4j.LoggerFactory;

import java.util.List;

public class SubsequenceImpl implements Subsequence {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SubsequenceImpl.class);

    /**
     * Search xI sequence in sequence yI
     *
     * @param xI
     * @param yI
     * @return
     */
    @Override public boolean find(List xI, List yI) {
        int result = 0;
        int index = 0;
        for (int i = 0; i < xI.size(); i++) {
            if (result < i) {
                break;
            }
            for (int j = index; j < yI.size(); j++) {
                if (xI.get(i).equals(yI.get(j))) {
                    index = j;
                    result++;
                    break;
                }
            }
        }
        return result == xI.size();
    }
}
