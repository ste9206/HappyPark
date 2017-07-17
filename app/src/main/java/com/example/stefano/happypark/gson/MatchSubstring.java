package com.example.stefano.happypark.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stefano on 13/05/17.
 */


public class MatchSubstring{

    @Expose
    @SerializedName("offset")
    int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Expose
    @SerializedName("length")
    int length;


}