package com.geekyarticles.linearalgebra.field;

import java.math.BigInteger;

public class RationalField implements Field<RationalNumber>{
    private static final RationalNumber one = new RationalNumber(new BigInteger("1"), new BigInteger("1"));
    private static final RationalNumber zero = new RationalNumber(new BigInteger("0"), new BigInteger("1"));
    @Override
    public RationalNumber add(RationalNumber lhs, RationalNumber rhs) {
        return lhs.add(rhs);
    }

    @Override
    public RationalNumber multiply(RationalNumber lhs, RationalNumber rhs) {
        return lhs.multiply(rhs);
    }

    @Override
    public RationalNumber one() {
        return one;
    }

    @Override
    public RationalNumber zero() {
        return zero;
    }

    @Override
    public RationalNumber invert(RationalNumber value) {
        return value.invert();
    }

    @Override
    public RationalNumber negate(RationalNumber value) {
        return value.negate();
    }
}
