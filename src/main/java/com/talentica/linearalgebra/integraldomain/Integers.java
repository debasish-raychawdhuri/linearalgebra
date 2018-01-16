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

import java.math.BigInteger;

public class Integers implements EuclideanDomain<BigInteger>{
    @Override
    public BigInteger euclideanFunction(BigInteger argument) {
        return argument.abs();
    }

    @Override
    public DivisionAlgorithmResult<BigInteger> divisionAlgorithm(BigInteger dividend, BigInteger divisor) {
        return new DivisionAlgorithmResult<>(dividend.divide(divisor), dividend.mod(divisor));
    }

    @Override
    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs);
    }

    @Override
    public BigInteger multiply(BigInteger lhs, BigInteger rhs) {
        return lhs.multiply(rhs);
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
    public BigInteger negate(BigInteger value) {
        return value.negate();
    }
}
