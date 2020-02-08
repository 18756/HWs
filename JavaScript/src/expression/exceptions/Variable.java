package expression.exceptions;


import expression.TripleExpression;

public class Variable implements TripleExpression {



    private final char var;

    public Variable(char var) {
        this.var = var;
    }

    public Variable(String var) {
        this(var.charAt(0));
    }


    @Override
    public int evaluate(int x, int y, int z) throws Exception {
        switch (var) {
            case 'x': return x;
            case 'y': return y;
            default : return z;
        }
    }

}
