package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public class DifferentialExpression extends Expression{

	/**
	 * The class constructor will generate the differential operation for our 
	 * input Polynomial
	 * @param operand - the Polynomial to be differentiated
	 */
	public DifferentialExpression(Polynomial operand) {
		super();
		this.operand1 = operand;
		this.computeDifferential();
	}

	/**
	 * The method computeDifferential() will compute the result of the Polynomial 
	 * differentiation operation
	 * @return the result of differentiation
	 */
	public Polynomial computeDifferential() {
		result = PolynomialOperations.differentiate(operand1);
		return result;				
	}

	
}
