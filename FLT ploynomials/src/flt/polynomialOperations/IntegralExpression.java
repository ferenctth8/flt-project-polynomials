package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public class IntegralExpression extends Expression{

	/**
	 * The class constructor will generate the integral operation for our 
	 * input Polynomial
	 * @param operand - the Polynomial to be integrated
	 */
	public IntegralExpression(Polynomial operand) {
		super();
		this.operand1 = operand;
		this.computeIntegral();
	}

	/**
	 * The method computeIntegral() will compute the result of the Polynomial 
	 * integration operation
	 * @return the result of integration
	 */
	public Polynomial computeIntegral() {
		result = PolynomialOperations.integrate(operand1);
		return result;				
	}
}
