package com.api.search.norman.models;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class Status {
    private int error_code;
    private String message;

    private Status(int error_code, String message) {
        this.error_code = error_code;
        this.message = message;
    }

    public Status(){

    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
