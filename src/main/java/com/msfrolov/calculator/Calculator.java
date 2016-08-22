package com.msfrolov.calculator;

public interface Calculator {

    /**
     * evaluating the expression (string calculator)
     *
     * @param expression string expression containing a number of operators and priority signs
     * @return the result of an expression or Null if the calculation is impossible
     */
    Double calculate(String expression);
}
