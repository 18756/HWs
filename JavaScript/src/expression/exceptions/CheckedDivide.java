package expression.exceptions;

import expression.TripleExpression;

public class CheckedDivide extends BinExpression {

    public CheckedDivide(TripleExpression expression1, TripleExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int doOperation(int a, int b) throws Exception {
        return a / b;
    }

}
