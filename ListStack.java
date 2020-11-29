import java.util.LinkedList;

/*
 This class implements the Stack interface and provides
 implementations for the stub methods defined in the stack
 interface. This class makes the stack data structure using
 linked lists.
*/

public class ListStack<T> implements Stack<T> {

	// making a stack as a list
	private LinkedList<T> stack;

	// default constructor creating the stack list
	public ListStack() {
		stack = new LinkedList<T>();
	}

	@Override
	public void push(T element) {
		stack.addFirst(element);
	}

	@Override
	public T pop() {
		// save the top element of stack before removing it
		T element = stack.get(0);
		stack.remove(0);
		return element;
	}

	@Override
	public T peek() {
		return stack.get(0);
	}

	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < stack.size(); i++) {
			// add each number to the string
			output += stack.get(i) + " ";
		}
		return output;
	}

}
