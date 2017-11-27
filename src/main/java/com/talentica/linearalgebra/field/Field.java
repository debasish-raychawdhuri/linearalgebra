package com.talentica.linearalgebra.field;

import com.talentica.linearalgebra.ring.Ring;

/**
 * Created by debasishc on 1/8/17.
 */
public interface Field<E> extends Ring<E>{
    E invert(E value);
}
