/*
 * This file is a part of LinearAlgebra project.
 *
 * Copyright  2018  Talentica Software Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.talentica.linearalgebra.matrix;

import com.talentica.linearalgebra.field.Field;
import com.talentica.linearalgebra.field.FloatField;
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
    public void testInverse2x2(){
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

    @Test
    public void testRank5x4(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(1), new Apfloat(2), new Apfloat(3), new Apfloat(4), new Apfloat(2)},
                {new Apfloat(2), new Apfloat(3), new Apfloat(4), new Apfloat(5), new Apfloat(4)},
                {new Apfloat(3), new Apfloat(4), new Apfloat(5), new Apfloat(6), new Apfloat(9)},
                {new Apfloat(4), new Apfloat(5), new Apfloat(6), new Apfloat(7), new Apfloat(4)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        int rank = A.getRank();

        assertEquals(3, rank);
    }

    @Test
    public void testRank5x4_2(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(1), new Apfloat(2), new Apfloat(3), new Apfloat(4), new Apfloat(2)},
                {new Apfloat(2), new Apfloat(3), new Apfloat(4), new Apfloat(5), new Apfloat(4)},
                {new Apfloat(3), new Apfloat(4), new Apfloat(5), new Apfloat(6), new Apfloat(9)},
                {new Apfloat(4), new Apfloat(1), new Apfloat(6), new Apfloat(7), new Apfloat(4)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        int rank = A.getRank();

        assertEquals(4, rank);
    }

    @Test
    public void testRank5x4_3(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(1), new Apfloat(2), new Apfloat(3), new Apfloat(4), new Apfloat(2)},
                {new Apfloat(2), new Apfloat(4), new Apfloat(4), new Apfloat(5), new Apfloat(4)},
                {new Apfloat(3), new Apfloat(6), new Apfloat(5), new Apfloat(6), new Apfloat(9)},
                {new Apfloat(4), new Apfloat(8), new Apfloat(6), new Apfloat(7), new Apfloat(4)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        int rank = A.getRank();

        assertEquals(3, rank);
    }
    @Test
    public void testRank5x4_4(){
        FloatField field = new FloatField(10);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(0), new Apfloat(0), new Apfloat(0), new Apfloat(0), new Apfloat(0)},
                {new Apfloat(0), new Apfloat(4), new Apfloat(4), new Apfloat(5), new Apfloat(4)},
                {new Apfloat(0), new Apfloat(6), new Apfloat(5), new Apfloat(6), new Apfloat(9)},
                {new Apfloat(0), new Apfloat(8), new Apfloat(6), new Apfloat(7), new Apfloat(4)}};
        Matrix<Apfloat, FloatField> A = new Matrix<>(lhsValues, field );
        int rank = A.getRank();

        assertEquals(3, rank);
    }

    @Test
    public void testDoCholesky(){
        int precision = 10;
        Field<Apfloat> field = new FloatField(precision);
        Apfloat[][] lhsValues = new Apfloat[][]{
                {new Apfloat(1,precision), new Apfloat(0, precision), new Apfloat(0, precision)},
                {new Apfloat(2, precision), new Apfloat(2, precision), new Apfloat(0, precision)},
                {new Apfloat(1, precision), new Apfloat(1, precision), new Apfloat(2, precision)}
        };

        Matrix<Apfloat, FloatField> m = new Matrix(lhsValues, field);
        Matrix testM = m.multiply(m.transpose());
        //System.out.println(testM);

        Matrix c = testM.doCholesky();
        //System.out.println(c);
        //System.out.println(c.multiply(c.transpose()));
        assertEquals(c,m);
    }
}
