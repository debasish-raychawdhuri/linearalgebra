package com.talentica.linearalgebra.matrix;

import com.talentica.linearalgebra.field.FloatField;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class FloatMatrix extends Matrix<Apfloat, FloatField>{
    public FloatMatrix(int rows, int cols, int precision) {
        super(rows, cols, new FloatField(precision));
    }

    public FloatMatrix(Apfloat[][] values, FloatField field) {
        super(values, field);
    }

    public FloatMatrix scale(Apfloat scaler){
        Apfloat [][] result = new Apfloat[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.multiply(values[i][j], scaler);
            }
        }
        return new FloatMatrix(result,field);
    }

    private void doInplaceCholesky(Apfloat[][] values, int topLeftXY){
        Apfloat a11 = values[topLeftXY][topLeftXY];
        Apfloat l11=ApfloatMath.sqrt(a11);
        values[topLeftXY][topLeftXY] = l11;
        if(topLeftXY==values.length-1){
            return;
        }


        //S = A22 - (1/a11) A21 A12
        //S = LsLs*
        for(int i=topLeftXY+1;i<values.length;i++){
            for(int j=topLeftXY+1;j<values.length;j++){
                Apfloat toSub = values[i][topLeftXY]
                        .multiply(values[topLeftXY][j])
                        .divide(a11);

                values[i][j] = values[i][j].subtract(toSub);
            }
        }

        for(int i=topLeftXY+1;i<values.length;i++){
            values[i][topLeftXY] = values[i][topLeftXY].divide(l11);
            values[topLeftXY][i] = field.zero();
        }
        doInplaceCholesky(values, topLeftXY+1);
    }

    public FloatMatrix doCholesky(){
        if(rows!=cols){
            throw new IllegalArgumentException("Matrix needs to be symmetric and positive definite for Cholesky decomposition");
        }
        Apfloat[][] vClone = new Apfloat[values.length][values.length];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                vClone[i][j] = values[i][j];
            }
        }
        FloatMatrix result = new FloatMatrix(vClone,field);
        doInplaceCholesky(vClone,0);
        return result;
    }

    public FloatMatrix add(FloatMatrix rhs) {
        checkAdditionCompatibility(rhs);
        Apfloat [][] result = new Apfloat[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[i][j] = field.add(values[i][j],rhs.values[i][j]);
            }
        }
        return new FloatMatrix(result, field);
    }


    public FloatMatrix multiply(FloatMatrix rhs) {
        checkMultiplicationCompatibility(this, rhs);
        Apfloat[][] result = new Apfloat[rows][cols];
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
        return new FloatMatrix(result, field);
    }

    @Override
    public  FloatMatrix transpose() {
        Apfloat [][] result = new Apfloat[cols][rows];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                result[j][i] = values[i][j];
            }
        }
        return new FloatMatrix(result, field);
    }
}
