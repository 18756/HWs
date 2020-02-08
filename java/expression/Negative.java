package expression;

public class Negative extends UnaryOperation {


    public Negative(TripleExpression expression) {
        super(expression);
    }

    @Override
    public int doOperation(int a) {
        return ~a;
    }
}
