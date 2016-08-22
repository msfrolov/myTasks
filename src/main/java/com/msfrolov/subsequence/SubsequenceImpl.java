package com.msfrolov.subsequence;

import org.slf4j.LoggerFactory;

import java.util.List;

public class SubsequenceImpl implements Subsequence {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SubsequenceImpl.class);

    /**
     * Search xI sequence in sequence yI in the same direction of the order
     *
     * @param xI which the are looking for
     * @param yI wherein is sought
     * @return
     */
    @Override public boolean find(List xI, List yI) {
        int result = 0;
        int index = 0;
        for (int i = 0; i < xI.size(); i++) {
            Object current = xI.get(i);
            log.debug("search object: " + current);
            if (result < i) {
                break;
            }
            for (int j = index; j < yI.size(); j++) {
                Object position = yI.get(j);
                log.debug("current position: " + position);
                if (current.equals(position)) {
                    log.debug("found the same object: " + position);
                    index = j;
                    result++;
                    break;
                }
            }
        }
        log.info("Objects found in the same sequence: " + result);
        log.info("Total objects in a sequence Xi: " + xI.size());
        return result == xI.size();
    }
}
