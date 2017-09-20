package com.geekyarticles.linearalgebra.integraldomain;

public class ExtendedEuclidResult<E> {
    private E gcd;
    private E leftMultiplier;
    private E rightMultiplier;

    public ExtendedEuclidResult(E gcd, E leftMultiplier, E rightMultiplier) {
        this.gcd = gcd;
        this.leftMultiplier = leftMultiplier;
        this.rightMultiplier = rightMultiplier;
    }

    public E getGcd() {
        return gcd;
    }

    public E getLeftMultiplier() {
        return leftMultiplier;
    }

    public E getRightMultiplier() {
        return rightMultiplier;
    }
}
