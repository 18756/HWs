package expression;

public class Subtract extends BinOperation {

    public Subtract(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a - b;
    }
}
