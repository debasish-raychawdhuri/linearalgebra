package com.talentica.linearalgebra.integraldomain;

import java.math.BigInteger;

public class Integers implements EuclideanDomain<BigInteger>{
    @Override
    public BigInteger euclideanFunction(BigInteger argument) {
        return argument.abs();
    }

    @Override
    public DivisionAlgorithmResult<BigInteger> divisionAlgorithm(BigInteger dividend, BigInteger divisor) {
        return new DivisionAlgorithmResult<>(dividend.divide(divisor), dividend.mod(divisor));
    }

    @Override
    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs);
    }

    @Override
    public BigInteger multiply(BigInteger lhs, BigInteger rhs) {
        return lhs.multiply(rhs);
    }

    @Override
    public BigInteger one() {
        return BigInteger.ONE;
    }

    @Override
    public BigInteger zero() {
        return BigInteger.ZERO;
    }

    @Override
    public BigInteger negate(BigInteger value) {
        return value.negate();
    }
}
