package com.jiahaoliuliu.publickeypinning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jiahao on 11/11/15.
 */
public class Response {

    @SerializedName("id")
    private int id;

    @SerializedName("jsonrpc")
    private String jsonRpc;

    @SerializedName("result")
    private Result result;

    public Response() {
    }

    public Response(int id, String jsonRpc, Result result) {
        this.id = id;
        this.jsonRpc = jsonRpc;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonRpc() {
        return jsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        this.jsonRpc = jsonRpc;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (id != response.id) return false;
        if (jsonRpc != null ? !jsonRpc.equals(response.jsonRpc) : response.jsonRpc != null)
            return false;
        return !(result != null ? !result.equals(response.result) : response.result != null);

    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (jsonRpc != null ? jsonRpc.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", jsonRpc='" + jsonRpc + '\'' +
                ", result=" + result +
                '}';
    }
}
