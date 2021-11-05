package com.spotify.outh2.api;

public enum StatusCode {
    STATUS_CODE_200(200,""),
    STATUS_CODE_201(201,""),
    STATUS_CODE_400(400,"Missing required field: name"),
    STATUS_CODE_401(401,"Invalid access token");
    public final int code;
    public final String msg;
    private StatusCode(int code,String msg)
    {
        this.code=code;
        this.msg=msg;
    }
}
