package expression;

import java.util.BitSet;

public class Count extends UnaryOperation {

    public Count(TripleExpression expression) {
        super(expression);
    }

    @Override
    public int doOperation(int a) {
        int count = 0;
        while (a != 0) {
            count++;
            a = a & (a - 1);
        }
        return count;
    }
}
