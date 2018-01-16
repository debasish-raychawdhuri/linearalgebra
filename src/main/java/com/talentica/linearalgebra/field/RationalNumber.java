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

package com.talentica.linearalgebra.field;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RationalNumber that = (RationalNumber) o;

        if (numerator != null ? !numerator.equals(that.numerator) : that.numerator != null) return false;
        return denominator != null ? denominator.equals(that.denominator) : that.denominator == null;
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        return result;
    }

    public static void main(String [] args){
        RationalNumber rn = new RationalNumber(new BigInteger("24"), new BigInteger("-9"));
        RationalNumber rn2 = new RationalNumber(new BigInteger("24"), new BigInteger("21"));
        System.out.println(rn.add(rn2));
    }
}
