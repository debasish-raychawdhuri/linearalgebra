package com.talentica.linearalgebra.field;

import com.talentica.linearalgebra.ring.Ring;

/**
 * Created by debasishc on 1/8/17.
 */
public interface Field<E> extends Ring<E>{
    E invert(E value);
    default E divide(E dividand, E divisor){
        return multiply(dividand, invert(divisor));
    }
    default E nthRoot(E value, int n){
        throw new UnsupportedOperationException(n+"th root is not supported");
    }
}
