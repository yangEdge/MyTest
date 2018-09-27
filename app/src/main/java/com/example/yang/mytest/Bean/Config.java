package com.example.yang.mytest.Bean;

public class Config {
    private int minCo2;
    private int maxCo2;
    private int minLight;
    private int maxLight;

    /**
     *
     * @param minCo2
     * @param maxCo2
     * @param minLight
     * @param maxLight
     * @param minSoilTemperature
     * @param maxSoilTemperature
     * @param minSoilHumidity
     * @param maxSoilHumidity
     * @param minAirTemperature
     * @param maxAirTemperature
     * @param minAirHumidity
     * @param maxAirHumidity
     */
    public Config(int minCo2, int maxCo2, int minLight, int maxLight, int minSoilTemperature, int maxSoilTemperature,
                  int minSoilHumidity, int maxSoilHumidity, int minAirTemperature, int maxAirTemperature,
                  int minAirHumidity, int maxAirHumidity) {
        this.minCo2 = minCo2;
        this.maxCo2 = maxCo2;
        this.minLight = minLight;
        this.maxLight = maxLight;
        this.minSoilTemperature = minSoilTemperature;
        this.maxSoilTemperature = maxSoilTemperature;
        this.minSoilHumidity = minSoilHumidity;
        this.maxSoilHumidity = maxSoilHumidity;
        this.minAirTemperature = minAirTemperature;
        this.maxAirTemperature = maxAirTemperature;
        this.minAirHumidity = minAirHumidity;
        this.maxAirHumidity = maxAirHumidity;
    }

    private int minSoilTemperature;
    private int maxSoilTemperature;
    private int minSoilHumidity;
    private int maxSoilHumidity;
    private int minAirTemperature;
    private int maxAirTemperature;
    private int minAirHumidity;
    private int maxAirHumidity;


    public int getMaxCo2() {
        return maxCo2;
    }

    public void setMaxCo2(int maxCo2) {
        this.maxCo2 = maxCo2;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }

    public int getMinCo2() {
        return minCo2;
    }

    public void setMinCo2(int minCo2) {
        this.minCo2 = minCo2;
    }

    public int getMinLight() {
        return minLight;
    }

    public void setMinLight(int minLight) {
        this.minLight = minLight;
    }

    public int getMaxSoilHumidity() {
        return maxSoilHumidity;
    }

    public void setMaxSoilHumidity(int maxSoilHumidity) {
        this.maxSoilHumidity = maxSoilHumidity;
    }

    public int getMinSoilHumidity() {
        return minSoilHumidity;
    }

    public void setMinSoilHumidity(int minSoilHumidity) {
        this.minSoilHumidity = minSoilHumidity;
    }

    public int getMinAirHumidity() {
        return minAirHumidity;
    }

    public void setMinAirHumidity(int minAirHumidity) {
        this.minAirHumidity = minAirHumidity;
    }

    public int getMinAirTemperature() {
        return minAirTemperature;
    }

    public void setMinAirTemperature(int minAirTemperature) {
        this.minAirTemperature = minAirTemperature;
    }

    public int getMaxAirHumidity() {
        return maxAirHumidity;
    }

    public void setMaxAirHumidity(int maxAirHumidity) {
        this.maxAirHumidity = maxAirHumidity;
    }

    public int getMaxAirTemperature() {
        return maxAirTemperature;
    }

    public void setMaxAirTemperature(int maxAirTemperature) {
        this.maxAirTemperature = maxAirTemperature;
    }

    public int getMaxSoilTemperature() {
        return maxSoilTemperature;
    }

    public void setMaxSoilTemperature(int maxSoilTemperature) {
        this.maxSoilTemperature = maxSoilTemperature;
    }

    public int getMinSoilTemperature() {
        return minSoilTemperature;
    }

    public void setMinSoilTemperature(int minSoilTemperature) {
        this.minSoilTemperature = minSoilTemperature;
    }
}
