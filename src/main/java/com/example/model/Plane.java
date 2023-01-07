package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plane")
public class Plane {
    @Id
    @Column(name = "planeid")
    private String planeid;
    @Column(name = "lineid")
    private String lineid;
    @Column(name = "company")
    private String company;
    @Column(name = "seat")
    private Integer seat;
    @Column(name = "begintime")
    private java.util.Date begintime;
    @Column(name = "arrivetime")
    private java.util.Date arrivetime;
    @Column(name = "price")
    private String price;
}
