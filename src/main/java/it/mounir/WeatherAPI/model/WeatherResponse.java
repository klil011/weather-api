package it.mounir.WeatherAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {

    private String address;
    private String description;

    @JsonProperty("days")
    private List<Day> days;

    public static class Day {
        @JsonProperty("tempmax")
        private double tempMax;

        @JsonProperty("tempmin")
        private double tempMin;

        @JsonProperty("temp")
        private double temp;

        // Getters and setters for the Day class
        public double getTempMax() {
            return tempMax;
        }

        public void setTempMax(double tempMax) {
            this.tempMax = tempMax;
        }

        public double getTempMin() {
            return tempMin;
        }

        public void setTempMin(double tempMin) {
            this.tempMin = tempMin;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    // Getters and setters for the WeatherResponse class
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}