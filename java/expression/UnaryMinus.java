package expression;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(TripleExpression expression) {
        super(expression);
    }

    @Override
    public int doOperation(int a) {
        return -a;
    }
}
