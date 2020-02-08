let pi  = (x, y, z) => Math.PI;
let e   = (x, y, z) => Math.E;
let one = (x, y, z) => 1;
let two = (x, y, z) => 2;

let someOperation = (operation) => (exp1, ...expressions) => (x, y, z) => {
	let result = operation(exp1(x, y, z));
	for (let i = 0; i < expressions.length; i++) {
		result = operation(result, expressions[i](x, y, z));
	}
	return result;
}

let add = someOperation((a, b = 0) => a + b);
let subtract = someOperation((a, b = 0) => a - b);
let multiply = someOperation((a, b = 1) => a * b);
let divide = someOperation((a, b = 1) => a / b);
let negate = someOperation((a) => -a);

let abs = (exp) => (x, y, z) => (Math.abs(exp(x, y, z)));

let iff = (exp1, exp2, exp3) => (x, y, z) => (exp1(x, y, z) >= 0 ? exp2(x, y, z) : exp3(x, y, z));

let avg5 = (exp1, ...expressions) => (divide(add(exp1, ...expressions), cnst(expressions.length + 1)));

let med3 = (exp1, ...expressions) => (x, y, z) => {
	expressions.push(exp1);
	for (let i = 0; i < expressions.length; i++) {
		expressions[i] = expressions[i](x, y, z)
	}
	return expressions.sort()[(expressions.length - expressions.length % 2) / 2];
};

let cnst = (num) => (x, y, z) => (num);

let variable = (v) => (x, y, z) => {
	switch(v) {
		case "x" : return x;
		case "y" : return y;
		default  : return z;
	}
};





