package com.example.msbomrel.studentinformationsystem.Model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModel {

    String  code, message;
    List<Student> result;

    public List<Student> getResult() {
        return result;
    }

    public void setResult(List<Student> result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String pesan) {
        this.message = message;
    }
}
