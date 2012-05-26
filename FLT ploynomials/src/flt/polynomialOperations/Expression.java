package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public abstract class Expression {
   
	/**
	 * The attribute list of the current class contains the Polynomial operands to 
	 * be used further on in the operations section.
	 */
	protected Polynomial operand1;
	protected Polynomial operand2;
	protected Polynomial result;
	
	/**
	 * The following method represents a getter for the first operand.
	 * @return operand1
	 */
	public Polynomial getOperand1() {
		return operand1;
	}
	
	/**
	 * The next method is a getter for the second operand
	 * @return operand2
	 */
	public Polynomial getOperand2() {
		return operand2;
	}
	
	/**
	 * The last method is a getter for the result 
	 * @return the operation result
	 */
	public Polynomial getResult() {
		return result;
	}
}
