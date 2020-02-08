package expression;

public class MoveLeft extends BinOperation {

    public MoveLeft(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a << b;
    }
}
