package expression;

public abstract class UnaryOperation implements TripleExpression {
    TripleExpression expression;

    public UnaryOperation(TripleExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x, int y, int z) throws Exception {
        return doOperation(expression.evaluate(x, y, z));
    }

    public abstract int doOperation(int a);
}
