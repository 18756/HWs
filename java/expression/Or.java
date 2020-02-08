package expression;

public class Or extends BinOperation {

    public Or(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a | b;
    }
}
