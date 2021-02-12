package com.company.microservices.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Employee implements Serializable {

    private String company;
    private String resource;
    private LocalDateTime time;
    private int longitude;
    private int latitude;

    public Employee() {
    }

    public Employee(String company, String resource, LocalDateTime time, int longitude, int latitude) {
        this.company = company;
        this.resource = resource;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", company='" + company + '\'' +
                ", resource='" + resource + '\'' +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
