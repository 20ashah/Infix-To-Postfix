/*
 This is an interface that contains stub methods
 for performing actions to a stack.
*/

public interface Stack<T> {

	// inserts an element at the top of the stack
	public void push(T element);

	// removes and returns element at the top of the stack
	public T pop();

	// returns object at the top of the stack
	public T peek();

	// checks whether the stack is empty
	public boolean isEmpty();

	// returns the size of the stack
	public int size();

	// returns the string version of the stack
	public String toString();

}
