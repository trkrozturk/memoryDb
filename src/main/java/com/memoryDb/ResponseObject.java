package com.memoryDb;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.memoryDb.constants.ResponseTypes;

import java.util.Date;

public class ResponseObject {

    private int status;
    private String msg;
    private JsonArray data;
    private String sData;

    public ResponseObject() {
        // TODO Auto-generated constructor stub
    }

    public ResponseObject(String msg, JsonArray data, int status) {
        this.msg = msg;
        this.data = data;
        this.status = status;
    }

    public ResponseObject(String msg, String sData, int status) {
        this.msg = msg;
        this.sData = sData;
        this.status = status;
    }

    public ResponseObject(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }

    public void addData(JsonObject obj) {
        if (this.data == null) {
            this.data = new JsonArray();
        }
        this.data.add(obj);
    }

    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public JsonObject toJson() {
        JsonObject res = new JsonObject();
        res.addProperty("status", status != 0 ? status : ResponseTypes.BAD_REQUEST);
        res.addProperty("message", msg != null ? msg : "");
        res.add("data", data != null ? data : new JsonArray());
        res.addProperty("sdata", sData != null ? sData : "");
        res.addProperty("timestamp", new Date().getTime());
        return res;
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }


}
