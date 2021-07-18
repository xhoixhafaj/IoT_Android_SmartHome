package com.cheatcodesexample.fo.challenge1app;

public class Sensors {

    private String Value1, Value2, typeSensor;
    private String PinNames;

    public Sensors(String typeSensor, String pinNames) {
        this.typeSensor = typeSensor;
        this.PinNames = pinNames;
    }

    public String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
    }

    public String getPinNames() {
        return PinNames;
    }

    public void setPinNames(String pinNames) {
        PinNames = pinNames;
    }
}
