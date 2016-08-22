package com.msfrolov.calculator;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class CalculatorImplTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CalculatorImplTest.class);
    private final Calculator calculator = new CalculatorImpl();

    @Test
    public void testCalculate1() throws Exception {
        Number result = calculator.calculate("-");
        log.debug("result: " + result);
        assertEquals(null, result);
    }

    @Test
    public void testCalculate2() throws Exception {
        Number result = calculator.calculate("12--");
        log.debug("result: " + result);
        assertEquals(null, result);
    }

    @Test
    public void testCalculate3() throws Exception {
        Number result = calculator.calculate("(5+7)*(4-5)");
        log.debug("result: " + result);
        assertEquals(-12.0000, result);
    }

    @Test
    public void testCalculate4() throws Exception {
        Number result = calculator.calculate("(5+7)*(4-5)/7.777777");
        log.debug("result: " + result);
        assertEquals(-1.5429, result);
    }

    @Test
    public void testCalculate5() throws Exception {
        Number result = calculator.calculate(" + 7 * 5 / 6       / ( 7 + 3 * 4 - ( 3 + ( 8 / 2 ) + 5 * 1 - 7 ) )");
        log.debug("result: " + result);
        assertEquals(0.4167, result);
    }

    @Test
    public void testCalculate6() throws Exception {
        Number result = calculator.calculate(" + 4.8 * 5.025 / 6.007       / ( 7.04 + 35 * 4 - (  3 . 4  1    007 + ( 8 / 5. 589 ) + 5 * 1 - 7 ) )");
        log.debug("result: " + result);
        assertEquals(0.0279, result);
    }
}