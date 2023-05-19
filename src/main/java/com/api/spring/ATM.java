package com.api.spring;

class ATM {
    private int region;
    private int atmId;

    public ATM(int region, int atmId) {
        this.region = region;
        this.atmId = atmId;
    }

    public int getRegion() {
        return region;
    }

    public int getAtmId() {
        return atmId;
    }
}