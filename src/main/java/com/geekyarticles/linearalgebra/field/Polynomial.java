package com.geekyarticles.linearalgebra.field;

public class Polynomial<E> {
    private Field<E> baseField;
    private E[] coefficients;

    public Polynomial(Field<E> baseField, E[] coefficients) {
        this.baseField = baseField;
        this.coefficients = coefficients;
    }

    public Polynomial<E> add(Polynomial<E> rhs){
        int degree = this.coefficients.length;
        if(rhs.coefficients.length>degree){
            degree = rhs.coefficients.length;
        }
        while(degree>=0){
            E firstVal = this.coefficients.length>=degree? this.coefficients[degree-1]:baseField.zero();
            E secondVal = rhs.coefficients.length>=degree? rhs.coefficients[degree-1]:baseField.zero();
            if(baseField.add(firstVal,secondVal).equals(baseField.zero())){
                degree--;
            }else{
                break;
            }
        }
        E[] coefficients = (E[]) new Object[degree];
        for(int i=0;i<degree;i++){
            E firstVal = this.coefficients.length>i? this.coefficients[i]:baseField.zero();
            E secondVal = rhs.coefficients.length>i? rhs.coefficients[i]:baseField.zero();
            coefficients[i] = baseField.add(firstVal, secondVal);
        }

        return new Polynomial<>(baseField, coefficients);
    }


}
