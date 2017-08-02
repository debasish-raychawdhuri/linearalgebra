package com.geekyarticles.linearalgebra.matrix;

import com.geekyarticles.linearalgebra.field.FloatField;
import org.apfloat.Apfloat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by debasishc on 1/8/17.
 */
public class MatrixTest {
    @Test
    public void testAdd2x2(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{{new Apfloat(1), new Apfloat(2)},
                {new Apfloat(3), new Apfloat(4)}};
        Apfloat[][] rhsValues = new Apfloat[][]{{new Apfloat(2), new Apfloat(3)},
                {new Apfloat(4), new Apfloat(5)}};
        Apfloat[][] resValues = new Apfloat[][]{{new Apfloat(3), new Apfloat(5)},
                {new Apfloat(7), new Apfloat(9)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        Matrix<Apfloat, FloatField> B = new Matrix<>(rhsValues, field );
        Matrix<Apfloat, FloatField> result = new Matrix<>(resValues, field );
        Matrix<Apfloat, FloatField> C = A.add(B);

        assertEquals(result, C);
    }

    @Test
    public void testMult2x2(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{{new Apfloat(1), new Apfloat(2)},
                {new Apfloat(3), new Apfloat(4)}};
        Apfloat[][] rhsValues = new Apfloat[][]{{new Apfloat(2), new Apfloat(3)},
                {new Apfloat(4), new Apfloat(5)}};
        Apfloat[][] resValues = new Apfloat[][]{{new Apfloat(10), new Apfloat(13)},
                {new Apfloat(22), new Apfloat(29)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        Matrix<Apfloat, FloatField> B = new Matrix<>(rhsValues, field );
        Matrix<Apfloat, FloatField> result = new Matrix<>(resValues, field );
        Matrix<Apfloat, FloatField> C = A.multiply(B);

        assertEquals(result, C);
    }

    @Test
    public void testInvers2x2(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{{new Apfloat(1), new Apfloat(2)},
                {new Apfloat(3), new Apfloat(4)}};
        Apfloat[][] resValues = new Apfloat[][]{{new Apfloat(-2), new Apfloat(1)},
                {new Apfloat(1.5), new Apfloat(-0.5)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        Matrix<Apfloat, FloatField> result = new Matrix<>(resValues, field );
        Matrix<Apfloat, FloatField> C = A.invert();

        assertEquals(result, C);
    }
}
