package com.talentica.linearalgebra.integraldomain;

public class Integers implements EuclideanDomain<Long>{
    @Override
    public double euclideanFunction(Long argument) {
        return Math.abs(argument);
    }

    @Override
    public DivisionAlgorithmResult<Long> divisionAlgorithm(Long dividend, Long divisor) {
        return new DivisionAlgorithmResult<>(dividend/divisor, dividend % divisor);
    }

    @Override
    public Long add(Long lhs, Long rhs) {
        return lhs+rhs;
    }

    @Override
    public Long multiply(Long lhs, Long rhs) {
        return lhs*rhs;
    }

    @Override
    public Long one() {
        return 1l;
    }

    @Override
    public Long zero() {
        return 0l;
    }

    @Override
    public Long negate(Long value) {
        return -value;
    }
}
