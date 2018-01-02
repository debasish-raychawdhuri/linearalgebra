package com.talentica.linearalgebra.matrix;

import com.talentica.linearalgebra.field.Field;
import com.talentica.linearalgebra.ring.Ring;

import java.util.Arrays;

/**
 * Created by debasishc on 1/8/17.
 */
public class Matrix<E, F extends Ring<E>> {
    int rows;
    int cols;
    F ring;
    Object [][] values;

    public Object[][] getValues(){
        return values;
    }

    public Matrix(int rows, int cols, F ring) {
        this.rows = rows;
        this.cols = cols;
        values = new Object[rows][cols];
        this.ring = ring;
    }
    public Matrix(Object[][] values, F ring){
        this.values = values;
        rows = values.length;
        cols = values[0].length;

        this.ring = ring;
    }

    public Matrix<E,F> scale(E scaler){
        Object [][] result =  new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = ring.multiply((E)values[i][j], scaler);
            }
        }
        return new Matrix<>(result, ring);
    }



    protected void checkAdditionCompatibility(Matrix<E, F> rhs){
        if(this.rows!=rhs.rows || this.cols!=rhs.cols){
            throw new IllegalArgumentException("The matrices have incompatible dimensions for addition");
        }
    }

    public Matrix<E,F> add(Matrix<E, F> rhs){
        checkAdditionCompatibility(rhs);
        Object [][] result = new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = ring.add((E)values[i][j],(E)rhs.values[i][j]);
            }
        }
        return new Matrix<>(result, ring);
    }

    protected void checkMultiplicationCompatibility(Matrix<E,F> lhs, Matrix<E,F> rhs){
        if(lhs.cols!=rhs.rows){
            throw new IllegalArgumentException("Matrices have incompatible dimensions for multiplication");
        }
    }

    public Matrix<E,F> multiply(Matrix<E, F> rhs){
        checkMultiplicationCompatibility(this, rhs);
        Object [][] result = new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = ring.zero();
            }
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<rhs.cols;j++){
                for(int k=0;k<cols;k++){
                    result[i][j] = ring.add((E)result[i][j],ring.multiply((E)values[i][k], (E)rhs.values[k][j]));
                }

            }
        }
        return new Matrix<>(result, ring);
    }

    private void checkSquareMatrix(){
        if(this.rows != this.cols){
            throw new IllegalArgumentException("The matrix is not a square matrix");
        }
    }

    public Matrix<E,F> transpose(){
        Object [][] result = new Object[cols][rows];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[j][i] = (E)values[i][j];
            }
        }
        return new Matrix<>(result, ring);
    }

    public int getRank(){
        Object [][] matrix = new Object[rows][cols];

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j] = (E)values[i][j];
            }
        }

        return getRank(0, matrix);
    }

    private int getRank(int diagpos, Object[][] matrix){
        if(!(ring instanceof Field)){
            throw new UnsupportedOperationException("Operation not supported for Matrix on a non-field");
        }
        Field<E> field = (Field<E>) ring;
        if(diagpos>=rows){
            return 0;
        }else if(diagpos>=cols){
            return 0;
        }
        for(int i=diagpos;i<rows;i++){
            try {
                swapWithPivotRow(matrix, i);
            }catch (SingularMatrixException ex){
                try{
                    swapWithPivotColumn(matrix,i);
                }catch (SingularMatrixException ex2) {
                    return i+getRank(i+1, matrix);
                }
            }
            E pivotValue = (E)matrix[i][i];
            multiplyRow(matrix, i, field.invert(pivotValue));
            for(int j=i+1;j<rows;j++){
                E mult = (E)matrix[j][i];
                substractMultipleOf(matrix,i,j,mult);
            }
        }
        return rows-diagpos;
    }

    public Matrix<E,F> invert(){
        if(!(ring instanceof Field)){
            throw new UnsupportedOperationException("Operation not supported for Matrix on a non-field");
        }
        Field<E> field = (Field<E>) ring;
        checkSquareMatrix();
        Object [][] result = new Object[rows][cols];
        Object [][] appendage = new Object[rows][cols];

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                appendage[i][j] = values[i][j];
                if(i==j){
                    result[i][j] = ring.one();
                }else{
                    result[i][j] = ring.zero();
                }
            }
        }

        for(int i=0;i<rows;i++){
            swapWithPivotRow(appendage, result, i);
            E pivotValue = (E)appendage[i][i];
            multiplyRow(appendage, result, i, field.invert(pivotValue));
            for(int j=i+1;j<rows;j++){
                E mult = (E)appendage[j][i];
                substractMultipleOf(appendage, result,i,j,mult);
            }
        }
        for(int i=rows-1;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                E mult = (E)appendage[j][i];
                substractMultipleOf(appendage, result,i,j,mult);
            }
        }
        return new Matrix<>(result, ring);
    }

    private void swapRows(Object [][] appendage, Object [][] result, int row1, int row2){
        swapRows(appendage, row1, row2);
        swapRows(result, row1, row2);
    }

    private void swapRows(Object [][] matrix, int row1, int row2){
        Object[] r1 = matrix[row1];
        Object[] r2 = matrix[row2];
        matrix[row1] = r2;
        matrix[row2] = r1;
    }

    private void swapColumns(Object [][] matrix, int col1, int col2){
        for(int i=0;i<rows;i++){
            E temp = (E)matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }

    private int findPivotRow(Object[][] matrix, int column){
        for(int i=column;i<rows;i++){
            if(!matrix[i][column].equals(ring.zero())){
                return i;
            }
        }
        return -1;
    }

    private int findPivotColumn(Object[][] matrix, int row){
        for(int i=row;i<cols;i++){
            if(!matrix[row][i].equals(ring.zero())){
                return i;
            }
        }
        throw new SingularMatrixException("Matrix is non-invertible");
    }

    private void swapWithPivotRow(Object [][] appendage, Object [][] result, int pivotColumn){
        int pivotRow = findPivotRow(appendage, pivotColumn);
        if(pivotRow!=pivotColumn){
            swapRows(appendage, result, pivotRow, pivotColumn);
        }
    }

    private void swapWithPivotRow(Object [][] appendage, int pivotColumn){
        int pivotRow = findPivotRow(appendage, pivotColumn);
        if(pivotRow<0){
            throw new SingularMatrixException("The matrix is singular");
        }
        if(pivotRow!=pivotColumn){
            swapRows(appendage, pivotRow, pivotColumn);
        }
    }

    private void swapWithPivotColumn(Object [][] appendage, int pivotRow){
        int pivotColumn = findPivotColumn(appendage, pivotRow);
        if(pivotColumn<0){
            throw new SingularMatrixException("The matrix is singular");
        }
        if(pivotRow!=pivotColumn){
            swapColumns(appendage, pivotColumn, pivotRow);
        }
    }

    private void substractMultipleOf(Object [][] appendage, Object [][] result, int source, int target, E multiplier){
        substractMultipleOf(appendage, source, target, multiplier);
        substractMultipleOf(result, source, target, multiplier);
    }

    private void substractMultipleOf(Object [][] matrix, int source, int target, E multiplier){
        for(int j=0;j<cols;j++){
            matrix[target][j]
                    = ring.add((E)matrix[target][j], ring.negate(ring.multiply((E)matrix[source][j], multiplier)));
        }
    }

    private void multiplyRow(Object [][] appendage, Object [][] result, int row,E multiplier){
        multiplyRow(appendage, row, multiplier);
        multiplyRow(result, row, multiplier);
    }

    private void multiplyRow(Object [][] matrix, int row,E multiplier){
        for(int j=0;j<cols;j++){
            matrix[row][j]
                    = ring.multiply((E)matrix[row][j], multiplier);
        }
    }

    private void doInplaceCholesky(Object[][] values, int topLeftXY){
        if(!(ring instanceof Field)){
            throw new UnsupportedOperationException("Operation not supported for Matrix on a non-field");
        }
        Field<E> field = (Field<E>) ring;
        E a11 = (E)values[topLeftXY][topLeftXY];
        E l11= field.nthRoot(a11,2);
        values[topLeftXY][topLeftXY] = l11;
        if(topLeftXY==values.length-1){
            return;
        }


        //S = A22 - (1/a11) A21 A12
        //S = LsLs*
        for(int i=topLeftXY+1;i<values.length;i++){
            for(int j=topLeftXY+1;j<values.length;j++){
                E toSub = field.divide(field.multiply((E)values[i][topLeftXY],
                        ((E)values[topLeftXY][j])),
                        a11);

                values[i][j] = field.substract((E)values[i][j],toSub);
            }
        }

        for(int i=topLeftXY+1;i<values.length;i++){
            values[i][topLeftXY] = field.divide((E)values[i][topLeftXY],l11);
            values[topLeftXY][i] = field.zero();
        }
        doInplaceCholesky(values, topLeftXY+1);
    }

    public Matrix doCholesky(){
        if(rows!=cols){
            throw new IllegalArgumentException("Matrix needs to be symmetric and positive definite for Cholesky decomposition");
        }
        Object[][] vClone = new Object[values.length][values.length];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                vClone[i][j] = values[i][j];
            }
        }
        Matrix result = new Matrix(vClone,ring);
        doInplaceCholesky(vClone,0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix<?, ?> matrix = (Matrix<?, ?>) o;

        if (rows != matrix.rows) return false;
        if (cols != matrix.cols) return false;
        if (!ring.equals(matrix.ring)) return false;
        return Arrays.deepEquals(values, matrix.values);
    }

    @Override
    public int hashCode() {
        int result = rows;
        result = 31 * result + cols;
        result = 31 * result + ring.hashCode();
        result = 31 * result + Arrays.deepHashCode(values);
        return result;
    }

    public String toString(){
        char upperLeft = '\u250C';
        char upperRight = '\u2510';
        char lowerLeft = '\u2514';
        char lowerRight = '\u2518';
        char leftBar = '\u2502';
        char bar = '\u2502';

        int [] colWidths = new int[cols];
        int totalWidth = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int widthOfValue = values[i][j].toString().length();
                if (widthOfValue > colWidths[j]) {
                    colWidths[j] = widthOfValue;
                }
            }
        }

        for(int i=0;i<colWidths.length;i++){
            totalWidth+=colWidths[i]+2;
        }
        totalWidth-=2;

        StringBuilder sb =new StringBuilder();
        sb.append(upperLeft);
        for(int i=0;i<totalWidth;i++){
            sb.append(' ');
        }
        sb.append(upperRight).append('\n');

        for(int i=0;i<rows;i++){
            sb.append(bar);
            for(int j=0;j<cols;j++){
                String val = values[i][j].toString();
                int widthOfValue = val.length();
                int space = colWidths[j] - widthOfValue;

                for(int k=0;k<space;k++){
                    sb.append(' ');
                }

                sb.append(val);
                if(j!=cols-1){
                    sb.append("  ");
                }
            }
            sb.append(bar).append('\n');
        }
        sb.append(lowerLeft);
        for(int i=0;i<totalWidth;i++){
            sb.append(' ');
        }
        sb.append(lowerRight);
        return sb.toString();

    }

}
