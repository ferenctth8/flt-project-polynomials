package flt.polynomialOperations;

import flt.polynomial.Monomial;
import flt.polynomial.Polynomial;

public class SubtractPolynomials {

	/**
	 * The attribute list of the current class - contains the 2 terms (Polynomials) to be subtracted
	 */
	private Polynomial minuend;
	private Polynomial subtrahend;
	
	/**
	 * The parameterized class constructor - uses the Polynomial parameters p and q for building the difference in question
	 * @param p - the minuend of the difference
	 * @param q - the subtrahend of the difference
	 */
	public SubtractPolynomials(Polynomial p, Polynomial q){
		this.minuend = p;
		this.subtrahend = q;
	}
	
	/**
	 * The method subtractPolynomials() is responsible for the subtraction of the 2 attributes of the current class
	 * @return the result of the subtraction
	 */
	public Polynomial subtractPolynomials(){
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition also establish a similar relation
		// for the nbOfCoefficients of the result
		isLonger = subtrahend.getDegree() < minuend.getDegree();
		if (subtrahend.getDegree() < minuend.getDegree()) {
			maxdeg = minuend.getDegree();
			mindeg = subtrahend.getDegree();
		} else {
			maxdeg = subtrahend.getDegree();
			mindeg = minuend.getDegree();
		}
		Polynomial result = new Polynomial(maxdeg);
		// until finishing with the operand of lower degree, perform the addition of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			//get the coefficients in question
			double coefficient1 = minuend.getElementAt(i).getCoefficient();
			double coefficient2 = subtrahend.getElementAt(i).getCoefficient();
			//compute their sum
			double difference = coefficient1 - coefficient2;
			//set the result at the corresponding position
			result.setElementAt(i, new Monomial(i,difference));
		}
		// copy the rest of the coefficients from the array associated to the operand possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				result.setElementAt(i, minuend.getElementAt(i));
			} else {
				result.setElementAt(i, subtrahend.getElementAt(i));
			}
		}
		return result;
	}
}
