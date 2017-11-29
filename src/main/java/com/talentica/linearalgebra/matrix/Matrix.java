package com.talentica.linearalgebra.matrix;

import com.talentica.linearalgebra.field.Field;

import java.util.Arrays;

/**
 * Created by debasishc on 1/8/17.
 */
public class Matrix<E, F extends Field<E>> {
    int rows;
    int cols;
    F field;
    E [][] values;

    public E[][] getValues(){
        return values;
    }

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

    public Matrix<E,F> scale(E scaler){
        E [][] result = (E[][]) new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.multiply(values[i][j], scaler);
            }
        }
        return new Matrix<>(result, field);
    }



    protected void checkAdditionCompatibility(Matrix<E, F> rhs){
        if(this.rows!=rhs.rows || this.cols!=rhs.cols){
            throw new IllegalArgumentException("The matrices have incompatible dimensions for addition");
        }
    }

    public Matrix<E,F> add(Matrix<E, F> rhs){
        checkAdditionCompatibility(rhs);
        E [][] result = (E[][]) new Object[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.add(values[i][j],rhs.values[i][j]);
            }
        }
        return new Matrix<>(result, field);
    }

    protected void checkMultiplicationCompatibility(Matrix<E,F> lhs, Matrix<E,F> rhs){
        if(lhs.cols!=rhs.rows){
            throw new IllegalArgumentException("Matrices have incompatible dimensions for multiplication");
        }
    }

    public Matrix<E,F> multiply(Matrix<E, F> rhs){
        checkMultiplicationCompatibility(this, rhs);
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

    private void checkSquareMatrix(){
        if(this.rows != this.cols){
            throw new IllegalArgumentException("The matrix is not a square matrix");
        }
    }

    public Matrix<E,F> transpose(){
        E [][] result = (E[][]) new Object[cols][rows];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[j][i] = values[i][j];
            }
        }
        return new Matrix<>(result, field);
    }

    public int getRank(){
        E [][] matrix = (E[][]) new Object[rows][cols];

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j] = values[i][j];
            }
        }

        return getRank(0, matrix);
    }

    private int getRank(int diagpos, E[][] matrix){
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
            E pivotValue = matrix[i][i];
            multiplyRow(matrix, i, field.invert(pivotValue));
            for(int j=i+1;j<rows;j++){
                E mult = matrix[j][i];
                substractMultipleOf(matrix,i,j,mult);
            }
        }
        return rows-diagpos;
    }

    public Matrix<E,F> invert(){
        checkSquareMatrix();
        E [][] result = (E[][]) new Object[rows][cols];
        E [][] appendage = (E[][]) new Object[rows][cols];

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                appendage[i][j] = values[i][j];
                if(i==j){
                    result[i][j] = field.one();
                }else{
                    result[i][j] = field.zero();
                }
            }
        }

        for(int i=0;i<rows;i++){
            swapWithPivotRow(appendage, result, i);
            E pivotValue = appendage[i][i];
            multiplyRow(appendage, result, i, field.invert(pivotValue));
            for(int j=i+1;j<rows;j++){
                E mult = appendage[j][i];
                substractMultipleOf(appendage, result,i,j,mult);
            }
        }
        for(int i=rows-1;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                E mult = appendage[j][i];
                substractMultipleOf(appendage, result,i,j,mult);
            }
        }
        return new Matrix<>(result, field);
    }

    private void swapRows(E [][] appendage, E [][] result, int row1, int row2){
        swapRows(appendage, row1, row2);
        swapRows(result, row1, row2);
    }

    private void swapRows(E [][] matrix, int row1, int row2){
        E[] r1 = matrix[row1];
        E[] r2 = matrix[row2];
        matrix[row1] = r2;
        matrix[row2] = r1;
    }

    private void swapColumns(E [][] matrix, int col1, int col2){
        for(int i=0;i<rows;i++){
            E temp = matrix[i][col1];
            matrix[i][col1] = matrix[i][col2];
            matrix[i][col2] = temp;
        }
    }

    private int findPivotRow(E[][] matrix, int column){
        for(int i=column;i<rows;i++){
            if(!matrix[i][column].equals(field.zero())){
                return i;
            }
        }
        return -1;
    }

    private int findPivotColumn(E[][] matrix, int row){
        for(int i=row;i<cols;i++){
            if(!matrix[row][i].equals(field.zero())){
                return i;
            }
        }
        return -1;
    }

    private void swapWithPivotRow(E [][] appendage, E [][] result, int pivotColumn){
        int pivotRow = findPivotRow(appendage, pivotColumn);
        if(pivotRow!=pivotColumn){
            swapRows(appendage, result, pivotRow, pivotColumn);
        }
    }

    private void swapWithPivotRow(E [][] appendage, int pivotColumn){
        int pivotRow = findPivotRow(appendage, pivotColumn);
        if(pivotRow<0){
            throw new SingularMatrixException("The matrix is singular");
        }
        if(pivotRow!=pivotColumn){
            swapRows(appendage, pivotRow, pivotColumn);
        }
    }

    private void swapWithPivotColumn(E [][] appendage, int pivotRow){
        int pivotColumn = findPivotColumn(appendage, pivotRow);
        if(pivotColumn<0){
            throw new SingularMatrixException("The matrix is singular");
        }
        if(pivotRow!=pivotColumn){
            swapColumns(appendage, pivotColumn, pivotRow);
        }
    }

    private void substractMultipleOf(E [][] appendage, E [][] result, int source, int target, E multiplier){
        substractMultipleOf(appendage, source, target, multiplier);
        substractMultipleOf(result, source, target, multiplier);
    }

    private void substractMultipleOf(E [][] matrix, int source, int target, E multiplier){
        for(int j=0;j<cols;j++){
            matrix[target][j]
                    = field.add(matrix[target][j], field.negate(field.multiply(matrix[source][j], multiplier)));
        }
    }

    private void multiplyRow(E [][] appendage, E [][] result, int row,E multiplier){
        multiplyRow(appendage, row, multiplier);
        multiplyRow(result, row, multiplier);
    }

    private void multiplyRow(E [][] matrix, int row,E multiplier){
        for(int j=0;j<cols;j++){
            matrix[row][j]
                    = field.multiply(matrix[row][j], multiplier);
        }
    }

    private void doInplaceCholesky(E[][] values, int topLeftXY){
        E a11 = values[topLeftXY][topLeftXY];
        E l11= field.nthRoot(a11,2);
        values[topLeftXY][topLeftXY] = l11;
        if(topLeftXY==values.length-1){
            return;
        }


        //S = A22 - (1/a11) A21 A12
        //S = LsLs*
        for(int i=topLeftXY+1;i<values.length;i++){
            for(int j=topLeftXY+1;j<values.length;j++){
                E toSub = field.divide(field.multiply(values[i][topLeftXY],
                        (values[topLeftXY][j])),
                        a11);

                values[i][j] = field.substract(values[i][j],toSub);
            }
        }

        for(int i=topLeftXY+1;i<values.length;i++){
            values[i][topLeftXY] = field.divide(values[i][topLeftXY],l11);
            values[topLeftXY][i] = field.zero();
        }
        doInplaceCholesky(values, topLeftXY+1);
    }

    public Matrix doCholesky(){
        if(rows!=cols){
            throw new IllegalArgumentException("Matrix needs to be symmetric and positive definite for Cholesky decomposition");
        }
        E[][] vClone = (E[][]) new Object[values.length][values.length];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                vClone[i][j] = values[i][j];
            }
        }
        Matrix result = new Matrix(vClone,field);
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

    public String toString(){
        char upperLeft = '\u250C';
        char upperRight = '\u2510';
        char lowerLeft = '\u2514';
        char lowerRight = '\u2518';
        char leftBar = '\u2502';
        char bar = '\u2502';

        int [] colWidths = new int[cols];
        int totalWidth = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int widthOfValue = values[i][j].toString().length();
                if(widthOfValue>colWidths[j]){
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
