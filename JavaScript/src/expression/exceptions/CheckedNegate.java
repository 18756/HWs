package expression.exceptions;


import expression.TripleExpression;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(TripleExpression expression) {
        super(expression);
    }

    @Override
    protected int doOperation(int a) throws Exception {
        return -a;
    }


}
