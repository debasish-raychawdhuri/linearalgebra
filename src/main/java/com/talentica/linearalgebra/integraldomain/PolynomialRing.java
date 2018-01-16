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

package com.talentica.linearalgebra.integraldomain;

import com.talentica.linearalgebra.field.Field;

import java.math.BigInteger;

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
        return new Polynomial<E>(baseField, (E[]) new Object[]{baseField.one()});
    }

    @Override
    public Polynomial<E> zero() {
        return new Polynomial<E>(baseField, (E[]) new Object[]{});
    }

    @Override
    public Polynomial<E> negate(Polynomial<E> value) {
        return value.negate();
    }

    @Override
    public BigInteger euclideanFunction(Polynomial<E> argument) {
        return BigInteger.valueOf(argument.degree());
    }

    @Override
    public DivisionAlgorithmResult<Polynomial<E>> divisionAlgorithm(Polynomial<E> dividend, Polynomial<E> divisor) {
        return dividend.divisionAlgorithm(divisor);
    }
}
