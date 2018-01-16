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

public class Longs implements EuclideanDomain<Long>{
    @Override
    public BigInteger euclideanFunction(Long argument) {
        return BigInteger.valueOf(-argument);
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

