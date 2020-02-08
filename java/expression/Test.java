package expression;

public class Test {
    public static void main(String[] args) throws Exception {
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression tripleExpression = expressionParser.parse("3 * 3 / 4 ");
        int c = 0;
        int fall = 0;
        System.out.println(tripleExpression.evaluate(1,1,1));

    }
}
