package expression.exceptions;


import expression.TripleExpression;

public class CheckedSubtract extends BinExpression {

    public CheckedSubtract(TripleExpression expression1, TripleExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int doOperation(int a, int b) throws Exception {
        return a - b;
    }


}
