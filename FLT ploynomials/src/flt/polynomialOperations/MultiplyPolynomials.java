package flt.polynomialOperations;

import flt.polynomial.Polynomial;

public class MultiplyPolynomials {
	/**
	 * The attribute list of the current class - contains the 2 factors (Polynomials) to be multiplied
	 */
	private Polynomial multiplicand;
	private Polynomial multiplier;

	/**
	 * The parameterized class constructor - uses the Polynomial parameters p and q for building the product in question
	 * @param p - the multiplicand of the product
	 * @param q - the multiplier of the product
	 */
	public MultiplyPolynomials(Polynomial p, Polynomial q){
		this.multiplicand= p;
		this.multiplier = q;
		//invoke the multiplication method
		this.multiplyPolynomials();
	} 

	/**
	 * The method multiplyPolynomials() computes the product of the 2 class attributes
	 * @return the product of the operation in question
	 */
	public Polynomial multiplyPolynomials(){
		// create the result Polynomial object with the degree equal to the sum of the degrees of the parameters
		Polynomial result = new Polynomial(multiplicand.getDegree() + multiplier.getDegree());
		// then we perform the requested operation
		for (int i = 0; i <= multiplicand.getDegree(); i++) {
			//take the coefficient of the multiplicand
			double coefficient1 = multiplicand.getElementAt(i).getCoefficient();
			for (int j = 0; j <= multiplier.getDegree(); j++){
				//then take the coefficients of the multiplier, 1 by 1
				double coefficient2 = multiplier.getElementAt(i).getCoefficient();
				//and of the result from the sum of the current coefficients
				double resCoefficient = result.getElementAt(i+j).getCoefficient();
				//set the value of the new coefficient
				result.getElementAt(i+j).setCoefficient(resCoefficient + coefficient1 * coefficient2);
			}
		}
		// and finally return the result
		return result;
	}

}
