package com.example.stefano.happypark.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stefano on 13/05/17.
 */

public class StructuredFormatting{

    @SerializedName("main_text" )
    String mainText;

    @SerializedName("main_text_matched_substrings")
    List<MatchSubstring> mainSubstrings;

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public List<MatchSubstring> getMainSubstrings() {
        return mainSubstrings;
    }

    public void setMainSubstrings(List<MatchSubstring> mainSubstrings) {
        this.mainSubstrings = mainSubstrings;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public List<MatchSubstring> getSecondSubstrings() {
        return secondSubstrings;
    }

    public void setSecondSubstrings(List<MatchSubstring> secondSubstrings) {
        this.secondSubstrings = secondSubstrings;
    }

    @SerializedName("secondary_text")
    String secondaryText;

    @SerializedName("secondary_text_matched_substrings")
    List<MatchSubstring> secondSubstrings;



}