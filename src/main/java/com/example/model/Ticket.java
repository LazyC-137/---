package com.example.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String TicketId;
    private String id;
    private String lineId;
    private String PlaneId;
    private java.util.Date BeginTime;
    private java.util.Date ArriveTime;
    private String price;
    private String beginPlace;
    private String endPlace;
    //private String type;

    public String getTicketId() {
        return TicketId;
    }

    public void setTicketId(String ticketId) {
        TicketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getPlaneId() {
        return PlaneId;
    }

    public void setPlaneId(String planeId) {
        PlaneId = planeId;
    }

    public java.util.Date getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(java.util.Date beginTime) {
        BeginTime = beginTime;
    }

    public java.util.Date getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(java.util.Date arriveTime) {
        ArriveTime = arriveTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBeginPlace() {
        return beginPlace;
    }

    public void setBeginPlace(String beginPlace) {
        this.beginPlace = beginPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
}
