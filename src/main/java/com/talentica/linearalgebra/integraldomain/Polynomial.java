/*
 * This file is a part of LinearAlgebra project.
 *
 * Copyright  2018  Talentica Software Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.talentica.linearalgebra.integraldomain;

import com.talentica.linearalgebra.field.Field;
import com.talentica.linearalgebra.field.RationalField;
import com.talentica.linearalgebra.field.RationalNumber;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

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
        if(degree==0){
            return this;
        }
        while(degree>0){
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

    public Polynomial<E> negate(){
        E[] coeffs = (E[]) new Object[coefficients.length];
        for(int i=0;i<coeffs.length;i++){
            coeffs[i] = baseField.negate(coefficients[i]);
        }
        return new Polynomial<>(baseField, coeffs);
    }

    public Polynomial<E> zero(){
        return new Polynomial<E>(baseField, (E[]) new Object[]{});
    }

    public Polynomial<E> one(){
        return new Polynomial<E>(baseField, (E[]) new Object[]{baseField.one()});
    }

    public int degree(){
        return coefficients.length-1;
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

    public DivisionAlgorithmResult<Polynomial<E>> divisionAlgorithm(Polynomial<E> divisor){
        if(coefficients.length<divisor.coefficients.length){
            return new DivisionAlgorithmResult(this.zero(), this);
        }
        E[] qCoeffs = (E[]) new Object[coefficients.length - divisor.coefficients.length+1];
        E[] rCoeffs = (E[]) new Object[coefficients.length];

        for(int i=0;i<rCoeffs.length;i++){
            rCoeffs[i] = this.coefficients[i];
        }
        for(int i=rCoeffs.length-1;i>=divisor.coefficients.length-1;i--){
            E multiplier = baseField.multiply(rCoeffs[i],
                    baseField.invert(divisor.coefficients[divisor.coefficients.length-1]));
            for(int j=i;divisor.coefficients.length-1-i+j>=0;j--){
                rCoeffs[j] = baseField.add(rCoeffs[j],baseField.negate(
                        baseField.multiply(divisor.coefficients[divisor.coefficients.length-1-i+j], multiplier)));
            }
            qCoeffs[i-divisor.coefficients.length+1] = multiplier;
        }

        int rDeg = -1;
        for(int i=rCoeffs.length-1;i>=0;i--){
            if(!rCoeffs[i].equals(baseField.zero())){
                rDeg = i;
                break;
            }
        }
        E[] rCoeffsRev = (E[]) new Object[rDeg+1];
        for(int i=0;i<=rDeg;i++){
            rCoeffsRev[i] = rCoeffs[i];
        }

        return new DivisionAlgorithmResult<>(new Polynomial<>(baseField, qCoeffs), new Polynomial<>(baseField, rCoeffsRev));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial<?> that = (Polynomial<?>) o;
        return Objects.equals(baseField, that.baseField) &&
                Arrays.equals(coefficients, that.coefficients);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(baseField);
        result = 31 * result + Arrays.hashCode(coefficients);
        return result;
    }

    @Override
    public String toString() {
        return  Arrays.toString(coefficients);
    }

    public static void main(String [] args){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("-5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        System.out.println(p1.add(p2));
        System.out.println(p1.multiply(p2));
        System.out.println(p2.divisionAlgorithm(p1));

        System.out.println(p1.divisionAlgorithm(p2));
    }
}
