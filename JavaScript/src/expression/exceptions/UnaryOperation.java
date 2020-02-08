package expression.exceptions;


import expression.TripleExpression;

public abstract class UnaryOperation implements TripleExpression {

    private TripleExpression expression;


    public UnaryOperation(TripleExpression expression) {
        this.expression = expression;
    }

    public int evaluate(int x, int y, int z) throws Exception {
        try {
            return doOperation(expression.evaluate(x, y, z));
        } catch (ClassCastException e) {
            throw new Exception("You should use abstract operations with same type");
        }
    }

    protected abstract int doOperation(int a) throws Exception;
}
