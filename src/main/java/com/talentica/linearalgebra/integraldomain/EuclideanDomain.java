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

import com.talentica.linearalgebra.ring.Ring;
import com.talentica.linearalgebra.util.Pair;

import java.math.BigInteger;

public interface EuclideanDomain<E> extends Ring<E> {
    BigInteger euclideanFunction(E argument);
    DivisionAlgorithmResult<E> divisionAlgorithm(E dividend, E divisor);

    default ExtendedEuclidResult<E> extendedEulidAlgorithm(E left, E right){
        Pair<E,E> firstPair, secondPair, remainderPair;
        E firstValue, secondValue, remainderValue;
        if(euclideanFunction(left).compareTo(euclideanFunction(right))>0){
            firstPair = new Pair<>(zero(), one());
            secondPair = new Pair<>(one(), zero());
            firstValue = right;
            secondValue = left;
        }else{
            secondPair = new Pair<>(zero(), one());
            firstPair = new Pair<>(one(), zero());
            secondValue = right;
            firstValue = left;
        }
        while(true){
            DivisionAlgorithmResult<E> divisionAlgorithmResult = divisionAlgorithm(secondValue, firstValue);
            remainderValue = divisionAlgorithmResult.getRemainder();
            if(remainderValue.equals(zero())){
                return new ExtendedEuclidResult<>(firstValue, firstPair.first, firstPair.last);
            }
            E remainderFirst = add(secondPair.first,  negate(multiply(firstPair.first,divisionAlgorithmResult.getQuotient())));
            E remainderSecond = add(secondPair.last,  negate(multiply(firstPair.last,divisionAlgorithmResult.getQuotient())));
            remainderPair = new Pair<>(remainderFirst, remainderSecond);

            secondValue = firstValue;
            firstValue = remainderValue;
            secondPair = firstPair;
            firstPair = remainderPair;
        }
    }
}
