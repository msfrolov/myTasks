package com.msfrolov.subsequence;

import java.util.List;

public interface Subsequence {

    /**
     * Search xI sequence in sequence yI in the same direction of the order
     *
     * @param xI which the are looking for
     * @param yI wherein is sought
     * @return
     */
    boolean find(List xI, List yI);
}
