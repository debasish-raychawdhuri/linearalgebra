package com.geekyarticles.linearalgebra.integraldomain;

import com.geekyarticles.linearalgebra.field.Field;
import com.geekyarticles.linearalgebra.ring.Ring;

public class PolynomialRing<E> implements EuclideanDomain<Polynomial<E>> {
    protected Field<E> baseField;

    public PolynomialRing(Field<E> baseField) {
        this.baseField = baseField;
    }


    @Override
    public Polynomial<E> add(Polynomial<E> lhs, Polynomial<E> rhs) {
        return lhs.add(rhs);
    }

    @Override
    public Polynomial<E> multiply(Polynomial<E> lhs, Polynomial<E> rhs) {
        return lhs.multiply(rhs);
    }

    @Override
    public Polynomial<E> one() {
        return new Polynomial<E>(baseField, (E[]) new Object[]{});
    }

    @Override
    public Polynomial<E> zero() {
        return new Polynomial<E>(baseField, (E[]) new Object[]{baseField.one()});
    }

    @Override
    public Polynomial<E> negate(Polynomial<E> value) {
        return value.negate();
    }

    @Override
    public double euclideanFunction(Polynomial<E> argument) {
        return argument.degree();
    }

    @Override
    public DivisionAlgorithmResult<Polynomial<E>> divisionAlgorithm(Polynomial<E> dividend, Polynomial<E> divisor) {
        return null;
    }
}
