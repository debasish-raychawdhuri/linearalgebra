package com.talentica.linearalgebra.integraldomain;

import com.talentica.linearalgebra.ring.Ring;
import com.talentica.linearalgebra.util.Pair;

public interface EuclideanDomain<E> extends Ring<E> {
    double euclideanFunction(E argument);
    DivisionAlgorithmResult<E> divisionAlgorithm(E dividend, E divisor);
    default ExtendedEuclidResult<E> extendedEulidAlgorithm(E left, E right){
        Pair<E,E> firstPair, secondPair, remainderPair;
        E firstValue, secondValue, remainderValue;
        if(euclideanFunction(left)> euclideanFunction(right)){
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
            DivisionAlgorithmResult<E> divisionAlgorithmResult = divisionAlgorithm(firstValue, secondValue);
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
