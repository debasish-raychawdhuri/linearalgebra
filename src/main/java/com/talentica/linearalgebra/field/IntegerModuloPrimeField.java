package com.talentica.linearalgebra.field;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerModuloPrimeField that = (IntegerModuloPrimeField) o;
        return Objects.equals(primeDivisor, that.primeDivisor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(primeDivisor);
    }
}
