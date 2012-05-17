package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public class DifferentiatePolynomial {

	/**
	 * The Polynomial to be differentiated is the only attribute of the current class.
	 */
	private Polynomial difInput;

	/**
	 * The class constructor is in charge of creating the input required by the operation in question
	 * @param difIn - the input for the differentiation process
	 */
	public DifferentiatePolynomial(Polynomial difIn){
		this.difInput = difIn;
		//invoke the differentiation method
		this.differentiatePolynomial();
	}

	/**
	 * The method differentiatePolynomial() is a method designed for computing the first formal derivative of a given Polynomial object.
	 * @return result - the Polynomial object being the result of the differentiation
	 */	
	private Polynomial differentiatePolynomial() {
		//create the object which is to be returned as a process result
		Polynomial differentiationResult = new Polynomial(difInput.getDegree() - 1);
		for (int i = difInput.getDegree(); i > 0; i--) {
			//get the current monome component of the input polynomial
			double currentCoefficient = difInput.getElementAt(i).getCoefficient();
			//compute formal derivative of order 1 by simply multiplying the current coefficient with the old power
			differentiationResult.getElementAt(i - 1).setCoefficient(currentCoefficient*i);
			//result.setElementAt(i - 1, new Monomial(i - 1, currentCoefficient*i));
		}
		return differentiationResult;				
	}

}
