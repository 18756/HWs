package expression;

public class Xor extends BinOperation {

    public Xor(TripleExpression a, TripleExpression b) {
        super(a, b);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a ^ b;
    }
}
