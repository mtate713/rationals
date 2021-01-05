package com.cooksys.ftd.assignments.objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SimplifiedRational implements IRational {
	private int num;
	private int dem;
    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int a, int b) throws IllegalArgumentException {
        if ((a <= 0) || (b < 0))
        	throw new IllegalArgumentException();
        else if (b == 0)
        	return a;
        else {
        	int greater, lessor;
        	if (a > b) {
        		greater = a;
        		lessor = b;
        	}
        	else {
        		greater = b;
        		lessor = a;
        	}
        		
        	int remainder = greater % lessor;
        	int prev = lessor;
        	int temp = remainder;
        	
        	while (remainder != 0) {
        		remainder = prev % temp;
        		prev = temp;
        		temp = remainder;
        		
        	}
        	
        	return prev;
   
        }
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0)
    		throw new IllegalArgumentException();
    	else {
    		
    		if (numerator == 0) {
    			int temp = denominator;
    			while ((temp % 10 == 0)) {
    				temp /= 10;
    			}
    			
    			return new int[] {numerator, temp};
    		}
    		
    		int divider = gcd(Math.abs(numerator), Math.abs(denominator));
    		return new int[] {(numerator/divider), (denominator/divider)};
    	}
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0)
    		throw new IllegalArgumentException();
    	else {
    		int[] values = simplify(numerator, denominator);
    		this.num = values[0];
    		this.dem = values[1];
    	}
    	
    }

    /**
     * @return the numerator of this rational number
     */
 
    public int getNumerator() {
        return this.num;
    }

    /**
     * @return the denominator of this rational number
     */
   
    public int getDenominator() {
        return this.dem;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
    	if (denominator == 0)
    		throw new IllegalArgumentException();
    	else 
    		return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
 
    public boolean equals(Object obj) {
    	if ((obj == null) || (obj.getClass() != this.getClass()))
    		return false;
    	else if (obj == this)
    		return true;
    	
    	SimplifiedRational temp = (SimplifiedRational) obj;
    	if ((temp.getNumerator() == this.getNumerator()) && (temp.getDenominator() == this.getDenominator()))
    			return true;
    	else
    		return false;
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */

    public String toString() {
    	if (((this.getNumerator() < 0) && (this.getDenominator() > 0)) || ((this.getNumerator() > 0) && (this.getDenominator() < 0)))
    		return "-" + Integer.toString(Math.abs(this.getNumerator())) + "/" + Integer.toString(Math.abs(this.getDenominator()));
    	else 
    		return Integer.toString(Math.abs(this.getNumerator())) + "/" + Integer.toString(Math.abs(this.getDenominator()));
    }
}
