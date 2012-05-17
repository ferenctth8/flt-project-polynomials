package flt.polynomialOperations;

import flt.polynomial.Monomial;
import flt.polynomial.Polynomial;

public class DividePolynomials {

	/**
	 * The attribute list of the current class includes the 4 Polynomials involved in the process of division:
	 * the dividend, the divisor, the quotient and the remainder.
	 */
	private Polynomial dividend;
	private Polynomial divisor;
	private Polynomial quotient;
	private Polynomial remainder;

	/**
	 * The current class constructor sets the divisor and the dividend Polynomial objects.
	 * @param p - the dividend 
	 * @param q - the divisor
	 */
	public DividePolynomials(Polynomial p, Polynomial q){
		this.dividend = p;
		this.divisor = q;
		//call the dividePolynomials() method
		this.dividePolynomials();
	}

	/**
	 * The current method, named dividePolynomials(), executes the division operation between the first 2 attributes.
	 */
	private void dividePolynomials() {
		// step 1:before performing the division itself, we have to test the dividend
		// and the divisor to see how their degrees are and take appropriate action
		if (dividend.getDegree() < divisor.getDegree()) {
			// step 2: if the degree of the dividend is less than that of the divisor
			// the remainder is the dividend, while the quotient is 0
			quotient = new Polynomial(-1);
			remainder = dividend;			
		}
		// step 3:if all the prerequisite conditions are assumed to be satisfied,
		// then we shall begin the performance of the division itself
		// step 3a:set the degrees of the quotient and the remainder
		quotient = new Polynomial(dividend.getDegree() - divisor.getDegree());
		remainder = new Polynomial(divisor.getDegree() - 1);
		// step 3b:perform the necessary arithmetic operations associated to our division
		// among the coefficients of the Polynomial objects in question as well as
		// set the values from the coefficient array of the old Polynomial to 0
		for (int i = quotient.getDegree(), j = dividend.getDegree(); i >= 0; i--, j--) {
			//get the divisor and dividend coefficients
			double dividendCoefficient = dividend.getElementAt(j).getCoefficient();
			double divisorCoefficient = divisor.getElementAt(divisor.getDegree()).getCoefficient();
			//perform the division operation of the coefficients in question
			double divResultCoefficient = dividendCoefficient / divisorCoefficient;
			//set the current quotient element according to the result
			quotient.setElementAt(i, new Monomial(i, divResultCoefficient));
			//adjust the partial result as the new dividend
			for (int k = divisor.getDegree(); k >= 0; k--) {
				//retrieve the coefficients in question
				double partDividendCoefficient1 = dividend.getElementAt(i + k).getCoefficient();
				double partDividendCoefficient2A = quotient.getElementAt(i).getCoefficient(); 
				double partDividendCoefficient2B = divisor.getElementAt(k).getCoefficient();
				//compute the product of the last 2
				double partDividendCoefficient2 = partDividendCoefficient2A * partDividendCoefficient2B;
				//compute the difference of the first coefficient and the previous product
				double newCoefficient = partDividendCoefficient1 - partDividendCoefficient2;
				dividend.setElementAt(i + k,  new Monomial(i + k, newCoefficient));
			}
			dividend.setElementAt(j, new Monomial(j,0));
		}
		// step 3c:assign all the non-zero coefficients from the array of the old
		// dividend Polynomial to the positions in the array of the remainder Polynomial
		for (int i = 0; i <= divisor.getDegree() - 1; i++) {
			remainder.setElementAt(i, dividend.getElementAt(i));
		}
	}
}
