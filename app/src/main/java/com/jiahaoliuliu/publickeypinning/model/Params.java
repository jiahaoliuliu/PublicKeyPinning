package com.jiahaoliuliu.publickeypinning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jiahao on 11/11/15.
 */
public class Params {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("n")
    private int numberResults;

    @SerializedName("min")
    private int minimumNumber;

    @SerializedName("max")
    private int maximumNumber;

    public Params() {}

    public Params(String apiKey, int numberResults, int minimumNumber, int maximumNumber) {
        this.apiKey = apiKey;
        this.numberResults = numberResults;
        this.minimumNumber = minimumNumber;
        this.maximumNumber = maximumNumber;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getNumberResults() {
        return numberResults;
    }

    public void setNumberResults(int numberResults) {
        this.numberResults = numberResults;
    }

    public int getMinimumNumber() {
        return minimumNumber;
    }

    public void setMinimumNumber(int minimumNumber) {
        this.minimumNumber = minimumNumber;
    }

    public int getMaximumNumber() {
        return maximumNumber;
    }

    public void setMaximumNumber(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Params params = (Params) o;

        if (numberResults != params.numberResults) return false;
        if (minimumNumber != params.minimumNumber) return false;
        if (maximumNumber != params.maximumNumber) return false;
        return !(apiKey != null ? !apiKey.equals(params.apiKey) : params.apiKey != null);

    }

    @Override
    public int hashCode() {
        int result = apiKey != null ? apiKey.hashCode() : 0;
        result = 31 * result + numberResults;
        result = 31 * result + minimumNumber;
        result = 31 * result + maximumNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Params{" +
                "apiKey='" + apiKey + '\'' +
                ", numberResults=" + numberResults +
                ", minimumNumber=" + minimumNumber +
                ", maximumNumber=" + maximumNumber +
                '}';
    }
}

