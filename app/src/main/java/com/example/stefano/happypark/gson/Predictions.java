package com.example.stefano.happypark.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stefano on 13/05/17.
 */

public class Predictions{

    @Expose
    @SerializedName("description")
    String description;

    @Expose
    @SerializedName("id")
    String id;

    @Expose
    @SerializedName("matched_substrings")
    List<MatchSubstring> matchSubstrings;

    @Expose
    @SerializedName("place_id")
    String placeId;

    @Expose
    @SerializedName("reference")
    String reference;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MatchSubstring> getMatchSubstrings() {
        return matchSubstrings;
    }

    public void setMatchSubstrings(List<MatchSubstring> matchSubstrings) {
        this.matchSubstrings = matchSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

    public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
        this.structuredFormatting = structuredFormatting;
    }

    public List<MatchSubstring> getTerms() {
        return terms;
    }

    public void setTerms(List<MatchSubstring> terms) {
        this.terms = terms;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Expose
    @SerializedName("structured_formatting")
    StructuredFormatting structuredFormatting;

    @Expose
    @SerializedName("terms" )
    List<MatchSubstring> terms;

    @Expose
    @SerializedName("types")
    List<String> types;



}