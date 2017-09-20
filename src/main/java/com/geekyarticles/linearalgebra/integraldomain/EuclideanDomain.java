package com.geekyarticles.linearalgebra.integraldomain;

import com.geekyarticles.linearalgebra.field.Field;
import com.geekyarticles.linearalgebra.ring.Ring;
import com.geekyarticles.linearalgebra.util.Tuple;

public interface EuclideanDomain<E> extends Ring<E> {
    double euclideanFunction(E argument);
    DivisionAlgorithmResult<E> divisionAlgorithm(E dividend, E divisor);
    default ExtendedEuclidResult<E> extendedEulidAlgorithm(E left, E right){
        Tuple<E,E> firstTuple, secondTuple, remainderTuple;
        E firstValue, secondValue, remainderValue;
        if(euclideanFunction(left)> euclideanFunction(right)){
            firstTuple = new Tuple<>(zero(), one());
            secondTuple = new Tuple<>(one(), zero());
            firstValue = right;
            secondValue = left;
        }else{
            secondTuple = new Tuple<>(zero(), one());
            firstTuple = new Tuple<>(one(), zero());
            secondValue = right;
            firstValue = left;
        }
        while(true){
            DivisionAlgorithmResult<E> divisionAlgorithmResult = divisionAlgorithm(firstValue, secondValue);
            remainderValue = divisionAlgorithmResult.getRemainder();
            if(remainderValue.equals(zero())){
                return new ExtendedEuclidResult<>(firstValue, firstTuple.first, firstTuple.last);
            }
            E remainderFirst = add(secondTuple.first,  negate(multiply(firstTuple.first,divisionAlgorithmResult.getQuotient())));
            E remainderSecond = add(secondTuple.last,  negate(multiply(firstTuple.last,divisionAlgorithmResult.getQuotient())));
            remainderTuple = new Tuple<>(remainderFirst, remainderSecond);

            secondValue = firstValue;
            firstValue = remainderValue;
            secondTuple = firstTuple;
            firstTuple = remainderTuple;
        }
    }
}
