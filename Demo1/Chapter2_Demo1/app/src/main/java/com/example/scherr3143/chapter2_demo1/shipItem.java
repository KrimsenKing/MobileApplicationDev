package com.example.scherr3143.chapter2_demo1;

/**
 * Created by scherr3143 on 11/10/2016.
 */
public class shipItem {

    static final double BASE = 3.00;
    static final double ADDED = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_ONCES = 4.0;

    private Integer mWeight;
    private Double mBaseCost;
    private Double mAddedCost;
    private Double mTotalCost;

    public shipItem(){
        mWeight = 0;
        mAddedCost = 0.0;
        mBaseCost = BASE;
        mTotalCost = 0.0;
    }

    public void setWeight(int weight){
        mWeight = weight;
        computeCosts();
    }

    private void computeCosts(){

        mAddedCost = 0.0;
        mBaseCost = BASE;

        if (mWeight <= 0){
            mBaseCost = 0.0;
        }
        else if (mWeight > BASE_WEIGHT){
            mAddedCost = Math.ceil((double)(mWeight - BASE_WEIGHT) / EXTRA_ONCES) * ADDED;
        }
        mTotalCost = mBaseCost + mAddedCost;
    }

    public Double getBaseCost(){
        return mBaseCost;
    }

    public Double getAddedCost(){
        return mAddedCost;
    }

    public Double getTotalCost(){
        return mTotalCost;
    }
}
