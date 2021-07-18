package com.cheatcodesexample.fo.challenge1app;

public class lamps {
    private String Name;
    private String PinNames;
    private String Value;

    public lamps(String name, String pinNames, String value) {
        Name = name;
        PinNames = pinNames;
        Value = value;
    }

    public lamps(String pinNames, String value) {
        PinNames = pinNames;
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPinNames() {
        return PinNames;
    }

    public void setPinNames(String pinNames) {
        PinNames = pinNames;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
