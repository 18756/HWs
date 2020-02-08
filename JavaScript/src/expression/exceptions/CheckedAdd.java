package expression.exceptions;


import expression.TripleExpression;

public class CheckedAdd extends BinExpression {


    public CheckedAdd(TripleExpression expression1, TripleExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a + b;
    }


}
