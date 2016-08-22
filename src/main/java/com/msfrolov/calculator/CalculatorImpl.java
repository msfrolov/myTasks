package com.msfrolov.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorImpl implements Calculator {

    private static final String REGEX_PARSER = "([0-9.]+)|([+-/*]+?)|([\\(\\)])+?";
    private static final String REGEX_CHARACTER = "^[0-9.+-/*\\(\\)]+$";
    private static final String REGEX_OPERATORS = "[+-/*]{2,}";
    private static final String REGEX_DIGITAL = "[0-9.]+";
    private static final String REGEX_USELESS_CHAR = "\\s";

    @Override
    public Double calculate(String expr) {
        if (isEmpty(expr)) {
            System.out.println("expression is empty");
            return null;
        }
        expr = removeUselessChar(expr);
        if (!checkCharacter(expr)) {
            System.out.println("expression is not valid");
            return null;
        }

        List<Object> list = splitExpression(expr);
        Double result = processExpression(list);
        result = new BigDecimal(result).setScale(4, RoundingMode.UP).doubleValue();
        return result;
    }

    private Double processExpression(List<Object> list) {
        System.out.println(list);
        System.out.println("size" + list.size());

        Double result;
        while (list.size() > 1) {
            list = multiplyAndDivide(list);
            list = additionAndSubtraction(list);
            list = openBrackets(list);
        }

        System.out.println(list);
        System.out.println("size" + list.size());
        return (Double) list.get(0);
    }

    private List<Object> additionAndSubtraction(List<Object> list) {
        Boolean found = true;
        while (found) {
            Object previous = null;
            Object next;
            found = false;
            for (int i = 0; i < list.size(); i++) {
                Object current = list.get(i);
                if (i > 0) {
                    previous = list.get(i - 1);
                }
                if (i != list.size() - 1) {
                    next = list.get(i + 1);
                } else {
                    next = null;
                }
                if (next instanceof Double && previous instanceof Double) {
                    if ("+".equals(current)) {
                        Double result = (Double) previous + (Double) next;
                        list.remove(i + 1);
                        list.remove(i);
                        list.set(i - 1, result);
                        found = true;
                        break;
                    }
                    if ("-".equals(current)) {
                        Double result = (Double) previous - (Double) next;
                        list.remove(i + 1);
                        list.remove(i);
                        list.set(i - 1, result);
                        found = true;
                        break;
                    }
                }
                if (list.size() == 2) {
                    list.set(0, Double.parseDouble((String) list.get(0) + list.get(1)));
                    list.remove(1);
                }
            }
        }
        return list;
    }

    private List<Object> openBrackets(List<Object> list) {
        Boolean found = true;
        while (found) {
            Object previous = null;
            Object next = null;
            found = false;
            for (int i = 0; i < list.size(); i++) {
                Object current = list.get(i);
                if (i > 0) {
                    previous = list.get(i - 1);
                }
                if (i != list.size() - 1) {
                    next = list.get(i + 1);
                } else {
                    next = null;
                }
                if (current instanceof Double) {
                    if (")".equals(next) && "(".equals(previous)) {
                        list.remove(i + 1);
                        list.remove(i);
                        list.set(i - 1, current);
                        found = true;
                        break;
                    }
                }
            }
        }
        return list;
    }

    private List<Object> multiplyAndDivide(List<Object> list) {
        Boolean found = true;
        while (found) {
            Object previous = null;
            Object next = null;
            found = false;
            for (int i = 0; i < list.size(); i++) {
                Object current = list.get(i);
                if (i > 0) {
                    previous = list.get(i - 1);
                }
                if (i != list.size() - 1) {
                    next = list.get(i + 1);
                } else {
                    next = null;
                }
                if (next instanceof Double && previous instanceof Double) {
                    if ("/".equals(current)) {
                        Double result = (Double) previous / (Double) next;
                        list.remove(i + 1);
                        list.remove(i);
                        list.set(i - 1, result);
                        found = true;
                        break;
                    }
                    if ("*".equals(current)) {
                        Double result = (Double) previous * (Double) next;
                        list.remove(i + 1);
                        list.remove(i);
                        list.set(i - 1, result);
                        found = true;
                        break;
                    }
                }
            }
        }
        return list;
    }

    private boolean isEmpty(String expr) {
        return "".equals(expr) || expr == null;
    }

    /**
     * check of the presence of illegal characters
     *
     * @param stringIn original expression
     * @return true if there are only permitted characters
     */
    private boolean checkCharacter(String stringIn) {

        Pattern patternOperators = Pattern.compile(REGEX_OPERATORS);
        Matcher matcherOperators = patternOperators.matcher(stringIn);
        boolean checkOperators = !matcherOperators.find();

        Pattern patternChar = Pattern.compile(REGEX_CHARACTER);
        Matcher matcherChar = patternChar.matcher(stringIn);
        boolean checkChar = matcherChar.find();

        Pattern patternDigital = Pattern.compile(REGEX_DIGITAL);
        Matcher matcherDigital = patternDigital.matcher(stringIn);
        boolean checkDigital = matcherDigital.find();

        return checkChar && checkOperators && checkDigital;
    }

    /**
     * parsing the original string
     *
     * @param stringIn expression to split
     * @return It returns a array split into determinant expression
     * in the form of numbers, operators, and priority signs
     */
    private List<Object> splitExpression(String stringIn) {
        Pattern pattern = Pattern.compile(REGEX_PARSER);
        Matcher matcher = pattern.matcher(stringIn);
        List<Object> groups = new ArrayList<>();
        while (matcher.find()) {
            Object o = matcher.group();
            //try to convert numbers
            try {
                o = Double.parseDouble((String) o);
            } catch (Exception e) {
            }
            groups.add(o);
        }
        return groups;
    }

    /**
     * removal of insignificant characters from the input string (White Space)
     *
     * @param stringIn expression to replace
     * @return string without insignificant  characters
     */
    private String removeUselessChar(String stringIn) {
        Pattern pattern = Pattern.compile(REGEX_USELESS_CHAR);
        Matcher matcher = pattern.matcher(stringIn);
        return matcher.replaceAll("");
    }

}
