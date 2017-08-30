package com.geekyarticles.linearalgebra.field;

import java.math.BigInteger;

public class RationalNumber {
    BigInteger numerator;
    BigInteger denominator;
    public RationalNumber(BigInteger numerator, BigInteger denominator){
        if(denominator.equals(BigInteger.ZERO)){
            throw new ArithmeticException("The denominator must be non-zero");
        }else if(denominator.compareTo(BigInteger.ZERO)<0){
            denominator = denominator.negate();
            numerator = numerator.negate();
        }
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    public String toString(){
        return numerator + "/" +denominator;
    }

    public RationalNumber invert(){
        return new RationalNumber(denominator, numerator);
    }

    public RationalNumber negate(){
        return new RationalNumber(numerator.negate(), denominator);
    }

    public RationalNumber add(RationalNumber rhs){
        BigInteger n = numerator.multiply(rhs.denominator).add(denominator.multiply(rhs.numerator));
        BigInteger d = denominator.multiply(rhs.denominator);
        return new RationalNumber(n,d);
    }

    public RationalNumber multiply(RationalNumber rhs){
        return new RationalNumber(numerator.multiply(rhs.numerator), denominator.multiply(rhs.denominator));
    }

    public static void main(String [] args){
        RationalNumber rn = new RationalNumber(new BigInteger("24"), new BigInteger("-9"));
        RationalNumber rn2 = new RationalNumber(new BigInteger("24"), new BigInteger("21"));
        System.out.println(rn.add(rn2));
    }
}
