package flt.polynomial;

public class Polynomial {

	/**
	 * The attribute list of the current Polynomial class: the degree of the current polynomial - 
	 * the maximum one out of the component monomials - and the list of monomials. 
	 */
	private int polynomialDegree;
	private Monomial[] monomeComponentList;

	/**
	 * The default parameterless class constructor, Polynomial(), is responsible for 
	 * setting up a new object, initially empty.It is only specified here, because 
	 * of not being provided by Java due to the usage of its previous, parameterized 
	 * version.
	 */
	public Polynomial(){

	}

	/**
	 * The parameterized constructor used for the building of a Polynomial in case of 
	 * obtaining it by an operation - addition, division, subtraction or multiplication 
	 * @param deg - the degree of the new Polynomial
	 */
	public Polynomial(int deg){
		//set the degree first
		this.polynomialDegree = deg;
		//set the size of the monomial vector
		this.monomeComponentList = new Monomial[deg+1];
	}	

	/**
	 * The constructor Polynomial() has the task of building a given object of this
	 * class with the degree given as an integer parameter to it, as well as set the list
	 * of monomes which make up the current Polynomial object
	 * @param degree - the integer to be used as a degree indicator of the current 
	 *              Polynomial object
	 * @param monomes - the list of monomes which make up the current polynomial
	 */
	public Polynomial(int degree, Monomial[] monomes){
		//set the degree first
		this.polynomialDegree = degree;
		//set the size of the monomial vector
		this.monomeComponentList = new Monomial[degree+1];
		//create and initialize the components of the vector in question
		for (int i=0; i<polynomialDegree+1; i++){
			monomeComponentList[i] = monomes[i];
		}
	}

	/**
	 * The class constructor used for initializing the new Polynomial object obtained with the 
	 * help of a scalar multiplication
	 * @param scalar - the scalar the Polynomial is multiplied with
	 * @param p - the initial Polynomial
	 */
	public Polynomial(int scalar, Polynomial p){
		//check if we have a non-zero scalar and act according to the result 
		if (scalar!=0){
			//set the degree of the result to be the degree of the polynomial Parameter
			this.polynomialDegree = p.getDegree();
			//set the size of the monome component vectors to that of the Polynomial
			this.monomeComponentList = new Monomial[polynomialDegree+1];
			//create and initialize the monome components in question
			for (int i=0; i<polynomialDegree+1; i++){
				//get the monome coefficient in question
				double oldCoefficient = p.getElementAt(i).getCoefficient();
				//set the new coefficient of p to be the scalar * the old one
				p.getElementAt(i).setCoefficient(scalar * oldCoefficient);
				monomeComponentList[i] = p.getElementAt(i);
			}
		} else {
			//in case of scalar being 0, just put 1 element in the vector
			this.polynomialDegree = 0;
			//set the vector size to 1
			this.monomeComponentList = new Monomial[1];
			//set the only element of the vector to 0
			this.monomeComponentList[0] = new Monomial(0,0.0);
		} 		
	}	

	/**
	 * The method toString() is inherited from class Object, here it is overridden 
	 * in order to output the Polynomial object created by the class constructor 
	 * from the procession of the input file
	 * @return s - the String object containing the required Polynomial
	 */
	public String toString() {
		//define the returned String object containing the required Polynomial 
		//initialize it with the free term of our Polynomial object - i.e. the 
		//coefficient of X^0
		String s =""; 
		if (polynomialDegree > 0){
			s = this.monomeComponentList[0].toString() + " + ";
			//attach successively the remaining terms of the Polynomial to the String
			for (int i = 1; i < this.polynomialDegree; i++){
				s += this.monomeComponentList[i].toString() + " + ";
			}
			//attach the last term of the Polynomial to the String
			s += this.monomeComponentList[polynomialDegree].toString();
		} else {
			s = ""+this.monomeComponentList[0].toString();
		}
		return s;
	}

	/**
	 * The method getMonomeComponentList() returns the monomeComponentList 
	 * array containing the monome components of a given Polynomial object     
	 * @return monomeComponentList - the vector containing the monome components 
	 *                               of a given polynomial
	 */
	public Monomial[] getMonomials(){
		return this.monomeComponentList;
	}

	/**
	 * The method setMonomeComponentList() has the task of setting the array 
	 * containing the monome components of a given Polynomial object 
	 * @param monomeComponentList - the vector containing the monome components 
	 *                              of a given polynomial
	 */
	public void setMonomials(Monomial[] monomials){
		for (int i=0; i<=this.polynomialDegree; i++){
			this.monomeComponentList[i] = monomials[i];
		}	
	}

	/**
	 * The method getDegree() is defined as a mutator method for accessing the 
	 * degree of the current Polynomial object
	 * @return degree - an integer value representing the degree of the current
	 *                  Polynomial object
	 */
	public int getDegree(){
		return this.polynomialDegree;
	}

	/**
	 * The method setDegree() has the responsibility of setting the degree of the 
	 * the current Polynomial object
	 * @param deg - the integer value representing the current Polynomial degree 
	 *              which is to be checked for correctness
	 */
	public void setDegree(int deg){
		//define a local variable of type int, called testDeg to verify if the degree
		//given as a parameter is valid or not
		int testDeg = deg;
		//define also a counter for traversing the array of monomial components in case 
		//it is necessary
		int i=0;
		//check if the degree is a non-negative number 
		if (deg>=0) {
			//check if the degree truly represents the current Polynomial degree
			if (this.monomeComponentList[0].getCoefficient() > 0.0){
				this.polynomialDegree = testDeg;
			} else {
				//in case the first element of the coefficientVector is 0,decrease
				//testDeg - proceed similarly until the first non-zero element is 
				//encountered
				do {
					testDeg--;
					i++;
				} while(this.monomeComponentList[i].getCoefficient()==0.0);
				this.polynomialDegree = testDeg;
			}
		} else {
			//specify the wrong choice and the cause of it
			System.out.println("Wrong choice for degree assignment!");
		}
	}

	/**
	 * The method getElementAt() has the task of accessing the element with the index
	 * given as a parameter from the array of monomial components
	 * @param i - an integer value given the element index
	 * @return - the Monomial element of the coefficient vector situated at the position given
	 *           as a parameter to the method
	 */
	public Monomial getElementAt(int i){
		return monomeComponentList[i];
	}

	/**
	 * The method setElementAt() has the responsibility of setting the element from
	 * a given index inside the coefficient array of the Polynomial object
	 * @param i - an integer value given the element index
	 * @param value - the double element to be put inside the array at the specified index
	 */
	public void setElementAt(int i, Monomial monomial){
		monomeComponentList[i] = monomial;
	}

}
