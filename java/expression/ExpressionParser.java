package expression;

import java.util.Stack;

public class ExpressionParser implements Parser {
    public TripleExpression parse(String expression) throws Exception {
        Stack<TripleExpression> rpn = new Stack<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder num = new StringBuilder();
        String line = expression;
        line = line.replace(" ", "");
        line = line.replace("\t", "");
        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (isNumber(chars[i])) {
                num.append(chars[i]);
            } else {
                if (num.length() != 0) {
                    rpn.add(new Const(Integer.parseInt(num.toString())));
                    num = new StringBuilder();
                }
                if (isVar(chars[i])) {
                    rpn.add(new Variable(chars[i] + ""));
                }
                else if (chars[i] == '-' && (i == 0 || chars[i - 1] == '(' ||
                        isBinOperation(chars[i - 1]) || isUnaryOperation(chars[i - 1]))) {
                    stack.push('_'); // unary -
                }
                else if (chars[i] == '~' || chars[i] == '(') {
                    stack.push(chars[i]);
                }
                else if (chars[i] == 'c') { // count
                    stack.push('c');
                    i += 4; // go to next element
                }
                else if (chars[i] == ')') {
                    while (!stack.empty() && stack.peek() != '(') {
                        rpn.push(generateExpression(rpn, stack.pop()));
                    }
                    stack.pop(); // delete "("
                } else if (isBinOperation(chars[i])) {
                    while (!stack.empty() && stack.peek() != '(' && (getPriority(stack.peek()) > getPriority(chars[i]) ||
                            (getPriority(stack.peek()) == getPriority(chars[i]) && isLeftAssociativ(stack.peek())))) {
                        rpn.push(generateExpression(rpn, stack.pop()));
                    }
                    stack.push(chars[i]);
                } else {
                    throw new NumberFormatException("Your expression is wrong");
                }
            }
        }
        if (num.length() != 0) {
            rpn.push(new Const(Integer.parseInt(num.toString())));
        }
        while (!stack.empty()) {
            rpn.push(generateExpression(rpn, stack.pop()));
        }
        return rpn.peek();
    }



    private boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    private boolean isBinOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '&' || c == '^' || c == '|';
    }

    private boolean isUnaryOperation(char c) { // without unary -
        return c == 't' || c == '~';
    }

    private int getPriority (char c) {
        if (c == '|') {
            return 0;
        }
        if (c == '^') {
            return 1;
        }
        if (c == '&') {
            return 2;
        }
        if (c == '-' || c == '+') {
            return 3;
        }
        if (c == '/' || c == '*') {
            return 4;
        }
        return 5; //unary operation (-, ~, count)
    }

    private boolean isLeftAssociativ (char c) {
        return c == '-' || c == '/' || c == '*';
    }

    private boolean isVar(char var) {
        return var == 'x' || var == 'y' || var == 'z';
    }

    private TripleExpression generateExpression (Stack<TripleExpression> rpn, char op) throws Exception {
        TripleExpression right = rpn.pop();
        switch (op) {
            case '+' : {
                return new Add(rpn.pop(), right);
            }
            case '-' : {
                return new Subtract(rpn.pop(), right);
            }
            case '*' : {
                return new Multiply(rpn.pop(), right);
            }
            case '/' : {
                return new Divide(rpn.pop(), right);
            }
            case '&' : {
                return new And(rpn.pop(), right);
            }
            case '|' : {
                return new Or(rpn.pop(), right);
            }
            case '^' : {
                return new Xor(rpn.pop(), right);
            }
            case '_' : { // unary -
                return new UnaryMinus(right);
            }
            case '~' : {
                return new Negative(right);
            }
            case 'c' : { // count
                return new Count(right);
            }
        }
        throw new Exception("Wrong operation");
    }
}

