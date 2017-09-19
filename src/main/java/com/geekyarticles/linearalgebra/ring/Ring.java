package com.geekyarticles.linearalgebra.ring;

public interface Ring<E>  {
    E add(E lhs, E rhs);
    E multiply(E lhs, E rhs);
    E one();
    E zero();
    E negate(E value);
}
