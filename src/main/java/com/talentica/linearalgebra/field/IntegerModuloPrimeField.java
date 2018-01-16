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

import java.math.BigInteger;
import java.util.Objects;

public class IntegerModuloPrimeField implements Field<BigInteger>{
    private BigInteger primeDivisor;

    public IntegerModuloPrimeField(BigInteger primeDivisor) {
        if(!primeDivisor.isProbablePrime(100)){
            throw new IllegalArgumentException("Divisor not a prime");
        }
        this.primeDivisor = primeDivisor;
    }

    @Override
    public BigInteger add(BigInteger lhs, BigInteger rhs) {
        return lhs.add(rhs).mod(primeDivisor);
    }

    @Override
    public BigInteger multiply(BigInteger lhs, BigInteger rhs) {
        return lhs.multiply(rhs).mod(primeDivisor);
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
    public BigInteger invert(BigInteger value) {
        return null;
    }

    @Override
    public BigInteger negate(BigInteger value) {
        return value.modInverse(primeDivisor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerModuloPrimeField that = (IntegerModuloPrimeField) o;
        return Objects.equals(primeDivisor, that.primeDivisor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(primeDivisor);
    }
}
