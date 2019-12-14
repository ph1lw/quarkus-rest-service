package com.appstruction.example.random;

import javax.json.bind.annotation.JsonbProperty;

public class RandomRange {
    @JsonbProperty("from")
    private int rangeFrom;

    @JsonbProperty("to")
    private int rangeTo;

    public RandomRange() {
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }
}
