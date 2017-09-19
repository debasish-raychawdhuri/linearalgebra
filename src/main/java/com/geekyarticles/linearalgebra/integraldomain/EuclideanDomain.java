package com.geekyarticles.linearalgebra.integraldomain;

import com.geekyarticles.linearalgebra.ring.Ring;

public interface EuclideanDomain<E> extends Ring<E> {
    double euclideanFunction(E argument);
    DivisionAlgorithmResult<E> divisionAlgorithm(E divisor);
}
