package com.pit.crudapp.response;

public class ApiResponse {
    private int hospitalId;
    private String message;

    // constructor
    public ApiResponse(int hospitalId, String message) {
        this.hospitalId = hospitalId;
        this.message = message;
    }
    public int getHospitalId() {
        return hospitalId;
    }

    public String getMessage() {
        return message;
    }
}
