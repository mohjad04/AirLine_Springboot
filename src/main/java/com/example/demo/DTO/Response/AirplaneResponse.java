package com.example.demo.DTO.Response;

public class AirplaneResponse {
    private int id;
    private String model;
    private String registrationNo;
    private Integer seatCapacity;

    public AirplaneResponse() {}

    public AirplaneResponse(int id, String model, String registrationNo, Integer seatCapacity) {
        this.id = id;
        this.model = model;
        this.registrationNo = registrationNo;
        this.seatCapacity = seatCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}