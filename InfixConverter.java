import java.util.LinkedList;

/*
 This class codes for the back end that converts an infix string to 
 post fix. To do this, this class contains many helper methods in 
 addition to the main one. This class uses a string and a stack to
 convert the infix string to post fix.  
*/

public class InfixConverter {

	// constant operators
	private final char ADD = '+';
	private final char SUBTRACT = '-';
	private final char DIVIDE = '/';
	private final char MULTIPLY = '*';
	private final char EXPONENT = '^';
	private final char OPEN = '(';
	private final char CLOSE = ')';

	// checking if a character is an operation
	private boolean isOperator(char operation) {
		if (operation == ADD || operation == SUBTRACT || operation == DIVIDE || operation == MULTIPLY
				|| operation == EXPONENT) {
			return true;
		}
		return false;
	}

	// assigning values for operators depending on PEMDAS
	private int getPreferenceNum(char operation) {
		if (operation == ADD || operation == SUBTRACT) { // lowest
			return 0;
		} else if (operation == DIVIDE || operation == MULTIPLY) {
			return 1;
		} else if (operation == EXPONENT) {
			return 2;
		} else { // highest
			return 3;
		}
	}

	// checking which operation is evaluated first
	private boolean getPreference(char op1, char op2) {
		// compare assigned values
		if (getPreferenceNum(op1) > getPreferenceNum(op2)) {
			return true;
		} else {
			return false;
		}
	}

	// converts an infix string to post fix
	public String convert(String infix) {
		// string and stack for performing conversion
		String postfix = "";
		ListStack<Character> stack = new ListStack<Character>();
		LinkedList<String> infixNum = PostfixEvaluator.parse(infix); // parsed out numbers
		// read in each entry
		for (int i = 0; i < infixNum.size(); i++) {
			if (infixNum.get(i).length() == 1 && infixNum.get(i).charAt(0) == OPEN) { // if open parenthesis
				stack.push(OPEN); // add to stack
			} else if (infixNum.get(i).length() == 1 && isOperator(infixNum.get(i).charAt(0))) { // if operator
				if (stack.size() == 0 || stack.peek() == OPEN) { // if nothing in the stack
					stack.push(infixNum.get(i).charAt(0)); // add to stack
				} else if (getPreference(infixNum.get(i).charAt(0), stack.peek())) { // higher precedence
					stack.push(infixNum.get(i).charAt(0)); // add to stack
				} else { // same or lower precedence
					// pop and add to string all operators with lower or same precedence
					while (stack.size() != 0 && stack.peek() != OPEN
							&& !getPreference(infixNum.get(i).charAt(0), stack.peek())) {
						postfix += stack.pop() + " ";
					}
					stack.push(infixNum.get(i).charAt(0)); // add newest operator to stack
				}
			} else if (infixNum.get(i).length() == 1 && infixNum.get(i).charAt(0) == CLOSE) { // if close parenthesis
				// pop and add to string until open parenthesis is reached
				while (stack.size() != 0 && stack.peek() != OPEN) {
					postfix += stack.pop() + " ";
				}
				stack.pop(); // remove open parenthesis
			} else { // regular number
				postfix += infixNum.get(i) + " "; // add to string
			}

		}

		// add rest of the stack to the string
		while (stack.size() != 0) {
			postfix += stack.pop() + " ";
		}

		return postfix;
	}

}
