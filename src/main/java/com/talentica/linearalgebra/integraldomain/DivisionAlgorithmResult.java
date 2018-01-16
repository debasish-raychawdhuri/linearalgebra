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
