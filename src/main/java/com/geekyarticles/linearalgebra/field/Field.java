package com.geekyarticles.linearalgebra.field;

import com.geekyarticles.linearalgebra.ring.Ring;

/**
 * Created by debasishc on 1/8/17.
 */
public interface Field<E> extends Ring<E>{
    E invert(E value);
}
