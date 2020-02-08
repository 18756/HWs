package expression;

public class Add extends BinOperation {

    public Add(TripleExpression a, TripleExpression b) {
        super(a, b);
    }


    @Override
    protected int doOperation(int a, int b) {
        return a + b;
    }
}
