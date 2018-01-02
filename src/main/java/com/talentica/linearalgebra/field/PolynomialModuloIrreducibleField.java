package com.talentica.linearalgebra.field;

import com.talentica.linearalgebra.integraldomain.ExtendedEuclidResult;
import com.talentica.linearalgebra.integraldomain.Polynomial;
import com.talentica.linearalgebra.integraldomain.PolynomialRing;

import java.util.Objects;

public class PolynomialModuloIrreducibleField<E> extends PolynomialRing<E> implements Field<Polynomial<E>> {
    private Polynomial<E> irreducibleBase;
    private Field<E> baseField;

    public PolynomialModuloIrreducibleField(Polynomial<E> irreducibleBase, Field<E> baseField) {
        super(baseField);
        this.irreducibleBase = irreducibleBase;
    }

    @Override
    public Polynomial<E> invert(Polynomial<E> value) {
        ExtendedEuclidResult<Polynomial<E>> eer = extendedEulidAlgorithm(value, irreducibleBase);
        return eer.getLeftMultiplier().divisionAlgorithm(irreducibleBase).getRemainder();
    }

    @Override
    public Polynomial<E> add(Polynomial<E> lhs, Polynomial<E> rhs) {
        return lhs.add(rhs).divisionAlgorithm(irreducibleBase).getRemainder();
    }

    @Override
    public Polynomial<E> multiply(Polynomial<E> lhs, Polynomial<E> rhs) {
        return lhs.multiply(rhs).divisionAlgorithm(irreducibleBase).getRemainder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolynomialModuloIrreducibleField<?> that = (PolynomialModuloIrreducibleField<?>) o;
        return Objects.equals(irreducibleBase, that.irreducibleBase) &&
                Objects.equals(baseField, that.baseField);
    }

    @Override
    public int hashCode() {

        return Objects.hash(irreducibleBase, baseField);
    }
}
