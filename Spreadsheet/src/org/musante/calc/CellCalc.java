package org.musante.calc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

enum OType {
	NUMBER, ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
} 

class StackEntry {
	OType type;
	double value;
	public StackEntry(OType t, double v) {
		type = t;
		value = v;
	}
}

public class CellCalc {
	Stack<StackEntry> stack = new Stack<StackEntry>();
	List<StackEntry> list = new ArrayList<StackEntry>();

	void checkOrderOfPrecedence() {
		if (stack.isEmpty()) {
			return;
		}
		StackEntry se = stack.peek();
		if (se.type == OType.MULTIPLICATION || se.type == OType.DIVISION) {
			list.add(stack.pop());
		}
	}

	public double calculate(String string) {
		double result = 0;
		boolean inNumber = false;
		boolean minusCheck = true;
		boolean isUnaryMinus = false;
		double fraction = 1;
		
		/* convert everything to infix notation */
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (inNumber) {
				if (c >= '0' && c <= '9') {
					if (fraction > 1) {
						result += (double)(c - '0') / fraction;
						fraction /= 10;
					} else {
						result = result * 10 + (int)(c - '0');
					}
					continue;
				} else if (c == '.') {
					fraction = 10;
					continue;
				} else {
					if (isUnaryMinus) {
						result = -result;
					}
					list.add(new StackEntry(OType.NUMBER, result));
					inNumber = false;
					isUnaryMinus = false;
					minusCheck = false;
					fraction = 1;
				}
			}
			
			if (c == ' ') {
				continue;
			}
			
			if (c >= '0' && c <= '9') {
				inNumber = true;
				result = (int)(c - '0');
				continue;
			}
			
			if (c == '-' && minusCheck) {
				minusCheck = false;
				isUnaryMinus = true;
				continue;
			}
			
			if (c == '+') {
				this.checkOrderOfPrecedence();
				stack.push(new StackEntry(OType.ADDITION, 0));
			} else if (c == '-') {
				this.checkOrderOfPrecedence();
				stack.push(new StackEntry(OType.SUBTRACTION, 0));
			} else if (c == '*') {
				stack.push(new StackEntry(OType.MULTIPLICATION, 0));
			} else if (c == '/') {
				stack.push(new StackEntry(OType.DIVISION, 0));
			}
			
			minusCheck = true;
		}
		
		if (inNumber) {
			if (isUnaryMinus) {
				result = -result;
			}
			list.add(new StackEntry(OType.NUMBER, result));
		}
		
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		
		/* now do the calculation based on the list */
		while (!list.isEmpty()) {
			StackEntry se = list.remove(0);
			
			switch (se.type) {
			case NUMBER:
				stack.push(se);
				break;
			case ADDITION: {
					StackEntry a = stack.pop();
					StackEntry b = stack.pop();
					stack.push(new StackEntry(OType.NUMBER, a.value + b.value));
				}
				break;
			case SUBTRACTION: {
					StackEntry a = stack.pop();
					StackEntry b = stack.pop();
					stack.push(new StackEntry(OType.NUMBER, b.value - a.value));
				}
				break;
			case MULTIPLICATION: {
					StackEntry a = stack.pop();
					StackEntry b = stack.pop();
					stack.push(new StackEntry(OType.NUMBER, b.value * a.value));
				}
				break;
			case DIVISION: {
					StackEntry a = stack.pop();
					StackEntry b = stack.pop();
					stack.push(new StackEntry(OType.NUMBER, b.value / a.value));
				}
				break;
			}
		}
		
		return stack.pop().value;
	}

	public String cellValue(String string) {
		if (string.charAt(0) == '=') {
			DecimalFormat format = new DecimalFormat("0.#");
			return format.format(this.calculate(string.substring(1)));
		}
		
		return string;
	}

}
