package com.geekyarticles.linearalgebra.integraldomain;

public class DivisionAlgorithmResult<E> {
    private E quotient;
    private E remainder;

    public DivisionAlgorithmResult(E quotient, E remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public E getQuotient() {
        return quotient;
    }

    public E getRemainder() {
        return remainder;
    }

    @Override
    public String toString() {
        return "DivisionAlgorithmResult{" +
                "quotient=" + quotient +
                ", remainder=" + remainder +
                '}';
    }
}
