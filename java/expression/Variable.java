package expression;

public class Variable implements TripleExpression {
    String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x, int y, int z) throws Exception {
        switch (var) {
            case "x" : return x;
            case "y" : return y;
            case "z" : return z;
        }
        throw new Exception("Wrong var's name. You should use: x, y , z");
    }
}
