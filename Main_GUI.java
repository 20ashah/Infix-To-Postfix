import javax.swing.*;
import BreezySwing.*;

/*
Arjun Shah
11/26/2019
B Block
Program Description:
This program takes in an infix expression from the user, converts it to post-fix
and then evaluates the answer. The answer and the post-fix expression will display 
on the screen.
*/

public class Main_GUI extends GBFrame {

	// GUI components
	private JLabel infixLabel, postfixLabel, answerLabel;
	private JTextField infixField, postfixField, answerField;
	private JButton convert;

	// constructor for positioning GUI components
	public Main_GUI() {
		infixLabel = addLabel("Infix Expression", 1, 1, 1, 1);
		postfixLabel = addLabel("Postfix Expression", 2, 1, 1, 1);
		answerLabel = addLabel("Answer", 4, 1, 1, 1);
		infixField = addTextField("", 1, 2, 1, 1);
		postfixField = addTextField("", 2, 2, 1, 1);
		answerField = addTextField("", 4, 2, 1, 1);
		convert = addButton("Evaluate", 3, 1, 1, 1);

		// user can't edit these fields
		postfixField.setEditable(false);
		answerField.setEditable(false);

	}

	// perform actions when buttons are clicked
	public void buttonClicked(JButton button) {
		if (button == convert) { // if user clicks the convert button
			InfixConverter i = new InfixConverter();
			PostfixEvaluator p = new PostfixEvaluator();
			try {
				// get post-fix and answer
				String postfix = i.convert(infixField.getText());
				double answer = p.evaluate(postfix);
				// set results to fields
				postfixField.setText(postfix);
				answerField.setText(answer + "");
			} catch (Exception e) { // throw error message if error occurs
				postfixField.setText("Invalid infix expression!");
				answerField.setText("Invalid infix expression!");
			}
		}
	}

	// main method
	public static void main(String[] args) {
		// sets the size and name of the window
		Main_GUI theGUI = new Main_GUI();
		theGUI.setSize(500, 175);
		theGUI.setVisible(true);
		theGUI.setTitle("Infix Evaluator");
	}

}
