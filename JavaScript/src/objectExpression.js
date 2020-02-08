class ParseError extends Error{
    constructor(message) {
        super(message);
        this.name = "ParseError";
    }
}


class AbstractOperation {
    constructor(operation, stringOperation, checkerIsWrongLengthExpressions, expressions) {
        while (expressions[0] instanceof Array) { // with it, you can use "new Add([...]);"
            expressions = expressions[0];
        }
        if (checkerIsWrongLengthExpressions(expressions.length)) {
            throw new ParseError("Wrong amount of expressions");
        }
        this.operation = operation;
        this.stringOperation = stringOperation;
        this.expressions = expressions;
    }

    evaluate(x, y, z) {
        let results = this.expressions.map(exp => exp.evaluate(x, y, z));
        let res = this.operation(results[0]);
        for (let i = 1; i < results.length; i++) {
            res = this.operation(res, results[i]);
        }
        return res;
    }

    postfix() {
        let rpn = "("; // reverse polish notation
        for (let i = 0; i < this.expressions.length; i++) {
            rpn += this.expressions[i].postfix() + " ";
        }
        if (this.expressions.length === 0) {
            rpn += " "; // example : ( sum)
        }
        rpn += this.stringOperation + ")";
        return rpn;
    }

    prefix() {
        let result = "(" + this.stringOperation;
        for (let i = 0; i < this.expressions.length; i++) {
            result += " " + this.expressions[i].prefix();
        }
        if (this.expressions.length === 0) {
            result += " "; // example : (sum )
        }
        return result + ")";
    }
}

class Add extends AbstractOperation {
    constructor(...expressions) {
    super((a, b = 0) => a + b, "+", (len) => (len !== 2), expressions);
}
}

class Subtract extends AbstractOperation {
    constructor(...expressions) {
    super((a, b = 0) => a - b, "-", (len) => (len !== 2), expressions);
}
}

class Multiply extends AbstractOperation {
    constructor(...expressions) {
    super((a, b = 1) => a * b, "*", (len) => (len !== 2), expressions);
}
}

class Divide extends AbstractOperation {
    constructor(...expressions) {
    super((a, b = 1) => a / b, "/", (len) => (len !== 2), expressions);
}
}

class Negate extends AbstractOperation {
    constructor(...expressions) {
    super((a) => -a, "negate", (len) => (len !== 1), expressions);
}
}

class Sum extends AbstractOperation {
    constructor(...expressions) {
    super((a = 0, b = 0) => a + b, "sum", (len) => (false), expressions);
}
}

class Avg extends Sum {
    constructor(...expressions) {
    super(expressions);
    this.stringOperation = "avg";
}

evaluate(x, y, z) {
    return super.evaluate(x, y, z) / this.expressions.length;
}
}



class Const {
    constructor(num) {
        this.num = num;
    }

    evaluate(x, y, z) {
        return this.num;
    }

    postfix() {
        return this.num + "";
    }

    prefix() {
        return this.num + "";
    }
}

class Variable {
    constructor(v) {
        this.v = v;
    }

    evaluate(x, y, z) {
        switch (this.v) {
            case "x" :
                return x;
            case "y" :
                return y;
            default  :
                return z;
        }
    }

    postfix() {
        return this.v;
    }

    prefix() {
        return this.v;
    }
}


