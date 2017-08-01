package com.geekyarticles.linearalgebra.field;

/**
 * Created by debasishc on 1/8/17.
 */
public interface Field<E> {
    E add(E lhs, E rhs);
    E multiply(E lhs, E rhs);
    E one();
    E zero();
    E invert(E value);
    E negate(E value);
}
