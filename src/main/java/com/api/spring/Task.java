package com.api.spring;

class Task {
    private int region;
    private String requestType;
    private int atmId;

    public Task(int region, String requestType, int atmId) {
        this.region = region;
        this.requestType = requestType;
        this.atmId = atmId;
    }

    public int getRegion() {
        return region;
    }

    public String getRequestType() {
        return requestType;
    }

    public int getAtmId() {
        return atmId;
    }
}