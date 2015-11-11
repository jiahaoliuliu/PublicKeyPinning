package com.jiahaoliuliu.publickeypinning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jiahao on 11/11/15.
 */
public class Request {

    @SerializedName("id")
    private int id = 42;

    @SerializedName("jsonrpc")
    private String jsonRpcVersion = "2.0";

    @SerializedName("method")
    private String requestMethod = "generateIntegers";

    @SerializedName("params")
    private Params params;

    public Request() {
    }

    public Request(Params params) {
        this.params = params;
    }

    public Request(int id, String jsonRpcVersion, String requestMethod, Params params) {
        this.id = id;
        this.jsonRpcVersion = jsonRpcVersion;
        this.requestMethod = requestMethod;
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonRpcVersion() {
        return jsonRpcVersion;
    }

    public void setJsonRpcVersion(String jsonRpcVersion) {
        this.jsonRpcVersion = jsonRpcVersion;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (id != request.id) return false;
        if (jsonRpcVersion != null ? !jsonRpcVersion.equals(request.jsonRpcVersion) : request.jsonRpcVersion != null)
            return false;
        if (requestMethod != null ? !requestMethod.equals(request.requestMethod) : request.requestMethod != null)
            return false;
        return !(params != null ? !params.equals(request.params) : request.params != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jsonRpcVersion != null ? jsonRpcVersion.hashCode() : 0);
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", jsonRpcVersion='" + jsonRpcVersion + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", params=" + params +
                '}';
    }
}
