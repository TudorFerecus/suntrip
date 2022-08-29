package com.smartherd.suntrip;

import java.util.List;

public class DirectionsResponse {
    private List<Direction> routes;
    private String status;

    public List<Direction> getRoutes(){return routes;}

    public void setRoutes(List<Direction> routes_) {routes = routes_;}

    public String getStatus(){return status;}

    public void setStatus(String status_){status = status_;}

}
