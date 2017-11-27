package com.talentica.linearalgebra.matrix;

import com.talentica.linearalgebra.field.FloatField;
import org.apfloat.Apfloat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FloatMatrixTest {
    @Test
    public void testAdd2x2(){
        int precision = 10;
        FloatField field = new FloatField(precision);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(3,precision), new Apfloat(0, precision), new Apfloat(0, precision)},
                {new Apfloat(2, precision), new Apfloat(2, precision), new Apfloat(0, precision)},
                {new Apfloat(3, precision), new Apfloat(1, precision), new Apfloat(4, precision)}
        };

        FloatMatrix m = new FloatMatrix(lhsValues, field);
        FloatMatrix testM = m.multiply(m.transpose());
        System.out.println(testM);

        FloatMatrix c = testM.doCholesky();
        System.out.println(c);
        System.out.println(c.multiply(c.transpose()));
        assertEquals(c,m);
    }

}
