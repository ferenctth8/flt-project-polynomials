package flt.polynomialOperations;

import flt.polynomial.Monomial;
import flt.polynomial.Polynomial;

public class AddPolynomials {

	/**
	 * The attribute list of the current class - contains the 2 terms (Polynomials) to be added
	 */
	private Polynomial termP;
	private Polynomial termQ;
	
	/**
	 * The parameterized class constructor - uses the Polynomial parameters p and q for building the sum in question
	 * @param p - the first term of the sum
	 * @param q - the second term of the sum
	 */
	public AddPolynomials(Polynomial p, Polynomial q){
		this.termP = p;
		this.termQ = q;
	}
	
	public Polynomial addPolynomials(){
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition also establish a similar relation
		// for the nbOfCoefficients of the result
		isLonger = termQ.getDegree() < termP.getDegree();
		if (termQ.getDegree() < termP.getDegree()) {
			maxdeg = termP.getDegree();
			mindeg = termQ.getDegree();
		} else {
			maxdeg = termQ.getDegree();
			mindeg = termP.getDegree();
		}
		Polynomial result = new Polynomial(maxdeg);
		// until finishing with the operand of lower degree, perform the addition of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			//get the coefficients in question
			double coefficient1 = termP.getElementAt(i).getCoefficient();
			double coefficient2 = termQ.getElementAt(i).getCoefficient();
			//compute their sum
			double sum = coefficient1 + coefficient2;
			//set the result at the corresponding position
			result.setElementAt(i, new Monomial(i,sum));
		}
		// copy the rest of the coefficients from the array associated to the operand possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				result.setElementAt(i, termP.getElementAt(i));
			} else {
				result.setElementAt(i, termQ.getElementAt(i));
			}
		}
		return result;
	}
}
