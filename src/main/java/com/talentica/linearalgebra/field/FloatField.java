package com.talentica.linearalgebra.field;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.util.Objects;


/**
 * Created by debasishc on 1/8/17.
 */
public class FloatField implements Field<Apfloat> {

    int precision;
    Apfloat zero;
    Apfloat one;

    public FloatField(int precision) {
        this.precision = precision;
        zero = new Apfloat(0d, precision);
        one = new Apfloat(1d, precision);
    }

    @Override
    public Apfloat add(Apfloat lhs, Apfloat rhs) {
        return lhs.add(rhs);
    }

    @Override
    public Apfloat multiply(Apfloat lhs, Apfloat rhs) {
        return lhs.multiply(rhs);
    }

    @Override
    public Apfloat one() {
        return one;
    }

    @Override
    public Apfloat zero() {
        return zero;
    }

    @Override
    public Apfloat invert(Apfloat value) {
        return one.divide(value);
    }

    @Override
    public Apfloat negate(Apfloat value) {
        return zero.subtract(value);
    }

    @Override
    public Apfloat nthRoot(Apfloat value, int n) {
        return ApfloatMath.root(value, n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatField that = (FloatField) o;
        return precision == that.precision &&
                Objects.equals(zero, that.zero) &&
                Objects.equals(one, that.one);
    }

    @Override
    public int hashCode() {

        return Objects.hash(precision, zero, one);
    }
}
