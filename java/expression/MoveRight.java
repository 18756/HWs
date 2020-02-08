package expression;

public class MoveRight extends BinOperation {

    public MoveRight(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a >> b;
    }
}
