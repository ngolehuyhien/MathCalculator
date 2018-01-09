package com.example.admin.mathcalculator;

public class CalculatorBrain {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
    private double mCalculatorMemory;

    // operator types
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";

    public static final String CLEAR = "C";
    public static final String CLEARLAST = "CE";
    public static final String CLEARMEMORY = "MC";
    public static final String ADDTOMEMORY = "M+";
    public static final String SUBTRACTFROMMEMORY = "M-";
    public static final String RECALLMEMORY = "MR";
    public static final String STOREMEMORY = "MS";
    public static final String SQUAREROOT = "√";
    public static final String INVERT = "1/x";
    public static final String TOGGLESIGN = "±";
    public static final String PERCENT ="%";

    // constructor
    public CalculatorBrain() {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        mCalculatorMemory = 0;
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    // used on screen orientation change
    public void setMemory(double calculatorMemory) {
        mCalculatorMemory = calculatorMemory;
    }

    // used on screen orientation change
    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

    protected double performOperation(String operator) {

        switch (operator) {
            case CLEAR:
                mOperand = 0;
                mWaitingOperator = "";
                mWaitingOperand = 0;
                break;
            case CLEARMEMORY:
                mCalculatorMemory = 0;
                break;
            case ADDTOMEMORY:
                mCalculatorMemory = mCalculatorMemory + mOperand;
                break;
            case SUBTRACTFROMMEMORY:
                mCalculatorMemory = mCalculatorMemory - mOperand;
                break;
            case RECALLMEMORY:
                mOperand = mCalculatorMemory;
                break;
            case SQUAREROOT:
                mOperand = Math.sqrt(mOperand);
                break;
            case PERCENT:
                mOperand = mOperand / 100;
                break;
            case CLEARLAST:
                mOperand = 0;
                break;
            case STOREMEMORY:
                mCalculatorMemory = mOperand;
                break;
            case INVERT:
                if (mOperand != 0) {
                    mOperand = 1 / mOperand;
                }
                break;
            case TOGGLESIGN:
                mOperand = -mOperand;
                break;
            default:
                performWaitingOperation();
                mWaitingOperator = operator;
                mWaitingOperand = mOperand;
                break;
        }
        return mOperand;
    }

    protected void performWaitingOperation() {

        switch (mWaitingOperator) {
            case ADD:
                mOperand = mWaitingOperand + mOperand;
                break;
            case SUBTRACT:
                mOperand = mWaitingOperand - mOperand;
                break;
            case MULTIPLY:
                mOperand = mWaitingOperand * mOperand;
                break;
            case DIVIDE:
                if (mOperand != 0) {
                    mOperand = mWaitingOperand / mOperand;
                }
                break;
        }

    }

}