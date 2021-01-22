package com.company.microservices.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    private Long id;
    private String company;
    private String resource;
    private Date time;
    private int longitude;
    private int latitude;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
                "id=" + id +
                ", company='" + company + '\'' +
                ", resource='" + resource + '\'' +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
