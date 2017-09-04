package com.geekyarticles.linearalgebra.field;

import java.math.BigInteger;
import java.util.Arrays;

public class Polynomial<E> {
    private Field<E> baseField;
    private E[] coefficients;

    public Polynomial(Field<E> baseField, E[] coefficients) {
        this.baseField = baseField;
        this.coefficients = coefficients;
    }

    public Polynomial<E> add(Polynomial<E> rhs){
        int degree = this.coefficients.length;
        if(rhs.coefficients.length>degree){
            degree = rhs.coefficients.length;
        }
        while(degree>=0){
            E firstVal = this.coefficients.length>=degree? this.coefficients[degree-1]:baseField.zero();
            E secondVal = rhs.coefficients.length>=degree? rhs.coefficients[degree-1]:baseField.zero();
            if(baseField.add(firstVal,secondVal).equals(baseField.zero())){
                degree--;
            }else{
                break;
            }
        }
        E[] coefficients = (E[]) new Object[degree];
        for(int i=0;i<degree;i++){
            E firstVal = this.coefficients.length>i? this.coefficients[i]:baseField.zero();
            E secondVal = rhs.coefficients.length>i? rhs.coefficients[i]:baseField.zero();
            coefficients[i] = baseField.add(firstVal, secondVal);
        }

        return new Polynomial<>(baseField, coefficients);
    }

    public Polynomial<E> multiply(Polynomial<E> rhs){
        int degree = coefficients.length-1 + rhs.coefficients.length;
        E[] coeffs = (E[]) new Object[degree];
        for(int i=0;i<degree;i++){
            E val = baseField.zero();
            for(int j=0;j<=i;j++){
                if(j>=coefficients.length ){
                    break;
                }
                if((i-j)>=rhs.coefficients.length){
                    continue;
                }
                E intSum = baseField.multiply(coefficients[j], rhs.coefficients[i-j]);
                val = baseField.add(val, intSum);
            }
            coeffs[i] = val;
        }
        return new Polynomial<>(baseField, coeffs);

    }

    @Override
    public String toString() {
        return  Arrays.toString(coefficients);
    }

    public static void main(String [] args){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("-5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        System.out.println(p1.add(p2));
        System.out.println(p1.multiply(p2));
    }
}
