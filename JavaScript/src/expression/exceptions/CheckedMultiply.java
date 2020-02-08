package expression.exceptions;


import expression.TripleExpression;

public class CheckedMultiply extends BinExpression {

    public CheckedMultiply(TripleExpression expression1, TripleExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int doOperation(int a, int b) throws Exception {
        return a * b;
    }


}
