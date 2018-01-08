package com.talentica.linearalgebra.example.fobonacci;

import com.talentica.linearalgebra.integraldomain.Integers;
import com.talentica.linearalgebra.matrix.Matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Fibonacci {
    public static void main(String [] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the Fibonacci series index you want to find: ");
        long index = Long.parseLong(in.readLine());
        Matrix<BigInteger, Integers> matrix = new Matrix<>(new BigInteger[][]{
                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        }, new Integers());
        Matrix<BigInteger, Integers> valueVector = new Matrix<>(new BigInteger[][]{
                {BigInteger.ZERO},
                {BigInteger.ONE}
        }, new Integers());

        int shift = 0;
        while((index & 0x8000_0000_0000_0000L) == 0){
            index<<=1;
            shift++;
        }

        Matrix<BigInteger, Integers> tempMatrix = new Matrix<>(new BigInteger[][]{
                {BigInteger.ONE, BigInteger.ZERO},
                {BigInteger.ZERO, BigInteger.ONE}
        }, new Integers()); ;
        for(int i=0;i<64-shift;i++){

            tempMatrix=tempMatrix.multiply(tempMatrix);
            if((index & 0x8000_0000_0000_0000L) != 0){
                tempMatrix = tempMatrix.multiply(matrix);
            }
            index<<=1;
        }

        Matrix<BigInteger, Integers> resultVector = tempMatrix.multiply(valueVector);

        System.out.println(resultVector.getValues()[0][0]);
    }
}
