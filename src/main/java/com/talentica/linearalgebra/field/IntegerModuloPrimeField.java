package com.talentica.linearalgebra.field;

import java.math.BigInteger;

public class IntegerModuloPrimeField implements Field<BigInteger>{
    private BigInteger primeDivisor;

    public IntegerModuloPrimeField(BigInteger primeDivisor) {
        if(!primeDivisor.isProbablePrime(100)){
            throw new IllegalArgumentException("Divisor not a prime");
        }
        this.primeDivisor = primeDivisor;
    }

    @Override
    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs).mod(primeDivisor);
    }

    @Override
    public BigInteger multiply(BigInteger lhs, BigInteger rhs) {
        return lhs.multiply(rhs).mod(primeDivisor);
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
    public BigInteger invert(BigInteger value) {
        return null;
    }

    @Override
    public BigInteger negate(BigInteger value) {
        return value.modInverse(primeDivisor);
    }

}
