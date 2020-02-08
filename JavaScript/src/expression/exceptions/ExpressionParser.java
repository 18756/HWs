package expression.exceptions;


import expression.TripleExpression;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionParser implements Parser {

    private Stack<TripleExpression> resultOfRpn; // Reverse Polish notation
    private Stack<String> operations;
    private StringBuilder buildingConst;
    private char[] chars;
    private int i; // position
    private String lastElement; // const, variable or operation
    private final String UNARY_MINUS = "Unary -";

        @Override
    public TripleExpression parse(String expression) throws Exception {
        resultOfRpn = new Stack<>();
        operations = new Stack<>();
        chars = expression.toCharArray();
        lastElement = " ";

        for (i = 0; i < chars.length; i++) {
            if (Character.isWhitespace(chars[i])) {
                continue;
            }
            else if (Character.isDigit(chars[i])) {
                buildingConst = new StringBuilder();
                if (!operations.empty() && operations.peek().equals("Unary -")) {
                    buildingConst.append("-");
                    operations.pop();
                }
                parseNumber();
            } else if (chars[i] >= 'x' && chars[i] <= 'z') { // variable
                resultOfRpn.push(new Variable(chars[i]));
                lastElement = "v"; // variable
            } else if (chars[i] == '-' && (" -+*/asm(".contains(lastElement))) { // "some operation - ..." This is unary -
                operations.push(UNARY_MINUS);
                lastElement = "-";
            } else if(chars[i] == 'a'){ // abs
                testWord("abs");
                operations.push("a");
                lastElement = "a";
            } else if(chars[i] == 's'){ // square
                testWord("square");
                operations.push("s");
                lastElement = "s";
            } else if (chars[i] == '(') {
                operations.push("(");
                lastElement = "(";
            } else if (chars[i] == ')') {
                if (lastElement.equals("(")) {
                    throw new Exception("Empty parentheses");
                }
                while (!operations.empty() && !operations.peek().equals("(")) {
                    addExpressionInResultOfRpn(operations.pop());
                }
                lastElement = ")";
                if (operations.empty() || !operations.pop().equals("(")) {
                    throw new Exception("Wrong expression. You missed '('");
                }
            } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/' || chars[i] == 'm') {
                String curOp = chars[i] + "";
                if (chars[i] == 'm') {
                    testWord("mod");
                }
                while (!operations.empty() && isLeftMorePriority(operations.peek(), curOp)){
                    addExpressionInResultOfRpn(operations.pop());
                }
                operations.push(curOp);
                lastElement = curOp;
            } else {
                throw new Exception("Wrong operation");
            }
        }
        while (!operations.empty()) {
            if (operations.peek().equals("(")) {
                throw new Exception("You missed ')'");
            }
            addExpressionInResultOfRpn(operations.pop());
        }
        if (resultOfRpn.empty()) {
            throw new Exception("Empty expression");
        }
        TripleExpression finalExpression = resultOfRpn.pop();
        if (!resultOfRpn.empty()) {
            throw new Exception("Wrong expression");
        }
        return finalExpression;
    }

    private void testWord(String word) throws Exception {
        boolean isOk = true;
        int k = 0;
        for (; i < chars.length && k < word.length(); i++, k++) {
            isOk = isOk && chars[i] == word.charAt(k);
        }
        i--; // cycle "for" in "parse" will increment i;
        isOk = isOk && k == word.length();
        if (!isOk) {
            throw new Exception("Wrong operation, perhaps you meant: " + word);
        }
    }

    private void parseNumber() throws Exception {
        lastElement = "c"; // const
        parseInteger();
        if (i < chars.length && chars[i] == '.') {
            parseInteger();
        }
        i--; // cycle "for" in "parse" will increment i
        try {
            //resultOfRpn.push(new Const(buildingConst.toString(), abstractOperations));
        } catch (NumberFormatException e) {
            throw new Exception("Wrong number: " + buildingConst.toString());
        }
    }

    private void parseInteger() {
        do {
            buildingConst.append(chars[i]);
            i++;
        } while (i < chars.length && Character.isDigit(chars[i]));
    }


    private void addExpressionInResultOfRpn(String operation) throws Exception {
        try {
            TripleExpression expression2 = this.resultOfRpn.pop(); // right expression
            switch (operation) {
                case "+": {
                    resultOfRpn.push(new CheckedAdd(resultOfRpn.pop(), expression2));
                    break;
                }
                case "-": {
                    resultOfRpn.push(new CheckedSubtract(resultOfRpn.pop(), expression2));
                    break;
                }
                case "*": {
                    resultOfRpn.push(new CheckedMultiply(resultOfRpn.pop(), expression2));
                    break;
                }
                case "/": {
                    resultOfRpn.push(new CheckedDivide(resultOfRpn.pop(), expression2));
                    break;
                }
                case UNARY_MINUS: {
                    resultOfRpn.push(new CheckedNegate(expression2));
                    break;
                }
            }
        } catch (EmptyStackException e) {
            throw new Exception("Wrong expression. You missed some operand");
        }
    }

    private boolean isLeftMorePriority(String op1, String op2) {
        if (getPriority(op1) > getPriority(op2)) {
            return true;
        } else if (getPriority(op1) == getPriority(op2) && isLeftAssociative(op1)) {
            return true;
        } else {
            return false;
        }
    }

    private int getPriority(String op) {
        if (op.equals(UNARY_MINUS) || op.equals("a") || op.equals("s")) {
            return 3;
        } else if (op.equals("*") || op.equals("/") || op.equals("m")) {
            return 2;
        } else { // - or + or (
            return 1;
        }
    }

    private boolean isLeftAssociative(String op) {
        return op.equals("-") || op.equals("/") || op.equals("*") || op.equals("m");
    }
}