let parsing = (function () {

    let stackOfOperations;
    let stackOfOperands;
    let strExp;
    let i;
    let curOperation;
    let curOperand;
    let lastElement;
    let lastIdOfStacks;


    function parsePrefixOrPostfix(isPrefixParse) {
        return function (exp) {
            stackOfOperations = [null]; // fictitious element
            stackOfOperands = [[]];
            strExp = exp;
            lastElement = "";
            lastIdOfStacks = 0; // It equals balance of brackets
            let iBeforeParse;

            for (i = 0; i < strExp.length; i++) {
                iBeforeParse = i; // for checking separator between elements
                if (strExp[i] === ' ') {
                    continue;
                } else if (strExp[i] === '(') {
                    stackOfOperations.push([]);
                    stackOfOperands.push([]);
                    lastIdOfStacks++;
                    lastElement = "(";
                } else if (strExp[i] === ')') {
                    lastIdOfStacks--;
                    if (lastIdOfStacks < 0) {
                        throw new ParseError("Wrong bracket sequence");
                    } else if (stackOfOperations[lastIdOfStacks + 1].length === 0) {
                        throw new ParseError("You should give one operation in each pair of brackets");

                    } else if (lastElement !== "operation" && !isPrefixParse) { // postfixParse
                        throw new ParseError("Wrong order of operation and operands. Syntax: (operand(s) operation)");
                    }
                    stackOfOperands[lastIdOfStacks].push(createOperation());
                    lastElement = ")";
                } else if ((curOperation = getOperation()) !== undefined) {
                    if (isPrevOperationOrOperand(iBeforeParse)) {
                        throw new ParseError("You should add at least 1 whitespace between operation and operands");
                    } else if (lastIdOfStacks === 0) {
                        throw new ParseError("You should give each operation in brackets");
                    } else if (stackOfOperations[lastIdOfStacks].length !== 0) {
                        throw new ParseError("You should give only one operation in each pair of brackets");
                    } else if (lastElement !== "(" && isPrefixParse) {
                        throw new ParseError("Wrong order of operation and operands. Syntax: (operation operand(s))");
                    }
                    stackOfOperations[lastIdOfStacks].push(curOperation);
                    lastElement = "operation";
                } else if ((curOperand = getOperand()) !== undefined) {
                    if (isPrevOperationOrOperand(iBeforeParse)) {
                        throw new ParseError("You should add at least 1 whitespace between operation and operands");
                    }
                    stackOfOperands[lastIdOfStacks].push(curOperand);
                    lastElement = "operand";
                } else {
                    throw new ParseError("Wrong element of expression.");
                }
            }
            if (stackOfOperands.length !== 1) {
                throw new ParseError("Wrong bracket sequence");
            } else if (stackOfOperands[0].length !== 1) {
                throw new ParseError("Wrong expression");
            }
            return stackOfOperands[0][0];
        }
    }


    function getOperation() {
        if (strExp[i] === '+' || strExp[i] === '-' || strExp[i] === '*' || strExp[i] === '/') {
            if (strExp[i] === '-' && i + 1 < strExp.length && isDigit(strExp[i + 1])) {
                return; // this is negative number;
            }
            return strExp[i];
        } else if (strExp[i] === 'n') {
            testWord("negate");
            return "negate";
        } else if (strExp[i] === 's') {
            testWord("sum");
            return "sum";
        } else if (strExp[i] === 'a') {
            testWord("avg");
            return "avg";
        }
    }

    function testWord(word) {
        let j = 0;
        for (; j < word.length && i < strExp.length; j++, i++) {
            if (word[j] !== strExp[i]) {
                throw new ParseError("Wrong name of function, may be you meant: " + word);
            }
        }
        i--; // cycle 'for' will increment i;
        if (j !== word.length) {
            throw new ParseError("Wrong end of expression, syntax: operation operand(s)");
        }
    }

    function getOperand() {
        if (isDigit(strExp[i]) || strExp[i] === '-') {
            return new Const(parseNumber());
        } else if (strExp[i] === 'x' || strExp[i] === 'y' || strExp[i] === 'z') {
            return new Variable(strExp[i]);
        }
    }

    function parseNumber() {
        let num = "";
        if (strExp[i] === '-') {
            num += "-";
            i++;
        }
        num += parseInteger();
        if (i < strExp.length && strExp[i] === '.') {
            num += ".";
            i++;
            if (i === strExp.length || !isDigit(strExp[i])) {
                throw new ParseError("Wrong number. Syntax of numbers: digits[.digits][e+(-)digits]");
            }
            num += parseInteger();
        }
        if (i < strExp.length && strExp[i] === 'e') {
            num += "e";
            i++;
            if (i === strExp.length || (strExp[i] !== '+' && strExp[i] !== '-')) {
                throw new ParseError("Wrong number. Syntax of numbers: digits[.digits][e+(-)digits]");
            }
            num += strExp[i];
            i++;
            if (i === strExp.length || !isDigit(strExp[i])) {
                throw new ParseError("Wrong number. Syntax of numbers: digits[.digits][e+(-)digits]");
            }
            num += parseInteger();
        }
        i--; // cycle for will increment "i"
        return Number(num);
    }

    function parseInteger() {
        let num = "";
        for (; i < strExp.length && isDigit(strExp[i]); i++) {
            num += strExp[i];
        }
        return num;
    }

    function createOperation() {
        let operation = stackOfOperations.pop();
        let operands = stackOfOperands.pop();

        switch (operation[0]) {
            case "+" :
                return new Add(operands);
            case "-" :
                return new Subtract(operands);
            case "*" :
                return new Multiply(operands);
            case "/" :
                return new Divide(operands);
            case "negate" :
                return new Negate(operands);
            case "sum" :
                return new Sum(operands);
            case "avg" :
                return new Avg(operands);
            default :
                throw new ParseError("Wrong operation in the stack");
        }
    }

    function isDigit(symbol) {
        return "0" <= symbol && symbol <= "9";
    }

    function isPrevOperationOrOperand(id) {
        return id > 0 && strExp[id - 1] !== " " && strExp[id - 1] !== "(" && strExp[id - 1] !== ")";
    }

    return {parsePrefix: parsePrefixOrPostfix(true), parsePostfix: parsePrefixOrPostfix(false)};
})();

let parsePrefix = parsing.parsePrefix;
let parsePostfix = parsing.parsePostfix;
