package expression;

public class And extends BinOperation {

    public And(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a & b;
    }
}
