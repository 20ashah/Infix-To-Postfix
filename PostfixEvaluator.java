import java.util.LinkedList;

/*
 This class codes the back end of the program that takes
 in a post-fix expression and evaluates it. It contains a 
 method to do this as well as many helper methods. 
*/

public class PostfixEvaluator {

	// declare stack
	private ListStack<Double> stack;

	// all possible operations
	private final char ADD = '+';
	private final char SUBTRACT = '-';
	private final char DIVIDE = '/';
	private final char MULTIPLY = '*';
	private final char EXPONENT = '^';

	// default constructor for creating stack
	public PostfixEvaluator() {
		stack = new ListStack<Double>();
	}

	// evaluates the given post-fix expression
	public double evaluate(String expression) throws Exception {
		// parse string to individual components
		LinkedList<String> nums = parse(expression);
		for (int i = 0; i < nums.size(); i++) {
			// check if the element is an operation
			if (nums.get(i).length() == 1 && isOperator(nums.get(i).charAt(0))) {
				// remove top to elements of stack
				double firstInStack = stack.pop();
				double secondInStack = stack.pop();
				// perform operation and add to the stack
				double eval = evalSingleOp(nums.get(i).charAt(0), secondInStack, firstInStack);
				stack.push(eval);
			} else {
				// add element to top of stack
				stack.push(Double.valueOf(nums.get(i)));
			}
		}

		// error check final stack
		if (stack.size() != 1) {
			throw new Exception();
		}

		return stack.peek(); // answer
	}

	// helper method that checks if a character is an operation
	private boolean isOperator(char operation) {
		if (operation == ADD || operation == SUBTRACT || operation == DIVIDE || operation == MULTIPLY
				|| operation == EXPONENT) {
			return true;
		}
		return false;
	}

	// evaluates a single mathematical operation
	private double evalSingleOp(char operation, double op1, double op2) {
		// checks which operation it is and performs it
		if (operation == ADD) {
			return op1 + op2;
		} else if (operation == SUBTRACT) {
			return op1 - op2;
		} else if (operation == DIVIDE) {
			return op1 / op2;
		} else if (operation == MULTIPLY) {
			return op1 * op2;
		} else {
			return Math.pow(op1, op2);
		}
	}

	// parsing a string separated by spaces into pieces
	public static LinkedList<String> parse(String csv) {
		LinkedList<String> nums = new LinkedList<String>();
		for (int i = 0; i < csv.length(); i++) {
			String num = "";
			int count = 0;
			// checks each character if its not a space
			while (i != csv.length() && csv.charAt(i) != ' ') {
				// add to appending number
				num += csv.charAt(i);
				i++; // increment position
				count = 1;
			}
			if (count == 1) {
				// add to list if not a space
				nums.add(num);
			}
		}
		return nums;
	}

}
