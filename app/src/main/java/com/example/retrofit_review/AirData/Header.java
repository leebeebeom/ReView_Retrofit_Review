package com.example.retrofit_review.AirData;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public class Header {
    private String resultCode;
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Override
    public String toString() {
        return "ClassPojo [resultCode = " + resultCode + ", resultMsg = " + resultMsg + "]";
    }
}

