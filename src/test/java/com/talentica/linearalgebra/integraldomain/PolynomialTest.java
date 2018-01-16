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

package com.talentica.linearalgebra.integraldomain;

import com.talentica.linearalgebra.field.RationalField;
import com.talentica.linearalgebra.field.RationalNumber;
import org.junit.Test;

import java.math.BigInteger;
import static org.junit.Assert.*;

public class PolynomialTest {
    @Test
    public void testPloynomialAddition(){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("-5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> result = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("4"), new BigInteger("3")),
                new RationalNumber(new BigInteger("0"), new BigInteger("2")),
                new RationalNumber(new BigInteger("-4"), new BigInteger("7")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        assertEquals(result, p1.add(p2));
    }

    @Test
    public void testPloynomialMultiplication(){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("-5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> result = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("4"), new BigInteger("9")),
                new RationalNumber(new BigInteger("0"), new BigInteger("2")),
                new RationalNumber(new BigInteger("-284"), new BigInteger("525")),
                new RationalNumber(new BigInteger("-4"), new BigInteger("21")),
                new RationalNumber(new BigInteger("-8"), new BigInteger("245")),
                new RationalNumber(new BigInteger("4"), new BigInteger("49")),
        });
        assertEquals(result, p1.multiply(p2));
    }
    @Test
    public void testPloynomialDivision2(){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
                new RationalNumber(new BigInteger("2"), new BigInteger("5")),
                new RationalNumber(new BigInteger("-2"), new BigInteger("7")),
        });
        Polynomial<RationalNumber> result =  new Polynomial<>(new RationalField(), new RationalNumber[]{});
        assertEquals(result, p1.divisionAlgorithm(p2).getRemainder());
    }
    @Test
    public void testPloynomialDivision3(){
        Polynomial<RationalNumber> p1 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
        });
        Polynomial<RationalNumber> p2 = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("3")),
        });
        Polynomial<RationalNumber> result =  new Polynomial<>(new RationalField(), new RationalNumber[]{});
        assertEquals(result, p1.divisionAlgorithm(p2).getRemainder());
    }
}
