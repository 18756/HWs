package expression;

public class Const implements TripleExpression {
    private final int c;

    public Const(int c) {
        this.c = c;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return (int) c;
    }
}
