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

public class RationalField implements Field<RationalNumber>{
    private static final RationalNumber one = new RationalNumber(new BigInteger("1"), new BigInteger("1"));
    private static final RationalNumber zero = new RationalNumber(new BigInteger("0"), new BigInteger("1"));
    @Override
    public RationalNumber add(RationalNumber lhs, RationalNumber rhs) {
        return lhs.add(rhs);
    }

    @Override
    public RationalNumber multiply(RationalNumber lhs, RationalNumber rhs) {
        return lhs.multiply(rhs);
    }

    @Override
    public RationalNumber one() {
        return one;
    }

    @Override
    public RationalNumber zero() {
        return zero;
    }

    @Override
    public RationalNumber invert(RationalNumber value) {
        return value.invert();
    }

    @Override
    public RationalNumber negate(RationalNumber value) {
        return value.negate();
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
