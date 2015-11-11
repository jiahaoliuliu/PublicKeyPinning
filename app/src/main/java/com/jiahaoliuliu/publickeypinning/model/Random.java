package com.jiahaoliuliu.publickeypinning.model;

import java.util.List;

/**
 * Created by Jiahao on 11/11/15.
 */
public class Random {

    private String completionTime;
    private List<Integer> data;

    public Random() {
    }

    public Random(String completionTime, List<Integer> data) {
        this.completionTime = completionTime;
        this.data = data;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Random random = (Random) o;

        if (completionTime != null ? !completionTime.equals(random.completionTime) : random.completionTime != null)
            return false;
        return !(data != null ? !data.equals(random.data) : random.data != null);

    }

    @Override
    public int hashCode() {
        int result = completionTime != null ? completionTime.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Random{" +
                "completionTime='" + completionTime + '\'' +
                ", data=" + data +
                '}';
    }
}
