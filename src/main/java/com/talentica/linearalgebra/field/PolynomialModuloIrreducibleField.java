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
