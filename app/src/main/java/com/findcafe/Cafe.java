package com.findcafe;

/**
 * Created by Tianying on 4/04/2016.
 */
public class Cafe {
    private String cafeId;
    private String cafeName;
    private String cafeAddress;
    private double cafeLatitude;
    private double cafeLongitude;
    private String cafePhone;
    private int cafeDistance;

    public Cafe(String cafeId, String cafeName, String cafeAddress, double cafeLatitude,
                double cafeLongitude, String cafePhone, int cafeDistance) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.cafeAddress = cafeAddress;
        this.cafeLatitude = cafeLatitude;
        this.cafeLongitude = cafeLongitude;
        this.cafePhone = cafePhone;
        this.cafeDistance = cafeDistance;
    }

    public String getCafeId() {
        return cafeId;
    }

    public String getCafeName() {
        return cafeName;
    }

    public String getCafeAddress() {
        return cafeAddress;
    }

    public double getCafeLatitude() {
        return cafeLatitude;
    }

    public double getCafeLongitude() {
        return cafeLongitude;
    }

    public String getCafePhone() {
        return cafePhone;
    }

    public int getCafeDistance() {
        return cafeDistance;
    }
}
