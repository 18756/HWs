package expression;

public class Divide extends BinOperation{

    public Divide(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a / b;
    }
}
