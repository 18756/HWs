package expression;

public abstract class BinOperation implements TripleExpression {
    TripleExpression a, b;

    public BinOperation(TripleExpression a, TripleExpression b) {
        this.a = a;
        this.b = b;
    }

    public int evaluate (int x, int y, int z) throws Exception {
        return doOperation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    protected abstract int doOperation(int a, int b);
}
