package expression;

public class Multiply extends BinOperation {

    public Multiply(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a * b;
    }
}
