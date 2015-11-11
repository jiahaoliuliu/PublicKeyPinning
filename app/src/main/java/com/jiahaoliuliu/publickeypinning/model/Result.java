package com.jiahaoliuliu.publickeypinning.model;

/**
 * Created by Jiahao on 11/11/15.
 */
public class Result {

    private int bitsUsed;

    private int bitsLeft;

    private int requestsLeft;

    private int advisoryDelay;

    private Random random;

    public Result() {
    }

    public Result(int bitsUsed, int bitsLeft, int requestsLeft, int advisoryDelay, Random random) {
        this.bitsUsed = bitsUsed;
        this.bitsLeft = bitsLeft;
        this.requestsLeft = requestsLeft;
        this.advisoryDelay = advisoryDelay;
        this.random = random;
    }

    public int getBitsUsed() {
        return bitsUsed;
    }

    public void setBitsUsed(int bitsUsed) {
        this.bitsUsed = bitsUsed;
    }

    public int getBitsLeft() {
        return bitsLeft;
    }

    public void setBitsLeft(int bitsLeft) {
        this.bitsLeft = bitsLeft;
    }

    public int getRequestsLeft() {
        return requestsLeft;
    }

    public void setRequestsLeft(int requestsLeft) {
        this.requestsLeft = requestsLeft;
    }

    public int getAdvisoryDelay() {
        return advisoryDelay;
    }

    public void setAdvisoryDelay(int advisoryDelay) {
        this.advisoryDelay = advisoryDelay;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (bitsUsed != result.bitsUsed) return false;
        if (bitsLeft != result.bitsLeft) return false;
        if (requestsLeft != result.requestsLeft) return false;
        if (advisoryDelay != result.advisoryDelay) return false;
        return !(random != null ? !random.equals(result.random) : result.random != null);

    }

    @Override
    public int hashCode() {
        int result = bitsUsed;
        result = 31 * result + bitsLeft;
        result = 31 * result + requestsLeft;
        result = 31 * result + advisoryDelay;
        result = 31 * result + (random != null ? random.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "bitsUsed=" + bitsUsed +
                ", bitsLeft=" + bitsLeft +
                ", requestsLeft=" + requestsLeft +
                ", advisoryDelay=" + advisoryDelay +
                ", random=" + random +
                '}';
    }
}
