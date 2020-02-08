package expression.exceptions;

import expression.TripleExpression;

public abstract class BinExpression implements TripleExpression {

    private TripleExpression expression1;
    private TripleExpression expression2;

    public BinExpression(TripleExpression expression1, TripleExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int evaluate(int x, int y, int z) throws Exception {
        try {
            return doOperation(expression1.evaluate(x, y, z), expression2.evaluate(x, y, z));
        } catch (ClassCastException e) {
            throw new Exception("You should give abstract operations with same type");
        }
    }

    protected abstract int doOperation(int a, int b) throws Exception;
}
