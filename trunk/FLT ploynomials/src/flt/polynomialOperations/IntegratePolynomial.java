package flt.polynomialOperations;

import flt.polynomial.Monomial;
import flt.polynomial.Polynomial;

public class IntegratePolynomial {

	/**
	 * The Polynomial to be integrated is the only attribute of the current class.
	 */
	private Polynomial integInput;

	/**
	 * The class constructor is in charge of creating the input required by the operation in question
	 * @param difIn - the input for the differentiation process
	 */
	public IntegratePolynomial(Polynomial integIn){
		this.integInput = integIn;
		//invoke the integration method
		this.integratePolynomial();		
	}

	/**
	 * The method computeIntegral() is a method written to compute the integral
	 * of the Polynomial object given as a parameter, with the result given back
	 * in a new Polynomial object
	 * @return result - the result of the integration
	 */
	private Polynomial integratePolynomial() {
		//create the result object to be processed further on
		Polynomial result = new Polynomial(integInput.getDegree() + 1);
		//set its last element to 1
		result.setElementAt(0, new Monomial(0,1.0));
		//for the rest of the elements, just compute the division between the current coefficient and the old degree
		for (int i = 1; i <= integInput.getDegree() + 1; i++) {
			//get the coefficient of the old term
			double initialCoefficient = integInput.getElementAt(i-1).getCoefficient();
			//divide it with the current power
			double integCoefficient = initialCoefficient / i;
			//set the current result element as a new monomial component
			result.setElementAt(i, new Monomial(i, integCoefficient));
		}
		return result;
	}
}
