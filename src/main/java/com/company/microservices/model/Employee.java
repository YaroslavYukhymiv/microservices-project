package com.company.microservices.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Employee implements Serializable {

    private String company;
    private String resource;
    private LocalDateTime time;
    private double longitude;
    private double latitude;

    public Employee() {
    }

    public Employee(String company, String resource, LocalDateTime time, double longitude, double latitude) {
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "company='" + company + '\'' +
                ", resource='" + resource + '\'' +
                ", time=" + time +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.longitude, longitude) == 0 && Double.compare(employee.latitude, latitude) == 0 && Objects.equals(company, employee.company) && Objects.equals(resource, employee.resource) && Objects.equals(time, employee.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, resource, time, longitude, latitude);
    }
}
