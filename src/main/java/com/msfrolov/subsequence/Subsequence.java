package com.msfrolov.subsequence;

import java.util.List;

public interface Subsequence {

    /**
     * Search xI sequence in sequence yI in the same direction of the order
     *
     * @param xI
     * @param yI
     * @return
     */
    boolean find(List xI, List yI);
}
