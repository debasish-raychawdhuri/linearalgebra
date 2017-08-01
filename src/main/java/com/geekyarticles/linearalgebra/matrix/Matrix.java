package com.geekyarticles.linearalgebra.matrix;

import com.geekyarticles.linearalgebra.field.Field;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by debasishc on 1/8/17.
 */
public class Matrix<E, F extends Field<E>> {
    int rows;
    int cols;
    F field;
    E [][] values;
    public Matrix(int rows, int cols, F field) {
        this.rows = rows;
        this.cols = cols;
        values = (E[][]) new Object[rows][cols];
        this. field = field;
    }
    public Matrix(E[][] values, F field){
        this.values = values;
        rows = values.length;
        cols = values[0].length;

        this. field = field;
    }

    public Matrix<E,F> add(Matrix<E, F> rhs){
        E [][] result = (E[][]) new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.add(values[i][j],rhs.values[i][j]);
            }
        }
        return new Matrix<>(result, field);
    }
    public Matrix<E,F> multiply(Matrix<E, F> rhs){
        E [][] result = (E[][]) new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.zero();
            }
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<rhs.cols;j++){
                for(int k=0;k<cols;k++){
                    result[i][j] = field.add(result[i][j],field.multiply(values[i][k], rhs.values[k][j]));
                }

            }
        }
        return new Matrix<>(result, field);
    }

    public Matrix<E,F> invert(){
        E [][] result = (E[][]) new Object[rows][cols];
        E [][] appendage = (E[][]) new Object[rows][cols];
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix<?, ?> matrix = (Matrix<?, ?>) o;

        if (rows != matrix.rows) return false;
        if (cols != matrix.cols) return false;
        if (!field.equals(matrix.field)) return false;
        return Arrays.deepEquals(values, matrix.values);
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + cols;
        result = 31 * result + field.hashCode();
        result = 31 * result + Arrays.deepHashCode(values);
        return result;
    }
}
