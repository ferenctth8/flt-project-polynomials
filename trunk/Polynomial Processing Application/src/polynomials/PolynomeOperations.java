package src.polynomials;

public class PolynomeOperations {

	/**
	 * This class has as main responsibility the performance of operations on
	 * the elements of the Polynomial class previously defined. All the methods
	 * invoked here are static ones; thus because of this and the fact that we
	 * do not instantiate any objects from this class, we have not provided
	 * neither instance variables nor explicit constructors for our class.
	 */

	/**
	 * The method addPolynomial() has the responsibility of performing the
	 * addition between the 2 Polynomial objects given as parameters.The result
	 * of this operation is to be stored in the result object returned by the
	 * current method after its execution.
	 * 
	 * @param p
	 *            - the Polynomial representing the first term
	 * @param q
	 *            - the Polynomial representing the second term
	 * @return result - the Polynomial holding the result of the operation in
	 *         question
	 */
	public static Polynomial addPolynomials(Polynomial p, Polynomial q) {
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition also establish the a similar relation
		// for the nbOfCoefficients of the result
		isLonger = q.getDegree() < p.getDegree();
		if (q.getDegree() < p.getDegree()) {
			maxdeg = p.getDegree();
			mindeg = q.getDegree();
		} else {
			maxdeg = q.getDegree();
			mindeg = p.getDegree();
		}
		Polynomial result = new Polynomial(maxdeg);
		// until finishing with the operand of lower degree, perform the addition of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			result.setElementAt(i, p.getElementAt(i) + q.getElementAt(i));
		}
		// copy the rest of the coefficients from the array associated to the operand possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				result.setElementAt(i, p.getElementAt(i));
			} else {
				result.setElementAt(i, q.getElementAt(i));
			}
		}
		return result;
	}

	/**
	 * The method subtractPolynomial() has the responsibility of performing the
	 * subtraction between the 2 Polynomial objects given as parameters.The
	 * result of this operation is to be stored in the result object returned by
	 * the current method after its execution.
	 * 
	 * @param p
	 *            - the Polynomial to subtract from
	 * @param q
	 *            - the Polynomial to be subtracted
	 * @return result - the Polynomial holding the result of the operation in
	 *         question
	 */
	public static Polynomial subtractPolynomial(Polynomial p, Polynomial q) {
		int mindeg = 0;
		int maxdeg = 0;
		boolean isLonger = false;

		// compare the 2 degrees to see which one will be assigned to the result
		// of the operation; in addition use this relation for establishing the
		// limit where to perform the operation - with variable isLonger of type
		// boolean
		isLonger = q.getDegree() < p.getDegree();

		if (q.getDegree() < p.getDegree()) {
			maxdeg = p.getDegree();
			mindeg = q.getDegree();
		} else {
			maxdeg = q.getDegree();
			mindeg = p.getDegree();
		}

		Polynomial result = new Polynomial(maxdeg);

		// until finishing with the operand of lower degree, perform the
		// subtraction
		// of the coefficients normally
		for (int i = 0; i <= mindeg; i++) {
			result.setElementAt(i, p.getElementAt(i) - q.getElementAt(i));
		}

		// copy the rest of the coefficients from the array associated to the
		// operand
		// possessing a higher degree
		for (int i = mindeg + 1; i <= maxdeg; i++) {
			if (isLonger) {
				result.setElementAt(i, p.getElementAt(i));
			} else {
				result.setElementAt(i, q.getElementAt(i));
			}
		}

		// return the result of the computation
		return result;
	}

	/**
	 * The method multiplyPolynomial(), as its name would suggest, has the task
	 * of computing the product between the 2 Polynomial objects given as
	 * parameters
	 * 
	 * @param p
	 *            - the first Polynomial processed by the current method
	 * @param q
	 *            - the second Polynomial processed by the current method
	 * @return result - the Polynomial representing the sum of the parameters
	 *         given as input
	 */
	public static Polynomial multiplyPolynomial(Polynomial p, Polynomial q) {
		// create the result Polynomial object with the degree equal to the sum
		// of the degrees of the parameters
		Polynomial result = new Polynomial(q.getDegree() + p.getDegree());

		// then we perform the requested operation
		for (int i = 0; i <= q.getDegree(); i++) {
			for (int j = 0; j <= p.getDegree(); j++)
				result.setElementAt(i + j, result.getElementAt(i + j) + p.getElementAt(j)* q.getElementAt(i));
		}
		// and finally return the result
		return result;
	}

	/**
	 * The method dividePolynomial() is responsible for performing the division
	 * between the Polynomial objects p and q given by the parameters.It returns
	 * as as results the quotient and the remainder of the operation.
	 * 
	 * @param p
	 *            - the Polynomial object used as a dividend
	 * @param q
	 *            - the Polynomial object used as a divisor
	 * @return result - the array of objects from this class containing the
	 *         quotient and the remainder of the division
	 */
	public static Polynomial[] dividePolynomial(Polynomial p, Polynomial q) {
		// step 1:reserve memory for the result vector
		Polynomial[] result = new Polynomial[2];

		// step 2:before performing the division itself, we have to test the
		// dividend
		// and the divisor to see how their degrees are and take appropriate
		// action
		if (p.getDegree() < q.getDegree()) {
			// step 3: if the degree of the dividend is less than that of the
			// divisor
			// the remainder is the dividend, while the quotient is 0
			result[1] = new Polynomial(-1);
			result[0] = p;
			return result;
		}
		// step 4:if all the prerequisite conditions are assumed to be
		// satisfied,
		// then we shall begin the performance of the division itself
		// step 4a:set the degrees of the quotient and the remainder
		Polynomial quotient = new Polynomial(p.getDegree() - q.getDegree());
		Polynomial remainder = new Polynomial(q.getDegree() - 1);
		// step 4b:perform the necessary arithmetic operations associated to our
		// division
		// among the coefficients of the Polynomial objects in question as well
		// as
		// set the values from the coefficient array of the old Polynomial to 0

		for (int i = quotient.getDegree(), j = p.getDegree(); i >= 0; i--, j--) {
			quotient.setElementAt(i,
					p.getElementAt(j) / q.getElementAt(q.getDegree()));
			for (int k = q.getDegree(); k >= 0; k--) {
				p.setElementAt(
						i + k,
						p.getElementAt(i + k) - quotient.getElementAt(i)
								* q.getElementAt(k));
			}
			p.setElementAt(j, 0);
		}
		// step 4c:assign all the non-zero coefficients from the array of the
		// old
		// Polynomial to the positions in the array of the remainder Polynomial
		for (int i = 0; i <= q.getDegree() - 1; i++) {
			remainder.setElementAt(i, p.getElementAt(i));
		}

		result[0] = remainder;
		result[1] = quotient;
		return result;

	}

	/**
	 * The method computeFormalDerivative() is a method designed for computing
	 * the first formal derivative of a given Polynomial object.
	 * 
	 * @param p
	 *            - the Polynomial given as input to the operation
	 * @return result - the Polynomial object being the result of the
	 *         differentiation
	 */
	public static Polynomial computeFormalDerivative(Polynomial p) {
		// compute formal derivative of order 1
		Polynomial result = new Polynomial(p.getDegree() - 1);
		for (int i = p.getDegree(); i > 0; i--) {
			result.setElementAt(i - 1, p.getElementAt(i) * i);
		}
		return result;
	}

	/**
	 * The method computeIntegral() is a method written to compute the integral
	 * of the Polynomial object given as a parameter, with the result given back
	 * in a new Polynomial object
	 * 
	 * @param p
	 *            - the input polynomial to be integrated
	 * @return result - the result of the integration
	 */
	public static Polynomial computeIntegral(Polynomial p) {
		Polynomial result = new Polynomial(p.getDegree() + 1);
		result.setElementAt(0, 1);
		for (int i = 1; i <= p.getDegree() + 1; i++) {
			result.setElementAt(i, p.getElementAt(i - 1) / i);
		}
		return result;
	}
}
