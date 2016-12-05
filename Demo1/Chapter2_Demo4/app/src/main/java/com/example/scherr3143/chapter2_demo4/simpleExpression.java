package com.example.scherr3143.chapter2_demo4;

/**
 * Created by scherr3143 on 12/1/2016.
 */
public class simpleExpression {

    private Integer mOperand1;
    private Integer mOperand2;
    private String mOperator;
    private Integer mValue;

    public simpleExpression(){
        mOperand1 = 0;
        mOperand2 = 0;
        mOperator = "+";
        mValue = 0;
    }
    public void setOperand1(int v){
        mOperand1 = v;
    }
    public int getOperand1(){
        return mOperand1;
    }
    public void setOperand2(int v){
        mOperand2 = v;
    }
    public int setOperand2(){
        return mOperand2;
    }
    public void setOperator(String s){
        mOperator = s;
    }
    public String getOperator(){
        return mOperator;
    }
    public int getValue(){
        computeValue();
        return mValue;
    }
    public void clearOperands(){
        setOperand1(0);
        setOperand2(0);
    }
    private void computeValue(){
        mValue = 0;
        if(mOperator.contentEquals("+")){
            mValue = mOperand1 + mOperand2;
        }
        else if(mOperator.contentEquals("-")){
            mValue = mOperand1 - mOperand2;
        }
        else if(mOperator.contentEquals("*")){
            mValue = mOperand1 * mOperand2;
        }
        else if(mOperator.contentEquals("/") && mOperand2 != 0){
            mValue = mOperand1 / mOperand2;
        }
        else {
            mValue = mOperand1 % mOperand2;
        }
    }
}
