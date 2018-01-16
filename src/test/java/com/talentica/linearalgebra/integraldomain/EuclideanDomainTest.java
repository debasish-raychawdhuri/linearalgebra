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


public class EuclideanDomainTest {
    @Test
    public void testDivisionAlgorith(){
        EuclideanDomain<Long> dom = new Longs();
        DivisionAlgorithmResult<Long> result = dom.divisionAlgorithm(35l,9l);
        assertEquals(Long.valueOf(8l), result.getRemainder());
    }
    @Test
    public void testExtendedEulidAlgorithm(){
        EuclideanDomain<Long> dom = new Longs();
        Long left = 10l, right = 3l;
        ExtendedEuclidResult<Long> result = dom.extendedEulidAlgorithm(left,right);
        assertEquals(Long.valueOf(1l), result.getGcd());
        assertEquals(result.getGcd(),Long.valueOf(result.getLeftMultiplier()*left+result.getRightMultiplier()*right));
    }
    @Test
    public void testExtendedEulidAlgorithm2(){
        EuclideanDomain<Long> dom = new Longs();
        Long left = 10l, right = 35l;
        ExtendedEuclidResult<Long> result = dom.extendedEulidAlgorithm(left,right);
        assertEquals(Long.valueOf(5l), result.getGcd());
        assertEquals(result.getGcd(),Long.valueOf(result.getLeftMultiplier()*left+result.getRightMultiplier()*right));
    }

    @Test
    public void testExtendedEulidAlgorithm3(){
        EuclideanDomain<Polynomial<RationalNumber>> dom = new PolynomialRing(new RationalField());
        Polynomial<RationalNumber> left = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("4"), new BigInteger("9")),
                new RationalNumber(new BigInteger("0"), new BigInteger("2")),
                new RationalNumber(new BigInteger("-284"), new BigInteger("525")),
                new RationalNumber(new BigInteger("-4"), new BigInteger("21")),
                new RationalNumber(new BigInteger("-8"), new BigInteger("245")),
                new RationalNumber(new BigInteger("4"), new BigInteger("49")),
        });

        Polynomial<RationalNumber> right = new Polynomial<>(new RationalField(), new RationalNumber[]{
                new RationalNumber(new BigInteger("2"), new BigInteger("9")),
                new RationalNumber(new BigInteger("0"), new BigInteger("2")),
                new RationalNumber(new BigInteger("1"), new BigInteger("5")),
                new RationalNumber(new BigInteger("3"), new BigInteger("2")),
                new RationalNumber(new BigInteger("-8"), new BigInteger("5")),
                new RationalNumber(new BigInteger("4"), new BigInteger("3")),
        });
        ExtendedEuclidResult< Polynomial<RationalNumber>> result = dom.extendedEulidAlgorithm(left,right);
        //assertEquals(Long.valueOf(5l), result.getGcd());
        assertEquals(result.getGcd(),
                result.getLeftMultiplier().multiply(left).add(result.getRightMultiplier().multiply(right)));
    }
}
