package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public class ArithmethicExpression extends Expression {
	
   
   /**
    * The following class is one of the extensions of the abstract Expression class.
    * It is, in other words, responsible for the implementation of the arithmetic 
    * expressions on polynomials. The only attribute of the current class is the list
    * of constants which nominate the type of operation being done.
    */
	public static final int ADDITION = 1;
 	public static final int SUBTRACTION = 2; 
 	public static final int MULTIPLICATION = 3;
 	public static final int DIVISION = 4;
 	public static final int REMAINDER = 5;
		
	/**
	 * The class constructor builds a new expression using the given terms 
	 * and builds also a result out of them
	 * @param t1 - the first term
	 * @param t2 - the second term
	 */
	public ArithmethicExpression(Polynomial t1, Polynomial t2, int index) {
		super();
		this.operand1 = t1;
		this.operand2 = t2;
		this.computeResult(index);
	}

	/**
	 * The current method computes the result of the desired arithmetic operation 
	 * in question
	 * @param index - the index of the desired operation
	 * @return the result of the operation in question
	 */
	public Polynomial computeResult(int index) {
	    switch(index){
	    case ADDITION:	    	
	    	result = PolynomialOperations.add(operand1, operand2);
	    break;
	    case SUBTRACTION:
	    	result = PolynomialOperations.subtract(operand1, operand2);
	    	break;
	    case MULTIPLICATION:
	    	result = PolynomialOperations.multiply(operand1, operand2);
	    	break;
	    case DIVISION:
	    	result = PolynomialOperations.computeQuotient(operand1, operand2);
	    	break;
	    case REMAINDER:
	    	result = PolynomialOperations.computeRemainder(operand1, operand2); 
	    	break;
	    }
		return result;			
	}
	
	
}
