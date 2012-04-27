import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class VisualPolynomial extends JFrame implements ActionListener{
	/**
	 * The instance variables we will work in out application:
	 * polynomePanel, resultPanel, inputPanel1, inputPanel2, operationPanel, 
	 * bOperationPanel, uOperationPanel - the objects of type JPanel used here in order 
	 *                                    to try to put the operations, inputs and outputs 
	 *                                    in separate places
	 * polynomeList1, polynomeList2, polynomeList3, bOperationList, uOperationList -
	 *      JList type objects for selecting operands and operations to be performed
	 * coefficientFields1, coefficientFields2, coefficientLabel1, coefficientLabel2 -
	 *    2 vectors of JTextField respectively JLabel type objects, associated so as to
	 *    input the coefficients of the 2 polynomials at a time - visible only at reading.
	 * degreeField1, degreeField2 - 2 JTextFields for introducing the degrees of the 
	 *                              input Polynomial objects
	 * value, evaluationField - a combination of a JTextField and a JLabel for 
	 *                          the introduction of the evaluation point or the scalar 
	 *                          value for multiplication
	 * operations1, operations2 - 2 arrays of Strings with the options to be inserted 
	 *                            in the JList objects bOperationsList and uOperationsList
	 * inputLabel, result, inputLabel2 - 3 JLabels used for displaying the inputs and
	 *                                   the result of a computation on the Panel                                  
	 * confirmButton1, confirmButton2, confirmButton3, confirmButton4, confirmButton5 -
	 *     5 buttons, each attached to a given Panel in order to either validate the 
	 *     introduction of the inputs or to give the command of the execution towards 
	 *     a selected operation and operands from the given JList objects  
	 * p,q - the input Polynomials for performing operations on
	 */
	private JPanel polynomePanel;
	private JPanel resultPanel;
	private JPanel inputPanel1;
	private JPanel inputPanel2;
	private JPanel operationPanel;
	private JPanel bOperationPanel;
	private JPanel uOperationPanel;

	private JList  polynomeList1;
	private JList  polynomeList2;
	private JList  polynomeList3;
	private JList  bOperationList;
	private JList  uOperationList;
	
	private JTextField[] coefficientFields1;
	private JTextField[] coefficientFields2;
	private JLabel[] coefficientLabel1;
	private JLabel[] coefficientLabel2;
	
	private JTextField degreeField1;
	private JTextField degreeField2;
	
	private JLabel value;
	private JTextField evaluationField;
	
	private String[] operations1={"differentiation", "integration", "evaluation","scalarMultiplication"};
	private String[] operations2={"addition","subtraction","multiplication","division"};
	
	private JLabel inputLabel; 
	private JLabel result;
	private JLabel inputLabel2;
	
	private JButton confirmButton1;
	private JButton confirmButton2;
	private JButton confirmButton3;
	private JButton confirmButton4;
	private JButton confirmButton5;
	
	private Polynomial p,q;

    /**
     * The class constructor, VisualPolynomial(), has the responsibility of performing
     * the adjustments to the GUI part of our application - that is the frames and their
     * components previously mentioned in the instance variable list as well as making
     * this part visible and operable by a user. 
     */
	public VisualPolynomial(){
		//set the big JPanel with a GridLayout for holding the rest of the graphical components
		this.polynomePanel = new JPanel(new GridLayout(5,1));
		//create the panel for result and input display
		this.resultPanel = new JPanel();
		//create the result label and attach to it
		this.result = new JLabel("Result:");
		this.resultPanel.add(result);
        //create the first input label and attach to it
		this.inputLabel = new JLabel("First input:");
		this.resultPanel.add(inputLabel);
        //create the second input label and attach to it
		this.inputLabel2 = new JLabel("Second input:");
		this.resultPanel.add(inputLabel2);
        //next create the panel for the introduction of the input degrees of the 2
		//polynomials  
		JPanel degreePanel = new JPanel();
		//also create the text fields for inputting the coefficients and add them to 
		//panel in question
		this.degreeField1 = new JTextField(2);
		degreePanel.add(degreeField1);
		this.degreeField2 = new JTextField(2);
		degreePanel.add(degreeField2);
        //add a button with an attached action listener in order to validate the 
		//introduction of the degrees
		confirmButton1 = new JButton("OK");
		confirmButton1.addActionListener(this);
		degreePanel.add(confirmButton1);
        //create the input panels and attach to them for now a button with an action 
		//listener to submit the introduction of coefficients 
		this.inputPanel1 = new JPanel();
		confirmButton2 = new JButton("Submit");
		confirmButton2.addActionListener(this);
		inputPanel1.add(confirmButton2);
		this.inputPanel2 = new JPanel();
		confirmButton3 = new JButton("Submit");
		confirmButton3.addActionListener(this);
		inputPanel2.add(confirmButton3);
        //create the large operationPanel and the 2 subpanels attached to it
		this.operationPanel = new JPanel();
		this.bOperationPanel = new JPanel();
        //create the JList objects containing the Polynomial type operands
		String[] vector= {"Polynomial 1", "Polynomial 2"};
		this.polynomeList1 = new JList(vector);
		this.polynomeList2 = new JList(vector);
		this.polynomeList3 = new JList(vector);
		//add the first such JList to subpanel 1
		bOperationPanel.add(polynomeList1);
		//create and add the JList containing the possible operations to select from
		//i.e. those ones accepting 2 operands
		this.bOperationList = new JList(this.operations2);
		bOperationPanel.add(bOperationList);
		//add the next JList with the operands to the this panel 
		bOperationPanel.add(polynomeList2);
		//finally add a button with an attached action listener to the current 
		//operation panel
		confirmButton4 = new JButton("Execute");
		confirmButton4.addActionListener(this);
		bOperationPanel.add(confirmButton4);
        //create the second such operation Panel, the one for supporting unary operations
		this.uOperationPanel = new JPanel();
		//create and add to it the 2 JList objects containing the possible operations
		//from this category and the operand candidates to select from
		this.uOperationList = new JList(operations1);
		this.uOperationPanel.add(uOperationList);
		this.uOperationPanel.add(polynomeList3);
		this.operationPanel.add(bOperationPanel);
		this.operationPanel.add(uOperationPanel);
		//finally, create and add an execution button together with an attached action 
		//listener to execute the required operation
		confirmButton5 = new JButton("Execute");
		confirmButton5.addActionListener(this);
		uOperationPanel.add(confirmButton5);
		//but add a text field and a label to the uOperationsPanel as well, in order to 
		//allow the execution of the scalar multiplication and evaluation for a value operations
		evaluationField = new JTextField(2);
		this.uOperationPanel.add(evaluationField);
		value = new JLabel("Value to multiply with/evaluate at");
		this.uOperationPanel.add(value); 
        //add all the previously defined subpanels to the big one which will be added
		//to the current frame object in turn at the end
		this.polynomePanel.add(resultPanel);
		this.polynomePanel.add(degreePanel);
		this.polynomePanel.add(inputPanel1);
		this.polynomePanel.add(inputPanel2);
		this.polynomePanel.add(operationPanel);
		this.add(polynomePanel);
        //perform the final adjustments on the current frame object:
		//set default close operation, make it resizable, set its title, background,
		//and bounds and last, but not least, make it visible
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setTitle("Polynome Processing Application");
		this.setBackground(Color.CYAN);
		this.setBounds(50, 50, 500, 300);
		this.setVisible(true);
	}

    /**
     * The method actionPerformed(), taken from the ActionListener interface 
     * implemented by the current class, has the responsibility of ensuring a 
     * permanent interaction between the user and the application.Its secondary task
     * is to provide the functionality of the current GUI component at each mouse click
     * on 1 of the 5 buttons.
     * @param e - the ActionEvent object which can occur on one of the buttons after
     *            performing a click on it 
     */
	public void actionPerformed(ActionEvent e) {
		//here we will analyze the source of the event occurred at a mouse click and 
		//take the corresponding action
		if (e.getSource() == confirmButton1){
			//for pressing the first button, we will show a message dialog saying 
			//that the degrees have been entered, and we will try to convert 
			//the input values to numbers, throwing an exception for any faulty input
			//and returning from the program, that is not proceeding with the execution
			JOptionPane.showMessageDialog(this, "Degrees entered");

			try {
				Integer.parseInt(degreeField1.getText());
				Integer.parseInt(degreeField2.getText());
			} 
			catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(this,"Wrong value for the degree");
				return;
			}
            //for successful conversion, we create 2 new Polynomial objects with the given
			//degrees and reserve the necessary number of text fields and labels for the 
			//introduction of coefficients for both of them 
			p = new Polynomial(Integer.parseInt(degreeField1.getText()));
			q = new Polynomial(Integer.parseInt(degreeField2.getText()));

			coefficientFields1 = new JTextField[p.getDegree()+1];
			coefficientLabel1 = new JLabel[p.getDegree()+1];
			for (int i=0; i<p.getDegree()+1; i++){
				coefficientFields1[i] = new JTextField(2);
				coefficientLabel1[i] = new JLabel("X^"+i);
				this.inputPanel1.add(coefficientFields1[i]);
				this.inputPanel1.add(coefficientLabel1[i]);
			} 
			
			coefficientFields2 = new JTextField[q.getDegree()+1];
			coefficientLabel2 = new JLabel[q.getDegree()+1];
			for (int i=0; i<q.getDegree()+1; i++){
				coefficientFields2[i] = new JTextField(2);
				coefficientLabel2[i] = new JLabel("X^"+i);
				this.inputPanel2.add(coefficientFields2[i]);
				this.inputPanel2.add(coefficientLabel2[i]);
			} 

            //finally, we will repaint the screen in order to see the newly attached
			//components on it
			this.repaint();

		} else if (e.getSource() == confirmButton2){
			//we proceed in exactly the same way with the buttons confirmButton2 
			//and confirmButton3 as previously presented 
			for (int i=0; i<p.getDegree()+1; i++){

				try {
					Integer.parseInt(coefficientFields1[i].getText());
				} 
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(this,"Wrong value for the current coefficient");
					return;
				}

				p.setElementAt(i, Integer.parseInt(coefficientFields1[i].getText()));
			}
			//at the end, if everything goes the right way, we will show the first 
			//input Polynomial in of a String and display a message for input validation
			inputLabel.setText("Polynomial 1:\n"+p.toString());
			JOptionPane.showMessageDialog(this, "First Polynomial input");
		} else if (e.getSource() == confirmButton3){
			//we proceed exactly in the same manner with the other Polynomial object
			//as previously presented
			JOptionPane.showMessageDialog(this, "Second Polynomial input");
			for (int i=0; i<q.getDegree()+1; i++){
				try{
					Integer.parseInt(coefficientFields2[i].getText());
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(this,"Wrong value for the current coefficient");
					return;
				}

				q.setElementAt(i, Integer.parseInt(coefficientFields2[i].getText()));
			}
			inputLabel2.setText("Polynomial 2:\n"+q.toString());
		} else if (e.getSource() == confirmButton4){
			Polynomial opresult = new Polynomial();
			Polynomial[] divResult = new Polynomial[2];
			divResult[0] = new Polynomial();
			divResult[1] = new Polynomial();
			Polynomial[] vector = {p,q};
			switch(bOperationList.getSelectedIndex()){
			case 0:
				opresult = PolynomeOperations.addPolynomials(vector[polynomeList1.getSelectedIndex()], vector[polynomeList2.getSelectedIndex()]);
				result.setText("Sum:\n"+opresult.toString());
				break;
			case 1:
				opresult = PolynomeOperations.subtractPolynomial(vector[polynomeList1.getSelectedIndex()], vector[polynomeList2.getSelectedIndex()]);
				result.setText("Difference:\n"+opresult.toString());
				break;
			case 2:
				opresult = PolynomeOperations.multiplyPolynomial(vector[polynomeList1.getSelectedIndex()], vector[polynomeList2.getSelectedIndex()]);
				result.setText("Product:\n"+opresult.toString());
				break;
			case 3:
				divResult = PolynomeOperations.dividePolynomial(vector[polynomeList1.getSelectedIndex()], vector[polynomeList2.getSelectedIndex()]);
				result.setText("Quotient:" + divResult[1].toString() + " " + "Remainder:" + divResult[0].toString());
				break;
			}
		} else {
			Polynomial opresult = new Polynomial();
			int calculationResult = 0;
			Polynomial[] vector = {p,q};
			switch(uOperationList.getSelectedIndex()){
			case 0:
				opresult = PolynomeOperations.computeFormalDerivative(vector[polynomeList3.getSelectedIndex()]);
				if (vector[polynomeList3.getSelectedIndex()].getDegree()==0){
					result.setText("The formal derivative here is 0");
				} else {
				result.setText("1st formal derivative:\n"+opresult.toString());
				}
				break;
			case 1:
				opresult = PolynomeOperations.computeIntegral(vector[polynomeList3.getSelectedIndex()]);
				result.setText("Integral:\n"+opresult.toString());
				break;
			case 2:
				int n = Integer.parseInt(evaluationField.getText());
				calculationResult = vector[polynomeList3.getSelectedIndex()].evaluatePolynomial(n);
				if (calculationResult == 0) {
					result.setText("You have found a root of the current Polynomial object!");
				} else {
					result.setText("The value of the Polynomial in this case is:" + calculationResult);
				}
				break;
			case 3:
				try {
					Integer.parseInt(evaluationField.getText());
				} 
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(this,"Wrong value for the operation/multiplication");
					return;
				}
				int scalar = Integer.parseInt(evaluationField.getText());
				vector[polynomeList3.getSelectedIndex()].scalarMultiply(scalar);
				if (scalar == 0){
					result.setText("The result is the null polynomial");
				} else {
				result.setText("The new polynome:\n"+vector[polynomeList3.getSelectedIndex()].toString());
				}
				break;
			}
		}

	}
}
